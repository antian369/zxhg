/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.util;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import org.apache.log4j.Logger;

/**
 * 日志工具，该工具用于记录可分析的日志。<br />在log4j.properties中强制设置日志级别为INFO
 * @author lianzt
 */
public class LogUtil {

    private static final Logger log = Logger.getLogger(LogUtil.class);

    /**
     * 输出info信息
     * @param obj
     */
    public static void info(Object obj) {
        log.info(obj);
    }

    /**
     * 输出info信息
     * @param obj
     * @param t
     */
    public static void info(Object obj, Throwable t) {
        log.info(obj, t);
    }

    /**
     * 判断LogUtil类的日志级别是否为debug，
     * @return
     */
    public static boolean isDebug() {
        return log.isDebugEnabled();
    }

    /**
     * 判断LogUtil类的日志级别是否为info，
     * @return
     */
    public static boolean isInfo() {
        return log.isInfoEnabled();
    }

    /**
     * 输出debug信息
     * @param obj
     */
    public static void debug(Object o) {
        log.debug(o);
    }

    /**
     * 输出debug信息
     * @param o
     * @param t
     */
    public static void debug(Object o, Throwable t) {
        log.debug(o, t);
    }

    /**
     * 记录可分析的日志
     * @param in 请求报文
     */
    public static void analysisLog(AbstractCommonData in) {
        if (in == null) {
            return;
        }
        log.info("分析日志\n['log']\t" + DataConvertFactory.praseJson(in));
    }

    /**
     * 记录可分析的日志
     * @param in 请求报文
     * @param runTime 运行时间
     */
    public static void analysisLog(AbstractCommonData in, long runTime) {
        if (in == null) {
            return;
        }
        in.putLongValue("_run_time", runTime);
        log.info("分析日志\n['log']\t" + DataConvertFactory.praseJson(in));
        in.remove("_run_time");
    }

    /**
     * 把一个CommanData做为一个可分析日志记录，使用json方式记录
     * @param in
     * @param prefix 前缀
     */
    public static void analysisCommanDataLog(AbstractCommonData in,
                                             String prefix) {
        if (in != null && !in.isEmpty()) {
            log.info("分析日志\n['log-" + prefix + "']\t" + DataConvertFactory.praseJson(in));
        }
    }

    /**
     * 输出一行json日志，用户没有commondata，又需要输出可分析日志的情况
     * @param json 数据
     * @param prefix 前缀
     */
    public static void analysisJsonLog(String json, String prefix) {
        log.info("分析日志\n['log-" + prefix + "']\t" + json);
    }
}
