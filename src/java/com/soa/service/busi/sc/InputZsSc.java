/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 生产综述录入
 * P13007
 * @author lianzt
 */
@Service
public class InputZsSc extends BaseService {

    private static final String[] KEY = new String[]{"scrq", "生产日期",
                                                     "rdzs", "热电综述",
                                                     "qhzs", "气化综述",
                                                     "jczs", "甲醇综述"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        Date scrq = in.getDateValue("scrq");
        AbstractCommonData xs = queryData("get_zs_sc", scrq);
        AbstractCommonData ses = getSession(in);
        Object[] args = new Object[7];
        if (xs == null || xs.isEmpty()) {
            //插入
            args[0] = scrq;
            args[1] = in.getStringValue("rdzs");
            args[2] = in.getStringValue("qhzs");
            args[3] = in.getStringValue("jczs");
            args[4] = getLoginUser(in);
            args[5] = ses.getStringValue("name");
            args[6] = in.getStringValue("bz");
            update("save_zs_sc", args);
        } else {
            //更新
            args[0] = in.getStringValue("rdzs");
            args[1] = in.getStringValue("qhzs");
            args[2] = in.getStringValue("jczs");
            args[3] = getLoginUser(in);
            args[4] = ses.getStringValue("name");
            args[5] = in.getStringValue("bz");
            args[6] = scrq;
            update("update_zs_sc", args);
        }
    }
}
