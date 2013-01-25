/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 修改部门信息
 * P11003
 * @author Asus
 */
@Service
public class UpdateDepPl extends BaseService {

    private static final String[] KEY = new String[]{"dep_id", "部门编号",
                                                     "dep_name", "部门名称",
                                                     "dep_type", "部门类型"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        update("update_dep_pl", in.getStringValue("dep_name"), in.getStringValue("dep_fzr"), in.getStringValue("dep_type"), in.getStringValue("dep_id"));
    }
}
