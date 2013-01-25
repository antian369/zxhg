/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.factory.AESFactory;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 修改密码<br />
 * service_code : P11001<br />
 * @author Asus
 */
@Service
public class UpdatePswPl extends BaseService {

    private static final String[] KEYS = new String[]{"old_password", "原密码",
                                                      "new_password", "新密码"};

    @Override
    public String[] keys() {
        return KEYS;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData user = queryData("get_user_pl", getLoginUser(in));
        if (user == null || user.isEmpty()) {
            throw new GlobalException(200004);      //用户名不存在
        }
        String psw = null;
        try {
            psw = AESFactory.encryptString(in.getStringValue("old_password"));
        } catch (Exception e) {
            throw new GlobalException(200002, e);       //加密算法异常
        }
        if (!user.getStringValue("password").equals(psw)) {
            throw new GlobalException(200006);      // 原密码错误
        }
        try {
            psw = AESFactory.encryptString(in.getStringValue("new_password"));
        } catch (Exception e) {
            throw new GlobalException(220006, e);       //保存密码时出现异常，请重试！
        }
        update("update_psw_pl", psw, getLoginUser(in));
    }
}
