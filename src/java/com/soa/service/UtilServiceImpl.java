package com.soa.service;

import com.soa.bean.StServiceBean;
import com.soa.bean.StTableParamet;
import com.lianzt.commondata.AbstractCommonData;
import com.soa.exception.GlobalException;
import com.soa.service.busi.impl.BusiService;
import com.soa.service.impl.UtilService;
import com.lianzt.util.StringUtil;
import com.soa.util.SystemUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilServiceImpl extends BaseService implements UtilService {

    private static final Logger log = Logger.getLogger(UtilServiceImpl.class);
    private static final String SELECT_ERR_MSG = "select err_code, err_msg from st_err_msg";
    private static final String SELECT_TABLE_PARA = "select table_name, col_name, col_value, col_desc, value_desc from st_table_paramet order by table_name, col_name, col_value";
    private static final String SELECT_SERVICE_BEAN = "select service_code, bean_name, service_desc, is_login, module from st_service_bean ";
    private static final String SAVE_ERROR = "insert into st_err_log (serial_num, err_time, err_code, err_msg, err_stack) value (?, now(), ?, ?, ?)";

    /**
     * 读取异常信息表
     * @return
     * @throws DataAccessException
     */
    @Override
    public Map<Integer, String> readErrMsg() {
        return getJdbcTemplate().execute(new StatementCallback<Map<Integer, String>>() {

            @Override
            public Map<Integer, String> doInStatement(Statement stmnt) throws
                    SQLException,
                    DataAccessException {
                Map<Integer, String> map = new HashMap<Integer, String>();
                ResultSet rs = null;
                try {
                    rs = stmnt.executeQuery(SELECT_ERR_MSG);
                    while (rs.next()) {
                        map.put(rs.getInt(1), rs.getString(2));
                    }
                    return map;
                } catch (SQLException e) {
                    log.error("执行SQL : " + SELECT_ERR_MSG + " \t出现异常 ： " + e);
                    throw e;
                } finally {
                    close(rs);
                }
            }
        });
    }

    /**
     * 读取st_table_paramet表
     * @return
     * @throws DataAccessException
     */
    @Override
    public Map<String, List<StTableParamet>> readTablePara() {
        return getJdbcTemplate().execute(new StatementCallback<Map<String, List<StTableParamet>>>() {

            @Override
            public Map<String, List<StTableParamet>> doInStatement(
                    Statement stmnt)
                    throws SQLException,
                           DataAccessException {
                Map<String, List<StTableParamet>> map = new HashMap<String, List<StTableParamet>>();
                ResultSet rs = null;
                StTableParamet stp = null;
                List<StTableParamet> colList = null;
                String lastCol = "";		//表名.列名
                try {
                    rs = stmnt.executeQuery(SELECT_TABLE_PARA);
                    while (rs.next()) {
                        stp = new StTableParamet();
                        stp.setTableName(rs.getString(1));
                        stp.setColName(rs.getString(2));
                        stp.setColValue(rs.getString(3));
                        stp.setColDesc(rs.getString(4));
                        stp.setValueDesc(rs.getString(5));
                        if (lastCol.equals(stp.getTableName() + "." + stp.getColName())) {
                            colList.add(stp);
                        } else {
                            colList = new ArrayList<StTableParamet>();
                            lastCol = stp.getTableName() + "." + stp.getColName();
                            map.put(lastCol, colList);
                            colList.add(stp);
                        }
                    }
                    return map;
                } catch (SQLException e) {
                    log.error("执行SQL : " + SELECT_TABLE_PARA + " \t出现异常 ： " + e);
                    throw e;
                } finally {
                    close(rs);
                }
            }
        });
    }

    /**
     * 读取服务bean
     * @return
     * @throws DataAccessException
     */
    @Override
    public Map<String, StServiceBean> readServiceBean() {
        return getJdbcTemplate().execute(new StatementCallback<Map<String, StServiceBean>>() {

            @Override
            public Map<String, StServiceBean> doInStatement(Statement stmnt)
                    throws SQLException,
                           DataAccessException {
                Map<String, StServiceBean> map = new HashMap<String, StServiceBean>();
                ResultSet rs = null;
                BusiService bs = null;
                StServiceBean service = null;
                try {
                    rs = stmnt.executeQuery(SELECT_SERVICE_BEAN);
                    while (rs.next()) {
                        try {
                            service = new StServiceBean();
                            service.setServiceCode(rs.getString(1));
                            service.setBeanName(rs.getString(2));
                            service.setServiceDesc(rs.getString(3));
                            service.setIsLogin(rs.getString(4));
                            service.setModule(rs.getString(5));
                            bs = (BusiService) SystemUtil.wac.getBean(service.getBeanName());
                            service.setService(bs);
                        } catch (BeansException e) {
                            log.warn("service ： " + service.getServiceCode() + "( " + service.getBeanName() + " ) 不存在. ");
                            bs = null;
                        }
                        if (bs != null) {
                            map.put(service.getServiceCode(), service);
                        }
                    }
                    return map;
                } catch (SQLException e) {
                    log.error("执行SQL : " + SELECT_SERVICE_BEAN + " \t出现异常 ： " + e);
                    throw e;
                } finally {
                    close(rs);
                }
            }
        });
    }

    /**
     * 保存异常信息
     * @param e 异常信息
     */
    @Override
    @Transactional
    public boolean saveError(Exception e) {
        String errCode = "";
        String errMsg = "";
        if (e instanceof GlobalException) {
            GlobalException ge = (GlobalException) e;
            errCode = ge.getErrorCode() + "";
            errMsg = ge.getErrorMsg();
        }
        //记录错误日志
        try {
            if (getJdbcTemplate().update(SAVE_ERROR, SystemUtil.getSerialNum(), errCode, errMsg, StringUtil.getExceptionStack(e)) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            log.error("保存异常信息时出现异常:", ex);
            return false;
        }
    }

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
    }
}
