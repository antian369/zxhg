/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import org.springframework.stereotype.Service;

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
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData session = getSession(in);
        session.remove(SystemUtil.loginRemark);
    }
}
