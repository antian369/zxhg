/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 污水处理录入
 * P13005
 * 说明：可选参数：zb_bz, yb_bz, bb_bz  备注
 * @author lianzt
 */
@Service
public class InputWsclSc extends BaseService {

    private static final String[] KEY = new String[]{"scrq", "生产日期",
                                                     "zb", "中班",
                                                     "yb", "班次",
                                                     "bb", "班次"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        Date scrq = in.getDateValue("scrq");        //生产日期       
        if (new Date().getTime() < scrq.getTime()) {
            throw new GlobalException(200032, "生产日期");       //!#!不能大于当前时间
        }
        AbstractCommonData ses = getSession(in);
        List<AbstractCommonData> wscl = queryList("get_wscl_sc", scrq);
        Object[] args = new Object[6];
        if (wscl == null || wscl.isEmpty()) {
            //插入
            args[0] = scrq;
            args[3] = getLoginUser(in);
            args[4] = ses.getStringValue("name");

            //中班
            args[1] = "z";
            args[2] = in.getStringValue("zb");
            args[5] = in.getStringValue("zb_bz");
            update("save_wscl_sc", args);
            //夜班
            args[1] = "y";
            args[2] = in.getStringValue("yb");
            args[5] = in.getStringValue("yb_bz");
            update("save_wscl_sc", args);
            //班
            args[1] = "b";
            args[2] = in.getStringValue("bb");
            args[5] = in.getStringValue("bb_bz");
            update("save_wscl_sc", args);
        } else {
            //更新
            //插入
            args[1] = getLoginUser(in);
            args[2] = ses.getStringValue("name");
            args[4] = scrq;

            //中班
            args[0] = in.getStringValue("zb");
            args[3] = in.getStringValue("zb_bz");
            args[5] = "z";
            update("update_wscl_sc", args);
            //夜班
            args[0] = in.getStringValue("yb");
            args[3] = in.getStringValue("yb_bz");
            args[5] = "y";
            update("update_wscl_sc", args);
            //班
            args[0] = in.getStringValue("bb");
            args[3] = in.getStringValue("bb_bz");
            args[5] = "b";
            update("update_wscl_sc", args);
        }
    }
}
