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
 * 修改角色信息
 * P11005
 * @author Asus
 */
@Service
public class UpdateRolePl extends BaseService {

    private static final String[] KEY = new String[]{"role_id", "角色编号",
                                                     "dep_id", "所在部门",
                                                     "role_name", "角色名"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        update("update_role_pl",
               in.getStringValue("dep_id"), in.getStringValue("role_name"),
               in.getStringValue("bz"), in.getStringValue("role_id"));
    }
}
