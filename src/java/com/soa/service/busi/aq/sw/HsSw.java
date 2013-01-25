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
 * 核实三违
 * P12003
 * @author Asus
 */
@Service
public class HsSw extends BaseService {

    private static final String[] KEY = new String[]{"sw_id", "三违编号"};

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData sw = queryData("get_sw", in.getStringValue("sw_id"));
        AbstractCommonData lrDep = queryData("get_dep_pl", sw.getStringValue("lrrbm"));     //录入人部门
        AbstractCommonData session = getSession(in);
        if ("0".equals(lrDep.getStringValue("dep_type"))) {      //管理部门，由安环部核实
            if (!"安环部".equals(session.getStringValue("dep_name"))) {
                throw new GlobalException(200011, "安环部");        //必须由 !#! 核实！
            }
        } else if ("1".equals(lrDep.getStringValue("dep_id"))) {       //分厂发现
            if (lrDep.getStringValue("dep_id").equals(sw.getStringValue("ssdw"))) {       //发现本单位三违
                if (!lrDep.getStringValue("dep_id").equals(session.getStringValue("dep_id"))) {
                    throw new GlobalException(200011, lrDep.getStringValue("dep_name"));        //必须由 !#! 核实！
                }
            } else {      //分厂发现其他单位的三违
                if (!"安环部".equals(session.getStringValue("dep_name"))) {
                    throw new GlobalException(200011, "安环部");        //必须由 !#! 核实！
                }
            }
        }
        Object[] args = new Object[8];
        args[0] = getLoginUser(in);
        args[1] = session.getStringValue("name");
        args[2] = session.getStringValue("dep_id");
        args[3] = in.getStringValue("hsbz");
        args[4] = in.getStringValue("cfyj");
        args[5] = in.getStringValue("cfcs");
        args[6] = in.getIntValue("cfje");
        args[7] = in.getStringValue("sw_id");
        update("hs_sw", args);

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
