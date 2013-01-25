/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 登陆后欢迎页面的安全管理信息
 * S12015
 * 说明：查询第一页的 三违通报、事故通报、整改通报
 * @author Asus
 */
@Service
public class IndexPageYh extends BaseService{

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        in.putIntValue("page_size", 10);        //每页记录数
        in.putStringValue("do", "1");
        AbstractCommonData res = runService(in, "S12014");      //隐患通报
        out.putArrayValue("yhtbs", res.getArrayValue("result"));
        in.putStringValue("zt", "03");      //三违、已确认
        res = runService(in, "S12001");     //三违
        out.putArrayValue("sws", res.getArrayValue("result"));
        in.putStringValue("zt", "2");      //事故、已通报
        res = runService(in, "S12002");     //三违
        out.putArrayValue("sgs", res.getArrayValue("result"));
    }

}
