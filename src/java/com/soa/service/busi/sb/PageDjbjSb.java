/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sb;

import com.lianzt.bean.DbDate;
import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.service.BaseService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 点检班级查询
 * S14005
 * @author lianzt
 */
@Service
public class PageDjbjSb extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        List<AbstractCommonData> list = new LinkedList<AbstractCommonData>();
        AbstractCommonData acd = null;
        //1..........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("bjbh", "001");
        acd.putStringValue("bjmc", "班级1");
        acd.putStringValue("gyfc", "备煤车间");
        acd.putStringValue("bjms", "设置维护");
        acd.putStringValue("lrrxm", "测试员工1");
        acd.putDateValue("lrsj", new DbDate());
        list.add(acd);
        //2..........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("bjbh", "002");
        acd.putStringValue("bjmc", "班级2");
        acd.putStringValue("gyfc", "备煤车间");
        acd.putStringValue("bjms", "设置维护");
        acd.putStringValue("lrrxm", "测试员工1");
        acd.putDateValue("lrsj", new DbDate());
        list.add(acd);
        //3..........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("bjbh", "003");
        acd.putStringValue("bjmc", "班级3");
        acd.putStringValue("gyfc", "备煤车间");
        acd.putStringValue("bjms", "设置维护");
        acd.putStringValue("lrrxm", "测试员工1");
        acd.putDateValue("lrsj", new DbDate());
        list.add(acd);
        out.putArrayValue("result", list);
    }
}
