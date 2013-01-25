/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.exception;

/**
 * 构建数据包异常
 * @author lianzt
 */
public class InstanceDataException extends Exception {

    /**
     * 构建数据包异常，默认的异常信息：构建数据包出现异常！
     */
    public InstanceDataException() {
        super("构建数据包出现异常！");
    }

    /**
     * 构建数据包异常，可自定义异常信息
     * @param message 自定义异常
     */
    public InstanceDataException(String message) {
        super("构建数据包出现异常 : " + message);
    }

    /**
     * 构建数据包异常，可自定义异常信息
     * @param message 自定义异常
     * @param t 异常堆栈
     */
    public InstanceDataException(String message, Throwable t) {
        super("构建数据包出现异常 : " + message, t);
    }
}
