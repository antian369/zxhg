package com.lianzt.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;

/**
 * 提供所有bean类的公共方法。
 * @author lianzt
 * @param <T> 子类的类名
 */
public class BaseBean<T> implements Cloneable, Serializable {

    private static final long serialVersionUID = -1;
    private static final Logger log = Logger.getLogger(BaseBean.class);

    /**
     * 克隆对象
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public T clone() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            log.error("clone error : " + e);
            return null;
        }
    }

    private String toString(int recursive) {
        if (++recursive > 2) {
            return "";
        }

        StringBuilder str = new StringBuilder();
        str.append(this.getClass().getName()).append(" { ");
        Method[] ms = this.getClass().getMethods();
        Object tar = null;
        String tab = "";
        for (int j = 1; j < recursive; j++) {
            tab += "\t";
        }
        for (int i = 0; i < ms.length; i++) {
            if (ms[i].getParameterTypes().length == 0
                && ms[i].getName().startsWith("get")
                && !ms[i].getReturnType().getName().equals("java.util.Set")) {
                try {
                    tar = ms[i].invoke(this);
                    if (tar instanceof BaseBean) {
                        str.append("\n\t").append(tab).append(ms[i].getName()).append(" = ").append(((BaseBean) tar).toString(recursive)).append(" ");
                    } else {
                        str.append("\n\t").append(tab).append(ms[i].getName()).append(" = ").append(tar.toString()).append(" ");
                    }
                } catch (Exception e) {
                }
            }
        }
        str.append("\n").append(tab).append("}");
        return str.toString();
    }

    /**
     * 借助java反射功能，输出类中成员变量，但是不输出Set集合。<br />注：如果成员变量中还有BaseBean类型时只向下递归输出两层。
     */
    @Override
    public String toString() {
        return toString(0);
    }

    @Override
    public int hashCode() {
        return -1;
    }
}
