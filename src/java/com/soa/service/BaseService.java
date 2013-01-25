package com.soa.service;

import com.soa.bean.StServiceBean;
import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.CommonDataElement;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.exception.GlobalException;
import com.soa.service.busi.impl.BusiService;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.util.SystemUtil;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public abstract class BaseService extends JdbcDaoSupport implements Serializable,
        BusiService {

    private static final long serialVersionUID = -1;
    private static final Logger log = Logger.getLogger(BaseService.class);
    protected static final String SUCCESS = "000000";
    private static final String[] NULL_KEY = new String[]{};
    private static final String SESSION_NAME = "ses";       //session 名
    /**
     * 一个默认的RowMapper实现，返回一个CommonData，结果集中的每一列都被当作String对象，CommonData中的key为结果集的列名称
     */
    @Resource
    protected RowMapper<AbstractCommonData> rowMapperByCol;
    /**
     * spring jdbc的一个简单实现类，返回一个CommonData，结果集中的每一列都被当作String对象，CommonData中的key为结果集的列名称
     */
    @Resource
    protected ResultSetExtractor<AbstractCommonData> resultSetExtractorByCol;
    /**
     * spring jdbc的一个简单实现类，返回一个CommonData，结果集中的每一列都被当作String对象，CommonData中的key为结果集的列名称，为空的列不被放入结果集
     */
    @Resource
    protected ResultSetExtractor<AbstractCommonData> resultSetExtractorByNotNullCol;

    @Resource(name = "jdbcTemplate")                //本地oracle
    public void setMyJdbcTemplate(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    /**
     * 获取http-session
     * @param req 请求报文
     * @return
     */
    public static HttpSession getHttpSession(AbstractCommonData req) {
        return (HttpSession) req.getObjectValue("session");
    }

    /**
     * 获取session（准确说应该是缓存）
     * @param req 请求报文
     * @return
     */
    public static AbstractCommonData getSession(AbstractCommonData req) {
        AbstractCommonData session = null;
        //为保证一个请求中所有服务获取到的缓存都是同一个，所以把缓存放入请求报文中。
        //在报文体中使用SESSION_NAME字段获取缓存
        session = req.getDataValue(SESSION_NAME);
        if (session != null) {
            return session;
        }
        if (log.isDebugEnabled()) {
            log.debug("从session中取缓存。");
        }
        HttpSession httpSession = (HttpSession) req.getObjectValue("session");
        session = (AbstractCommonData) httpSession.getAttribute(SESSION_NAME);
        if (session == null) {
            if (log.isDebugEnabled()) {
                log.debug("session中的缓存为空，创建一个新的Comondata。");
            }
            session = DataConvertFactory.getInstanceEmpty();
            httpSession.setAttribute(SESSION_NAME, session);
        }
        req.putDataValue(SESSION_NAME, session);
        if (log.isDebugEnabled()) {
            log.debug("session commondata : " + session);
        }
        return session;
    }

    /**
     * 刷新session
     * @param req 
     */
    public static void flushSession(AbstractCommonData req) {
        if (true) {      //只有在集群部署时才需要刷新session
            if (log.isDebugEnabled()) {
                log.debug("put session : " + req.getDataValue(SESSION_NAME));
            }
            HttpSession hs = (HttpSession) req.getObjectValue("session");
            hs.setAttribute(SESSION_NAME, req.getDataValue(SESSION_NAME));
            hs.setAttribute("_timesamp", new Date().getTime());
//            HttpSession hs = (HttpSession) req.getObjectValue("session");
//            AbstractCommonData oldSes = (AbstractCommonData) hs.getAttribute(CookiesInterceptor.COOKIE_NAME);
//            AbstractCommonData newSes = req.getDataValue(CookiesInterceptor.COOKIE_NAME);
//            if (log.isDebugEnabled()) {
//                log.debug("\nold-ses : " + oldSes + "\nnew-ses : " + newSes);
//            }
//            //如果新Ses不为空，而且与老ses不同时
//            //或者老ses不为空，而且与新ses不同时
//            //更新ses
//            if ((newSes != null && !newSes.equals(oldSes)) || (oldSes != null && !oldSes.equals(newSes))) {
//                hs.setAttribute(CookiesInterceptor.COOKIE_NAME, newSes);
//            }
        }
    }

    /**
     * 获取当前登录的用户
     * @param req 请求报文
     * @return 用户名
     */
    public static String getLoginUser(AbstractCommonData req) {
        //如果是从网站发出的请求，从session中获取
        return getSession(req).getStringValue(SystemUtil.loginRemark);
    }

    /**
     * 校验函数的一个默认实现，从该服务的keys函数中提取请求需要的字段，并判断该项不能为空
     * @param in
     * @param inHead
     */
    @Override
    public void check(AbstractCommonData in, AbstractCommonData inHead) {
        if (log.isDebugEnabled()) {
            log.debug("开始使用默认的校验方法校验 " + inHead.getStringValue("service_code") + " 服务的请求报文！");
        }
        check(in, keys());
    }

    /**
     * 使用给定的参数校验请求报文
     * @param in
     * @param keys
     */
    protected void check(AbstractCommonData in, String[] keys) {
        if (keys == null) {
            if (log.isInfoEnabled()) {
                log.info("服务 " + in.getDataValue("head").getStringValue("service_code") + " 的校验参数为null！！！");
            }
            keys = NULL_KEY;
        }
        if (log.isDebugEnabled()) {
            log.debug("服务 " + in.getDataValue("head").getStringValue("service_code") + " 的校验参数为 : " + StringUtil.connectArray(keys, ","));
        }
        if (keys.length % 2 != 0) {
            throw new GlobalException(999976);     //给定的验证参数格式不符合要求！
        }
        CommonDataElement cde = null;
        //使用KEYS获取请求报文中的参数，并判断是否为空
        for (int i = 0; i < keys.length; i += 2) {
            cde = in.get(keys[i]);
            if (cde == null) {
                throw new GlobalException(200026, keys[i + 1]);       //k不能为空
            }
            if (CommonDataElement.STRING.equals(cde.getType())) {
                //如果参数是一个字符串
                if (StringUtil.isNull((String) cde.getValue())) {
                    throw new GlobalException(200026, keys[i + 1]);       //k不能为空
                }
            } else {
                //如果不是字符串
                if (cde.getValue() == null) {
                    throw new GlobalException(200026, keys[i + 1]);       //k不能为空
                }
            }
        }
    }

    /**
     * 执行服务
     * @param in 请求报文
     * @param inHead 请求报文的报文头
     * @param out 响应报文
     * @param outHead 响应报文的报文头
     * @throws Exception
     */
    public static void runService(AbstractCommonData in,
            AbstractCommonData inHead,
            AbstractCommonData out,
            AbstractCommonData outHead) {
        String serviceCode = inHead.getStringValue("service_code");
        StServiceBean service = SystemUtil.getServiceByCode(serviceCode);
        if (log.isInfoEnabled()) {
            log.info("开始调用服务: " + service);
        }
        //使用报文头中的username判断登录状态
        if ("Y".equalsIgnoreCase(service.getIsLogin())) {
            if (StringUtil.isNull(getLoginUser(in))) {       //不为空，也不为0
                throw new GlobalException(200023);      //请先登录
            }
        }
        BusiService bs = service.getService();
        bs.check(in, inHead);       //数据校验
        bs.execute(in, inHead, out, outHead);   //业务执行
        outHead.putStringValue("response_code", "000000");
        outHead.putStringValue("response_desc", "执行成功!");
    }

    /**
     * 使用服务
     * @param serviceCode 服务码
     * @param in
     * @param inHead
     * @param out
     * @param outHead
     * @exception
     */
    public void runService(String serviceCode,
            AbstractCommonData in,
            AbstractCommonData inHead,
            AbstractCommonData out,
            AbstractCommonData outHead) {
        String temp = inHead.getStringValue("service_code");
        inHead.putStringValue("service_code", serviceCode);
        if (log.isDebugEnabled()) {
            log.debug("原服务：" + temp + "\n开始重用服务: " + serviceCode + "\n请求数据：" + in);
        }
        try {
            runService(in, inHead, out, outHead);
        } finally {
            inHead.putStringValue("service_code", temp);
        }
    }

    /**
     * 使用其它service的校验函数
     * @param in
     * @param inHead
     * @param serviceCode
     * @throws Exception
     */
    public void useCheckForService(AbstractCommonData in,
            AbstractCommonData inHead,
            String serviceCode) {
        SystemUtil.getServiceByCode(serviceCode).getService().check(in, inHead);
    }

    /**
     * 在控制层调用服务的方法
     * @param pageData 在request对象中保存的pageData
     * @param serviceCode 需要调用服务的serviceCode
     * @return 服务的outData做为返回值
     */
    public static AbstractCommonData runService(AbstractCommonData pageData,
            String serviceCode) {
        AbstractCommonData head = pageData.getDataValue("head");
        head.putStringValue("service_code", serviceCode);
        AbstractCommonData out = DataConvertFactory.getInstance();
        runService(pageData, head, out, out.getDataValue("head"));
        return out;
    }

    /**
     * 提取请求报文中的查询条件<br />提取后的格式为：service_code,SystemUtil.loginRemark,条件1,条件2,.....
     * @param in
     * @return
     */
    protected static String takeCondition(AbstractCommonData in) {
        AbstractCommonData head = in.getDataValue("head");
        StringBuilder sb = new StringBuilder();
        sb.append(head.getStringValue("service_code")).append(',');
        sb.append(head.getStringValue(SystemUtil.loginRemark)).append(',');
        for (Entry<String, CommonDataElement> e : in.entrySet()) {
            if (!"head".equals(e.getKey())) {
                if (!e.getValue().getType().equals(CommonDataElement.DATA)
                        && !e.getValue().getType().equals(CommonDataElement.ARRAY)
                        && !e.getValue().getType().equals(CommonDataElement.OBJECT)) {
                    if (e.getValue().getType().equals(CommonDataElement.DATE)) {
                        sb.append(DateUtil.detaledLshFormat((Date) e.getValue().getValue()));
                    } else {
                        sb.append(e.getValue().getValue());
                    }
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }

    /**
     * 该服务需要使用到的请求数据的key，用于对内存数据库进行缓存，把该服务用到的key做为缓存条件，因为执行一个服务时可以提供多于该服务需要的请求数据，所以给出该keys函数。
     * @return 服务执行需要的key，格式要求为，一位字段名，一位字段说明。<br />例如：username, passowrd。则返回： new String[]{"username", "用户名", "password", "密码"}
     */
    public abstract String[] keys();

    /**
     * 获取sql语句
     * @param key
     * @return
     */
    public static String getSql(String key) {
        if (StringUtil.isNull(key)) {
            throw new GlobalException(200026, " sqlName ");     //!#!不能为空
        }
        String sql = SystemUtil.sqlProperties.getProperty(key);
        if (StringUtil.isNull(sql)) {
            log.warn("找不到sql语句：" + key);
            throw new GlobalException(999974, key);     //查询不到SQL语句：!#!
        }
        if (log.isDebugEnabled()) {
            log.debug("\n使用sql语句 " + key + " : " + sql.replace("\n", "\\n"));
        }
        return sql;
    }

    /**
     * 使用sql语句查询目标数据库，并返回查询结果<br />该方法只返回结果集的第一行
     * @param sql sql语句
     * @param objects sql语句的参数
     * @return 结果集的第一行数据
     */
    protected AbstractCommonData queryData(String sqlName, Object... objects) {
        if (log.isInfoEnabled()) {
            log.info("开始执行查询语句： \nsql_name = " + sqlName + " \nsql = " + getSql(sqlName) + "\nargs = " + StringUtil.connectArray(objects, ","));
        }
        Date begin = new Date();
        AbstractCommonData acd = getJdbcTemplate().query(getSql(sqlName), resultSetExtractorByCol, objects);
        long sqlTime = new Date().getTime() - begin.getTime();
        //记录可分析的日志，由于LogUtil只提供了参数为CommonData的方法，而此处没有使用CommonData，如果创建一个CommonData会消耗不必要的资源，
        //所以按照可分析日志的格式，拼接了一个json字符串。
        log.info("分析日志\n['log-sql-time']\t{sql:'" + sqlName + "', args:'" + StringUtil.connectArray(objects, ",") + "', database:'local', _runTime:" + sqlTime + "}");
        return acd;
    }

    /**
     * 使用sql语句查询本地数据库，并返回查询结果
     * @param sqlName sql语句的id，所有sql语句都记录在sql.propertise文件中
     * @param objects sql语句的参数
     * @return 结果集的CommonData形式
     */
    protected List<AbstractCommonData> queryList(String sqlName,
            Object... objects) {
        return queryList(sqlName, rowMapperByCol, objects);
    }

    /**
     * 使用sql语句查询本地数据库，并返回查询结果
     * @param sqlName sql语句的id，所有sql语句都记录在sql.propertise文件中
     * @param rowMapper 自定义的rowMapper
     * @param objects sql语句的参数
     * @return  结果集的CommonData形式
     */
    protected List<AbstractCommonData> queryList(String sqlName,
            RowMapper<AbstractCommonData> rowMapper,
            Object... objects) {
        if (log.isInfoEnabled()) {
            log.info("开始执行查询语句： \nsql_name = " + sqlName + " \nsql = " + getSql(sqlName) + "\nargs = " + StringUtil.connectArray(objects, ","));
        }
        Date begin = new Date();
        List<AbstractCommonData> list = getJdbcTemplate().query(getSql(sqlName), rowMapper, objects);
        long sqlTime = new Date().getTime() - begin.getTime();
        //记录可分析的日志，由于LogUtil只提供了参数为CommonData的方法，而此处没有使用CommonData，如果创建一个CommonData会消耗不必要的资源，
        //所以按照可分析日志的格式，拼接了一个json字符串。
        log.info("分析日志\n['log-sql-time']\t{sql:'" + sqlName + "', args:'" + StringUtil.connectArray(objects, ",") + "', database:'local', _runTime:" + sqlTime + "}");
        return list;
    }

    /**
     * 本地数据库更新
     * @param sqlName sql语句
     * @param objectssql语句的参数
     * @return
     */
    protected int update(String sqlName, Object... objects) {
        if (log.isInfoEnabled()) {
            log.info("开始执行更新语句： \nsql_name = " + sqlName + " \nsql = " + getSql(sqlName) + "\nargs = " + StringUtil.connectArray(objects, ","));
        }
        Date begin = new Date();
        int line = getJdbcTemplate().update(getSql(sqlName), objects);
        long sqlTime = new Date().getTime() - begin.getTime();
        //记录可分析的日志，由于LogUtil只提供了参数为CommonData的方法，而此处没有使用CommonData，如果创建一个CommonData会消耗不必要的资源，
        //所以按照可分析日志的格式，拼接了一个json字符串。
        log.info("分析日志\n['log-sql-time']\t{sql:'" + sqlName + "', args:'" + StringUtil.connectArray(objects, ",") + "', database:'local',update_line:" + line + ",  _runTime:" + sqlTime + "}");
        return line;
    }

    /**
     * 批量执行SQL语句
     * @param sqlName
     * @param objArrs
     * @return
     */
    protected int[] batchUpdate(String sqlName, List<Object[]> objArrs) {
        if (objArrs == null || objArrs.isEmpty()) {
            return null;
        }
        Object[][] args = new Object[0][];
        args = objArrs.toArray(args);
        return batchUpdate(sqlName, args);
    }

    /**
     * 批量执行SQL语句
     * @param sqlName
     * @param objArrs
     * @return
     */
    protected int[] batchUpdate(String sqlName, final Object[][] objArrs) {
        Date begin = new Date();
        if (log.isInfoEnabled()) {
            StringBuilder sb = new StringBuilder();
            for (Object[] objs : objArrs) {
                sb.append("\n--\t").append(StringUtil.connectArray(objs, ","));
            }
            log.info("开始批量执行sql语句：\nsql_name = " + sqlName + "\n sql = " + getSql(sqlName) + "\nargs = " + sb);
        }
        int[] res = getJdbcTemplate().batchUpdate(getSql(sqlName), new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws
                    SQLException {
                if (objArrs == null || objArrs[i] == null) {
                    return;
                }
                for (int r = 0; r < objArrs[i].length; r++) {
                    if (objArrs[i][r] == null) {
                        ps.setString(r + 1, "");
                    } else if ("Integer".equals(objArrs[i][r].getClass().getSimpleName())) {
                        ps.setInt(r + 1, (Integer) objArrs[i][r]);
                    } else if ("String".equals(objArrs[i][r].getClass().getSimpleName())) {
                        ps.setString(r + 1, (String) objArrs[i][r]);
                    } else if ("Long".equals(objArrs[i][r].getClass().getSimpleName())) {
                        ps.setLong(r + 1, (Long) objArrs[i][r]);
                    } else if ("Double".equals(objArrs[i][r].getClass().getSimpleName())) {
                        ps.setDouble(r + 1, (Double) objArrs[i][r]);
                    } else if ("Float".equals(objArrs[i][r].getClass().getSimpleName())) {
                        ps.setFloat(r + 1, (Float) objArrs[i][r]);
                    } else if ("Date".equals(objArrs[i][r].getClass().getSimpleName())
                            || "DbDate".equals(objArrs[i][r].getClass().getSimpleName())) {
                        ps.setTimestamp(r + 1, new Timestamp(((Date) objArrs[i][r]).getTime()));
                    } else if ("Timestamp".equals(objArrs[i][r].getClass().getSimpleName())) {
                        ps.setTimestamp(r + 1, (Timestamp) objArrs[i][r]);
                    } else {
                        ps.setString(r + 1, (String) objArrs[i][r]);
                    }
                }
            }

            @Override
            public int getBatchSize() {
                return objArrs.length;
            }
        });
//        log.info("分析日志\n['log-sql-time']\t{sql:'" + sqlName + "', args:'" + StringUtil.connectArray(objects, ",") + "', database:'local',update_line:" + line + ",  _runTime:" + sqlTime + "}");
        return res;
    }

    /**
     * 调用存储过程
     * @param procedure 过程名
     * @param callable 执行过程
     * @return 执行结果
     */
    protected AbstractCommonData callProcedure(String procedure,
            CallableStatementCallback<AbstractCommonData> callable) {
        if (log.isInfoEnabled()) {
            log.info("开始执行存储过程： \nsql_name = " + procedure + " \nsql = " + getSql(procedure));
        }
        Date begin = new Date();
        AbstractCommonData res = getJdbcTemplate().execute(getSql(procedure), callable);
        long sqlTime = new Date().getTime() - begin.getTime();
        if (log.isDebugEnabled()) {
            log.debug("过程执行结果：" + res);
        }
        log.info("分析日志\n['log-sql-time']\t{sql:'" + procedure.replace("{", "<").replace("}", ">") + "', database:'local',  _runTime:" + sqlTime + "}");
        return res;
    }

    /**
     * 获取一个记录操作日志的sql语句参数，需要自己补全第2、3个参数
     * @param in 请求报文
     * @return 参数数组
     */
    protected Object[] getOperArgs(AbstractCommonData in) {
        Object[] args = new Object[6];
        args[0] = getLoginUser(in);
        args[1] = in.getDataValue("head").getStringValue("service_code");
        args[2] = null;
        args[3] = null;
        args[4] = in.getStringValue("m_id");
        args[5] = in.getStringValue("m_id").substring(0, 2);
        return args;
    }

    protected void close(ResultSet res) {
        if (res != null) {
            try {
                res.close();
            } catch (Exception e) {
            }
        }
    }

    protected void close(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
            }
        }
    }

    protected void close(Statement stat) {
        if (stat != null) {
            try {
                stat.close();
            } catch (Exception e) {
            }
        }
    }

    protected void close(CallableStatement cs) {
        if (cs != null) {
            try {
                cs.close();
            } catch (Exception e) {
            }
        }
    }
}
