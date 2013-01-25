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
 * 获取一条安全查询
 * S12013
 * 说明：同时查询整改记录
 * @author Asus
 */
@Service
public class GetYhjcYh extends BaseService {

    private static final String[] KEY = new String[]{"yh_id", "隐患编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData yhjc = queryData("get_yhjc_v_yh", in.getStringValue("yh_id"));
        if (yhjc == null || yhjc.isEmpty()) {
            throw new GlobalException(200019, "隐患");  //!#!不存在
        }
        yhjc.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhlb", yhjc.getStringValue("yhlb")));
        yhjc.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhjb", yhjc.getStringValue("yhjb")));
        yhjc.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_info.zt", yhjc.getStringValue("zt")));
        yhjc.putStringValue("jclx_desc", SystemUtil.getColValueDesc("aq_yh_yhjc.jclx", yhjc.getStringValue("jclx")));
        List<AbstractCommonData> list = queryList("get_zgjl_yh", in.getStringValue("yh_id"));
        yhjc.putArrayValue("zgjl", list);
        for (AbstractCommonData zg : list) {
            zg.putStringValue("zgjg_desc", SystemUtil.getColValueDesc("aq_yh_zgjl.zgjg", zg.getStringValue("zgjg")));
        }
        out.putAll(yhjc);
    }
}
