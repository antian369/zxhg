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
 * 获取隐患的整改记录
 * S12022
 * @author lianzt
 */
@Service
public class GetZgYh extends BaseService {

    private final String[] KEY = new String[]{"yh_id", "隐患编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //select * from aq_yh_zg where yh_id=? order by kssj
        List<AbstractCommonData> list = queryList("get_zg_yh", in.getStringValue("yh_id"));
        if (list == null) {
            list = new LinkedList<AbstractCommonData>();
        }
        for (AbstractCommonData acd : list) {
            acd.putStringValue("lazy_zt_desc", SystemUtil.getColValueDesc("aq_yh_zg.lazy_zt", acd.getStringValue("lazy_zt")));
            acd.putStringValue("yszt_desc", SystemUtil.getColValueDesc("aq_yh_zg.yszt", acd.getStringValue("yszt")));
        }
        out.putArrayValue("zgs", list);
    }
}
