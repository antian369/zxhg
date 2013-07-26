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
        AbstractCommonData ssdw = queryData("get_dep_pl", sw.getStringValue("ssdw"));     //三违单位
        AbstractCommonData session = getSession(in);
        if ("2".equals(sw.getStringValue("hslx"))) {      //公司核实(2)，由安环部核实
            if (session.getStringValue("dep_name").indexOf("安环") == -1) {
                throw new GlobalException(200011, "安环部");        //必须由 !#! 核实！
            }
        } else {       //内部核实(1) 分厂核实
            if (!session.getStringValue("dep_id").equals(sw.getStringValue("ssdw"))) {       //发现本单位三违
                throw new GlobalException(200011, ssdw.getStringValue("dep_name"));        //必须由 !#! 核实！
            }
        }
        //update aq_sw_info set hsr=?, hssj=now(), hsrxm=?, hsrbm=?, hsbz=?,cfyj=?, cfcs=?, cfje=?, zt='02' where sw_id=?
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
        //如果是分厂核实，或罚款为0，直接更新为确认
        if ("1".equals(sw.getStringValue("hslx"))) {
            args = new Object[5];
            args[0] = getLoginUser(in);
            args[1] = session.getStringValue("name");
            args[2] = session.getStringValue("dep_id");
            args[3] = "分厂内部三违，直接确认。";
            args[4] = in.getStringValue("sw_id");
            update("qr_sw", args);
        } else if (in.getIntValue("cfje") == 0) {
            args = new Object[5];
            args[0] = getLoginUser(in);
            args[1] = session.getStringValue("name");
            args[2] = session.getStringValue("dep_id");
            args[3] = "罚款金额为0，直接确认。";
            args[4] = in.getStringValue("sw_id");
            update("qr_sw", args);
        }

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
