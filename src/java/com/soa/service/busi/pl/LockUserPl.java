/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 冻结用户
 * P11006
 * @author Asus
 */
@Service
public class LockUserPl extends BaseService {

    private static final String[] KEY = new String[]{"username", "用户名",
                                                     "zt", "状态"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        update("lock_user_pl", in.getStringValue("zt"), in.getStringValue("username"));
    }
}
