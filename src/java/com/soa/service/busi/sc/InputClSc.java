/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 产量录入
 * P13001
 * 说明：可选参数 zb中班, bb白班,  yb夜班, kc库存(默认为0), bz库存备注
 * @author lianzt
 */
@Service
public class InputClSc extends BaseService {

    private static final String[] KEY = new String[]{"scrq", "生产日期",
                                                     "cpbh", "产品编号"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //查中班
        Date scrq = in.getDateValue("scrq");        //生产日期
        if (new Date().getTime() < scrq.getTime()) {
            throw new GlobalException(200032, "生产日期");       //!#!不能大于当前时间
        }
        String cpbh = in.getStringValue("cpbh");    //产品编号
        AbstractCommonData ses = getSession(in);
        AbstractCommonData today = null;
        Object[] insertArgs = new Object[6];
        insertArgs[0] = scrq;
        insertArgs[1] = cpbh;
        insertArgs[4] = getLoginUser(in);
        insertArgs[5] = ses.getStringValue("name");
        Object[] updateArgs = new Object[6];
        updateArgs[1] = getLoginUser(in);
        updateArgs[2] = ses.getStringValue("name");
        updateArgs[3] = scrq;
        updateArgs[4] = cpbh;

        //判断中班
        today = queryData("get_one_cl_sc", scrq, cpbh, "z");
        if (today == null || today.isEmpty()) {
            //插入
            insertArgs[2] = "z";        //中班
            insertArgs[3] = in.getDoubleValue("zb");        //中班产量
            update("save_one_cl_sc", insertArgs);
        } else {
            //修改
            if (in.getDoubleValue("zb") != null //由于数据库中cl不能为空，所以只需要判断请求报文
                    && today.getDoubleValue("cl") != in.getDoubleValue("zb")) {
                //产量不为空，而且不同时再修改
                updateArgs[0] = in.getDoubleValue("zb");
                updateArgs[5] = "z";        //中班
                update("update_one_cl_sc", updateArgs);
            }
        }

        //判断夜班
        today = queryData("get_one_cl_sc", scrq, cpbh, "y");
        if (today == null || today.isEmpty()) {
            //插入
            insertArgs[2] = "y";        //夜班
            insertArgs[3] = in.getDoubleValue("yb");        //夜班产量
            update("save_one_cl_sc", insertArgs);
        } else {
            //修改
            if (in.getDoubleValue("zb") != null //由于数据库中cl不能为空，所以只需要判断请求报文
                    && today.getDoubleValue("cl") != in.getDoubleValue("yb")) {
                //产量不为空，而且不同时再修改
                updateArgs[0] = in.getDoubleValue("yb");
                updateArgs[5] = "y";        //夜班
                update("update_one_cl_sc", updateArgs);
            }
        }

        //判断白班
        today = queryData("get_one_cl_sc", scrq, cpbh, "b");
        if (today == null || today.isEmpty()) {
            //插入
            insertArgs[2] = "b";        //白班
            insertArgs[3] = in.getDoubleValue("bb");        //白班产量
            update("save_one_cl_sc", insertArgs);
        } else {
            //修改
            if (in.getDoubleValue("bb") != null //由于数据库中cl不能为空，所以只需要判断请求报文
                    && today.getDoubleValue("cl") != in.getDoubleValue("bb")) {
                //产量不为空，而且不同时再修改
                updateArgs[0] = in.getDoubleValue("bb");
                updateArgs[5] = "b";        //白班
                update("update_one_cl_sc", updateArgs);
            }
        }

        //判断库存
        today = queryData("get_one_kc_sc", scrq, cpbh);
        if (today == null || today.isEmpty()) {
            //数据库为空，请求不为空
            //插入
            insertArgs = new Object[6];
            insertArgs[0] = scrq;
            insertArgs[1] = cpbh;
            insertArgs[2] = in.getDoubleValue("kc") == null ? 0 : in.getDoubleValue("kc");
            insertArgs[3] = getLoginUser(in);
            insertArgs[4] = ses.getStringValue("name");
            insertArgs[5] = in.getStringValue("bz");
            update("save_one_kc_sc", insertArgs);
        } else {
            //数据库不为空，请求不为空
            //更新
            updateArgs = new Object[6];
            updateArgs[0] = in.getDoubleValue("kc") == null ? 0 : in.getDoubleValue("kc");
            updateArgs[1] = in.getStringValue("bz");
            updateArgs[2] = getLoginUser(in);
            updateArgs[3] = ses.getStringValue("name");
            updateArgs[4] = scrq;
            updateArgs[5] = cpbh;
            update("update_one_kc_sc", updateArgs);
        }
    }
}
