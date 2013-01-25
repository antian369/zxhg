/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.frame;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 检验验证码，验证成功后会将值从session中删除，所以一个验证码只生效一次<br />
 * service_code : S10004<br />
 * 请求数据： check_num(String)
 * 响应数据： 无
 * @author lianzt
 */
@Service
public class CheckNumServiceImpl extends BaseService {

    private static final Logger log = Logger.getLogger(CheckNumServiceImpl.class);
    private static final String[] KEYS = new String[]{"check_num", "验证码"};

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData session = getSession(in);
        if (log.isDebugEnabled()) {
            log.debug("验证码：" + session.getStringValue("_checkNum"));
        }
        if (!in.getStringValue("check_num").equals(session.getStringValue("_checkNum"))) {
            throw new GlobalException(200001, "验证码");        //!#!错误
        } else {
            session.remove("_checkNum");
        }
    }

    @Override
    public String[] keys() {
        return KEYS;
    }
}
