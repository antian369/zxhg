/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sb;

import com.lianzt.bean.DbDate;
import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 设备备品查询
 * S14003
 * @author Asus
 */
@Service
public class PageSbbpSb extends BaseService{

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
        acd.putStringValue("bpggxh", "11X22");     //规格型号
        acd.putStringValue("bpsjsm", "30天");     //设计寿命
        acd.putStringValue("bpyysm", "10天");     //已使用寿命
        acd.putStringValue("bpsl", "1");     //数量
        acd.putStringValue("bpcz", "金属");     //材质
        acd.putStringValue("bpth", "2");     //图号
        acd.putStringValue("bpycj", "天坛洗煤设备厂");     //原生产厂家
        acd.putStringValue("bpxcj", "天坛洗煤设备厂");     //现使用生产厂家
        acd.putDateValue("bpzjsj", new DbDate());     //最近一次更换时间
        acd.putStringValue("bpghyy", "无");     //更换原因分析
        acd.putStringValue("bpfy", "100");     //费用
        acd.putStringValue("bpsx", "3");     //显示顺序
        list.add(acd);
        //2....
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gymc", "煤化工工艺");
        acd.putStringValue("sbwh", "1");        //工艺设备位号
        acd.putStringValue("whsm", "洗煤");
        acd.putStringValue("sbmc", "压滤机");
        acd.putStringValue("bpmc", "洗煤备件");     //备件名称
        acd.putStringValue("bpggxh", "11X22");     //规格型号
        acd.putStringValue("bpsjsm", "30天");     //设计寿命
        acd.putStringValue("bpyysm", "10天");     //已使用寿命
        acd.putStringValue("bpsl", "1");     //数量
        acd.putStringValue("bpcz", "金属");     //材质
        acd.putStringValue("bpth", "2");     //图号
        acd.putStringValue("bpycj", "天坛洗煤设备厂");     //原生产厂家
        acd.putStringValue("bpxcj", "天坛洗煤设备厂");     //现使用生产厂家
        acd.putDateValue("bpzjsj", new DbDate());     //最近一次更换时间
        acd.putStringValue("bpghyy", "无");     //更换原因分析
        acd.putStringValue("bpfy", "100");     //费用
        acd.putStringValue("bpsx", "3");     //显示顺序
        list.add(acd);
        //3.........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("gymc", "煤化工工艺");
        acd.putStringValue("sbwh", "1");        //工艺设备位号
        acd.putStringValue("whsm", "洗煤");
        acd.putStringValue("sbmc", "压滤机");
        acd.putStringValue("bpmc", "洗煤备件");     //备件名称
        acd.putStringValue("bpggxh", "11X22");     //规格型号
        acd.putStringValue("bpsjsm", "30天");     //设计寿命
        acd.putStringValue("bpyysm", "10天");     //已使用寿命
        acd.putStringValue("bpsl", "1");     //数量
        acd.putStringValue("bpcz", "金属");     //材质
        acd.putStringValue("bpth", "2");     //图号
        acd.putStringValue("bpycj", "天坛洗煤设备厂");     //原生产厂家
        acd.putStringValue("bpxcj", "天坛洗煤设备厂");     //现使用生产厂家
        acd.putDateValue("bpzjsj", new DbDate());     //最近一次更换时间
        acd.putStringValue("bpghyy", "无");     //更换原因分析
        acd.putStringValue("bpfy", "100");     //费用
        acd.putStringValue("bpsx", "3");     //显示顺序
        list.add(acd);
        out.putArrayValue("result", list);
    }

}
