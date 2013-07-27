/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.sw;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import static com.soa.service.BaseService.runService;
import com.soa.util.SystemUtil;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 获取通报的三违
 * S12019
 * 说明：可选参数，三违单位、三违分类
 * @author lianzt
 */
@Service
public class GetTbSw extends BaseService{

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        Object[] args = new Object[2];
        args[0] = in.getStringValue("swfl");
        args[1] = in.getStringValue("ssdw");
        in.putStringValue("sql", "get_tb_sw");
        in.putObjectValue("args", args);
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for(AbstractCommonData sw : list){
            sw.putStringValue("swfl_desc", SystemUtil.getColValueDesc("aq_sw_info.swfl", sw.getStringValue("swfl")));
            sw.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_sw_info.zt", sw.getStringValue("zt")));
            sw.putStringValue("hslx_desc", SystemUtil.getColValueDesc("aq_sw_info.hslx", sw.getStringValue("hslx")));
        }
        out.putAll(page);
    }

}
