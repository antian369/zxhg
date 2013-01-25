/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.bean.DbDate;
import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.service.BaseService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 工艺日志查询
 * S13002
 * 说明：
 * @author Asus
 */
@Service
public class PageGyrzSc extends BaseService{

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        List<AbstractCommonData> list = new LinkedList<AbstractCommonData>();
        AbstractCommonData acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("lrdw", "热电厂");
        acd.putStringValue("lrrxm", "测试账户");
        acd.putDateValue("lrsj", new DbDate());
        acd.putStringValue("jczt", "测试主题1");
        acd.putStringValue("jcnr", "工艺检查内容1111111");
        acd.putStringValue("jb_desc", "面向公司全体人员");
        list.add(acd);
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("lrdw", "气化厂");
        acd.putStringValue("lrrxm", "测试账户");
        acd.putDateValue("lrsj", new DbDate());
        acd.putStringValue("jczt", "测试主题2");
        acd.putStringValue("jcnr", "工艺检查内容22222");
        acd.putStringValue("jb_desc", "面向公司全体人员");
        list.add(acd);
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("lrdw", "气化厂");
        acd.putStringValue("lrrxm", "测试账户");
        acd.putDateValue("lrsj", new DbDate());
        acd.putStringValue("jczt", "测试主题3");
        acd.putStringValue("jcnr", "工艺检查内容333333333");
        acd.putStringValue("jb_desc", "面向公司全体人员");
        list.add(acd);
        out.putArrayValue("result", list);
    }

}
