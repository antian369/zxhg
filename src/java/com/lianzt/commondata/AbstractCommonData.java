/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.commondata;

import com.lianzt.commondata.impl.CommonData;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 数据包类的顶层父类
 * @author lianzt
 */
public abstract class AbstractCommonData extends LinkedHashMap<String, CommonDataElement>
        implements Cloneable, Serializable,
                   CommonData<String, CommonDataElement> {

    private static final long serialVersionUID = -1L;

    @Override
    public String toString() {
        return toString(1);
    }

    /**
     * 重写put函数，使value中的name与key一致
     * @param key
     * @param value
     * @return
     */
    @Override
    public CommonDataElement put(String key, CommonDataElement value) {
        if (value != null) {
            value.setName(key);
            return super.put(key, value);
        } else {
            putStringValue(key, null);
            return get(key);
        }
    }

    /**
     * 用于格式化输出
     * @param i 递归层数
     * @return
     */
    protected String toString(int i) {
        Iterator<Entry<String, CommonDataElement>> entry = entrySet().iterator();
        if (!entry.hasNext()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (;;) {
            for (int j = 0; j < i; j++) {
                sb.append("\t");
            }
            Entry<String, CommonDataElement> e = entry.next();
            String key = e.getKey();
            String value = e.getValue().toString(i + 1);
            sb.append(key);
            sb.append('=');
            sb.append(value);
            if (!entry.hasNext()) {
                sb.append("\n");
                for (int j = 1; j < i; j++) {
                    sb.append("\t");
                }
                return sb.append("}").toString();
            }
            sb.append("\n");
        }
    }
}
