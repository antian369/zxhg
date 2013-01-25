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
 * 隐患收购分页查询
 * S12010
 * 说明：隐患管理分类下的查询，查询隐患收购视图
 * 参数：zt yhlb yhdw yhjb yhmc fxrxm
 * @author Asus
 */
@Service
public class PageYhsgYh extends BaseService{

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
        Object[] args = new Object[6];      //yhmc fxrxm zt yhlb yhjb yhdw
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
        in.putStringValue("sql", "page_yhsg_yh");
        in.putObjectValue("args", args);
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for (AbstractCommonData yhsg : list) {
            yhsg.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhlb", yhsg.getStringValue("yhlb")));
            yhsg.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhjb", yhsg.getStringValue("yhjb")));
            yhsg.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_info.zt", yhsg.getStringValue("zt")));
            yhsg.putStringValue("sgzt_desc", SystemUtil.getColValueDesc("aq_yh_yhsg.sgzt", yhsg.getStringValue("sgzt")));
        }
        out.putAll(page);
    }

}
