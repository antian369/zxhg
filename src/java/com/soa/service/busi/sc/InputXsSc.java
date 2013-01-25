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
 * 日销售录入
 * P13006
 * @author lianzt
 */
@Service
public class InputXsSc extends BaseService {

    private static final String[] KEY = new String[]{"scrq", "生产日期",
                                                     "cjc", "粗甲醇",
                                                     "jjc", "精甲醇",
                                                     "yy", "液氧",
                                                     "yd", "液氮",
                                                     "ya", "液氩",
                                                     "lh", "硫磺",
                                                     "lsa", "硫酸铵"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        Date scrq = in.getDateValue("scrq");
        AbstractCommonData xs = queryData("get_xs_sc", scrq);
        AbstractCommonData ses = getSession(in);
        Object[] args = new Object[11];
        if (xs == null || xs.isEmpty()) {
            //插入
            args[0] = scrq;
            args[1] = in.getDoubleValue("cjc");
            args[2] = in.getDoubleValue("jjc");
            args[3] = in.getDoubleValue("yy");
            args[4] = in.getDoubleValue("yd");
            args[5] = in.getDoubleValue("ya");
            args[6] = in.getDoubleValue("lh");
            args[7] = in.getDoubleValue("lsa");
            args[8] = getLoginUser(in);
            args[9] = ses.getStringValue("name");
            args[10] = in.getStringValue("bz");
            update("save_xs_sc", args);
        } else {
            //更新
            args[0] = in.getDoubleValue("cjc");
            args[1] = in.getDoubleValue("jjc");
            args[2] = in.getDoubleValue("yy");
            args[3] = in.getDoubleValue("yd");
            args[4] = in.getDoubleValue("ya");
            args[5] = in.getDoubleValue("lh");
            args[6] = in.getDoubleValue("lsa");
            args[7] = getLoginUser(in);
            args[8] = ses.getStringValue("name");
            args[9] = in.getStringValue("bz");
            args[10] = scrq;
            update("update_xs_sc", args);
        }
    }
}
