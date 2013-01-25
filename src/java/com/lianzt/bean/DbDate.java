/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lianzt.bean;

import com.lianzt.util.DateUtil;
import java.util.Date;

/**
 * CommanData结构中的日期类型，主要用于兼容数据库的日期类型
 * @author lianzt
 */
public class DbDate extends Date {

    private static final long serialVersionUID = -1;
    
    public DbDate(Date d) {
        super(d.getTime());
    }

    public DbDate() {
        super();
    }

    /**
     * 返回日期的详细格式，在el表达式中使用this.detaled 或 this.value.detaled 可返回该格式
     * @return
     */
    public String getDetaled() {
        return DateUtil.detaledFormat(this);
    }

    /**
     * 返回日期的详细格式，在el表达式中使用this.simple 或 this.value.simple 可返回该格式
     * @return
     */
    public String getSimple() {
        return DateUtil.simpleFormat(this);
    }

    @Override
    public String toString() {
        return DateUtil.defualtFormat(this);
    }
}
