/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 申请延时
 * P12009
 * 说明：把前一条整改记录置为延时，再新增一条整改记录
 * @author Asus
 */
@Service
public class AppDelayYh extends BaseService {

    private static final String[] KEY = new String[]{"yh_id", "隐患编号",
                                                     "zg_id", "整改记录编号",
                                                     "zgrxm", "整改人姓名",
                                                     "zgcs", "整改措施",
                                                     "sqsx", "整改时限",
                                                     "sqsx_h", "整改时限(时)"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        String yhId = in.getStringValue("yh_id");
        Date sqsx = in.getDateValue("sqsx");
        sqsx = DateUtil.setHour(sqsx, in.getIntValue("sqsx_h"));        //设置时限的小时数
        AbstractCommonData yh = queryData("get_yh", yhId);      //获取隐患
        if (yh == null || yh.isEmpty()) {
            throw new GlobalException(200019, "隐患");  //!#!不存在
        }
        if (!"1".equals(yh.getStringValue("zt"))) {
            throw new GlobalException(200021);      //只能对正在整改的隐患申请延时
        }
        String oldZgId = in.getStringValue("zg_id");
        update("update_zgjl_yh", "3", yhId, oldZgId);       //延时申请
        String newZgId = SystemUtil.getSerialNum();
        Object[] args = new Object[7];      //新整改记录参数：yh_id,zg_id,zgr,zgrxm,zgcs,kssj(now()),sqsx
        args[0] = yhId;
        args[1] = newZgId;
        args[2] = in.getStringValue("zgr");
        args[3] = in.getStringValue("zgrxm");
        args[4] = in.getStringValue("zgcs");
        args[5] = sqsx;
        args[6] = "(申请延时)  " + StringUtil.changeNull(in.getStringValue("zgbz"));
        update("save_zgjl_yh", args);       //保存新更改记录
    }
}
