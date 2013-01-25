/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.service.BaseService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 获取用户特有权限
 * S11009
 * @author Asus
 */
@Service
public class GetUserLimitPl extends BaseService {

    private static final String[] KEY = new String[]{"username", "用户名"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        List<AbstractCommonData> limitList = queryList("get_all_menus_pl");
        for (AbstractCommonData acd : limitList) {
            if ("1".equals(acd.getStringValue("m_type"))) {     //给所有目录项增加一个子节点数组
                acd.putArrayValue("chidren", new LinkedList<AbstractCommonData>());
            }
        }
        out.putArrayValue("all_menu", GetUserAllLimitPl.marge(limitList));
        limitList = queryList("get_user_limit_pl", in.getStringValue("username"));
        out.putArrayValue("user_limit", limitList);
    }
}
