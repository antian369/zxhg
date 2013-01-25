package com.soa.util;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.LogUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.jsp.JspWriter;
import org.apache.log4j.Logger;

/**
 * 一些ajax的简单操作
 * @author lianzt
 */
public class AjaxUtil {

    private static final Logger log = Logger.getLogger(AjaxUtil.class);

    /**
     * 发送一个业务执行成功的数据包
     * @param out
     * @param acd
     */
    public static void sendSucJson(PrintWriter out, AbstractCommonData acd) {
        AbstractCommonData head = acd.getDataValue("head");
        if (head == null) {
            head = DataConvertFactory.getInstanceEmpty();
            acd.putDataValue("head", head);
        }
        head.putStringValue("response_code", "000000");
        head.putStringValue("response_desc", "执行成功");
        sendJsonData(out, acd);
    }

    /**
     * 发送一个业务执行成功的数据包
     * @param out
     */
    public static void sendSucJson(PrintWriter out) {
        AbstractCommonData acd = DataConvertFactory.getInstance();
        AbstractCommonData head = acd.getDataValue("head");
        head.putStringValue("response_code", "000000");
        head.putStringValue("response_desc", "执行成功");
        sendJsonData(out, acd);
    }

    /**
     * 以JSON的形式发送一个数据包
     * @param out
     * @param resData 要发送的数据包
     */
    public static void sendJsonData(PrintWriter out, AbstractCommonData resData) {
        if (log.isDebugEnabled()) {
            log.debug("开始记录可分析的日志信息。。");
        }
        if (LogUtil.isInfo()) {
            //记录一下返回的数据
            LogUtil.analysisCommanDataLog(resData, "ajax-res");
        }
        if (log.isDebugEnabled()) {
            log.debug("ajax返回的数据：" + resData);
        }
        out.print(DataConvertFactory.praseJson(resData));
    }

    /**
     * 发送一个业务执行成功的数据包
     * @param out
     */
    public static void sendSucJson(JspWriter out) {
        AbstractCommonData acd = DataConvertFactory.getInstance();
        AbstractCommonData head = acd.getDataValue("head");
        head.putStringValue("response_code", "000000");
        head.putStringValue("response_desc", "执行成功");
        sendJsonData(out, acd);
    }

    /**
     * 以JSON的形式发送一个数据包
     * @param out
     * @param resData 要发送的数据包
     */
    public static void sendJsonData(JspWriter out, AbstractCommonData resData) {
        if (log.isDebugEnabled()) {
            log.debug("开始记录可分析的日志信息。。");
        }
        if (LogUtil.isDebug()) {
            //记录一下返回的数据
            LogUtil.analysisCommanDataLog(resData, "log-ajax-res");
        }
        try {
            out.print(DataConvertFactory.praseJson(resData));
        } catch (IOException e) {
            log.error("JSP输出异常：", e);
        }
    }
}
