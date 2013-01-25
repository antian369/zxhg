/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 新增一条角色信息
 * P11004
 * @author Asus
 */
@Service
public class AddRolePl extends BaseService {

    private static final String[] KEY = new String[]{"dep_id", "所在部门",
                                                     "role_name", "角色名"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        Object[] args = new Object[5];
        args[0] = SystemUtil.getSerialNum();
        args[1] = in.getStringValue("dep_id");
        args[2] = in.getStringValue("role_name");
        args[3] = getLoginUser(in);
        args[4] = in.getStringValue("bz");
        update("add_role_pl", args);
        out.putStringValue("role_id", args[0].toString());
    }
}
