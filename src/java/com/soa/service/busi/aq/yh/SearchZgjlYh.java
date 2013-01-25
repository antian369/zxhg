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
 * 查询整改记录
 * S12006
 * 说明：查询条件 yhmc yhlb yhjb yhdw zgjg ly zt
 * @author Asus
 */
@Service
public class SearchZgjlYh extends BaseService{

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
        Object[] args = new Object[7];      //yhmc yhlb yhjb yhdw zgjg ly
        if (!StringUtil.isNull(in.getStringValue("yhmc"))) {
            args[0] = "%" + in.getStringValue("yhmc") + "%";
        } else {
            args[0] = "";
        }
        args[1] = in.getStringValue("yhlb");
        args[2] = in.getStringValue("yhjb");
        args[3] = in.getStringValue("yhdw");
        args[4] = in.getStringValue("zgjg");
        args[5] = in.getStringValue("ly");
        args[6] = in.getStringValue("zt");
        in.putStringValue("sql", "search_zgjl_yh");
        in.putObjectValue("args", args);
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for (AbstractCommonData zsxb : list) {
            zsxb.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhlb", zsxb.getStringValue("yhlb")));
            zsxb.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhjb", zsxb.getStringValue("yhjb")));
            zsxb.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_info.zt", zsxb.getStringValue("zt")));
            zsxb.putStringValue("zw_desc", SystemUtil.getColValueDesc("pl_user.zw", zsxb.getStringValue("zw")));
            zsxb.putStringValue("zgjg_desc", SystemUtil.getColValueDesc("aq_yh_zgjl.zgjg", zsxb.getStringValue("zgjg")));
            zsxb.putStringValue("ly_desc", SystemUtil.getColValueDesc("aq_yh_info.ly", zsxb.getStringValue("ly")));
        }
        out.putAll(page);
    }

}
