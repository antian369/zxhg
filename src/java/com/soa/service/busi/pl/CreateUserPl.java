/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.factory.AESFactory;
import com.lianzt.util.StringUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建用户
 * P11008
 * @author Asus
 */
@Service
public class CreateUserPl extends BaseService {

    private static final String[] KEY = new String[]{"username", "用户名",
                                                     "password", "密码",
                                                     "name", "姓名",
                                                     "dep_id", "部门",
                                                     "role_id", "角色"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData user = runService(in, "S11007");     //获取用户信息
        if (!StringUtil.isNull(user.getStringValue("username"))) {
            throw new GlobalException(200009);        //用户名重复
        }
        Object[] args = new Object[12];
        args[0] = in.getStringValue("username");
        try {
            args[1] = AESFactory.encryptString(in.getStringValue("password"));
        } catch (Exception e) {
            throw new GlobalException(200002, e);       //加密算法异常
        }
        args[2] = in.getStringValue("name");
        args[3] = in.getStringValue("role_id");
        args[4] = in.getStringValue("dep_id");
        args[5] = in.getStringValue("rank");
        args[6] = getLoginUser(in);
        args[7] = in.getStringValue("bz");
        args[8] = in.getStringValue("zw1");
        args[9] = in.getStringValue("zw2");
        args[10] = in.getStringValue("zw3");
        args[11] = in.getStringValue("tel");
        update("create_user_pl", args);
    }
}
