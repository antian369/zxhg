/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.StringUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分厂验收
 * P12010
 * 说明：验收成功需要修改整改结果与隐患记录、验收失败把状态置为逾期
 * @author Asus
 */
@Service
public class FactoryAccYh extends BaseService {

    private static final String[] KEY = new String[]{"yh_id", "隐患编号",
                                                     "zg_id", "整改记录编号",
                                                     "fcfcrxm", "分厂复查人姓名",
                                                     "fcysjg", "分厂验收结果"};
    //                                                     "fcfcr", "分厂复查人",
//                                                     "fcsj", "",
//                                                     "fcfcbz", ""};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        String yhId = in.getStringValue("yh_id");
        String zgId = in.getStringValue("zg_id");
        String fcysjg = in.getStringValue("fcysjg");
        AbstractCommonData yh = queryData("get_yh", yhId);      //获取隐患
        if (yh == null || yh.isEmpty()) {
            throw new GlobalException(200019, "隐患");  //!#!不存在
        }
        if (!"1".equals(yh.getStringValue("zt"))) {
            throw new GlobalException(200020);      //只能复查正在整改的隐患
        }
        AbstractCommonData zgjl = queryData("get_zgjl", yhId, zgId);        //获取整改记录
        if (zgjl == null || zgjl.isEmpty()) {
            throw new GlobalException(200019, "整改记录");  //!#!不存在
        }
        if (!"0".equals(zgjl.getStringValue("zgjg"))) {
            throw new GlobalException(200024);        //不能对该记录做修改
        }
        AbstractCommonData session = getSession(in);
        if (!session.getStringValue("dep_id").equals(yh.getStringValue("yhdw"))) {
            throw new GlobalException(200018);      //只能复查本厂的隐患
        }
        Object[] args = new Object[6];      //整改参数 set fcfcr=?,fcfcrxm=?,fcfcbz=?, zgjg=? where yh_id=? and zg_id=?
        args[0] = in.getStringValue("fcfcr");
        args[1] = in.getStringValue("fcfcrxm");
        args[2] = in.getStringValue("fcfcbz");
        args[4] = yhId;
        args[5] = zgId;
        if ("1".equals(fcysjg)) {       //未通过
            args[3] = "1";      //整改状态置为 分厂验收未通过
            update("update_yhzt_yh", "2", yhId);        //隐患状态置为 逾期
            update("factory_acc_yh", args);        //整改状态置为 分厂验收未通过
        } else if ("4".equals(fcysjg)) {     //复查通过
            args[3] = "0";       //不赋值
            update("update_yhzt_yh", "4", yhId);        //隐患状态置为 分厂已验收
            update("factory_acc_yh", args);        //只修改分厂相关信息
        } else {
            throw new GlobalException(210005, "整改结果参数");      //!#!不符！
        }
    }
}
