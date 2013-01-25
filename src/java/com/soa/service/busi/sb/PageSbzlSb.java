/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sb;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 查询设备资料
 * S14004
 * @author Asus
 */
@Service
public class PageSbzlSb extends BaseService{

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
        //1...
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gymc", "煤化工工艺");
        acd.putStringValue("sbwh", "1");        //工艺设备位号
        acd.putStringValue("whsm", "洗煤");     //位号描述
        acd.putStringValue("sbmc", "压滤机");   //设备名称
        acd.putStringValue("bpmc", "洗煤备件");     //备件名称
        acd.putStringValue("zlbt", "设备保养说明"); //资料标题
        acd.putStringValue("zlnr", "1....\n2...\n3...\n");  //内容
        list.add(acd);
        //1...
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gymc", "煤化工工艺");
        acd.putStringValue("sbwh", "1");        //工艺设备位号
        acd.putStringValue("whsm", "洗煤");
        acd.putStringValue("sbmc", "压滤机");
        acd.putStringValue("bpmc", "洗煤备件");     //备件名称
        acd.putStringValue("zlbt", "设备保养说明");
        acd.putStringValue("zlnr", "1....\n2...\n3...\n");
        list.add(acd);
        //1...
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gymc", "煤化工工艺");
        acd.putStringValue("sbwh", "1");        //工艺设备位号
        acd.putStringValue("whsm", "洗煤");
        acd.putStringValue("sbmc", "压滤机");
        acd.putStringValue("bpmc", "洗煤备件");     //备件名称
        acd.putStringValue("zlbt", "设备保养说明");
        acd.putStringValue("zlnr", "1....\n2...\n3...\n");
        list.add(acd);
        //1...
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gymc", "煤化工工艺");
        acd.putStringValue("sbwh", "1");        //工艺设备位号
        acd.putStringValue("whsm", "洗煤");
        acd.putStringValue("sbmc", "压滤机");
        acd.putStringValue("bpmc", "洗煤备件");     //备件名称
        acd.putStringValue("zlbt", "设备保养说明");
        acd.putStringValue("zlnr", "1....\n2...\n3...\n");
        list.add(acd);
        out.putArrayValue("result", list);
    }

}
