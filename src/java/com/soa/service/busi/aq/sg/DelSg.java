/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.sg;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事故删除
 * P12019
 * @author lianzt
 */
@Service
public class DelSg extends BaseService{

    private final String[] KEY = new String[]{"sg_id", "事故编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //delete from aq_sg_info where sg_id=?
        update("del_sg", in.getStringValue("sg_id"));
    }
}
