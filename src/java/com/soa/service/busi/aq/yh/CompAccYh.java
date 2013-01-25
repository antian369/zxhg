/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公司验收
 * P12011
 * @author Asus
 */
@Service
public class CompAccYh extends BaseService {

    private static final String[] KEY = new String[]{"yh_id", "隐患编号",
                                                     "zg_id", "整改记录编号",
                                                     "gsfcrxm", "公司复查人姓名",
                                                     "gsysjg", "公司验收结果"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData session = getSession(in);
        if (!"安环部".equals(session.getStringValue("dep_name"))) {
            throw new GlobalException(200022);        //必须由安环部复查
        }

        String yhId = in.getStringValue("yh_id");
        String zgId = in.getStringValue("zg_id");
        String gsysjg = in.getStringValue("gsysjg");
        AbstractCommonData yh = queryData("get_yh", yhId);      //获取隐患
        if (yh == null || yh.isEmpty()) {
            throw new GlobalException(200019, "隐患");  //!#!不存在
        }
        if (!"4".equals(yh.getStringValue("zt"))) {
            throw new GlobalException(200028);      //只能复查分厂已验收的隐患
        }
        AbstractCommonData zgjl = queryData("get_zgjl", yhId, zgId);        //获取整改记录
        if (zgjl == null || zgjl.isEmpty()) {
            throw new GlobalException(200019, "整改记录");  //!#!不存在
        }
        if (!"0".equals(zgjl.getStringValue("zgjg"))) {
            throw new GlobalException(200024);        //不能对该记录做修改
        }
        Object[] args = new Object[6];      //整改参数 set gsfcr=?,gsfcrxm=?,gsfcbz=?, zgjg=? where yh_id=? and zg_id=?
        args[0] = in.getStringValue("gsfcr");
        args[1] = in.getStringValue("gsfcrxm");
        args[2] = in.getStringValue("gsfcbz");
        args[4] = yhId;
        args[5] = zgId;
        if ("1".equals(gsysjg)) {       //未通过
            args[3] = "2";      //整改状态置为 公司验收未通过
            update("update_yhzt_yh", "2", yhId);        //隐患状态置为 逾期
            update("comp_acc_yh", args);        //整改状态置为 分厂验收未通过
        } else if ("4".equals(gsysjg)) {     //复查通过
            args[3] = "4";       //复查通过
            update("update_yhzt_yh", "0", yhId);        //隐患状态置为 复查通过
            update("comp_acc_yh", args);        //只修改分厂相关信息
        } else {
            throw new GlobalException(210005, "参数");      //!#!不符！
        }
    }
}
