/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 获取一条角色信息
 * S11005
 * @author Asus
 */
@Service
public class GetRolePl extends BaseService {

    private static final String[] KEY = new String[]{"role_id", "角色编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData role = queryData("get_role_pl", in.getStringValue("role_id"));
        if (role == null || role.isEmpty()) {
            throw new GlobalException(220016, "角色信息");      //要查询的!#!不存在
        }
        out.putAll(role);
    }
}
