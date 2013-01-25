/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.frame;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 控制器中的事务管理<br />把多个服务放到同一个数据库事物中执行<br />
 * service_code : P10001<br />
 * @author lianzt
 */
@Service
public class TranslationServiceImpl extends BaseService {

    private static final String[] KEYS = new String[]{"translation_service",
                                                      "服务码队列"};
    private static final Logger log = Logger.getLogger(TranslationServiceImpl.class);

    @Override
    public String[] keys() {
        return KEYS;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        if (log.isDebugEnabled()) {
            log.debug("需要在同一个事务中执行的服务：" + in.getStringValue("translation_service"));
        }
        String[] sevices = in.getStringValue("translation_service").split(",");
        for (String service : sevices) {
            runService(service, in, inHead, out, outHead);
        }
    }
}
