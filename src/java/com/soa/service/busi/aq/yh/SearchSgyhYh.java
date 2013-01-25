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
 * 查询收购的隐患
 * S12009
 * 说明：查询视图，条件：yhmc fxrxm zt yhlb yhjb yhdw
 * @author Asus
 */
@Service
public class SearchSgyhYh extends BaseService{

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
        Object[] args = new Object[6];      //yhmc tbrxm zt yhlb yhjb yhdw
        if (!StringUtil.isNull(in.getStringValue("yhmc"))) {
            args[0] = "%" + in.getStringValue("yhmc") + "%";
        } else {
            args[0] = "";
        }
        if (!StringUtil.isNull(in.getStringValue("fxrxm"))) {
            args[1] = "%" + in.getStringValue("fxrxm") + "%";
        } else {
            args[1] = "";
        }
        args[2] = in.getStringValue("zt");
        args[3] = in.getStringValue("yhlb");
        args[4] = in.getStringValue("yhjb");
        args[5] = in.getStringValue("yhdw");
        in.putStringValue("sql", "search_sgyh_yh");
        in.putObjectValue("args", args);
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for (AbstractCommonData zsxb : list) {
            zsxb.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhlb", zsxb.getStringValue("yhlb")));
            zsxb.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhjb", zsxb.getStringValue("yhjb")));
            zsxb.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_info.zt", zsxb.getStringValue("zt")));
            zsxb.putStringValue("zw_desc", SystemUtil.getColValueDesc("pl_user.zw", zsxb.getStringValue("zw")));
        }
        out.putAll(page);
    }

}
