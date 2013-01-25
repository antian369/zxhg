/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.commondata.impl;

import com.lianzt.commondata.AbstractCommonData;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据结构对象接口
 * @author lianzt
 */
public interface CommonData<K, T> extends Map<K, T> {

    /**
     * 数组中不区分对象类型，全部认为是object
     * @param name
     * @return
     */
    public List getArrayValue(String name);

    public Boolean getBooleanValue(String name);

    public AbstractCommonData getDataValue(String name);

    public Double getDoubleValue(String name);

    public Float getFloatValue(String name);

    public Integer getIntValue(String name);

    public Long getLongValue(String name);

    public Object getObjectValue(String name);

    public String getStringValue(String name);

    public Date getDateValue(String name);

    /**
     * 增加一个数组值，数组中不区分对象类型，全部认为是object
     * @param name
     * @param value
     */
    public void putArrayValue(String name, List value);

    public void putBooleanValue(String name, boolean value);

    /**
     * 增加一个数据结构值
     * @param name
     * @param value
     */
    public void putDataValue(String name, AbstractCommonData value);

    public void putDoubleValue(String name, double value);

    public void putFloatValue(String name, float value);

    /**
     * 增加一个int值
     * @param name
     * @param value
     */
    public void putIntValue(String name, int value);

    public void putLongValue(String name, long value);

    /**
     * 增加一个Object值
     * 会自动填充类路径
     * @param name
     * @param value
     */
    public void putObjectValue(String name, Object value);

    public void putStringValue(String name, String value);

    /**
     * 增加一个带长度的String值
     * 如果length小于value.length()，截取前length位
     * @param name
     * @param value
     * @param length
     */
    public void putStringValue(String name, String value, int length);

    public void putDateValue(String name, Date value);
}
