/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 隐患检查登记
 * P12016
 * 说明：登记后同时记录到 aq_yh_yhjc aq_yh_info aq_yh_zgjl 表，并分配整改的批准时间和申请时间
 * @author Asus
 */
@Service
public class InputYhjcYh extends BaseService{

    private static final String[] KEY = new String[]{"ly", "检查类型",
                                                     "jcsj", "检查时间",
                                                     "yhmc", "隐患名称",
                                                     "yhlb", "隐患类别",
                                                     "yhms", "隐患描述",
                                                     "yhjb", "隐患级别",
                                                     "yhdw", "隐患单位",
                                                     "yhdd", "隐患地点",
                                                     "pzsx", "批准完成时限"};
    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        String yhId = SystemUtil.getSerialNum();
        AbstractCommonData session = getSession(in);
        //insert into aq_yh_info (yh_id,yhmc,yhlb,yhms,yhjb,yhdw,yhdd,jcsj,lrr,lrsj,zt,ly) value (?,?,?,?,?,?,?,?,?,now(),'1',?)
        Object[] yhArgs = new Object[10];
        yhArgs[0] = yhId;
        yhArgs[1] = in.getStringValue("yhmc");
        yhArgs[2] = in.getStringValue("yhlb");
        yhArgs[3] = in.getStringValue("yhms");
        yhArgs[4] = in.getStringValue("yhjb");
        yhArgs[5] = in.getStringValue("yhdw");
        yhArgs[6] = in.getStringValue("yhdd");
        yhArgs[7] = in.getDateValue("jcsj");
        yhArgs[8] = getLoginUser(in);
        yhArgs[9] = in.getStringValue("ly");

        //insert into aq_yh_zg (zg_id,yh_id,pzsx,lazy_zt,yszt) value (?,?,?,'1','1')
        Object[] zgArgs = new Object[3];
        zgArgs[0] = SystemUtil.getSerialNum();
        zgArgs[1] = yhId;
        zgArgs[2] = in.getDateValue("pzsx");

        //先插入隐患
        update("save_yh", yhArgs);
        //再插入整改
        update("save_zg", zgArgs);
    }

}
