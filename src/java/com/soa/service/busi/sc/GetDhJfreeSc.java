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
import com.soa.util.ReportUtil;
import com.soa.util.SystemUtil;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

/**
 * 获取月单耗的 JSChart 数据数组
 * S13005
 * 参数：search_month 日期的 yyyy-mm格式，可选，如果为空，查询当月信息
 * @author Asus
 */
@Service
public class GetDhJfreeSc extends BaseService {

    private static final Logger log = Logger.getLogger(GetDhJfreeSc.class);

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
            AbstractCommonData out, AbstractCommonData outHead) {

        String searchMonth = in.getStringValue("search_month");
        Date begin, end;
        if (StringUtil.isNull(searchMonth)) {     //如果为空，取当月
            begin = new Date();
            begin = DateUtil.setDay(begin, 1);
            end = DateUtil.addMonth(begin, 1);
            searchMonth = DateUtil.defualtFormat(begin);
        } else {
            searchMonth = searchMonth + "-01";      //补全，方便计算
            try {
                begin = DateUtil.formatDate(searchMonth, "yyyy-MM-dd");
            } catch (InstanceDataException e) {
                throw new GlobalException(210036);      //日期格式不正确
            }
            end = DateUtil.addMonth(begin, 1);
        }
        if(begin.getTime() > new Date().getTime()){
            throw new GlobalException(200032, "查询月份");      //!#!不能大于当前时间
        }
        if (log.isDebugEnabled()) {
            log.debug("begin:" + DateUtil.defualtFormat(begin) + ",  end:" + DateUtil.defualtFormat(end));
        }
        //查询单耗
        //select * from sc_rdh where dhlx='d' and scrq >= ? and scrq < ? order by scrq
        List<AbstractCommonData> list = queryList("get_dh_chart_sc", begin, end);
        if(list == null ||list.isEmpty()){
            throw new GlobalException(200003);      //没有查到相关数据
        }
        AbstractCommonData charts = DataConvertFactory.getInstanceEmpty();      //用于存放JSChart的图表数据，包含7种单耗曲线，每种都为一个二维数组
        List<List<String>> hym = new LinkedList<List<String>>();
        List<List<String>> hrm = new LinkedList<List<String>>();
        List<List<String>> hs = new LinkedList<List<String>>();
        List<List<String>> hwd = new LinkedList<List<String>>();
        List<List<String>> hq = new LinkedList<List<String>>();
        List<List<String>> qhym = new LinkedList<List<String>>();
        List<List<String>> zqhm = new LinkedList<List<String>>();
        charts.putArrayValue("hym", hym);
        charts.putArrayValue("hrm", hrm);
        charts.putArrayValue("hs", hs);
        charts.putArrayValue("hwd", hwd);
        charts.putArrayValue("hq", hq);
        charts.putArrayValue("qhym", qhym);
        charts.putArrayValue("zqhm", zqhm);
        out.putDataValue("JSChart", charts);
        List<String> temp = null;
        Integer i = 1;
        for(AbstractCommonData dh : list){
            //增加 hym 单耗
            temp = new LinkedList<String>();
            temp.add(i.toString());
            temp.add(dh.getStringValue("hym"));
            hym.add(temp);
            //增加 hrm 单耗
            temp = new LinkedList<String>();
            temp.add(i.toString());
            temp.add(dh.getStringValue("hrm"));
            hrm.add(temp);
            //增加 hs 单耗
            temp = new LinkedList<String>();
            temp.add(i.toString());
            temp.add(dh.getStringValue("hs"));
            hs.add(temp);
            //增加 hwd 单耗
            temp = new LinkedList<String>();
            temp.add(i.toString());
            temp.add(dh.getStringValue("hwd"));
            hwd.add(temp);
            //增加 hq 单耗
            temp = new LinkedList<String>();
            temp.add(i.toString());
            temp.add(dh.getStringValue("hq"));
            hq.add(temp);
            //增加 qhym 单耗
            temp = new LinkedList<String>();
            temp.add(i.toString());
            temp.add(dh.getStringValue("qhym"));
            qhym.add(temp);
            //增加 zqhm 单耗
            temp = new LinkedList<String>();
            temp.add(i.toString());
            temp.add(dh.getStringValue("zqhm"));
            zqhm.add(temp);
            i++;
        }


    }
}
//以前JfreeChart的代码
//生成测试数据
//        List<Double> list = new LinkedList<Double>();
//        long now = new Date().getTime();
//        Date beginDate = new Date();
//        beginDate = DateUtil.setDay(beginDate, 0);
//        beginDate = DateUtil.setHour(beginDate, 0);
//        beginDate = DateUtil.setMinute(beginDate, 0);
//        beginDate = DateUtil.setSecond(beginDate, 1);
//        long begin = beginDate.getTime();
//        for (; begin < now; begin += (24L * 3600000)) {
//            BigDecimal bigDouble = new BigDecimal(Math.random() + 1);
//            list.add(bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//        }
//        //end
//
//        String title = "产品单耗图";      //标题
//        String y = "单耗";          //y轴
//        HttpSession ses = getHttpSession(in);
//        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
//        for (int i = 0; i < list.size(); i++) {
//            dcd.setValue(list.get(i), y, (i + 1) + "");
//        }
//        try {
//            JFreeChart chart = ChartFactory.createBarChart3D(title, "日期", "吨甲醇耗原煤(t/t)", dcd, PlotOrientation.VERTICAL, true, true, false);
//            ReportUtil.DefualtFormatBarChart(chart);        //默认参数设置柱状图
//            String url = "/" + SystemUtil.serverName + SystemUtil.reportUrl + ServletUtilities.saveChartAsJPEG(chart, 800, 600, ses);
//            if (log.isDebugEnabled()) {
//                log.debug("图表url : " + url);
//            }
//            out.putStringValue("url", url);
//        } catch (Exception e) {
//            throw new GlobalException(220017, e);       //图表生成错误
//        }
