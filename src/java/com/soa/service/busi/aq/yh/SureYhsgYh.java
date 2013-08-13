/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 隐患确认 P12026
 *
 * @author lianzt
 */
@Service
public class SureYhsgYh extends BaseService {

    private final String[] KEY = new String[]{"yh_id", "隐患编号",
                                                "yhjb", "隐患级别",
                                                "rdbz", "认定备注"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData ses = getSession(in);
        //update aq_yh_yhsg set yhjb=?, rdbz=?,rdr=?,rdrxm=?,zt='4' where yh_id=?
        Object[] args = new Object[5];
        args[0] = in.getStringValue("yhjb");
        args[1] = in.getStringValue("rdbz");
        args[2] = getLoginUser(in);
        args[3] = ses.getStringValue("name");
        args[4] = in.getStringValue("yh_id");
        update("sure_yhsg_yh", args);
    }
}
