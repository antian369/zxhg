/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import org.springframework.stereotype.Service;

/**
 * 获取单个用户信息
 * S11007
 * @author Asus
 */
@Service
public class GetUserPl extends BaseService {

    private static final String[] KEY = new String[]{"username", "用户名"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData user = queryData("get_user_pl", in.getStringValue("username"));
        if (user != null) {
            out.putAll(user);
            out.putStringValue("zt_desc", SystemUtil.getColValueDesc("pl_user.zt", out.getStringValue("zt")));
            out.putStringValue("zw1_desc", SystemUtil.getColValueDesc("pl_user.zw", out.getStringValue("zw1")));
            out.putStringValue("zw2_desc", SystemUtil.getColValueDesc("pl_user.zw", out.getStringValue("zw2")));
            out.putStringValue("zw3_desc", SystemUtil.getColValueDesc("pl_user.zw", out.getStringValue("zw3")));
        }
    }
}
