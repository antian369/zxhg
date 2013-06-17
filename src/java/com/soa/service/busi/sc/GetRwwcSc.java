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
import org.springframework.stereotype.Service;

/**
 * 获取任务完成度
 * S13017
 * @author lianzt
 */
@Service
public class GetRwwcSc extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        String month = DateUtil.defualtFormat(new Date());
        month = month.substring(0, 7).replace("-0", "-");
        AbstractCommonData jh = queryData("get_month_jh_sc", month, "002");

        double rw = 0;
        if (jh != null && !jh.isEmpty()) {
            if (StringUtil.notNull(jh.getStringValue("yzb"))) {
                rw = jh.getDoubleValue("yzb");
            }
        }

        Date begin, end;
        begin = new Date();
        end = DateUtil.setDay(begin, 26);
        begin = DateUtil.addMonth(end, -1);       //从上个月26号开始
        AbstractCommonData cl = queryData("get_jc_ycl_sc", "002", begin, end);
        double wcl = 0;
        if (cl != null && !cl.isEmpty()) {
            if (StringUtil.notNull(cl.getStringValue("sum_cl"))) {
                wcl = cl.getDoubleValue("sum_cl");
            }
        }

        double result = 0;
        if (rw != 0 && wcl != 0) {
            result = round(wcl / rw * 100);
        }
        String rwwc = month + "月甲醇生产计划 " + rw + " 吨，产量总计 " + wcl + " 吨，已完成 " + result + "%";
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
