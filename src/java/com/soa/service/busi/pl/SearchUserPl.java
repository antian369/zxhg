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
 * 获取所有用户
 * S11006
 * @author Asus
 */
@Service
public class SearchUserPl extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        Object[] args = new Object[3];
        args[0] = in.getStringValue("username");
        args[1] = in.getStringValue("name");
        args[2] = in.getStringValue("dep_id");
        in.putStringValue("sql", "search_user_pl");
        in.putObjectValue("args", args);
        runService("S10001", in, inHead, out, outHead);
        List<AbstractCommonData> list = out.getArrayValue("result");
        if (list != null) {
            for (AbstractCommonData user : list) {
                user.putStringValue("zt_desc", SystemUtil.getColValueDesc("pl_user.zt", user.getStringValue("zt")));
                user.putStringValue("zw1_desc", SystemUtil.getColValueDesc("pl_user.zw", user.getStringValue("zw1")));
                user.putStringValue("zw2_desc", SystemUtil.getColValueDesc("pl_user.zw", user.getStringValue("zw2")));
                user.putStringValue("zw3_desc", SystemUtil.getColValueDesc("pl_user.zw", user.getStringValue("zw3")));
            }
        }
    }
}
