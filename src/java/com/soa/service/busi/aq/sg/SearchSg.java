/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.sg;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 查询事故
 * S12002
 * @author Asus
 */
@Service
public class SearchSg extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        Object[] args = new Object[5];
        args[0] = in.getStringValue("zt");
        args[1] = in.getStringValue("sgdw");
        args[2] = in.getStringValue("sglb");
        args[3] = in.getStringValue("sgjb");
        args[4] = in.getStringValue("sgxz");
        in.putStringValue("sql", "search_sg");
        in.putObjectValue("args", args);
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for (AbstractCommonData sw : list) {
            sw.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_sg_info.zt", sw.getStringValue("zt")));
            sw.putStringValue("sglb_desc", SystemUtil.getColValueDesc("aq_sg_info.sglb", sw.getStringValue("sglb")));
            sw.putStringValue("sgjb_desc", SystemUtil.getColValueDesc("aq_sg_info.sgjb", sw.getStringValue("sgjb")));
            sw.putStringValue("sgxz_desc", SystemUtil.getColValueDesc("aq_sg_info.sgxz", sw.getStringValue("sgxz")));
        }
        out.putAll(page);
    }
}
