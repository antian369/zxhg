/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sb;

import com.lianzt.bean.DbDate;
import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 查询工艺设备
 * S14002
 * @author Asus
 */
@Service
public class PageGysbSb extends BaseService{

    private static final Logger log  = Logger.getLogger(PageGysbSb.class);
    
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
        //1..........工艺流程编号 设备位号 设备名称 出厂编号 型号规格 结构类型 设备重量 介质成份 生产厂家 生产日期 安装日期 投用日期 设备分类 技术参数
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gymc", "煤化工工艺");
        acd.putStringValue("sbwh", "1");
        acd.putStringValue("whsm", "洗煤");
        acd.putStringValue("sbmc", "压滤机");
        acd.putStringValue("ccbh", "201209002233");
        acd.putStringValue("xhgg", "#2000");
        acd.putStringValue("jglx", "");
        acd.putStringValue("sbzl", "2012kg");
        acd.putStringValue("jzcf", "..");
        acd.putStringValue("sccj", "临沂永顺机械有限公司");
        acd.putDateValue("scrq", new DbDate());
        acd.putDateValue("azrq", new DbDate());
        acd.putDateValue("tyrq", new DbDate());
        acd.putStringValue("sbfl", "..");
        acd.putStringValue("jscs", "......");
        acd.putDateValue("zjjxsj", new DbDate());
        acd.putStringValue("jxdjs", "1天6小时");
        list.add(acd);
        //2..........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gymc", "煤化工工艺");
        acd.putStringValue("sbwh", "2");
        acd.putStringValue("whsm", "粉碎");
        acd.putStringValue("sbmc", "破碎机");
        acd.putStringValue("ccbh", "201209002233");
        acd.putStringValue("xhgg", "750cmX60cm");
        acd.putStringValue("jglx", "");
        acd.putStringValue("sbzl", "2012kg");
        acd.putStringValue("jzcf", "..");
        acd.putStringValue("sccj", "临沂永顺机械有限公司");
        acd.putDateValue("scrq", new DbDate());
        acd.putDateValue("azrq", new DbDate());
        acd.putDateValue("tyrq", new DbDate());
        acd.putStringValue("sbfl", "辊式破碎机");
        acd.putStringValue("jscs", "......");
        acd.putDateValue("zjjxsj", new DbDate());
        acd.putStringValue("jxdjs", "2天3小时");
        list.add(acd);
        //3./........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gymc", "煤化工工艺");
        acd.putStringValue("sbwh", "1");
        acd.putStringValue("whsm", "洗煤");
        acd.putStringValue("sbmc", "破碎机");
        acd.putStringValue("ccbh", "201209002233");
        acd.putStringValue("xhgg", "750cmX60cm");
        acd.putStringValue("jglx", "");
        acd.putStringValue("sbzl", "2012kg");
        acd.putStringValue("jzcf", "..");
        acd.putStringValue("sccj", "临沂永顺机械有限公司");
        acd.putDateValue("scrq", new DbDate());
        acd.putDateValue("azrq", new DbDate());
        acd.putDateValue("tyrq", new DbDate());
        acd.putStringValue("sbfl", "辊式破碎机");
        acd.putStringValue("jscs", "......");
        acd.putDateValue("zjjxsj", new DbDate());
        acd.putStringValue("jxdjs", "1天13小时");
        list.add(acd);
        out.putArrayValue("result", list);
    }

}
