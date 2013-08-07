/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 获取某单位的隐患信息
 * S12021
 * @author lianzt
 */
@Service
public class SearchYhdwYh extends BaseService {

    private final String[] KEY = new String[]{"zt", "状态",
                                              "yhdw", "隐患单位"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //select * from aq_yh_info where (zt=? or zt='4') and yhdw=? order by lrsj desc
        List<AbstractCommonData> list = queryList("search_yhdw_yh", in.getStringValue("zt"), in.getStringValue("yhdw"));
        if (list == null) {
            list = new LinkedList<AbstractCommonData>();
        }
        for (AbstractCommonData acd : list) {
            acd.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhlb", acd.getStringValue("yhlb")));
            acd.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhjb", acd.getStringValue("yhjb")));
            acd.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_info.zt", acd.getStringValue("zt")));
            acd.putStringValue("ly_desc", SystemUtil.getColValueDesc("aq_yh_info.ly", acd.getStringValue("ly")));
        }
        out.putArrayValue("yhs", list);
    }
}
