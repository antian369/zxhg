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
 * 自述旬报录入
 * P12008
 * 说明：自述旬报录入之后就进入 正在整改 状态，所以需要输入 自述旬报、隐患和整改 信息
 * @author Asus
 */
@Service
public class InputZsxbYh extends BaseService {

    private static final String[] KEY = new String[]{"tbrxm", "填报人姓名",
                                                     //"tbdw", "填报单位",
                                                     "zw", "填报人职务",
                                                     "fggz", "分管主要工作",
                                                     "yhmc", "隐患名称",
                                                     "yhlb", "隐患类别",
                                                     "yhms", "隐患描述",
                                                     "yhjb", "隐患级别",
                                                     //"yhdw", "隐患单位", //录入人的单位
                                                     "yhdd", "隐患地点",
                                                     "zgrxm", "整改人姓名",
                                                     "zgcs", "整改措施",
                                                     "sqsx", "预计整改完成时间",
                                                     "sqsx_h", "预计整改完成时间(时)"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        String yhId = SystemUtil.getSerialNum();
        Date sqsx = in.getDateValue("sqsx");
        sqsx = DateUtil.setHour(sqsx, in.getIntValue("sqsx_h"));        //设置时限的小时数
        AbstractCommonData session = getSession(in);
        Object[] zsxbArgs = new Object[6];      //自述旬报参数: yh_id,tbr,tbrxm,tbdw,zw,fggz,tbsj
        zsxbArgs[0] = yhId;
        zsxbArgs[1] = in.getStringValue("tbr");
        zsxbArgs[2] = in.getStringValue("tbrxm");
        zsxbArgs[3] = session.getStringValue("dep_id");
        zsxbArgs[4] = in.getStringValue("zw");
        zsxbArgs[5] = in.getStringValue("fggz");
        Object[] yhArgs = new Object[9];        //隐患参数：aq_yh_info (yh_id,yhmc,yhlb,yhms,yhjb,yhdw,yhdd,lrr,lrsj,zt,ly) value (?,?,?,?,?,?,?,?,now(),'1',?)
        yhArgs[0] = yhId;
        yhArgs[1] = in.getStringValue("yhmc");
        yhArgs[2] = in.getStringValue("yhlb");
        yhArgs[3] = in.getStringValue("yhms");
        yhArgs[4] = in.getStringValue("yhjb");
        yhArgs[5] = session.getStringValue("dep_id");
        yhArgs[6] = in.getStringValue("yhdd");
        yhArgs[7] = getLoginUser(in);
        yhArgs[8] = "2";        //来源，自述旬报
        String zgId = SystemUtil.getSerialNum();
        Object[] zgArgs = new Object[7];        //整改参数：yh_id,zg_id,zgr,zgrxm,zgcs,kssj(now()),sqsx
        zgArgs[0] = yhId;
        zgArgs[1] = zgId;
        zgArgs[2] = in.getStringValue("zgr");
        zgArgs[3] = in.getStringValue("zgrxm");
        zgArgs[4] = in.getStringValue("zgcs");
        zgArgs[5] = sqsx;
        zgArgs[6] = in.getStringValue("zgbz");
        //先插入隐患
        update("save_yh", yhArgs);
        //后两个随意插入
        update("save_zsxb_yh", zsxbArgs);       //自述旬报 保存
        update("save_zgjl_yh", zgArgs);
    }
}
