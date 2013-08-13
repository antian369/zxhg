/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 隐患收购核实
 * P12015
 * 说明：只能核实自己部门的隐患，核实后会将数据记录到aq_yh_info、aq_yh_zgjl表，如果不是隐患，只修改收购单状态
 * @author Asus
 */
@Service
public class VerifyYhsgYh extends BaseService {

    private final String[] KEY = new String[]{"yhfl", "隐患分类",
                                                      "zgsj", "整改时间",
                                                      "zgqk", "整改情况",
                                                      "zgr", "整改人",
                                                      "yh_id", "隐患编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData ses = getSession(in);
        //update aq_yh_yhsg set zt='3',yhlb=?,zgr=?,zgqk=?,zgsj=?,qrr=?,qrrxm=? where yh_id=?
        Object[] args = new Object[7];
        args[0] = in.getStringValue("yhfl");
        args[1] = in.getStringValue("zgr");
        args[2] = in.getStringValue("zgqk");
        args[3] = in.getDateValue("zgsj");
        args[4] = getLoginUser(in);
        args[5] = ses.getStringValue("name");
        args[6] = in.getStringValue("yh_id");
        update("verify_yhsg_yh", args);
    }
}
