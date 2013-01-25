/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 获取单耗信息
 * S13010
 * 说明：可选参数 scrq 为空是查询当天
 * @author lianzt
 */
@Service
public class GetDhSc extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData acd = queryData("get_dh_auto_sc", in.getStringValue("scrq"));
        if (acd != null) {
            out.putAll(acd);
        }
    }
}
