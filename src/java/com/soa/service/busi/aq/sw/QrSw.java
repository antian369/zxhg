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
 * 确认三违
 * P12004
 * @author Asus
 */
@Service
public class QrSw extends BaseService {

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
        if (!"02".equals(sw.getStringValue("zt"))) {
            throw new GlobalException(200013);        //登记的三违还未核实
        }

        AbstractCommonData session = getSession(in);
        if (session.getStringValue("dep_name").indexOf("财务") == -1) {
            throw new GlobalException(200012, "财务部");        //必须由 !#! 确认！
        }
//        if ("3".equals(ssdwDep.getStringValue("dep_type"))) {       //非我公司的三违有财务部确认
//            if (!"财务部".equals(session.getStringValue("dep_name"))) {
//                throw new GlobalException(200012, "财务部");        //必须由 !#! 确认！
//            }
//        } else if ("安环部".equals(hsDep.getStringValue("dep_name"))) {      //1）安环部核实的由人力资源部确认
//            if (!"人力资源部".equals(session.getStringValue("dep_name"))) {
//                throw new GlobalException(200012, "人力资源部");        //必须由 !#! 确认！
//            }
//        } else if ("1".equals(hsDep.getStringValue("dep_type"))) {     //分厂核实的由分厂确认
//            if (!hsDep.getStringValue("dep_id").equals(session.getStringValue("dep_id"))) {
//                throw new GlobalException(200012, hsDep.getStringValue("dep_name"));        //必须由 !#! 确认！
//            }
//        }
        Object[] args = new Object[5];
        args[0] = getLoginUser(in);
        args[1] = session.getStringValue("name");
        args[2] = session.getStringValue("dep_id");
        args[3] = in.getStringValue("qrbz");
        args[4] = in.getStringValue("sw_id");
        update("qr_sw", args);

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
