/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * 查询调度快报
 * S13014
 * 说明：如果scrq为空，查询最后一个快报
 * @author lianzt
 */
@Service
public class GetDdkbSc extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        Date scrq = in.getDateValue("scrq");        //请求报文中的日期
        if (scrq == null) {
            AbstractCommonData acd = queryData("get_last_rb_sc");       //最后一个快报
            if (acd != null && !acd.isEmpty()) {
                scrq = acd.getDateValue("scrq");
            } else {
                return; //如果快报为空，什么都不返回
            }
        }
        AbstractCommonData res = queryData("get_rb_sc", scrq);
        if (res == null || res.isEmpty()) {
            throw new GlobalException(200019, "调度日报");      //!#!不存在
        }
        scrq = res.getDateValue("scrq");
        res.putStringValue("scrq", DateUtil.defualtFormat(scrq));
        out.putAll(res);
    }
}
