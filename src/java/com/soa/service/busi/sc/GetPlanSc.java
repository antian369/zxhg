/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 获取月指标
 * S13009
 * @author lianzt
 */
@Service
public class GetPlanSc extends BaseService {

    private static final String[] KEY = new String[]{"jhyf", "计划月份",
                                                     "cpbh", "产品编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData acd = queryData("get_month_jh_sc", in.getStringValue("jhyf"), in.getStringValue("cpbh"));
        if (acd != null && !acd.isEmpty()) {
            out.putAll(acd);
        }
    }
}
