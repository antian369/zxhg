/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.soa.interceptor;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.controller.SystemController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * sprint MVC 拦截器，把ajax请示转发到/ajax.do控制器，而不管之前的地址是什么<br />必须放在拦截器栈的最后一位
 * @author lianzt
 */
public class ForwardAjaxInterceptor implements HandlerInterceptor {

    private static final Logger log = Logger.getLogger(ForwardAjaxInterceptor.class);
    private SystemController systemController;

    /**
     * 拦截ajax请求
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
        AbstractCommonData req = (AbstractCommonData) request.getAttribute("page_data");
        AbstractCommonData reqHead = req.getDataValue("head");
        //把ajax相关的一些参数转到head中，并把报文体中的参数删除
        reqHead.putStringValue("_type", req.getStringValue("_type"));
        reqHead.putStringValue("_target_url", req.getStringValue("_target_url"));
        reqHead.putStringValue("_begin_url", req.getStringValue("_begin_url"));
        reqHead.putStringValue("service_code", req.getStringValue("service_code"));
        req.remove("_type");
        req.remove("_target_url");
        req.remove("_begin_url");
        req.remove("service_code");
        //把commondata重新放回request
        request.setAttribute("page_data", req);
        if ("ajax".equals(reqHead.getStringValue("_type"))) {
            if (log.isInfoEnabled()) {
                log.info("拦截到ajax请求。");
            }
            if (log.isDebugEnabled()) {
                log.debug("调用ajax处理的控制器...");
            }
            //手动调用ajax处理的控制器
            systemController.ajaxServicePost(request, response.getWriter());
            //不再执行原本指定的控制器
            return false;
        } else {
            return true;
        }
    }

    /**
     * 在控制器执行结束后进行拦截，在拦截器链中的执行顺序与preHandle函数相反<br />
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
    }

    /**
     * 出现异常时执行，在拦截器链中的执行顺序与preHandle相反<br />
     * 一般用户释放资源，也可以在方法中处理异常信息
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
    }

    public SystemController getSystemController() {
        return systemController;
    }

    public void setSystemController(SystemController systemController) {
        this.systemController = systemController;
    }
}
