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
 * 作废隐患收购
 * P12025
 * @author lianzt
 */
@Service
public class CancelYhsgYh extends BaseService{

    private final String[] KEY = new String[]{"yh_id","隐患编号","zgqk","备注"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //update aq_yh_yhsg set zt='2', zgqk=?,qrr=?,qrrxm=? where yh_id=?
        AbstractCommonData ses = getSession(in);
        update("cancel_yhsg_yh", in.getStringValue("zgqk"),getLoginUser(in),ses.getStringValue("name"),in.getStringValue("yh_id"));
    }

}
