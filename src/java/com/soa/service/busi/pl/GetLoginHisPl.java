/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 获取详细登录历史
 * S11013
 * @author lianzt
 */
@Service
public class GetLoginHisPl extends BaseService {

    private final String[] KEY = new String[]{"username", "用户名"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //select * from pl_login_his where username=?
        List<AbstractCommonData> list = queryList("get_login_his_pl", in.getStringValue("username"));
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData l : list) {
                l.putStringValue("zt_desc", SystemUtil.getColValueDesc("pl_login_his.zt", l.getStringValue("zt")));
            }
        }
        out.putArrayValue("logins", list);
    }
}
