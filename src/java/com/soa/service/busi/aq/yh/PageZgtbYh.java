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
 * 查询整改通报
 * S12014
 * 说明：查询aq_yh_zgjl_v
 * 参数：yhlb ly yhdw yhjb yhmc
 * @author Asus
 */
@Service
public class PageZgtbYh extends BaseService {

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
        Object[] args = new Object[5];      //yhmc yhlb ly yhjb yhdw
        if (!StringUtil.isNull(in.getStringValue("yhmc"))) {
            args[0] = "%" + in.getStringValue("yhmc") + "%";
        } else {
            args[0] = "";
        }
        args[1] = in.getStringValue("ly");
        args[2] = in.getStringValue("yhlb");
        args[3] = in.getStringValue("yhjb");
        args[4] = in.getStringValue("yhdw");
        in.putStringValue("sql", "page_zgtb_yh");
        in.putObjectValue("args", args);
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for (AbstractCommonData yhjc : list) {
            yhjc.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhlb", yhjc.getStringValue("yhlb")));
            yhjc.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhjb", yhjc.getStringValue("yhjb")));
            yhjc.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_info.zt", yhjc.getStringValue("zt")));
            yhjc.putStringValue("ly_desc", SystemUtil.getColValueDesc("aq_yh_info.ly", yhjc.getStringValue("ly")));
        }
        out.putAll(page);
    }
}
