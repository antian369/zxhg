/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.task.aq;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import com.soa.task.BaseTask;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 隐患的定时任务
 * @author Asus
 */
@Component
public class ExpiredZgYhTask extends BaseTask {

    @Override
    protected void runTask() {
        AbstractCommonData req = DataConvertFactory.getInstance();
        BaseService.runService(req, "P12013");
    }
}
