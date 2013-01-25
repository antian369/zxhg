/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.exception.InstanceDataException;
import com.lianzt.util.DateUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 录入消耗品计划
 * P13009
 * 说明：计划月份 格式yyyy-mm
 * @author lianzt
 */
@Service
public class InputXhPlanSc extends BaseService {
    
    public static final String[] KEY = new String[]{"jhyf", "计划月份",
                                                    "xhpbh", "消耗品编号",
                                                    "yzb", "月指标"};
    
    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //判断该月的天数，计算日均指标
        AbstractCommonData ses = getSession(in);
        String jhyf = in.getStringValue("jhyf");
        String xhpbh = in.getStringValue("xhpbh");
        Date begin = null;      //该月开始
        try {
            begin = DateUtil.formatDate(jhyf, "yyyy-MM");
        } catch (InstanceDataException e) {
            throw new GlobalException(210033, e, "计划月份");        //!#!格式错误
        }
        Date end = DateUtil.addMonth(begin, 1);     //下月第一天
        int days = (int) ((end.getTime() - begin.getTime()) / (3600000L * 24));     //这个月有多少天
        double rzb = in.getDoubleValue("yzb") / days;
        BigDecimal bd = new BigDecimal(rzb);
        rzb = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        AbstractCommonData month = queryData("get_month_xhpjh_sc", jhyf, xhpbh);
        Object[] args = new Object[7];
        if (month == null || month.isEmpty()) {
            //插入
            args[0] = jhyf;
            args[1] = xhpbh;
            args[2] = in.getDoubleValue("yzb");
            args[3] = rzb;
            args[4] = ses.getStringValue(SystemUtil.loginRemark);
            args[5] = ses.getStringValue("name");
            args[6] = in.getStringValue("bz");
            update("save_month_xhpjh_sc", args);
        } else {
            //更新
            args[0] = in.getDoubleValue("yzb");
            args[1] = rzb;
            args[2] = ses.getStringValue(SystemUtil.loginRemark);
            args[3] = ses.getStringValue("name");
            args[4] = in.getStringValue("bz");
            args[5] = jhyf;
            args[6] = xhpbh;
            update("update_month_xhpjh_sc", args);
        }
        out.putDoubleValue("rzb", rzb);
    }
}
