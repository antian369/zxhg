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
 * 获取需要认定的隐患
 * S12028
 * @author lianzt
 */
@Service
public class GetSureYh extends BaseService{

    private final String[] KEY = new String[]{"yhlb","隐患类别"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //状态为3 未认定
        //select * from aq_yh_yhsg where yhlb=? and zt='3'
        List<AbstractCommonData> list = queryList("get_sure_yhsg", in.getStringValue("yhlb"));
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
