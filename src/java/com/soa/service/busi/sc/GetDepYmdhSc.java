/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.bean.DbDate;
import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 获取本单位的原煤单耗
 * S13003
 * 参数：日期，默认为当天
 * @author Asus
 */
@Service
public class GetDepYmdhSc extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(GetDepYmdhSc.class);
    
    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        String today = in.getStringValue("dhrq");
        if (StringUtil.isNull(today)) {
            today = DateUtil.defualtFormat(new Date());
        }
        List<AbstractCommonData> list = new LinkedList<AbstractCommonData>();
        AbstractCommonData acd = DataConvertFactory.getInstanceEmpty();
        //1........
        acd.putStringValue("bzlb_desc", "白班");
        acd.putStringValue("dhrq", today);
        acd.putStringValue("lrrxm", "测试员工1");
        acd.putDateValue("lrsj", new DbDate());
        acd.putStringValue("cpdh", getRandom());
        list.add(acd);
        //2...
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("bzlb_desc", "夜班");
        acd.putStringValue("dhrq", today);
        acd.putStringValue("lrrxm", "测试员工2");
        acd.putDateValue("lrsj", new DbDate());
        acd.putStringValue("cpdh", getRandom());
        list.add(acd);
        out.putArrayValue("dhxx", list);
        out.putStringValue("dhrq", today);
    }

    /**
     * 随机数
     * @return
     */
    private String getRandom() {
        double d = Math.random();
        return (d + 1 + "").substring(0, 4);
    }
}
