/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import java.math.BigDecimal;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 获取任务完成度
 * S13017
 * @author lianzt
 */
@Service
public class GetRwwcSc extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(GetRwwcSc.class);

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        String month = DateUtil.defualtFormat(new Date());
        month = month.substring(0, 7).replace("-0", "-");
        AbstractCommonData jhCc = queryData("get_month_jh_sc", month, "001");
        AbstractCommonData jhYec = queryData("get_month_jh_sc", month, "012");

        double rw = 0;
        if (jhCc != null && !jhCc.isEmpty()) {
            if (StringUtil.notNull(jhCc.getStringValue("yzb"))) {
                rw = jhCc.getDoubleValue("yzb") * 0.97;
                if(log.isDebugEnabled()){
                    log.debug("粗醇月指标：" + jhCc.getDoubleValue("yzb"));
                }
            }
        }
        if (jhYec != null && !jhYec.isEmpty()) {
            if (StringUtil.notNull(jhYec.getStringValue("yzb"))) {
                rw += jhYec.getDoubleValue("yzb") * 2.2;
                if(log.isDebugEnabled()){
                    log.debug("乙二醇月指标：" + jhYec.getDoubleValue("yzb"));
                }
            }
        }

        Date begin, end;
        begin = new Date();
        end = DateUtil.setDay(begin, 26);
        begin = DateUtil.addMonth(end, -1);       //从上个月26号开始
        AbstractCommonData clCc = queryData("get_ycl_sc", "001", begin, end);
        AbstractCommonData clYec = queryData("get_ycl_sc", "012", begin, end);
        double wcl = 0;
        if (clCc != null && !clCc.isEmpty()) {
            if (StringUtil.notNull(clCc.getStringValue("sum_cl"))) {
                wcl = clCc.getDoubleValue("sum_cl") * 0.97;
                if(log.isDebugEnabled()){
                    log.debug("粗醇月产量：" + clCc.getDoubleValue("sum_cl"));
                }
            }
        }
        if (clYec != null && !clYec.isEmpty()) {
            if (StringUtil.notNull(clYec.getStringValue("sum_cl"))) {
                wcl += clYec.getDoubleValue("sum_cl") * 2.2;
                if(log.isDebugEnabled()){
                    log.debug("乙二醇月产量：" + clYec.getDoubleValue("sum_cl"));
                }
            }
        }

        double result = 0;
        if (rw != 0 && wcl != 0) {
            result = round(wcl / rw * 100);
        }
        String rwwc = month + "月甲醇生产计划 " + round(rw) + " 吨，产量总计 " + round(wcl) + " 吨，已完成 " + result + "%";
        out.putStringValue("result", rwwc);
    }

    /**
     * 四舍五入，LENGTH 位小数
     * @param d
     * @return
     */
    private double round(double d) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
