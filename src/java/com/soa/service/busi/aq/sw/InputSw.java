/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.sw;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 三违录入
 * P12001
 * @author Asus
 */
@Service
public class InputSw extends BaseService {

    private static final String[] KEY = new String[]{"swsj", "三违时间",
                                                     "swdd", "三违地点",
                                                     "swry", "三违人员",
                                                     "swxx", "三违现象",
                                                     "swfl", "三违分类",
                                                     //"swbz", "备注",
                                                     "ssdw", "所属单位",
                                                     //"fxr", "发现人ID",
                                                     "fxrxm", "发现人姓名",
//                                                     "fxrbm", "发现人部门",
                                                     "fxsj", "发现时间"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData session = getSession(in);
        Object[] args = new Object[14];
        args[0] = SystemUtil.getSerialNum();
        args[1] = in.getDateValue("swsj");
        args[2] = in.getStringValue("swdd");
        args[3] = in.getStringValue("swry");
        args[4] = in.getStringValue("swxx");
        args[5] = in.getStringValue("swfl");
        args[6] = in.getStringValue("ssdw");
        args[7] = in.getStringValue("swbz");
        args[8] = in.getStringValue("fxr");
        args[9] = in.getStringValue("fxrxm");
        args[10] = in.getStringValue("fxrbm");
        args[11] = in.getStringValue("fxsj");
        args[12] = getLoginUser(in);
        args[13] = session.getStringValue("dep_id");
        update("input_sw", args);       //插入

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
