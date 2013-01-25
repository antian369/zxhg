/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 获取消耗品月指标
 * S13015
 * @author lianzt
 */
@Service
public class GetXhPlanSc extends BaseService {

    private static final String[] KEY = new String[]{"jhyf", "计划月份",
                                                     "xhpbh", "消耗品编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData acd = queryData("get_month_xhpjh_sc", in.getStringValue("jhyf"), in.getStringValue("xhpbh"));
        if (acd != null && !acd.isEmpty()) {
            out.putAll(acd);
        }
    }
}
