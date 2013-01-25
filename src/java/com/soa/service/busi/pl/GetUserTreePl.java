/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.service.BaseService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 获取用户部门树
 * S11010
 * @author lianzt
 */
@Service
public class GetUserTreePl extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        List<AbstractCommonData> allUser = queryList("get_all_user_pl");
        //用户部门树
        Map<String, AbstractCommonData> tree = new HashMap<String, AbstractCommonData>();
        String depId, depName;
        //把用户放入部门树
        for (AbstractCommonData u : allUser) {
            depId = u.getStringValue("dep_id");
            depName = u.getStringValue("dep_name");
            if (tree.containsKey(u.getStringValue("dep_id"))) {
                //如果部门已经存在，把用户放入部门
                tree.get(depId).getArrayValue("users").add(u);
            } else {
                //如果不存在，创建部门
                AbstractCommonData dep = DataConvertFactory.getInstanceEmpty();
                LinkedList<AbstractCommonData> users = new LinkedList<AbstractCommonData>();
                users.add(u);
                dep.putStringValue("dep_id", depId);
                dep.putStringValue("dep_name", depName);
                dep.putArrayValue("users", users);
                tree.put(depId, dep);
            }
        }
        out.putArrayValue("dep_tree", new LinkedList<AbstractCommonData>(tree.values()));
    }
}
