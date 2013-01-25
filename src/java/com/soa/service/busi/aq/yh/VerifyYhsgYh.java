/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 隐患收购核实
 * P12015
 * 说明：只能核实自己部门的隐患，核实后会将数据记录到aq_yh_info、aq_yh_zgjl表，如果不是隐患，只修改收购单状态
 * @author Asus
 */
@Service
public class VerifyYhsgYh extends BaseService {

    private static final String[] KEY = new String[]{"yh_id", "收购编号",
                                                     "sgzt", "收购状态"};
    private static final String[] KEY2 = new String[]{"jiangjin", "奖金",
                                                      "yhmc", "隐患名称",
                                                      "yhlb", "隐患类别",
                                                      "yhms", "隐患描述",
                                                      "yhjb", "隐患级别",
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
    public void check(AbstractCommonData in, AbstractCommonData inHead) {
        super.check(in, inHead);
        if ("2".equals(in.getStringValue("sgzt"))) {        //如果核实为隐患，需要对整改相关内容做校验
            check(in, KEY2);
        } else if (!"0".equals(in.getStringValue("sgzt"))) {
            throw new GlobalException(210005, "参数");      //!#!不符！
        }
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        String sgzt = in.getStringValue("sgzt");
        String yhId = in.getStringValue("yh_id");
        AbstractCommonData yhsg = queryData("get_yhsg_yh", yhId);
        if (yhsg == null || yhsg.isEmpty()) {
            throw new GlobalException(200019, "隐患收购单");        //!#!不存在
        }
        AbstractCommonData session = getSession(in);
        if (!yhsg.getStringValue("yhdw").equals(session.getStringValue("dep_id"))) {
            throw new GlobalException(200031);        //只能核实自己分厂或部门的隐患！
        }
        Object[] args = new Object[5];
        args[0] = getLoginUser(in);
        args[1] = session.getStringValue("name");
        args[3] = sgzt;
        args[4] = yhId;
        if ("0".equals(sgzt)) {
            //不是隐患，只修改收购单状态
            args[2] = 0;
            update("update_yhsg_yh", args);
            return;         //更新完成后退出
        } else {
            args[2] = in.getIntValue("jiangjin");
            update("update_yhsg_yh", args);
        }
        Object[] yhArgs = new Object[9];        //隐患参数：aq_yh_info (yh_id,yhmc,yhlb,yhms,yhjb,yhdw,yhdd,lrr,lrsj,zt,ly) value (?,?,?,?,?,?,?,?,now(),'1',?)
        yhArgs[0] = yhId;
        yhArgs[1] = in.getStringValue("yhmc");
        yhArgs[2] = in.getStringValue("yhlb");
        yhArgs[3] = in.getStringValue("yhms");
        yhArgs[4] = in.getStringValue("yhjb");
        yhArgs[5] = session.getStringValue("dep_id");
        yhArgs[6] = in.getStringValue("yhdd");
        yhArgs[7] = getLoginUser(in);
        yhArgs[8] = "1";        //来源，隐患收购
        String zgId = SystemUtil.getSerialNum();
        Date sqsx = in.getDateValue("sqsx");
        sqsx = DateUtil.setHour(sqsx, in.getIntValue("sqsx_h"));        //设置时限的小时数
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
        //再插入整改
        update("save_zgjl_yh", zgArgs);
    }
}
