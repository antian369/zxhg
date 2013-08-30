/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.listener;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web application lifecycle listener.
 * @author lianzt
 */
public class SessionListener implements HttpSessionListener {

    private Logger log = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        AbstractCommonData ses = (AbstractCommonData) se.getSession().getAttribute("ses");
        if (ses == null || ses.isEmpty()) {
            return;
        }
        String optId = ses.getStringValue("opt_id");
        if (StringUtil.isNull(optId)) {
            return;
        }
        //调用注销服务
        AbstractCommonData req = DataConvertFactory.getInstance();
        req.putStringValue("zt", "2");
        req.putObjectValue("session", se.getSession());
        try {
            BaseService.runService(req, "S11000");
        } catch (Exception e) {
            log.error("注销服务出现异常：", e);
        }
    }
}
