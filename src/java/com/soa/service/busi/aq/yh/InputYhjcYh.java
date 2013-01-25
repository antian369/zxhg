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

    private static final String[] KEY = new String[]{"jcrxm", "检查人姓名",
                                                     "jclx", "检查类型",
                                                     "jcsj", "检查时间",
                                                     "yhmc", "隐患名称",
                                                     "yhlb", "隐患类别",
                                                     "yhms", "隐患描述",
                                                     "yhjb", "隐患级别",
                                                     "yhdw", "隐患单位",
                                                     "yhdd", "隐患地点",
                                                     "pzsx", "批准完成时限",
                                                     "pzsx_h", "批准完成时限(时)"};
    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        String yhId = SystemUtil.getSerialNum();
        Date pzsx = in.getDateValue("pzsx");
        pzsx = DateUtil.setHour(pzsx, in.getIntValue("pzsx_h"));        //设置时限的小时数
        AbstractCommonData session = getSession(in);
        Object[] yhjcArgs = new Object[6];      //隐患整改参数: yh_id,jcdw,jclx,jcsj,jcr,jcrxm
        yhjcArgs[0] = yhId;
        yhjcArgs[1] = session.getStringValue("dep_id");
        yhjcArgs[2] = in.getStringValue("jclx");
        yhjcArgs[3] = in.getDateValue("jcsj");
        yhjcArgs[4] = in.getStringValue("jcr");
        yhjcArgs[5] = in.getStringValue("jcrxm");
        Object[] yhArgs = new Object[9];        //隐患参数：aq_yh_info (yh_id,yhmc,yhlb,yhms,yhjb,yhdw,yhdd,lrr,lrsj,zt,ly) value (?,?,?,?,?,?,?,?,now(),'1',?)
        yhArgs[0] = yhId;
        yhArgs[1] = in.getStringValue("yhmc");
        yhArgs[2] = in.getStringValue("yhlb");
        yhArgs[3] = in.getStringValue("yhms");
        yhArgs[4] = in.getStringValue("yhjb");
        yhArgs[5] = in.getStringValue("yhdw");
        yhArgs[6] = in.getStringValue("yhdd");
        yhArgs[7] = getLoginUser(in);
        yhArgs[8] = "3";        //来源，安全检查
        String zgId = SystemUtil.getSerialNum();
        Object[] zgArgs = new Object[3];        //整改参数：yh_id,zg_id,pzsj
        zgArgs[0] = yhId;
        zgArgs[1] = zgId;
        zgArgs[2] = pzsx;
        //先插入隐患
        update("save_yh", yhArgs);
        //后两个随意插入
        update("save_yhjc_yh", yhjcArgs);       //自述旬报 保存
        update("save_yhsg_zgjl_yh", zgArgs);
    }

}
