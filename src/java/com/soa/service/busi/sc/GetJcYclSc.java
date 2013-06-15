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
            begin = new Date();
            end = DateUtil.setDay(begin, 26);
            begin = DateUtil.addMonth(end, -1);       //从上个月26号开始
        } else {
            searchMonth = searchMonth + "-26";      //补全，方便计算
            try {
                end = DateUtil.formatDate(searchMonth, "yyyy-MM-dd");
            } catch (InstanceDataException e) {
                throw new GlobalException(210036);      //日期格式不正确
            }
            begin = DateUtil.addMonth(end, -1);
        }
        if (log.isDebugEnabled()) {
            log.debug("begin:" + DateUtil.defualtFormat(begin) + ",  end:" + DateUtil.defualtFormat(end));
        }
        //甲醇产量=粗甲醇产量*0.97+送乙二醇合成气/2.2       001*0.97 + 012/2.2
        //select scrq, sum(cl) sum_cl from sc_rcl group by scrq, cpbh having cpbh=? and scrq >= ? and scrq < ?
        //先查询甲醇
        List<AbstractCommonData> jcList = queryList("get_jc_ycl_sc", "001", begin, end);
        List<AbstractCommonData> hcqList = queryList("get_jc_ycl_sc", "012", begin, end);
        //把两个数组转成map，方便取数
        Map<String, Double> jcMap = new HashMap<String, Double>();
        Map<String, Double> hcqMap = new HashMap<String, Double>();
        for (AbstractCommonData acd : jcList) {
            jcMap.put(acd.getStringValue("scrq"), acd.getDoubleValue("sum_cl"));
        }
        for (AbstractCommonData acd : hcqList) {
            hcqMap.put(acd.getStringValue("scrq"), acd.getDoubleValue("sum_cl"));
        }
        if (log.isDebugEnabled()) {
            log.debug("\njc map : " + jcMap + "\nhcq map : " + hcqMap);
        }


        AbstractCommonData charts = DataConvertFactory.getInstanceEmpty();      //用于存放JSChart的图表数据，包含7种单耗曲线，每种都为一个二维数组
        out.putDataValue("JSChart", charts);
        List<List<String>> cc = new LinkedList<List<String>>();     //粗醇
        List<List<String>> yec = new LinkedList<List<String>>();    //乙二醇
        charts.putArrayValue("cc", cc);
        charts.putArrayValue("yec", yec);
        List<String> tempList = null;
        double jccl = 0;
        int i=1;
        String tempDay = "";
        //temp从begin开始，大于等于end结束，每次加一天
        for (temp = begin; temp.getTime() < end.getTime(); temp = DateUtil.addDay(temp, 1)) {
            tempDay = DateUtil.detaledFormat(temp);
            if (jcMap.get(tempDay) == null || hcqMap.get(tempDay) == null || hcqMap.get(tempDay) == 0) {
                jccl = 0.0;
            } else {
                jccl = (jcMap.get(tempDay) * 0.97) + (hcqMap.get(tempDay) / 2.2);       //甲醇产量计算公式
                jccl = round(jccl);
            }

            if (log.isDebugEnabled()) {
                log.debug(tempDay + "{unit:" + i + ", value : " + jccl + "}");
            }
            i++;
        }


        //开始组装 JSCharts 图表报文
        AbstractCommonData res = DataConvertFactory.getInstanceEmpty();     //报文根节点
        out.putDataValue("JSChart", res);           //把报文放入响应
        AbstractCommonData JSChart = DataConvertFactory.getInstanceEmpty();
        res.putDataValue("JSChart", JSChart);
        List<AbstractCommonData> datasets = new LinkedList<AbstractCommonData>();   //datasets 数组
        JSChart.putArrayValue("datasets", datasets);     //
        AbstractCommonData bar = DataConvertFactory.getInstanceEmpty();     //柱状图
        datasets.add(bar);      //由于产量图只有一个数组项，所以只有一个数组元素
        bar.putStringValue("type", "bar");      //图表类型为bar
        List<AbstractCommonData> data = new LinkedList<AbstractCommonData>();       //图表的data数组
        bar.putArrayValue("data", data);
        if (log.isDebugEnabled()) {
            log.debug("数据格式：" + DataConvertFactory.praseNormJson(JSChart));
        }
        AbstractCommonData dataTemp = null;
        for (temp = begin; temp.getTime() < end.getTime(); temp = DateUtil.addDay(temp, 1)) {
            dataTemp = DataConvertFactory.getInstanceEmpty();
            tempDay = DateUtil.detaledFormat(temp);
            dataTemp.putStringValue("unit", temp.getDate() + "");
            dataTemp.putStringValue("value", jccl + "");
            data.add(dataTemp);
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
