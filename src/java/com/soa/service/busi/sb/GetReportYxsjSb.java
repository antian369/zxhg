/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sb;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.DateUtil;
import com.soa.service.BaseService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 主要装置运行时间统计报表
 * S14006
 * @author lianzt
 */
@Service
public class GetReportYxsjSb extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        List<AbstractCommonData> list = new LinkedList<AbstractCommonData>();
        AbstractCommonData acd = null;
        long now = new Date().getTime() - (24L * 3600000);
        Date beginDate = new Date();
        beginDate = DateUtil.setDay(beginDate, 0);
        beginDate = DateUtil.setHour(beginDate, 0);
        beginDate = DateUtil.setMinute(beginDate, 0);
        beginDate = DateUtil.setSecond(beginDate, 1);
        long begin = beginDate.getTime();
        AbstractCommonData sum = DataConvertFactory.getInstanceEmpty();
        sum.putIntValue("sb1", 0);
        sum.putIntValue("sb2", 0);
        sum.putIntValue("sb3", 0);
        sum.putIntValue("sb4", 0);
        sum.putIntValue("sb5", 0);
        sum.putIntValue("sb6", 0);
        sum.putIntValue("sb7", 0);
        sum.putIntValue("sb8", 0);
        sum.putIntValue("sb9", 0);
        sum.putIntValue("sb10", 0);
        sum.putIntValue("sb11", 0);
        sum.putIntValue("sb12", 0);
        sum.putIntValue("sb13", 0);
        sum.putIntValue("sb14", 0);
        int temp;
        for (; begin < now; begin += (24L * 3600000)) {
            acd = DataConvertFactory.getInstanceEmpty();
            for (int i = 1; i <= 14; i++) {
                temp = getRandom();
                acd.putIntValue("sb" + i, temp);
                sum.putIntValue("sb" + i, sum.getIntValue("sb" + i) + temp);
            }
            list.add(acd);
        }
        out.putArrayValue("list", list);
        out.putDataValue("sum", sum);
        out.putStringValue("yf", DateUtil.defualtFormat(beginDate).substring(0, 7));
    }
    /**
     * 随机数
     * @return
     */
    private int getRandom() {
        double d = Math.random();
        return (int) (d * 24);
    }
}
