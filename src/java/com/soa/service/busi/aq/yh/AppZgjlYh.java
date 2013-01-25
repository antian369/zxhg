/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 整改审批
 * P12012
 * 说明：对初次整改和延时申请的隐患审批，oper字段 0为通报、1为同意
 * @author Asus
 */
@Service
public class AppZgjlYh extends BaseService {

    private static final String[] KEY = new String[]{"yh_id", "隐患编号",
                                                     "zg_id", "整改记录编号",
                                                     "pzsx", "整改批准时限",
                                                     "pzsx_h", "整改批准时限(时)",
                                                     "oper", "操作"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData session = getSession(in);
        Date pzsx = in.getDateValue("pzsx");
        pzsx = DateUtil.setHour(pzsx, in.getIntValue("pzsx_h"));        //设置时限的小时数
        if (!"安环部".equals(session.getStringValue("dep_name"))) {
            throw new GlobalException(200025, "审批");        //必须由安环部!#!
        }
        if(pzsx.getTime() < new Date().getTime()){
            throw new GlobalException(200027);        //批准时间必须大于当前时间
        }
        String yhId = in.getStringValue("yh_id");
        String zgId = in.getStringValue("zg_id");
        AbstractCommonData yh = queryData("get_yh", yhId);      //获取隐患
        if (yh == null || yh.isEmpty()) {
            throw new GlobalException(200019, "隐患");  //!#!不存在
        }
        if (!"1".equals(yh.getStringValue("zt"))) {
            throw new GlobalException(200020);      //只能复查正在整改的隐患
        }
        AbstractCommonData zgjl = queryData("get_zgjl", yhId, zgId);        //获取整改记录
        if (zgjl == null || zgjl.isEmpty()) {
            throw new GlobalException(200019, "整改记录");  //!#!不存在
        }
        if (!"0".equals(zgjl.getStringValue("zgjg"))) {
            throw new GlobalException(200024);        //不能对该记录做修改
        }
        if ("0".equals(in.getStringValue("oper"))) {
            //通报
            update("update_zgjl_yh", "5", yhId, zgId);      //整改记录置为 逾期
            update("update_yhzt_yh", "2", yhId);            //隐患置为 逾期
        } else if ("1".equals(in.getStringValue("oper"))) {
            //同意
            update("agree_zgjl_yh", pzsx, yhId, zgId);      //批准时限
        } else {
            throw new GlobalException(210005, "参数");      //!#!不符！
        }
    }
}
