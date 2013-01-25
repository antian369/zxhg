/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保存调度日报快照
 * P13012
 * @author lianzt
 */
@Service
public class InputDdkbSc extends BaseService {

    private static final String[] KEY = new String[]{"scrq", "生产日期", "rbnr", "日报内容"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData ses = getSession(in);
        Date scrq = in.getDateValue("scrq");
        Object[] args = new Object[5];
        AbstractCommonData acd = queryData("get_rb_sc", scrq);
        if (acd == null || acd.isEmpty()) {
            //插入
            args[0] = scrq;
            args[1] = in.getStringValue("rbnr");
            args[2] = ses.getStringValue(SystemUtil.loginRemark);
            args[3] = ses.getStringValue("name");
            args[4] = in.getStringValue("bz");
            update("save_rb_sc", args);
        } else {
            //更新
            args[0] = in.getStringValue("rbnr");
            args[1] = ses.getStringValue(SystemUtil.loginRemark);
            args[2] = ses.getStringValue("name");
            args[3] = in.getStringValue("bz");
            args[4] = scrq;
            update("update_rb_sc", args);
        }
    }
}
