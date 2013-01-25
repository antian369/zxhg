/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.bq;

import com.lianzt.bean.DbDate;
import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.service.BaseService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 查询私信
 * S15002
 * @author lianzt
 */
@Service
public class PagePriMsgBq extends BaseService{

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
        acd.putStringValue("sxbt", "私信1");
        acd.putStringValue("sxnr", "1.......\n2..........\n3...............\n4444444444");
        acd.putStringValue("fsrxm", "测试员工1");
        acd.putDateValue("fssj", new DbDate());
        list.add(acd);
        //2..........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("sxbt", "私信2");
        acd.putStringValue("sxnr", "1.......\n2..........\n3...............\n4444444444");
        acd.putStringValue("fsrxm", "测试员工1");
        acd.putDateValue("fssj", new DbDate());
        list.add(acd);
        //3..........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("sxbt", "私信3");
        acd.putStringValue("sxnr", "1.......\n2..........\n3...............\n4444444444");
        acd.putStringValue("fsrxm", "测试员工1");
        acd.putDateValue("fssj", new DbDate());
        list.add(acd);
        //4..........
        acd = DataConvertFactory.getInstanceEmpty();
        acd.putStringValue("sxbt", "私信4");
        acd.putStringValue("sxnr", "1.......\n2..........\n3...............\n4444444444");
        acd.putStringValue("fsrxm", "测试员工1");
        acd.putDateValue("fssj", new DbDate());
        list.add(acd);
        out.putArrayValue("result", list);
    }    
}
