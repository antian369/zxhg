/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.factory.AESFactory;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户登录<br />
 * service_code : P11000<br />
 * @author Asus
 */
@Service
public class LoginPl extends BaseService {

    private static final String[] KEYS = new String[]{"username", "用户名",
                                                      "password", "密码",
                                                      "check_num", "验证码"};

    @Override
    public String[] keys() {
        return KEYS;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        runService("S10004", in, inHead, out, outHead);
        AbstractCommonData user = queryData("get_enabled_user_pl", in.getStringValue("username"));
        if (user == null || user.isEmpty()) {
            throw new GlobalException(200008);      //用户名或密码错误
        }
        String psw = null;
        try {
            psw = AESFactory.encryptString(in.getStringValue("password"));
        } catch (Exception e) {
            throw new GlobalException(200002, e);       //加密算法异常
        }
        if (!user.getStringValue("password").equals(psw)) {
            throw new GlobalException(200008);      //用户名或密码错误
        }
        AbstractCommonData session = getSession(in);
        session.putStringValue(SystemUtil.loginRemark, in.getStringValue("username"));
        session.putAll(user);         //把用户信息放入session
        update("login_sus_pl", inHead.getStringValue("_ip"), in.getStringValue("username"));
        Set<String> limitSet = new HashSet<String>();       //在session中保存用户权限
        limitSet.add("index");      //把不需要权限验证的页面加入session
        limitSet.add("welcome");
        limitSet.add("logout");
        limitSet.add("update_psw");
        limitSet.add("error");
        session.putObjectValue("limit", limitSet);
    }
}
