/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除隐患
 * P12024
 * @author lianzt
 */
@Service
public class DelYh extends BaseService{

    private final String[] KEY = new String[]{"yh_id","隐患编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //delete from aq_yh_zg where yh_id=?
        update("delete_zg_yh", in.getStringValue("yh_id"));
        //delete from aq_yh_info where yh_id=?
        update("delete_yh", in.getStringValue("yh_id"));
    }
}
