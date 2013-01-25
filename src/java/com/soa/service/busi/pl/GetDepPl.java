/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 获取单一的部门信息
 * service_code : S11003
 * @author Asus
 */
@Service
public class GetDepPl extends BaseService {

    private static final String[] KEY = new String[]{"dep_id", "部门编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        out.putAll(queryData("get_dep_pl", in.getStringValue("dep_id")));
    }
}
