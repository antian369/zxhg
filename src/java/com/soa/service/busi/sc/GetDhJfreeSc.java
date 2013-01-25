/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
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
 * 获取单耗图表
 * S13005
 * 参数：单耗类型
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
        //生成测试数据
        List<Double> list = new LinkedList<Double>();
        long now = new Date().getTime();
        Date beginDate = new Date();
        beginDate = DateUtil.setDay(beginDate, 0);
        beginDate = DateUtil.setHour(beginDate, 0);
        beginDate = DateUtil.setMinute(beginDate, 0);
        beginDate = DateUtil.setSecond(beginDate, 1);
        long begin = beginDate.getTime();
        for (; begin < now; begin += (24L * 3600000)) {
            BigDecimal bigDouble = new BigDecimal(Math.random() + 1);
            list.add(bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        //end

        String title = "产品单耗图";      //标题
        String y = "单耗";          //y轴
        HttpSession ses = getHttpSession(in);
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        for (int i = 0; i < list.size(); i++) {
            dcd.setValue(list.get(i), y, (i + 1) + "");
        }
        try {
            JFreeChart chart = ChartFactory.createBarChart3D(title, "日期", "吨甲醇耗原煤(t/t)", dcd, PlotOrientation.VERTICAL, true, true, false);
            ReportUtil.DefualtFormatBarChart(chart);        //默认参数设置柱状图
            String url = "/" + SystemUtil.serverName + SystemUtil.reportUrl + ServletUtilities.saveChartAsJPEG(chart, 800, 600, ses);
            if (log.isDebugEnabled()) {
                log.debug("图表url : " + url);
            }
            out.putStringValue("url", url);
        } catch (Exception e) {
            throw new GlobalException(220017, e);       //图表生成错误
        }
    }
}
