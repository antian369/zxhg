/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.sw;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除三违
 * P12018
 * @author lianzt
 */
@Service
public class DelSw extends BaseService{

    private final String[] KEY = new String[]{"sw_id", "三违编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //delete from aq_sw_info where sw_id=?
        update("del_sw", in.getStringValue("sw_id"));
    }

}
