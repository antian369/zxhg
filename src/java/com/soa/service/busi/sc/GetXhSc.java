/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 获取日消耗
 * S13013
 * 说明：可选参数 scrq 为空是查询当天
 * @author lianzt
 */
@Service
public class GetXhSc extends BaseService {

    private static final String[] KEY = new String[]{"xhpbh", "消耗品编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        List<AbstractCommonData> list = queryList("get_xh_sc", in.getDateValue("scrq"), in.getStringValue("xhpbh"));
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData cl : list) {
                if ("b".equals(cl.getStringValue("bc"))) {
                    out.put("bb", cl.get("xhl"));
                } else if ("y".equals(cl.getStringValue("bc"))) {
                    out.put("yb", cl.get("xhl"));
                } else if ("z".equals(cl.getStringValue("bc"))) {
                    out.put("zb", cl.get("xhl"));
                }
            }
        }
        AbstractCommonData kc = queryData("get_xhpkc_sc", in.getDateValue("scrq"), in.getStringValue("xhpbh"));
        if (kc != null && !kc.isEmpty()) {
            out.put("kc", kc.get("kc"));
            out.put("cqe", kc.get("cqe"));
            out.put("bz", kc.get("bz"));
        }
    }
}