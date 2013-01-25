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
 * 修改事故
 * P12007
 * @author Asus
 */
@Service
public class UpdateSg extends BaseService {

    private static final String[] KEY = new String[]{"sg_id", "事故编号",
                                                     "sgdw", "事故单位",
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
        AbstractCommonData sg = queryData("get_sg", in.getStringValue("sg_id"));
        if(sg == null || sg.isEmpty()){
            throw new GlobalException(200015);      //事故不存在
        }
        if(!getLoginUser(in).equals(sg.getStringValue("lrr"))){
            throw new GlobalException(200010);      //只有录入人才能修改
        }
        if(!"1".equals(sg.getStringValue("zt"))){
            throw new GlobalException(200014);      //只能修改未通报的事故
        }

        //保存
        Object[] args = new Object[17];
        args[0] = in.getStringValue("sgdw");
        args[1] = in.getStringValue("sgdd");
        args[2] = in.getDateValue("sgsj");
        args[3] = in.getStringValue("sglb");
        args[4] = in.getStringValue("sgjb");
        args[5] = in.getDoubleValue("zjss");
        args[6] = in.getDoubleValue("jjss");
        args[7] = in.getStringValue("zywhp");
        args[8] = in.getIntValue("swrs");
        args[9] = in.getIntValue("zsrs");
        args[10] = in.getIntValue("ssrs");
        args[11] = in.getIntValue("zdrs");
        args[12] = in.getIntValue("ryrs");
        args[13] = in.getIntValue("szrs");
        args[14] = in.getStringValue("sgxz");
        args[15] = in.getStringValue("sgbz");
        args[16] = in.getStringValue("sg_id");
        update("update_sg", args);

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
