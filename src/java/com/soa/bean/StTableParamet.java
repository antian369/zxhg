package com.soa.bean;

import com.lianzt.bean.BaseBean;
import com.lianzt.bean.BaseBean;

public class StTableParamet extends BaseBean<StTableParamet> {

    private static final long serialVersionUID = -1;
    private String tableName;
    private String colName;
    private String colValue;
    private String colDesc;
    private String valueDesc;

    public StTableParamet() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColValue() {
        return colValue;
    }

    public void setColValue(String colValue) {
        this.colValue = colValue;
    }

    public String getColDesc() {
        return colDesc;
    }

    public void setColDesc(String colDesc) {
        this.colDesc = colDesc;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }
}