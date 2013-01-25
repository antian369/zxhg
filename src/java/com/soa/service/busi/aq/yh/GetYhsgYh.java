/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 获取隐患收购
 * S12011
 * 说明：同时查询整改记录
 * @author Asus
 */
@Service
public class GetYhsgYh extends BaseService {

    private static final String[] KEY = new String[]{"yh_id", "隐患编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData zsxb = queryData("get_yhsg_v_yh", in.getStringValue("yh_id"));
        if (zsxb == null || zsxb.isEmpty()) {
            throw new GlobalException(200019, "隐患");  //!#!不存在
        }
        zsxb.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhlb", zsxb.getStringValue("yhlb")));
        zsxb.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhjb", zsxb.getStringValue("yhjb")));
        zsxb.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_info.zt", zsxb.getStringValue("zt")));
        zsxb.putStringValue("sgzt_desc", SystemUtil.getColValueDesc("aq_yh_yhsg.sgzt", zsxb.getStringValue("sgzt")));
        List<AbstractCommonData> list = queryList("get_zgjl_yh", in.getStringValue("yh_id"));
        zsxb.putArrayValue("zgjl", list);
        for (AbstractCommonData zg : list) {
            zg.putStringValue("zgjg_desc", SystemUtil.getColValueDesc("aq_yh_zgjl.zgjg", zg.getStringValue("zgjg")));
        }
        out.putAll(zsxb);
    }
}
