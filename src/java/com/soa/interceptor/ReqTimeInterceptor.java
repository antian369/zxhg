/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.soa.interceptor;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.DateUtil;
import com.lianzt.util.LogUtil;
import com.lianzt.util.StringUtil;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * sprint MVC 拦截器，用于计算一个请求所使用的时间
 * @author lianzt
 */
public class ReqTimeInterceptor implements HandlerInterceptor {

    private static final Logger log = Logger.getLogger(ReqTimeInterceptor.class);
    /**
     * 请求的超时预警时间，默认为10秒；<br />如果一个请求的处理时间超过这个期限，将被警报。<br />后期可能会将预警时间细化到一个请求一个时间，单位 ms
     */
    public static int warnTime = 10000;

    /**
     * 拦截器的起始函数，把当前日期放入request对象
     * @param request
     * @param response
     * @param o 拦截器链的下一个节点，可能是拦截器，也可能是控制器
     * @return 如果返回false，请求会在当前拦截器停止，并从当前拦截器开始执行afterCompletion函数
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object o) throws Exception {
        Date beginDate = new Date();
        if (log.isDebugEnabled()) {
            log.debug("把请求的开始时间( " + DateUtil.nsFormat(beginDate) + " )放入request对象。");
        }
        request.setAttribute("_beginTime", beginDate);
        return true;
    }

    /**
     * 在控制器执行结束后进行拦截，在拦截器链中的执行顺序与preHandle函数相反<br />
     * 把目标视图的名称放入request
     * @param request
     * @param response
     * @param o 拦截器的下一个节点
     * @param mav 控制器返回的视图
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object o, ModelAndView mav) throws Exception {
        //在日志级别为info是才进行时间记录
        if (log.isInfoEnabled()) {
            AbstractCommonData acd = (AbstractCommonData) request.getAttribute("page_data");
            if (acd == null) {
                if (log.isDebugEnabled()) {
                    log.debug("请求参数的CommonData为空！");
                }
                acd = DataConvertFactory.getInstanceEmpty();
                acd.putStringValue("_url", request.getRequestURI());
                acd.putStringValue("_ip", request.getRemoteAddr());
                //请求中的参数
                Enumeration<String> en = request.getParameterNames();
                String[] valueArr = null;
                String name = "";
                String value = "";
                while (en.hasMoreElements()) {
                    name = en.nextElement();
                    valueArr = request.getParameterValues(name);
                    value = StringUtil.connectArray(valueArr, ",");
                    request.setAttribute(name, value);
                    acd.putStringValue(name, value);
                }
            }
            //目标视图
            if (mav == null) {
                acd.putStringValue("_targetView", "null");
            } else {
                acd.putStringValue("_targetView", mav.getViewName());
            }
            request.setAttribute("page_data", acd);
        }
    }

    /**
     * 出现异常时执行，在拦截器链中的执行顺序与preHandle相反<br />
     * 一般用户释放资源，也可以在方法中处理异常信息<br />
     * 从request中获取请求的开始时间，计算出请求的执行时间
     * @param request
     * @param response
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object o, Exception e) throws Exception {
        Date endDate = new Date();
        if (log.isDebugEnabled()) {
            log.debug("开始计算请求处理时间。。");
        }
        Date beginDate = (Date) request.getAttribute("_beginTime");
        if (beginDate == null) {
            log.warn("开始日期为空！");
            return;
        }
        if (log.isDebugEnabled()) {
            if (e != null) {
                log.debug("ReqTime after : " + e);
            }
        }
        AbstractCommonData acd = (AbstractCommonData) request.getAttribute("page_data");
        //处理请求使用的时间
        long runTime = endDate.getTime() - beginDate.getTime();
        acd.getDataValue("head").putLongValue("_runTime", runTime);
        if (runTime > warnTime) {
            log.warn("请求的处理时间过长（ " + runTime + " ms ），超过设定的预警时间。");
            //超时的处理
            timeoutOpt(acd);
        }
        if (log.isDebugEnabled()) {
            log.debug("开始记录可分析的日志信息。。");
        }
        LogUtil.analysisCommanDataLog(acd, "request-time");
        if (log.isInfoEnabled()) {
            log.info("\n---------------------- 请求结束 --------------------------");
        }
    }

    /**
     * 超时处理
     * @param in 包含所有请求参数的CommonData
     */
    public static void timeoutOpt(AbstractCommonData in) {
    }
}
