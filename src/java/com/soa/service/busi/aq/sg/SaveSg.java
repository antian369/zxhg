/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.sg;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 录入事故
 * P12005
 * @author Asus
 */
@Service
public class SaveSg extends BaseService {

    private static final String[] KEY = new String[]{"sgdw", "事故单位",
                                                     "sgdd", "事故地点",
                                                     "sgsj", "事故时间",
                                                     "sglb", "事故类别",
                                                     "sgjb", "事故级别",
                                                     "zjss", "直接损失",
                                                     "jjss", "间接损失",
                                                     "zywhp", "主要危化品",
                                                     "swrs", "死亡人数",
                                                     "zsrs", "重伤人数",
                                                     "ssrs", "疏散人数",
                                                     "zdrs", "中毒人数",
                                                     "ryrs", "入院人数",
                                                     "szrs", "失踪人数",
                                                     "sgxz", "事故性质"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        Object[] args = new Object[18];
        args[0] = SystemUtil.getSerialNum();
        args[1] = in.getStringValue("sgdw");
        args[2] = in.getStringValue("sgdd");
        args[3] = in.getDateValue("sgsj");
        args[4] = in.getStringValue("sglb");
        args[5] = in.getStringValue("sgjb");
        try {
            args[6] = in.getDoubleValue("zjss");
        } catch (Exception e) {
            throw new GlobalException(200016, "直接损失");      //!#! 必须是数字
        }
        try {
            args[7] = in.getDoubleValue("jjss");
        } catch (Exception e) {
            throw new GlobalException(200016, "间接损失");      //!#! 必须是数字
        }
        args[8] = in.getStringValue("zywhp");
        args[9] = in.getIntValue("swrs");
        args[10] = in.getIntValue("zsrs");
        args[11] = in.getIntValue("ssrs");
        args[12] = in.getIntValue("zdrs");
        args[13] = in.getIntValue("ryrs");
        args[14] = in.getIntValue("szrs");
        args[15] = in.getStringValue("sgxz");
        args[16] = getLoginUser(in);
        args[17] = in.getStringValue("sgbz");
        update("save_sg", args);
        out.putStringValue("sg_id", args[0].toString());        //事故ID

        //记录日志
        if (SystemUtil.isMin()) {
            Object[] operArgs = getOperArgs(in);
            if (SystemUtil.isAll()) {
//                AbstractCommonData sw = queryData("get_sw", args[0]);
//                operArgs[3] = DataConvertFactory.praseJson(sw);     //操作后的日志
            }
            update("save_oper_log_pl", operArgs);
        }
    }
}
