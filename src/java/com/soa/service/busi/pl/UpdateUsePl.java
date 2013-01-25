/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 修改用户信息
 * P11011
 * @author Asus
 */
@Service
public class UpdateUsePl extends BaseService {

    private static final String[] KEY = new String[]{"username", "用户名",
                                                     "role_id", "角色编号",
                                                     "dep_id", "部门编号",
                                                     "name", "姓名"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        Object[] args = new Object[9];
        args[0] = in.getStringValue("role_id");
        args[1] = in.getStringValue("dep_id");
        args[2] = in.getStringValue("name");
        args[3] = in.getStringValue("rank");
        args[4] = in.getStringValue("zw1");
        args[5] = in.getStringValue("zw2");
        args[6] = in.getStringValue("zw3");
        args[7] = in.getStringValue("tel");
        args[8] = in.getStringValue("username");
        update("update_user_pl", args);
    }
}
