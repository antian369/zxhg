/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * S12025
 * 整改通报分页查询
 * @author lianzt
 */
@Service
public class PageTbYh extends BaseService{

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        in.putIntValue("lazy", 1);      //已通报
        runService("S12024", in, inHead, out, outHead);
    }
}
