/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保存角色权限
 * P11009
 * @author Asus
 */
@Service
public class SaveRoleLimitPl extends BaseService {

    private static final String[] KEY = new String[]{"role_id", "角色编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        update("del_role_limit_pl", in.getStringValue("role_id"));      //先删除旧数据
        if (StringUtil.isNull(in.getStringValue("menus"))) {
            return;
        }
        String[] menus = in.getStringValue("menus").replace("undefined", "").split(",");     //菜单项
        List<String> menuList = new LinkedList<String>();
        for (String s : menus) {            //删除重复项
            if (!StringUtil.isNull(s)) {
                if (!menuList.contains(s)) {
                    menuList.add(s);
                }
            }
        }
        Object[][] argsArr = new Object[menuList.size()][3];        //组装参数
        for (int i = 0; i < argsArr.length; i++) {
            argsArr[i][0] = in.getStringValue("role_id");
            argsArr[i][1] = menuList.get(i);
            argsArr[i][2] = getLoginUser(in);
        }
        batchUpdate("save_role_limit_pl", argsArr);     //插入新的授权
    }
}
