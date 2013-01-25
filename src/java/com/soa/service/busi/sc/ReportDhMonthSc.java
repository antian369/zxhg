/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.DateUtil;
import com.soa.service.BaseService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 单耗月报表
 * S13004
 *
 * @author Asus
 */
@Service
public class ReportDhMonthSc extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        List<AbstractCommonData> list = new LinkedList<AbstractCommonData>();
        AbstractCommonData acd = null;
        long now = new Date().getTime();
        Date beginDate = new Date();
        beginDate = DateUtil.setDay(beginDate, 0);
        beginDate = DateUtil.setHour(beginDate, 0);
        beginDate = DateUtil.setMinute(beginDate, 0);
        beginDate = DateUtil.setSecond(beginDate, 1);
        long begin = beginDate.getTime();

        for (; begin < now; begin += (24L * 3600000)) {
            acd = DataConvertFactory.getInstanceEmpty();
            acd.putStringValue("dhrq", DateUtil.defualtFormat(new Date(begin)));
            acd.putStringValue("ym", getRandom());
            acd.putStringValue("rm", getRandom());
            acd.putStringValue("ys", getRandom());
            acd.putStringValue("wd", getRandom());
            acd.putStringValue("qh", getRandom());
            list.add(acd);
        }
        out.putArrayValue("dhbb", list);
    }

    /**
     * 随机数
     * @return
     */
    private String getRandom() {
        double d = Math.random();
        return (d + 1 + "").substring(0, 4);
    }
}
