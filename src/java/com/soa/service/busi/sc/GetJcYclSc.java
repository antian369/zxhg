/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.exception.InstanceDataException;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 获取甲醇月产量图表数据
 * S13016
 * 参数：search_month 日期的 yyyy-mm格式，可选，如果为空，查询当月信息
 * @author lianzt
 */
@Service
public class GetJcYclSc extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(GetJcYclSc.class);

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        String searchMonth = in.getStringValue("search_month");
        Date begin, end, temp;
        if (StringUtil.isNull(searchMonth)) {     //如果为空，取当月
            searchMonth = DateUtil.defualtFormat(new Date()).substring(0, 7);
        }
        searchMonth = searchMonth + "-26";      //补全，方便计算
        try {
            end = DateUtil.formatDate(searchMonth, "yyyy-MM-dd");
        } catch (InstanceDataException e) {
            throw new GlobalException(210036);      //日期格式不正确
        }
        begin = DateUtil.addMonth(end, -1);

        if (log.isDebugEnabled()) {
            log.debug("begin:" + DateUtil.defualtFormat(begin) + ",  end:" + DateUtil.defualtFormat(end));
        }
        //甲醇产量=粗甲醇产量*0.97+送乙二醇合成气/2.2       001*0.97 + 012/2.2
        //select scrq, sum(cl) sum_cl from sc_rcl group by scrq, cpbh having cpbh=? and scrq >= ? and scrq < ?
        //先查询甲醇
        List<AbstractCommonData> ccList = queryList("get_jc_ycl_sc", "001", begin, end);
        List<AbstractCommonData> yecList = queryList("get_jc_ycl_sc", "012", begin, end);
        //把两个数组转成map，方便取数
        Map<String, Double> ccMap = new HashMap<String, Double>();
        Map<String, Double> yecMap = new HashMap<String, Double>();
        for (AbstractCommonData acd : ccList) {
            ccMap.put(acd.getStringValue("scrq"), acd.getDoubleValue("sum_cl"));
        }
        for (AbstractCommonData acd : yecList) {
            yecMap.put(acd.getStringValue("scrq"), acd.getDoubleValue("sum_cl"));
        }
        if (log.isDebugEnabled()) {
            log.debug("\njc map : " + ccMap + "\nyec map : " + yecMap);
        }


        AbstractCommonData charts = DataConvertFactory.getInstanceEmpty();      //用于存放JSChart的图表数据，包含7种单耗曲线，每种都为一个二维数组
        out.putDataValue("JSChart", charts);
        List<List<String>> result = new LinkedList<List<String>>();     //粗醇、乙二醇
        charts.putArrayValue("result", result);
        List<String> tempList = null;
        Double ccz = 0.0, yecz = 0.0;
        String tempDay = "";
        //temp从begin开始，大于等于end结束，每次加一天
        for (temp = begin; temp.getTime() < end.getTime(); temp = DateUtil.addDay(temp, 1)) {
            tempDay = DateUtil.detaledFormat(temp);
            tempList = new LinkedList<String>();
            tempList.add("\"" + temp.getDate() + "\"");

            if (ccMap.get(tempDay) == null || ccMap.get(tempDay) == 0) {
                ccz = 0.0;
            } else {
                ccz = round(ccMap.get(tempDay) * 0.97);
            }
            tempList.add(ccz.toString());

            if (yecMap.get(tempDay) == null || yecMap.get(tempDay) == 0) {
                yecz = 0.0;
            } else {
                yecz = round(yecMap.get(tempDay) / 2.2);
            }
            tempList.add(yecz.toString());
            result.add(tempList);
        }
    }

    /**
     * 四舍五入，LENGTH 位小数
     * @param d
     * @return
     */
    private double round(double d) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
