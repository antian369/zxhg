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
 * 分厂验收安全检查
 * P12017
 * 说明：验收时需要补充完整整改记录
 * 参数：fcysjg 4通过、1未通过
 * @author Asus
 */
@Service
public class FactoryAccYhjc extends BaseService{

    private static final String[] KEY = new String[]{"yh_id", "隐患编号",
                                                     "zg_id", "整改记录编号",
                                                     "fcfcrxm", "分厂复查人姓名",
                                                     "fcysjg", "分厂验收结果",
                                                     "zgrxm", "整改人姓名",
                                                     "zgcs", "整改措施"};
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
        Object[] args = new Object[9];      //整改参数 set zgr=?, zgrxm=?, zgcs=?, fcfcr=?,fcfcrxm=?,fcfcbz=?, zgjg=? where yh_id=? and zg_id=?
        args[0] = in.getStringValue("zgr");
        args[1] = in.getStringValue("zgrxm");
        args[2] = in.getStringValue("zgcs");
        args[3] = in.getStringValue("fcfcr");
        args[4] = in.getStringValue("fcfcrxm");
        args[5] = in.getStringValue("fcfcbz");
        args[7] = yhId;
        args[8] = zgId;
        if ("1".equals(fcysjg)) {       //未通过
            args[6] = "1";      //整改状态置为 分厂验收未通过
            update("update_yhzt_yh", "2", yhId);        //隐患状态置为 逾期
            update("factory_acc_yhjc_yh", args);        //整改状态置为 分厂验收未通过
        } else if ("4".equals(fcysjg)) {     //复查通过
            args[6] = "0";       //不赋值
            update("update_yhzt_yh", "4", yhId);        //隐患状态置为 分厂已验收
            update("factory_acc_yhjc_yh", args);        //只修改分厂相关信息
        } else {
            throw new GlobalException(210005, "整改结果参数");      //!#!不符！
        }
    }

}
