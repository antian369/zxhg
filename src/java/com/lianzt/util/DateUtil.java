/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.util;

import com.lianzt.exception.InstanceDataException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 	日期:	2010-2-26
 * 	说明:	日期工具
 * 	@author 练作泰
 */
public class DateUtil {

    private static final String DEFUALT_FORMAT = "yyyy-MM-dd";
    private static final String DETAILED_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String LSH_FORMAT = "yyyyMMdd";
    private static final String DETAILED_LSH_FORMAT = "yyyyMMddHHmmss";
    private static final String SIMPLE_FORMAT = "yyMMdd";
    private static final String NS_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    private static final SimpleDateFormat defualtFormat = new SimpleDateFormat(DEFUALT_FORMAT);
    private static final SimpleDateFormat detaledFormat = new SimpleDateFormat(DETAILED_FORMAT);
    private static final SimpleDateFormat lshFormat = new SimpleDateFormat(LSH_FORMAT);
    private static final SimpleDateFormat detaledLshFormat = new SimpleDateFormat(DETAILED_LSH_FORMAT);
    private static final SimpleDateFormat simpleFormat = new SimpleDateFormat(SIMPLE_FORMAT);
    private static final SimpleDateFormat nsFormat = new SimpleDateFormat(NS_FORMAT);

    /**
     * 返回默认的日期格式： yyyy-MM-dd
     * @param date
     * @return
     */
    public static String defualtFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return defualtFormat.format(date);
        }
    }

    /**
     * 返回日期详细格式：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String detaledFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return detaledFormat.format(date);
        }
    }

    /**
     * 返回默认的流水号中使用的日期格式：yyyyMMdd
     * @param date
     * @return
     */
    public static String lshFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return lshFormat.format(date);
        }
    }

    /**
     * 返回简单的日期格式：yyMMdd
     * @param date
     * @return
     */
    public static String simpleFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return simpleFormat.format(date);
        }
    }

    /**
     * 返回详细的流水号中使用的日期格式： yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static String detaledLshFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return detaledLshFormat.format(date);
        }
    }

    /**
     * 返回包含纳秒的日期格式： yyyy-MM-dd HH:mm:ss.S
     * @param date
     * @return
     */
    public static String nsFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return nsFormat.format(date);
        }
    }

    /**
     * 为日期增加年数
     * @param date 日期
     * @param year 年数，可以为负
     * @return
     */
    public static Date addYear(Date date, int year) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 为日期增加天数
     * @param date 日期
     * @param day 天数，可以为负
     * @return
     */
    public static Date addDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 为日期增加月数
     * @param date 日期
     * @param month 月，可以为负
     * @return
     */
    public static Date addMonth(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 为日期增加小时数
     * @param date 日期
     * @param hour 小时，可以为负
     * @return
     */
    public static Date addHour(Date date, int hour) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 为日期增加分钟数
     * @param date 日期
     * @param minute 分钟，可以为负
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 为日期增加秒数
     * @param date 日期
     * @param second 钞，可以为负
     * @return
     */
    public static Date addSecond(Date date, int second) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 设置秒
     * @param date 时间
     * @param second 秒
     * @return
     */
    public static Date setDay(Date date, int day) {
        if (day < 0) {
            day = 0;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 设置小时
     * @param date 时间
     * @param hour 小时
     * @return
     */
    public static Date setHour(Date date, int hour) {
        if (hour < 0) {
            hour = 0;
        } else if (hour > 23) {
            hour = 23;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 设置分钟
     * @param date 时间
     * @param minute 分钟
     * @return
     */
    public static Date setMinute(Date date, int minute) {
        if (minute < 0) {
            minute = 0;
        } else if (minute > 59) {
            minute = 59;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 设置秒
     * @param date 时间
     * @param second 秒
     * @return
     */
    public static Date setSecond(Date date, int second) {
        if (second < 0) {
            second = 0;
        } else if (second > 59) {
            second = 59;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 把 yyyy-MM-dd HH:mm:ss 的字符串转为日期
     * @param str
     * @return
     * @throws InstanceDataException
     */
    public static Date parseDate(String str) throws InstanceDataException {
        if (StringUtil.isNull(str)) {
            return null;
        }
        try {
            return detaledFormat.parse(str);
        } catch (ParseException ex) {
            throw new InstanceDataException("字符串： " + str + "  转换日期出现异常： " + ex.toString());
        }
    }

    /**
     * 把 yyyy-MM-dd HH:mm:ss.S 的字符串转为日期
     * @param str
     * @return
     * @throws InstanceDataException
     */
    public static Date nsDate(String str) throws InstanceDataException {
        if (StringUtil.isNull(str)) {
            return null;
        }
        try {
            return nsFormat.parse(str);
        } catch (ParseException ex) {
            throw new InstanceDataException("字符串： " + str + "  转换日期出现异常： " + ex.toString());
        }
    }

    /**
     * 把 dateStr 日志按照 formatStr 格式转为date
     * @param dateStr 日期字符串
     * @param formatStr 日期格式
     * @return
     * @throws InstanceDataException
     */
    public static Date formatDate(String dateStr, String formatStr) throws
            InstanceDataException {
        if (StringUtil.isNull(dateStr)) {
            return null;
        }
        if (StringUtil.isNull(formatStr)) {
            return null;
        }
        try {
            return new SimpleDateFormat(formatStr).parse(dateStr);
        } catch (ParseException ex) {
            throw new InstanceDataException("字符串： " + formatStr + " 按照 '" + formatStr + "' 格式转换日期出现异常： " + ex.toString());
        }
    }
}