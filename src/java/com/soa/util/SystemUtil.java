package com.soa.util;

import com.soa.bean.StServiceBean;
import com.soa.bean.StTableParamet;
import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.exception.GlobalException;
import com.soa.service.impl.UtilService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

public class SystemUtil {

    public static final Logger log = LogManager.getLogger(SystemUtil.class);
    //图片上传的文件系统路径，在监听器上赋值
    public static String filePath = "/file_upload";
    //图片上传的Web路径，在监听器上赋值
    public static String fileWebPath = "/file_upload/";
    //public static String version = "1.0.0";     //版本号
    public static String serverName = "zxhg";       //服务名
    public static int timeout = 10000;             //超时时间 (ms)
    public static int serialCache = 1000;         //流水号
    public static int taskTime = 22;            //批处理时间
    public static String timeTask = "";         //定时任务
    public static String charset = "utf-8";       //字符格式
    public static String loginRemark = "username";          //登录状态的校验标记
    public static String saveOperatorLog = "min";       //#记录操作日志，none、min 与 all三个级别
    public static String serverDesc = "新乡中新化工精细化管理信息平台";       //
    public static String yhzgTask = "";         //隐患整改定时任务
    public static long yhzgCycle = 3600000;     //隐患整改运行周期
    public static String reportUrl = "/DisplayChart?filename=";
    //系统中的所有自定义异常
    public static Map<Integer, String> exMap = null;
    public static UtilService utilServiceImpl;
    public static Map<String, List<StTableParamet>> tableMap;
    public static Map<String, StServiceBean> serviceMap;
    public static WebApplicationContext wac;
    public static Properties sqlProperties = new Properties();          //sql语句 key-value 键值对
    public static Properties pageServiceProperties = new Properties();      //pageService 的 key-value 键值对
    public static Set<String> msgCache = new HashSet<String>();

    /**
     * 使用默认路径上传文件
     * @param file
     * @param fileFileName
     * @throws IOException
     */
    public static void saveFile(File file, String fileFileName) throws
            IOException {
        saveFile(file, filePath, fileFileName);
    }

    /**
     * 保存文件
     * @param file
     * @param path
     * @param fileFileName
     * @throws IOException
     */
    public static void saveFile(File file, String path, String fileFileName)
            throws IOException {
        InputStream is = null;
        FileOutputStream os = null;
        try {
            if (StringUtil.isNull(fileFileName)) {
                throw new FileNotFoundException();
            }
            if (StringUtil.isNull(path)) {
                path = "";
            }
            os = new FileOutputStream(new File(path, fileFileName));
            is = new FileInputStream(file);
            int length = 0;
            byte[] buf = new byte[512];
            while ((length = is.read(buf)) > 0) {
                os.write(buf, 0, length);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * 保存文件
     * @param bs 文件字节数组
     * @param path 路径
     * @param fileFileName 文件名
     * @throws IOException
     */
    public static void saveFile(byte[] bs, String path, String fileFileName)
            throws IOException {
        FileOutputStream os = null;
        try {
            if (StringUtil.isNull(fileFileName)) {
                throw new FileNotFoundException();
            }
            if (StringUtil.isNull(path)) {
                path = "";
            }
            os = new FileOutputStream(new File(path, fileFileName));
            os.write(bs);
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * 保存文件
     * @param bs 文件字节数组
     * @param path 路径
     * @param fileFileName 文件名
     * @throws IOException
     */
    public static void saveFile(byte[] bs, String fileName) throws IOException {
        saveFile(bs, filePath, fileName);
    }

    /**
     * 返回真实路径
     * @param path URL地址
     * @return 磁盘路径
     */
    public static String getReadPath(String path) {
        return SystemUtil.filePath + File.separatorChar + path.substring(path.lastIndexOf("/"));
    }

    /**
     * 删除磁盘文件
     * @param path 文件路径，可以是磁盘路径，也可以是URL路径
     */
    public static void deleteFile(String path) {
        if (!StringUtil.isNull(path)) {
            if (path.indexOf("/") != -1) {
                path = getReadPath(path);
            }
            log.debug("删除文件： " + path);
            new File(path).delete();
        }
    }

    /**
     * 获取流水号
     * 前置的流水号只在校验出现异常，且报文的流水号为空时使用
     * @return 流水号
     */
    public static synchronized String getSerialNum() throws GlobalException {
        try {
            if (serialCache >= 9999) {
                serialCache = 1000;
            } else {
                serialCache++;
            }
            return DateUtil.detaledLshFormat(new Date()) + serialCache;
        } catch (Exception e) {
            log.error("流水号更新异常 : " + e);
            throw new GlobalException(999995);     //生成流水号异常
        }
    }

    /**
     * 获取异常对象
     * @param code 异常码
     * @return
     */
    public static String getExByCode(Integer code) {
        return exMap.get(code);
    }

    /**
     * 生成错误包
     * @param ge
     * @return
     */
    public static AbstractCommonData creatErrorCommonData(GlobalException ge) {
        AbstractCommonData acd = DataConvertFactory.getInstance();
        AbstractCommonData head = acd.getDataValue("head");
        head.putStringValue("response_code", ge.getErrorCode() + "");
        head.putStringValue("response_desc", ge.getErrorMsg());
        return acd;
    }

    /**
     * 生成错误包
     * @param ge
     * @return
     */
    public static void creatErrorCommonData(GlobalException ge,
                                            AbstractCommonData head) {
        head.putStringValue("response_code", ge.getErrorCode() + "");
        head.putStringValue("response_desc", ge.getErrorMsg());
    }

    /**
     * 获取服务接口
     * @param serviceCode
     * @return
     * @throws GlobalException
     */
    public static StServiceBean getServiceByCode(String serviceCode) throws
            GlobalException {
        StServiceBean service = serviceMap.get(serviceCode);
        if (service == null) {
            throw new GlobalException(999996, serviceCode);     //服务码不存在
        }
        return service;
    }

    /**
     * 获取sql语句
     * @param key
     * @return
     */
    public static String getSql(String key) {
        if (StringUtil.isNull(key)) {
            return null;
        }
        String sql = sqlProperties.getProperty(key);
        if (StringUtil.isNull(sql)) {
            log.warn("找不到sql语句：" + key);
            return null;
        }
        log.debug("\n使用sql语句 " + key + " : " + sql);
        return sql;
    }

    /**
     * 获取列表中某一个值对应的描述
     * @param col 列表名
     * @param value 值
     * @return 值的描述
     */
    public static String getColValueDesc(String col, String value) {
        if (StringUtil.isNull(col) || StringUtil.isNull(value)) {
            return null;
        }
        List<StTableParamet> list = tableMap.get(col);
        if (list.isEmpty()) {
            return null;
        }
        for (StTableParamet p : list) {
            if (p.getColValue().equals(value)) {
                return p.getValueDesc();
            }
        }
        return null;
    }

    /**
     * 操作的日志级别是否为none，
     */
    public static boolean isNone() {
        if (saveOperatorLog.equals("none") || saveOperatorLog.equals("min") || saveOperatorLog.equals("all")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 操作的日志级别是否大于min，
     * @return
     */
    public static boolean isMin() {
        if (saveOperatorLog.equals("min") || saveOperatorLog.equals("all")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 操作的日志级别是否为all
     * @return
     */
    public static boolean isAll() {
        if (saveOperatorLog.equals("all")) {
            return true;
        } else {
            return false;
        }
    }
}
