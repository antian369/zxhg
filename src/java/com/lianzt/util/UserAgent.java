/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.util;

/**
 * user-agent处理工具
 * @author lianzt
 */
public class UserAgent {

    public static String[] phoneAgent = new String[]{"iPhone", "iPad", "Android",
                                                     "Windows Phone OS", "UCWEB"};

    /**
     * 判断是否是手机/平板，只能判断android和ios
     * @param userAgent
     * @return 手机操作系统，否则为null
     */
    public static String isPhone(String userAgent) {
        if (StringUtil.isNull(userAgent)) {
            return null;
        }
        for (String phone : phoneAgent) {
            if (userAgent.indexOf(phone) != -1) {
                return phone;
            }
        }
        return null;
    }
}
