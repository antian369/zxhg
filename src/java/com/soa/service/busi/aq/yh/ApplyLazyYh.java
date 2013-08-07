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
 * 申请延时
 * P12021
 * @author lianzt
 */
@Service
public class ApplyLazyYh extends BaseService{

    private final String[] KEY = new String[]{"yh_id", "隐患编号", "zgbz", "整改备注"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //把隐患的状态修改为 3申请延时
        //update aq_yh_info set zt=? where yh_id=?
        update("update_yhzt_yh", "3", in.getStringValue("yh_id"));
        //隐患对应的整改记录中，把延时状态为 1未超时 的改为 2申请延时
        //update aq_yh_zg set lazy_zt=?, zgbz=nvl(?,zgbz) where yh_id=? and yszt=?
        update("update_lazy_ys_yh", "2", in.getStringValue("zgbz"), in.getStringValue("yh_id"), "1");
    }
}
