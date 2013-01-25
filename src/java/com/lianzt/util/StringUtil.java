/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 字符串工具
 * 2011-11-18
 * @author lianzt
 */
public class StringUtil {

    private static final Logger logger = LogManager.getLogger(StringUtil.class);

    /**
     * 字符串为 null或"" 时返回true
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null");
    }

    /**
     * 字符串不为空时返回true
     * @param str
     * @return
     */
    public static boolean notNull(String str){
        return !isNull(str);
    }

    /**
     * 转换上传文件的文件名 以当前时间命名
     *
     * @param name
     * @return
     */
    public static String fileName(String name) {
        String fileName = new Date().getTime()
                          + name.substring(name.lastIndexOf("\\.") + 1);
        logger.debug("上文件名为'" + name + "' 重命名后为 '" + fileName + "'");
        return fileName;
    }

    /**
     * 转换上传文件的文件名，以 前缀 + 当前时间 命名
     *
     * @param name
     * @param arg 前缀
     * @return
     */
    public static String fileName(String name, Object arg) {
        logger.debug("文件名增加前缀： " + arg);
        return arg + fileName(name);
    }

    /**
     * 转换上传文件的文件名，以 前缀 + 当前时间 命名
     *
     * @param arg 前缀
     * @return
     */
    public static String fileName(Object arg) {
        logger.debug("文件名增加前缀： " + arg);
        return "" + arg + new Date().getTime() + ".jpg";
    }

    /**
     * 转换字符串，如果字符串的长度大于num的值，返回前num-1位和一个省略号
     *
     * @param str 要转换的字符串
     * @param num 位数
     * @return
     */
    public static String stringNum(String str, int num) {
        if (!isNull(str)) {
            if (str.length() <= num) {
                return str;
            }
            if (str == null || num < 1) {
                return "";
            }
            return str.substring(0, num - 1) + "...";
        } else {
            return "";
        }
    }

    /**
     * 用指定字符连接数组
     *
     * @param arr 要连接的数组
     * @param ch 分割的字符
     * @return
     */
    public static String connectArray(Object[] arr, String ch) {
        if (arr == null) {
            return null;
        }
        String str = "";
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null || arr[i].equals("")) {
                j++;
            } else {
                if (i != j) {
                    str = str + ch;
                }
                str = str + arr[i];
            }
        }
        return str;
    }

    /**
     * 用指定字符连接数组，并用另一字符包裹数组项
     *
     * @param arr
     * @param ch
     * @param s
     * @return
     */
    public static String connectArray(Object[] arr, String ch, String s) {
        if (arr == null) {
            return null;
        }
        String str = "";
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null || arr[i].equals("")) {
                j++;
            } else {
                if (i != j) {
                    str = str + ch;
                }
                str = str + s + arr[i] + s;
            }
        }
        return str;
    }

    /**
     * 用指定字符连接数组
     *
     * @param list 要连接的list
     * @param ch 分割的字符
     * @return
     */
    public static String connectArray(List list, String ch) {
        if (list == null) {
            return null;
        }
        String str = "";
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null || list.get(i).equals("")) {
                j++;
            } else {
                if (i != j) {
                    str = str + ch;
                }
                str = str + list.get(i);
            }
        }
        return str;
    }

    /**
     * 用指定字符连接数组，并用另一字符包裹数组项
     *
     * @param list
     * @param ch
     * @param s
     * @return
     */
    public static String connectArray(List list, String ch, String s) {
        if (list == null) {
            return null;
        }
        String str = "";
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null || list.get(i).equals("")) {
                j++;
            } else {
                if (i != j) {
                    str = str + ch;
                }
                str = str + s + list.get(i) + s;
            }
        }
        return str;
    }

    /**
     * 转换空字符串
     *
     * @param sou
     * @param str
     * @return
     */
    public static String changeNull(String sou, String str) {
        return sou == null || sou.equals("") || sou.equals("null") ? str : sou;
    }

    /**
     * 转换NULL为""
     *
     * @param sou
     * @return
     */
    public static String changeNull(String sou) {
        return sou == null || sou.equals("null") ? "" : sou;
    }

    /**
     * 把字符串转换为html代码
     *
     * @param str
     * @return
     */
    public static String replaceHtml(String str) {
        try {
            str = str.trim();
            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll(" ", "&nbps;");
            str = str.replace("'", "&#39;");
            str = str.replaceAll("\"", "&quot;");
            str = str.replace("\n", "<br />");
            return str;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * 把<br />
     * 转换为换行，用于在文本域中显示
     *
     * @param str
     * @return
     */
    public static String replaceBr(String str) {
        try {
            str = str.replaceAll("<br />", "\n");
            return str;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * MD5 加密
     * @return 转换后的字符串
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException caught!", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException : ", e);
        }

        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    /**
     * 生成异常的堆栈信息
     * @param e
     * @return
     */
    public static String getExceptionStack(Exception e) {
        if (e == null) {
            return "";
        }
        StackTraceElement[] trace = e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString()).append('\n');
        for (int i = 0; i < trace.length; i++) {
            sb.append("\tat ").append(trace[i]).append('\n');
        }
        if (e.getCause() != null) {
            appStackTraceAsCause(sb, trace, e.getCause());
        }
        return sb.toString();
    }

    /**
     * Caused by 信息
     * @param s
     * @param causedTrace
     * @param t
     */
    private static void appStackTraceAsCause(StringBuilder s,
                                             StackTraceElement[] causedTrace,
                                             Throwable t) {
        StackTraceElement[] trace = t.getStackTrace();
        int m = trace.length - 1, n = causedTrace.length - 1;
        while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
            m--;
            n--;
        }
        int framesInCommon = trace.length - 1 - m;

        s.append("Caused by: ").append(t).append('\n');
        for (int i = 0; i <= m; i++) {
            s.append("\tat ").append(trace[i]).append('\n');
        }
        if (framesInCommon != 0) {
            s.append("\t... ").append(framesInCommon).append(" more").append('\n');
        }
        if (t.getCause() != null) {
            appStackTraceAsCause(s, trace, t.getCause());
        }
    }

    /**
     * 把堆栈信息转为字符串
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    /**
     * 在左边填充字符串
     * @param obj 要填充的字符串
     * @param length 位数
     * @param ch 使用该字符填充
     * @return
     */
    public static String padLeft(Object obj, int length, Object ch) {
        String str = obj.toString();
        while (str.length() < length) {
            str = ch.toString() + str;
        }
        return str;
    }

    /**
     * 在右边填充字符串
     * @param obj 要填充的字符串
     * @param length 位数
     * @param ch 使用该字符填充
     * @return
     */
    public static String padRigth(Object obj, int length, Object ch) {
        String str = obj.toString();
        while (str.length() < length) {
            str = str + ch.toString();
        }
        return str;
    }

    /**
     * 生成length位数随机数
     * @param length
     * @return
     */
    public static String random(int length) {
        return StringUtil.padLeft((int) (Math.random() * Math.pow(10, length) / 1), length, 0);
    }
}
