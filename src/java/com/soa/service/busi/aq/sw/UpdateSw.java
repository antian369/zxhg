/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.sw;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 修改三违
 * P12002
 * @author Asus
 */
@Service
public class UpdateSw extends BaseService {

    private static final String[] KEY = new String[]{"sw_id", "三违编号",
                                                     "swsj", "三违时间",
                                                     "swdd", "三违地点",
                                                     "swry", "三违人员",
                                                     "swxx", "三违现象",
                                                     "swfl", "三违分类",
                                                     //"swbz", "备注",
                                                     "ssdw", "所属单位"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData sw = queryData("get_sw", in.getStringValue("sw_id"));
        if (!sw.getStringValue("lrr").equals(getLoginUser(in))) {
            throw new GlobalException(200010);        //只有录入人才能修改
        }
        Object[] args = new Object[8];
        args[0] = in.getDateValue("swsj");
        args[1] = in.getStringValue("swdd");
        args[2] = in.getStringValue("swry");
        args[3] = in.getStringValue("swxx");
        args[4] = in.getStringValue("swfl");
        args[5] = in.getStringValue("ssdw");
        args[6] = in.getStringValue("swbz");
        args[7] = in.getStringValue("sw_id");
        update("update_sw", args);
        //日志
        if (SystemUtil.isMin()) {
            Object[] operArgs = getOperArgs(in);
            if (SystemUtil.isAll()) {
                operArgs[2] = DataConvertFactory.praseJson(sw);
//                sw = queryData("get_sw", args[7]);
//                operArgs[3] = DataConvertFactory.praseJson(sw);     //操作后的日志
            }
            update("save_oper_log_pl", operArgs);
        }
    }
}
