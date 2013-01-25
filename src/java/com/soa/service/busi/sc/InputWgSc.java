/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日外售录入
 * P13004
 * @author lianzt
 */
@Service
public class InputWgSc extends BaseService {

    private static final String[] KEY = new String[]{"scrq", "生产日期",
                                                     "jjc", "日进精甲醇",
                                                     "cjc", "日进粗甲醇"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        Date scrq = in.getDateValue("scrq");
        if (new Date().getTime() < scrq.getTime()) {
            throw new GlobalException(200032, "生产日期");       //!#!不能大于当前时间
        }
        AbstractCommonData dh = queryData("get_wg_sc", scrq);
        AbstractCommonData ses = getSession(in);
        Object[] args = new Object[6];
        if (dh == null || dh.isEmpty()) {
            //插入
            args[0] = scrq;
            args[1] = in.getDoubleValue("jjc");
            args[2] = in.getDoubleValue("cjc");
            args[3] = getLoginUser(in);
            args[4] = ses.getStringValue("name");
            args[5] = in.getStringValue("bz");
            update("save_wg_sc", args);
        } else {
            //更新
            args[0] = in.getDoubleValue("jjc");
            args[1] = in.getDoubleValue("cjc");
            args[2] = getLoginUser(in);
            args[3] = ses.getStringValue("name");
            args[4] = in.getStringValue("bz");
            args[5] = scrq;
            update("update_wg_sc", args);
        }
    }
}
