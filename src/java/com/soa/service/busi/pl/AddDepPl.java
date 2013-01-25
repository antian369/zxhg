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
 * 新增部门
 * P11002
 * @author Asus
 */
@Service
public class AddDepPl extends BaseService{

    private static final String[] KEY = new String[]{"dep_name", "部门名称"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        update("add_dep_pl", SystemUtil.getSerialNum(), in.getStringValue("dep_name"), in.getStringValue("dep_fzr"), in.getStringValue("dep_type"));
    }
}
