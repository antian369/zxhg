/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 查询隐患收购单
 * S12008
 * 说明：参数 发现人fxrxm、隐患单位yhdw、收购状态sgzt
 * @author Asus
 */
@Service
public class SearchYhsgYh extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        if (StringUtil.isNull(in.getStringValue("do"))) {
            //为空时为第一次进入页面，不进行查询
            return;
        }
        Object[] args = new Object[3];      //yhmc yhlb yhjb yhdw zgjg ly
        if (!StringUtil.isNull(in.getStringValue("fxrxm"))) {
            args[0] = "%" + in.getStringValue("fxrxm") + "%";
        } else {
            args[0] = "";
        }
        args[1] = in.getStringValue("yhdw");
        args[2] = in.getStringValue("sgzt");
        in.putStringValue("sql", "search_yhsg_yh");
        in.putObjectValue("args", args);
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for (AbstractCommonData zsxb : list) {
            zsxb.putStringValue("sgzt_desc", SystemUtil.getColValueDesc("aq_yh_yhsg.sgzt", zsxb.getStringValue("sgzt")));
        }
        out.putAll(page);
    }
}
