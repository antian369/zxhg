/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 获取某单位的隐患收购 S12027
 *
 * @author lianzt
 */
@Service
public class GetDwYhsgYh extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //select * from aq_yh_yhsg where zt=nvl(?,zt) and yhdw=nvl(?,yhdw) and yhlb=nvl(?,yhlb)
        List<AbstractCommonData> list = queryList("get_dw_yhsg_yh", in.getStringValue("zt"), in.getStringValue("yhdw"));
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData yh : list) {
                yh.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_yhsg.yhlb", yh.getStringValue("yhlb")));
                yh.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_yhsg.yhjb", yh.getStringValue("yhjb")));
                yh.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_yhsg.zt", yh.getStringValue("zt")));
            }
        }
        out.putArrayValue("yhsgs", list);
    }
}
