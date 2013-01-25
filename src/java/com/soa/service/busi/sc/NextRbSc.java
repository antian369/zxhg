/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 生成下一个调度日报
 * P13010
 * 说明：检查最后一个日报快照，如果没有，就查询最早一个产量、日耗、销售、单耗、生产综述日期 信息，取日期最大一项
 *      取到日期后，调用日报生成服务，传入该日期
 * @author lianzt
 */
@Service
public class NextRbSc extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData acd = queryData("get_last_rb_sc");
        Date scrq = null;
        if (acd == null || acd.isEmpty() || acd.getDateValue("scrq") == null) {
            //查询最早一个产量、日耗、销售、单耗、生产综述日期 信息，取日期最大一项
            acd = queryData("get_before_cl_sc");
            scrq = acd.getDateValue("scrq");
        } else {
            //如果存在调度日报，取最大日期加1
            scrq = acd.getDateValue("scrq");
            scrq = DateUtil.addDay(scrq, 1);
        }
        if (scrq.getTime() > new Date().getTime()) {
            throw new GlobalException(210034);      //没有更新的调度快报了！
        }
        in.putDateValue("scrq", scrq);
        runService("P13011", in, inHead, out, outHead);
        out.putStringValue("scrq", DateUtil.defualtFormat(scrq));
    }
}
