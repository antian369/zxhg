package com.soa.listener;

import com.lianzt.factory.AESFactory;
import com.soa.interceptor.ReqTimeInterceptor;
import com.soa.service.impl.UtilService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import com.soa.util.SystemUtil;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 加载所有启动项
 *
 * @author lianzt
 */
public class ReadGlobalArgsListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ReadGlobalArgsListener.class);
    private Timer timer = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 加载配置信息
        log.info("开始加载配置文件 ...");
        String path = "";
        FileInputStream fis = null;
        String sqlFile = "";
        try {
            AESFactory.setKey("lian.zuotai.1030".getBytes());       //设置加密密码
            path = sce.getServletContext().getRealPath("/WEB-INF")
                    + File.separator;
            log.info("配置文件 path : " + path + "system.properties");
            fis = new FileInputStream(path + "system.properties");
            Properties p = new Properties();
            p.load(fis);
            SystemUtil.serverName = p.getProperty("server_name");
            SystemUtil.timeout = Integer.parseInt(p.getProperty("timeout"));
            SystemUtil.taskTime = Integer.parseInt(p.getProperty("task_time"));
            SystemUtil.filePath = sce.getServletContext().getRealPath(p.getProperty("file_path"));
            SystemUtil.timeTask = p.getProperty("time_task");
            SystemUtil.charset = p.getProperty("charset");
            ReqTimeInterceptor.warnTime = Integer.parseInt(p.getProperty("request_warn_time"));
            //创建文件上传目录，如果目录已存在，什么也不做。
            new File(SystemUtil.filePath).mkdir();
            SystemUtil.fileWebPath = sce.getServletContext().getContextPath() + p.getProperty("file_path");
            sqlFile = p.getProperty("sql_file_list");
            SystemUtil.loginRemark = p.getProperty("login_remark");
            SystemUtil.saveOperatorLog = p.getProperty("save_operator_log");
            SystemUtil.serverDesc = p.getProperty("server_desc");
            SystemUtil.yhzgTask = p.getProperty("yhzg_task");
            SystemUtil.yhzgCycle = Long.parseLong(p.getProperty("yhzg_cycle"));
        } catch (FileNotFoundException e) {
            log.warn("/WEB-INF/system.properties文件不存在！ 使用默认配置.");
        } catch (Exception e) {
            log.warn("配置文件读取异常 ： " + e + "\n使用默认配置.");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
            if (log.isInfoEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append("\n配置文件加载完成:");
                sb.append("\n\t服务名 ：");
                sb.append(SystemUtil.serverName);
                sb.append("\n\t系统名称：");
                sb.append(SystemUtil.serverDesc);
                sb.append("\n\t超时时间（毫秒） ：");
                sb.append(SystemUtil.timeout);
                sb.append("\n\t批处理时间(整点的小时数) ：");
                sb.append(SystemUtil.taskTime);
                sb.append("\n\t上传文件的保存目录 ：");
                sb.append(SystemUtil.filePath);
                sb.append("\n\t上传文件的保存目录(WEB路径) ：");
                sb.append(SystemUtil.fileWebPath);
                sb.append("\n\t定时任务：");
                sb.append(SystemUtil.taskTime);
                sb.append("\n\t字符集：");
                sb.append(SystemUtil.charset);
                sb.append("\n\t请求耗时预警时间，单位 ms：");
                sb.append(ReqTimeInterceptor.warnTime);
                sb.append("\n\tsql属性文件名：");
                sb.append(sqlFile);
                sb.append("\n\t登录状态的校验标记：");
                sb.append(SystemUtil.loginRemark);
                sb.append("\n\t记录操作日志级别：");
                sb.append(SystemUtil.saveOperatorLog);
                log.info(sb.toString());
            }
        }
        // 加载所有异常信息
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        SystemUtil.wac = wac;
        log.info("开始加载异常代码和异常信息。。。");
        UtilService us = (UtilService) wac.getBean("utilServiceImpl");
        SystemUtil.utilServiceImpl = us;
        try {
            SystemUtil.exMap = us.readErrMsg();
        } catch (DataAccessException e) {
            log.error("加载异常代码和异常信息出现错误 ： ", e);
        }

        if (SystemUtil.exMap != null && !SystemUtil.exMap.isEmpty()) {
            log.info("读取数据库成功，共 " + SystemUtil.exMap.size() + " 条异常信息");
        } else {
            log.warn("读取数据异常，异常信息和异常代码加载失败。。。");
        }

        //加载 st_table_paramet
        log.info("开始加载下拉列表选项。。。");
        SystemUtil.tableMap = us.readTablePara();
        if (SystemUtil.tableMap == null || SystemUtil.tableMap.isEmpty()) {
            log.warn("读取下拉列表选项为空，需要检查数据表是否有数据！");
        }
        log.info("加载下拉列表选项完成，共加载 " + SystemUtil.tableMap.size() + " 项");

        //加载 service bean
        log.info("开始加载service bean。。。");
        SystemUtil.serviceMap = us.readServiceBean();
        if (SystemUtil.serviceMap == null || SystemUtil.serviceMap.isEmpty()) {
            log.warn("读取service bean为空，需要检查数据表是否有数据！");
        }
        log.info("加载service bean完成，共加载 " + SystemUtil.serviceMap.size() + " 项");

        //加载定时任务
        timer = new Timer();
        log.info("开始加载定时任务。。。");
        TimerTask task = null;
        try {
            task = (TimerTask) wac.getBean(SystemUtil.yhzgTask);
        } catch (Exception e) {
            log.warn("定时任务 " + SystemUtil.yhzgTask + " 加载异常：" + e);
            task = null;
        }
        if (task != null) {
            timer.schedule(task, 10000, SystemUtil.yhzgCycle);      //10秒后开始重复执行
        }
        log.info("定时任务加载完成。。。");

        log.info("开始加载sql语句。。");
        log.info("sql语句配置文件 path : " + path + "sql" + File.separator);
        String[] sqlFileList = sqlFile.split(",");
        //循环加载sql语句
        for (String f : sqlFileList) {
            try {
                fis = new FileInputStream(path + "sql" + File.separator + f + ".properties");
                SystemUtil.sqlProperties.load(fis);
            } catch (FileNotFoundException e) {
                log.error("/WEB-INF/" + f + ".properties文件不存在！");
            } catch (Exception e) {
                log.error("sql配置文件读取异常 ： " + e);
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ioe) {
                        log.error(f + ".properties error : " + ioe);
                    }
                }
            }
        }
        log.info("共加载SQL语句 " + SystemUtil.sqlProperties.size() + " 条。");

        log.info("开始加载page-service。。。");
        log.info("page-service 配置文件 path : " + path + "pageService.properties");
        try {
            fis = new FileInputStream(path + "pageService.properties");
            SystemUtil.pageServiceProperties.load(fis);
        } catch (FileNotFoundException e) {
            log.error("/WEB-INF/pageService.properties文件不存在！");
        } catch (Exception e) {
            log.error("sql配置文件读取异常 ： " + e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ioe) {
                    log.error("pageService.properties error : " + ioe);
                }
            }
        }
        log.info("共加载 " + SystemUtil.pageServiceProperties.size() + " 条。");

        //系统启动时执行一次流水表转存
//        log.info("系统启动时执行一次流水表转存...");
//        DumpMsgBookTask dmbt = (DumpMsgBookTask) wac.getBean("dumpMsgBookTask");
//        dmbt.run();
//        log.info("开始加载sql语句。。");
//        String path = sce.getServletContext().getRealPath("/WEB-INF") + File.separator + "sql.properties";
//        log.info("sql语句配置文件 path : " + path);
//        try {
//            fis = new FileInputStream(path);
//            SystemUtil.sqlProperties.load(fis);
//            log.info("共加载SQL语句 " + SystemUtil.sqlProperties.size() + " 条。");
//        } catch (FileNotFoundException e) {
//            log.error("/WEB-INF/sql.properties文件不存在！");
//        } catch (Exception e) {
//            log.error("sql配置文件读取异常 ： " + e);
//        }
        log.info("启动项加载完成。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (timer != null) {
            log.info("销毁定时器！");
            timer.cancel();
        }
    }
}
