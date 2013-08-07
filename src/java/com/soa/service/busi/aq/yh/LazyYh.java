/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import static com.soa.service.BaseService.getLoginUser;
import static com.soa.service.BaseService.getSession;
import com.soa.util.SystemUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 审核延时申请
 * P12023
 * 说明：ystg字段，如果为1，表示不批准延时，此时把延时状态改变为未超时，然后由定时任务判断是否超时
 * @author lianzt
 */
@Service
public class LazyYh extends BaseService {

    private final String[] KEY = new String[]{"yh_id", "隐患编号", "ysbz", "验收备注", "ystg", "验收通过"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        AbstractCommonData ses = getSession(in);
        //把隐患的状态修改为 1正在整改
        //update aq_yh_info set zt=? where yh_id=?
        update("update_yhzt_yh", "1", in.getStringValue("yh_id"));
        if ("1".equals(in.getStringValue("ystg"))) {
            //不批准延时
            //隐患对应的整改记录中，把延时状态为 2申请延时 的改为 1未延时
            //update aq_yh_zg set lazy_zt=?, ysbz=?,ysr=?,ysrxm=?,yssj=now() where yh_id=? and lazy_zt=?
            update("lazy_not_zg_yh", "1", "不批准延时：" + in.getStringValue("ysbz"), getLoginUser(in), ses.getStringValue("name"), in.getStringValue("yh_id"), "2");
        } else {
            //批准延时
            //隐患对应的整改记录中，把延时状态为 2申请延时 的改为 3批准延时 并修改 验收状态为 3验收不通过
            //update aq_yh_zg set lazy_zt=?, yszt=?, ysbz=?,ysr=?,ysrxm=?,yssj=now() where yh_id=? and lazy_zt=?
            update("lazy_zg_ys_yh", "3", "3", "批准延时：" + in.getStringValue("ysbz"), getLoginUser(in), ses.getStringValue("name"), in.getStringValue("yh_id"), "2");
            //验收不通过时，需要再插入一新的整改记录
            //insert into aq_yh_zg (zg_id,yh_id,kssj,pzsx,lazy_zt,yszt) value (?,?,now(),?,'1','1')
            update("save_zg", SystemUtil.getSerialNum(), in.getStringValue("yh_id"), in.getDateValue("pzsx"));
        }
    }
}
