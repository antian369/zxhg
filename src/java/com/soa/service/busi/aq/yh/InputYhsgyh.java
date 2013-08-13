/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 隐患收购登记单
 * P12014
 * 说明：录入后向aq_yh_yhsg插入一条数据，隐患被核实后才会记录到aq_yh_info表
 * @author Asus
 */
@Service
public class InputYhsgyh extends BaseService {

    public static final String[] KEY = new String[]{"fxdw", "发现单位",
                                                    "fxr", "发现人",
                                                    "fxsj","发现时间",
                                                    "yhdw", "隐患单位",
                                                    "yhdd", "隐患地点",
                                                    "yhms", "隐患描述"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData ses = getSession(in);
        Object[] args = new Object[9];
        args[0] = SystemUtil.getSerialNum();
        args[1] = in.getStringValue("fxdw");
        args[2] = in.getStringValue("fxr");
        args[3] = in.getDateValue("fxsj");
        args[4] = in.getStringValue("yhdw");
        args[5] = in.getStringValue("yhdd");
        args[6] = in.getStringValue("yhms");
        args[7] = getLoginUser(in);
        args[8] = ses.getStringValue("name");
        update("save_yhsg_yh", args);
    }
}
