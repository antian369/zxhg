/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.commondata;

import com.lianzt.util.DateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 通用数据包中的数据元素
 * @author lianzt
 */
public class CommonDataElement implements Serializable {

    private static final long serialVersionUID = -1L;
    public static final String INT = "int";
    public static final String LONG = "long";
    public static final String STRING = "string";
    public static final String DOUBLE = "double";
    public static final String FLOAT = "float";
    public static final String BOOLEAN = "boolean";
    public static final String ARRAY = "array";
    public static final String DATA = "data";
    public static final String OBJECT = "object";
    public static final String DATE = "date";
    private String name;
    private String type;
    private Object value;
    private String className;
    private Integer length;

    /**
     * 通用数据包中的数据元素
     * @param name 元素名
     * @param type 数据类型(建议使用声明中的类型)
     * @param value 元素值
     */
    public CommonDataElement(String name, String type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    /**
     * 通用数据包中的数据元素
     * @param name 元素名
     * @param type 数据类型(建议使用声明中的类型)
     * @param value 元素值
     * @param length 字符串长度(数据类型为String时有效)
     */
    public CommonDataElement(String name, String type, Object value,
                             Integer length) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.length = length;
    }

    /**
     * 通用数据包中的数据元素
     * @param name 元素名
     * @param type 数据类型(建议使用声明中的类型)
     * @param value 元素值
     * @param className Object的反射字符串(数据类型为Object时有效)
     */
    public CommonDataElement(String name, String type, Object value,
                             String className) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.className = className;
    }

    /**
     * 数据类型为String时有效
     * @return 字符串长度
     */
    public Integer getLength() {
        return length;
    }

    /**
     * 数据类型为String时有效
     * @param length 字符串长度
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * @return 元素名
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 元素名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 数据类型为Object时有效
     * @return Object的反射字符串
     */
    public String getClassName() {
        return className;
    }

    /**
     * 数据类型为Object时有效
     * @param className Object的反射字符串
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 建议使用声明中的类型
     * @return 数据类型
     */
    public String getType() {
        return type;
    }

    /**
     * 建议使用声明中的类型
     * @param type 数据类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return 元素值
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value 元素值
     */
    public void setValue(Serializable value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[name : ");
        str.append(name);
        str.append(",  type : ");
        str.append(type);
        if (OBJECT.equals(type)) {
            str.append(",  className : ");
            str.append(className);
        }
        str.append(",  length : ");
        str.append(length);
        str.append(",  value : ");
        str.append(value.toString());
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CommonDataElement)) {
            return false;
        }

        CommonDataElement cde = (CommonDataElement) obj;
        if (cde.getName() == null) {
            if (name != null) {
                return false;
            }
        } else {
            if (!cde.getName().equals(name)) {
                return false;
            }
        }

        if (cde.getValue() == null) {
            if (value != null) {
                return false;
            }
        } else {
            if (!cde.getValue().equals(value)) {
                return false;
            }
        }

        if (cde.getType() == null) {
            if (type != null) {
                return false;
            }
        } else {
            if (!cde.getType().equals(type)) {
                return false;
            }
        }

        if (cde.getLength() == null) {
            if (length != null) {
                return false;
            }
        } else {
            if (!cde.getLength().equals(length)) {
                return false;
            }
        }

        if (cde.getClassName() == null) {
            if (className != null) {
                return false;
            }
        } else {
            if (!cde.getClassName().equals(className)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 用于格式化输出
     * @param i 递归层数
     * @return
     */
    protected String toString(int i) {
        StringBuilder str = new StringBuilder();
        str.append("[name : ");
        str.append(name);
        str.append(",  type : ");
        str.append(type);
        if (OBJECT.equals(type)) {
            str.append(",  className : ");
            str.append(className);
        }
        str.append(",  length : ");
        str.append(length);
        str.append(",  value : ");
        if (value == null) {
            str.append("null");
        } else if (DATA.equals(type) || value instanceof AbstractCommonData) {
            str.append(((AbstractCommonData) value).toString(i));
        } else if (ARRAY.equals(type)) {
            str.append("(");
            for (Object obj : (List) value) {
                if (obj instanceof AbstractCommonData) {
                    str.append("\n");
                    for (int j = 0; j < i; j++) {
                        str.append("\t");
                    }
                    str.append(((AbstractCommonData) obj).toString(i + 1));
                } else {
                    str.append("\n");
                    for (int j = 0; j < i; j++) {
                        str.append("\t");
                    }
                    str.append(obj.toString());
                }
            }
            str.append("\n");
            for (int j = 1; j < i; j++) {
                str.append("\t");
            }
            str.append(")");
        } else if (DATE.equals(type)) {
            str.append(DateUtil.detaledFormat((Date) value));
        } else {
            str.append(value.toString());
        }
        str.append("]");
        return str.toString();
    }
}
