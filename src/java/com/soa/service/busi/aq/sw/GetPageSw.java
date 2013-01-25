/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.sw;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 获取一页三违信息
 * S12001
 * @author Asus
 */
@Service
public class GetPageSw extends BaseService {

    private static final String[] KEY = new String[]{"zt", "三违状态",
                                                     "swfl", "三违分类",
                                                     "ssdw", "三违单位"};

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        Object[] args = new Object[3];
        args[0] = in.getStringValue("zt");
        args[1] = in.getStringValue("swfl");
        args[2] = in.getStringValue("ssdw");
        in.putStringValue("sql", "get_page_sw");
        in.putObjectValue("args", args);
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for(AbstractCommonData sw : list){
            sw.putStringValue("swfl_desc", SystemUtil.getColValueDesc("aq_sw_info.swfl", sw.getStringValue("swfl")));
            sw.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_sw_info.zt", sw.getStringValue("zt")));
        }
        out.putAll(page);
    }
}
