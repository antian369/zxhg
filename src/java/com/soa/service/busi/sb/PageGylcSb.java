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
 * 查询工艺流程
 * S14001
 * @author Asus
 */
@Service
public class PageGylcSb extends BaseService{

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
//        if(StringUtil.isNull(in.getStringValue("do"))){
//            return;
//        }
        List<AbstractCommonData> list = new LinkedList<AbstractCommonData>();
        AbstractCommonData acd = null;
        //1..........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gybh", "#001");
        acd.putStringValue("gymc", "煤化工工艺");
        acd.putStringValue("gyfc", "备煤车间");
        acd.putStringValue("gyms", "原煤->煤场->配煤->洗煤->粉碎->煤塔.....");
        acd.putStringValue("lrrxm", "测试人员1");
        acd.putDateValue("lrsj", new DbDate());
        list.add(acd);

        //2........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gybh", "#002");
        acd.putStringValue("gymc", "甲醇生产工艺");
        acd.putStringValue("gyfc", "甲醇厂");
        acd.putStringValue("gyms", "汽化,净化,合成,精馏");
        acd.putStringValue("lrrxm", "测试人员1");
        acd.putDateValue("lrsj", new DbDate());
        list.add(acd);
        out.putArrayValue("result", list);
    }

}
