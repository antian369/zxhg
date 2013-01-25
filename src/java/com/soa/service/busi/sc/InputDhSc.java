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
 * 单耗录入
 * P13003
 * @author lianzt
 */
@Service
public class InputDhSc extends BaseService {

    private static final String[] KEY = new String[]{"scrq", "生产日期",
                                                     "hym", "耗原煤",
                                                     "hrm", "耗燃煤",
                                                     "hs", "耗水",
                                                     "hwd", "耗外电",
                                                     "hq", "耗气"};

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
        AbstractCommonData dh = queryData("get_dh_sc", scrq);
        AbstractCommonData ses = getSession(in);
        Object[] args = new Object[9];
        if (dh == null || dh.isEmpty()) {
            //插入
            args[0] = scrq;
            args[1] = in.getDoubleValue("hym");
            args[2] = in.getDoubleValue("hrm");
            args[3] = in.getDoubleValue("hs");
            args[4] = in.getDoubleValue("hwd");
            args[5] = in.getDoubleValue("hq");
            args[6] = getLoginUser(in);
            args[7] = ses.getStringValue("name");
            args[8] = in.getStringValue("bz");
            update("save_dh_sc", args);
        } else {
            //更新
            args[0] = in.getDoubleValue("hym");
            args[1] = in.getDoubleValue("hrm");
            args[2] = in.getDoubleValue("hs");
            args[3] = in.getDoubleValue("hwd");
            args[4] = in.getDoubleValue("hq");
            args[5] = getLoginUser(in);
            args[6] = ses.getStringValue("name");
            args[7] = in.getStringValue("bz");
            args[8] = scrq;
            update("update_dh_sc", args);
        }
    }
}
