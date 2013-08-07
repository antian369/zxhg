/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 隐患整改历史
 * S12026
 * @author lianzt
 */
@Service
public class PageLsYh extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData ses = getSession(in);
        in.putStringValue("sgdw", ses.getStringValue("dep_id"));
        runService("S12024", in, inHead, out, outHead);
    }
}