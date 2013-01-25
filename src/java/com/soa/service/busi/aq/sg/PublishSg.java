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
 * 通报事故
 * P12006
 * @author Asus
 */
@Service
public class PublishSg extends BaseService {

    private static final String[] KEY = new String[]{"sg_id", "事故编号",
                                                     "sgcl", "事故处理",
                                                     "zgcs", "整改措施",
                                                     "dczcy", "调查组成员",
                                                     "tbfzr", "填表单位负责人"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData session = getSession(in);
        if (!"安环部".equals(session.getStringValue("dep_name"))) {
            throw new GlobalException(200017, "安环部");        //必须由 !#! 通报
        }
        Object[] args = new Object[7];
        args[0] = in.getStringValue("sgcl");
        args[1] = in.getStringValue("zgcs");
        args[2] = in.getStringValue("dczcy");
        args[3] = in.getStringValue("tbfzr");
        args[4] = getLoginUser(in);
        args[5] = in.getStringValue("tbbz");
        args[6] = in.getStringValue("sg_id");
        update("publish_sg", args);

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
