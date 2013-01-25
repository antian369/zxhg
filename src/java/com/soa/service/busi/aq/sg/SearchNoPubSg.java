/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.sg;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 查询未通报的事故
 * S12003
 * @author Asus
 */
@Service
public class SearchNoPubSg extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        in.putStringValue("zt", "1");       //未通报 的事故
        runService("S12002", in, inHead, out, outHead);
    }
}
