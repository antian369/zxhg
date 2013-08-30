/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 获取在线用户列表
 * S11011
 * @author lianzt
 */
@Service
public class GetOnLinePl extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //select * from pl_login_his where zt='0'
        out.putArrayValue("users", queryList("get_on_line_pl"));
    }
}
