/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * 用户登录统计
 * S11012
 * 参数：begin 月份的第一天，为空时表示当月
 * @author lianzt
 */
@Service
public class RepLoginPl extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        Date begin = in.getDateValue("begin");
        if (begin == null) {
            begin = new Date();
            begin = DateUtil.setDay(begin, 1);
            begin = DateUtil.setHour(begin, 0);
            begin = DateUtil.setMinute(begin, 0);
            begin = DateUtil.setSecond(begin, 0);
        }
        Date end = DateUtil.addMonth(begin, 1);
        //select username,name,
        //rep_login_pl
        out.putArrayValue("users", queryList("rep_login_pl", begin, end));
    }
}
