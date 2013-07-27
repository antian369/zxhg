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
 * 获取可修改的三违数据
 * S12018
 * 说明：只能修改未核实的三违
 * @author lianzt
 */
@Service
public class GetXgSw extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //select * from aq_sw_info where lrr=? and zt='01'
        in.putStringValue("sql", "get_xg_sw");
        in.putObjectValue("args", new Object[]{getLoginUser(in)});
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for (AbstractCommonData sw : list) {
            sw.putStringValue("swfl_desc", SystemUtil.getColValueDesc("aq_sw_info.swfl", sw.getStringValue("swfl")));
            sw.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_sw_info.zt", sw.getStringValue("zt")));
            sw.putStringValue("hslx_desc", SystemUtil.getColValueDesc("aq_sw_info.hslx", sw.getStringValue("hslx")));
        }
        out.putAll(page);
    }
}
