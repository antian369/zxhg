/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户退出<br />
 * service_code : S11000<br />
 * @author Asus
 */
@Service
public class LogoutPl extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
            AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData session = getSession(in);
        String zt = in.getStringValue("zt");        //超时自动注销
        if (StringUtil.isNull(zt)) {
            zt = "1";       //手动注销
        }
        String optId = session.getStringValue("opt_id");
        if (!StringUtil.isNull(optId)) {
            long onLine = (new Date().getTime() - session.getLongValue("login_time")) / 60000;
            session.clear();
            //update pl_login_his set logout_time=now(), on_line=?,zt=? where opt_id=?
            update("save_logout_his", onLine, zt, optId);
        } else {
            session.clear();
        }
    }
}
