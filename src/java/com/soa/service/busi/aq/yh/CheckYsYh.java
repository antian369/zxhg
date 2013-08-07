/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 审核验收申请
 * P12022
 * @author lianzt
 */
@Service
public class CheckYsYh extends BaseService {

    private final String[] KEY = new String[]{"yh_id", "隐患编号", "ysbz", "验收备注", "ystg", "验收通过"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData ses = getSession(in);
        //把隐患的状态修改为 1或5
        //update aq_yh_info set zt=? where yh_id=?
        update("update_yhzt_yh", in.getStringValue("ystg"), in.getStringValue("yh_id"));
        if ("5".equals(in.getStringValue("ystg"))) {
            //验收通过
            //隐患对应的整改记录中，把验收状态为 2申请验收 的改为 4验收通过
            //update aq_yh_zg set yszt=?, ysbz=?,ysr=?,ysrxm=?,yssj=now() where yh_id=? and yszt=?
            update("check_zg_ys_yh", "4", in.getStringValue("ysbz"), getLoginUser(in), ses.getStringValue("name"), in.getStringValue("yh_id"), "2");
        } else {
            //验收不通过
            //隐患对应的整改记录中，把验收状态为 2申请验收 的改为 3验收不通过
            //update aq_yh_zg set yszt=?, ysbz=?,ysr=?,ysrxm=?,yssj=now() where yh_id=? and yszt=?
            update("check_zg_ys_yh", "3", in.getStringValue("ysbz"), getLoginUser(in), ses.getStringValue("name"), in.getStringValue("yh_id"), "2");
            //验收不通过时，需要再插入一新的整改记录
            //insert into aq_yh_zg (zg_id,yh_id,kssj,pzsx,lazy_zt,yszt) value (?,?,now(),?,'1','1')
            update("save_zg", SystemUtil.getSerialNum(), in.getStringValue("yh_id"), in.getDateValue("pzsx"));
        }
    }
}
