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
 * 获取需要核实的三违
 * S12016
 * 根据当前登录用户所在部门，显示三违数据
 * 公司三违核实，由安环部进行核实
 * 内部三违核实，由分厂内部进行核实
 * @author lianzt
 */
@Service
public class GetHsSw extends BaseService {

    private final String GLHM = "0";        //部门类型：管理部门

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData ses = getSession(in);
        //先判断是分厂用户登录，还是管理部门的用户登录
        if (GLHM.equals(ses.getStringValue("dep_type"))) {
            //管理部门用户，只查询 公司核实(2) 类型
            //select * from aq_sw_info where hslx='2' and zt='01'
            in.putStringValue("sql", "get_hm_hs_sw");
        } else {
            //分厂用户，只查询该分厂下 内部核实(1) 类型
            //select * from aq_sw_info where hslx='1' and zt='01' and ssdw=?
            in.putStringValue("sql", "get_fc_hs_sw");
            in.putObjectValue("args", new Object[]{ses.getStringValue("dep_id")});
        }
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
