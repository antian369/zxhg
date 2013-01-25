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
 * 重置用户密码
 * P11007
 * @author Asus
 */
@Service
public class ResetUserPswPl extends BaseService {

    private static final String[] KEY = new String[]{"username", "用户名",
                                                     "password", "密码"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        String psw = in.getStringValue("password");
        try {
            psw = AESFactory.encryptString(psw);
        } catch (Exception e) {
            throw new GlobalException(200002, e);       //加密算法异常
        }
        update("reset_user_psw_pl", psw, in.getStringValue("username"));
    }
}
