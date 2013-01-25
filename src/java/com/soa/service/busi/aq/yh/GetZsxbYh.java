/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.StringUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 获取一条自述旬报信息
 * S12005
 * 说明：把整改记录一并查询
 * @author Asus
 */
@Service
public class GetZsxbYh extends BaseService {

    public static final String[] KEY = new String[]{"yh_id", "隐患编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData zsxb = queryData("get_zsxb_yh", in.getStringValue("yh_id"));
        if (zsxb == null || zsxb.isEmpty()) {
            throw new GlobalException(200019, "隐患");  //!#!不存在
        }
        zsxb.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhlb", zsxb.getStringValue("yhlb")));
        zsxb.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhjb", zsxb.getStringValue("yhjb")));
        zsxb.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_info.zt", zsxb.getStringValue("zt")));
        zsxb.putStringValue("zw_desc", SystemUtil.getColValueDesc("pl_user.zw", zsxb.getStringValue("zw")));
        List<AbstractCommonData> list = queryList("get_zgjl_yh", in.getStringValue("yh_id"));
        zsxb.putArrayValue("zgjl", list);
        for (AbstractCommonData zg : list) {
            zg.putStringValue("zgjg_desc", SystemUtil.getColValueDesc("aq_yh_zgjl.zgjg", zg.getStringValue("zgjg")));
        }
        out.putAll(zsxb);
    }
}
