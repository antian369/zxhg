/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.commondata;

import com.lianzt.exception.InstanceDataException;
import com.lianzt.util.DateUtil;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 * 一个通用的数据包对象，可通过jms、xml等方式传输<br />
 * 通过xml传输时，如果数据包中数组元素中存储有基本数据类型的变量(int、long、boolean等)时，会被转为String类型；xml传输不支持Object类型。<br />
 * 如果需要传输Object类型的元素，目前只能使用jms方式。<br />
 * 如果value类型为string，当使用getIntValue等函数获取时，可进行字符串到目标类型的转换，其他类型不能转换。<br />
 * 其它自定义实现的CommonData必须加上 private static final long serialVersionUID = -1L;
 * @author lianzt
 */
public class DefualtCommonData extends AbstractCommonData {

    private static final long serialVersionUID = -1;
    private static final Logger log = Logger.getLogger(DefualtCommonData.class);

    protected DefualtCommonData() {
        super();
    }

    /**
     * 增加一个int值
     * @param name
     * @param value
     */
    @Override
    public void putIntValue(String name, int value) {
        put(name, new CommonDataElement(name, CommonDataElement.INT, value));
    }

    /**
     * 获取一个int值
     * @param name key值
     * @return key对应的value，如果value是字符串类型会自动转为int，如果不存在，返回null。
     */
    @Override
    public Integer getIntValue(String name) {
        CommonDataElement mde = this.get(name);
        if (mde == null || mde.getValue() == null) {
            return null;
        } else {
            if (mde.getType().equals(CommonDataElement.STRING)) {
                return Integer.parseInt(mde.getValue().toString());
            } else {
                return (Integer) mde.getValue();
            }
        }
    }

    @Override
    public void putLongValue(String name, long value) {
        put(name, new CommonDataElement(name, CommonDataElement.LONG, value));
    }

    /**
     * 获取一个long值
     * @param name key值
     * @return key对应的value，如果value是字符串类型会自动转为long，如果不存在，返回null。
     */
    @Override
    public Long getLongValue(String name) {
        CommonDataElement mde = this.get(name);
        if (mde == null || mde.getValue() == null) {
            return null;
        } else {
            if (mde.getType().equals(CommonDataElement.STRING)) {
                return Long.parseLong(mde.getValue().toString());
            } else {
                return (Long) mde.getValue();
            }
        }
    }

    @Override
    public void putStringValue(String name, String value) {
        put(name, new CommonDataElement(name, CommonDataElement.STRING, value));
    }

    /**
     * 增加一个带长度的String值
     * 如果length小于value.length()，截取前length位
     * @param name
     * @param value
     * @param length
     */
    @Override
    public void putStringValue(String name, String value, int length) {
        put(name, new CommonDataElement(name, CommonDataElement.STRING,
                                        value.subSequence(0, value.length() > length ? length : value.length()), length));
    }

    @Override
    public String getStringValue(String name) {
        CommonDataElement mde = this.get(name);
        if (mde == null || mde.getValue() == null) {
            return null;
        } else {
            if (CommonDataElement.DATE.equals(mde.getType())) {
                return DateUtil.detaledFormat((Date) mde.getValue());
            } else {
                return mde.getValue().toString();
            }
        }
    }

    @Override
    public void putDoubleValue(String name, double value) {
        put(name, new CommonDataElement(name, CommonDataElement.DOUBLE, value));
    }

    /**
     * 获取一个Double值
     * @param name key值
     * @return key对应的value，如果value是字符串类型会自动转为Double，如果不存在，返回null。
     */
    @Override
    public Double getDoubleValue(String name) {
        CommonDataElement mde = this.get(name);
        if (mde == null || mde.getValue() == null) {
            return null;
        } else {
            if (mde.getType().equals(CommonDataElement.STRING)) {
                return Double.parseDouble(mde.getValue().toString());
            } else {
                return (Double) mde.getValue();
            }
        }
    }

    @Override
    public void putBooleanValue(String name, boolean value) {
        put(name, new CommonDataElement(name, CommonDataElement.BOOLEAN, value));
    }

    /**
     * 获取一个Boolean值
     * @param name key值
     * @return key对应的value，如果value是字符串类型会自动转为Boolean，如果不存在，返回null。
     */
    @Override
    public Boolean getBooleanValue(String name) {
        CommonDataElement mde = this.get(name);
        if (mde == null || mde.getValue() == null) {
            return null;
        } else {
            if (mde.getType().equals(CommonDataElement.STRING)) {
                return Boolean.parseBoolean(mde.getValue().toString());
            } else {
                return (Boolean) mde.getValue();
            }
        }
    }

    @Override
    public void putFloatValue(String name, float value) {
        put(name, new CommonDataElement(name, CommonDataElement.FLOAT, value));
    }

    /**
     * 获取一个Float值
     * @param name key值
     * @return key对应的value，如果value是字符串类型会自动转为Float，如果不存在，返回null。
     */
    @Override
    public Float getFloatValue(String name) {
        CommonDataElement mde = this.get(name);
        if (mde == null || mde.getValue() == null) {
            return null;
        } else {
            if (mde.getType().equals(CommonDataElement.STRING)) {
                return Float.parseFloat(mde.getValue().toString());
            } else {
                return (Float) mde.getValue();
            }
        }
    }

    /**
     * 增加一个数组值
     * @param name
     * @param value
     */
    @Override
    public void putArrayValue(String name, List value) {
        if (value == null) {
            put(name, new CommonDataElement(name, CommonDataElement.ARRAY, value, 0));
        } else {
            put(name, new CommonDataElement(name, CommonDataElement.ARRAY, value, value.size()));
        }
    }

    @Override
    public List getArrayValue(String name) {
        CommonDataElement mde = this.get(name);
        if (mde == null || mde.getValue() == null) {
            return null;
        } else {
            return (List) mde.getValue();
        }
    }

    /**
     * 增加一个数据包对象值
     * @param name
     * @param value
     */
    @Override
    public void putDataValue(String name, AbstractCommonData value) {
        put(name, new CommonDataElement(name, CommonDataElement.DATA, value));
    }

    @Override
    public AbstractCommonData getDataValue(String name) {
        CommonDataElement mde = this.get(name);
        if (mde == null || mde.getValue() == null) {
            return null;
        } else {
            return (DefualtCommonData) mde.getValue();
        }
    }

    /**
     * 增加一个Object值
     * 会自动填充类路径
     * @param name
     * @param value
     */
    @Override
    public void putObjectValue(String name, Object value) {
        if (value == null) {
            put(name, new CommonDataElement(name, CommonDataElement.OBJECT, null));
        } else {
            put(name, new CommonDataElement(name, CommonDataElement.OBJECT, value, value.getClass().getName()));
        }
    }

    @Override
    public Object getObjectValue(String name) {
        CommonDataElement mde = this.get(name);
        if (mde == null || mde.getValue() == null) {
            return null;
        } else {
            return mde.getValue();
        }
    }

    /**
     * 获取一个Date值
     * @param name key值
     * @return key对应的value，如果value是字符串类型会按照yyyy-MM-dd HH:mm:ss格式自动转为Date，如果不存在，返回null。
     */
    @Override
    public Date getDateValue(String name) {
        CommonDataElement mde = this.get(name);
        if (mde == null || mde.getValue() == null) {
            return null;
        } else {
            if (mde.getType().equals(CommonDataElement.STRING)) {
                try {
                    switch (mde.getValue().toString().length()) {
                        case 10:
                            return DateUtil.formatDate(mde.getValue().toString(), "yyyy-MM-dd");
                        case 8:
                            return DateUtil.formatDate(mde.getValue().toString(), "yyyyMMdd");
                        case 14:
                            return DateUtil.formatDate(mde.getValue().toString(), "yyyyMMddHHmmss");
                        case 19:
                            return DateUtil.formatDate(mde.getValue().toString(), "yyyy-MM-dd HH:mm:ss");
                        default:
                            throw new RuntimeException("提取date类型元素 " + mde.getName() + " 时，出现异常");
                    }
                } catch (InstanceDataException ex) {
                    throw new RuntimeException("提取date类型元素 " + mde.getName() + " 时，出现异常");
                }
            } else {
                return (Date) mde.getValue();
            }
        }
    }

    @Override
    public void putDateValue(String name, Date value) {
        put(name, new CommonDataElement(name, CommonDataElement.DATE, value));
    }
}
