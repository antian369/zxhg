package com.soa.util;

import java.awt.Font;
import java.text.DecimalFormat;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.ui.TextAnchor;

public class ReportUtil {

    private static final Font TITLE_FONT = new Font("����", Font.BOLD, 20);         //����
    private static final Font DOMAIN_FONT = new Font("����", Font.BOLD, 14);        //ˮƽ�ײ�˵��
    private static final Font TICK_FONT = new Font("����", Font.PLAIN, 12);         //ˮƽ�ײ��б�
    private static final Font RANGE_FONT = new Font("����", Font.BOLD, 14);         //��ֱ����˵��
    private static final Font LGEGEND_FONT = new Font("����", Font.BOLD, 14);       //ͼ��˵���

    /**
     * ʹ��Ĭ�ϲ���������״ͼ
     * @param chart
     */
    public static void DefualtFormatBarChart(JFreeChart chart) {
        chart.getTitle().setFont(TITLE_FONT);
        CategoryPlot plot = chart.getCategoryPlot();                    //ͼ�?��
        NumberAxis vn = (NumberAxis) plot.getRangeAxis();
        DecimalFormat df = new DecimalFormat("#0");                     // ����ᾫ��
        vn.setNumberFormatOverride(df);                                 // �������ݱ�ǩ����ʾ��ʽ
        CategoryAxis domainAxis = plot.getDomainAxis();                 //ˮƽ�ײ�
        domainAxis.setLabelFont(DOMAIN_FONT);                           //ˮƽ�ײ��б�
        domainAxis.setTickLabelFont(TICK_FONT);
        ValueAxis rangeAxis = plot.getRangeAxis();                      //��ֱ����
        rangeAxis.setLabelFont(RANGE_FONT);
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//��ʾÿ�������ֵ
        renderer.setBaseItemLabelsVisible(true);                        //��ʾ�����ϵ���ֵ
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        chart.getLegend().setItemFont(LGEGEND_FONT);                    //˵���
    }
}
