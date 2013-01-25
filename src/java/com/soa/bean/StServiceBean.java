package com.soa.bean;

import com.lianzt.bean.BaseBean;
import com.soa.service.busi.impl.BusiService;

/**
 *
 * @author lianzt
 */
public class StServiceBean extends BaseBean<StServiceBean> {

    private static final long serialVersionUID = -1;
    private String serviceCode;
    private String beanName;
    private String serviceDesc;
    private String isLogin;
    private String module;
    private BusiService service;

    public StServiceBean() {
    }

    public StServiceBean(String serviceCode, String beanName, String serviceDesc,
                         String isLogin, String module, BusiService service) {
        this.serviceCode = serviceCode;
        this.beanName = beanName;
        this.serviceDesc = serviceDesc;
        this.isLogin = isLogin;
        this.module = module;
        this.service = service;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public BusiService getService() {
        return service;
    }

    public void setService(BusiService service) {
        this.service = service;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }
}
