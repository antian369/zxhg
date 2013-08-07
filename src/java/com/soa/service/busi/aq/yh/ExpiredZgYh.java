/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 定时修改隐患状态
 * P12013
 * 说明：把隐患状态修改为 4逾期未整改，再把整改记录修改为 3验收不通过 3超时，然后再新增一条整改记录
 * @author Asus
 */
@Service
public class ExpiredZgYh extends BaseService {

    private static final Logger log = Logger.getLogger(ExpiredZgYh.class);

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
            AbstractCommonData out, AbstractCommonData outHead) {
        //查询，隐患状态为 1正在整改， 验收状态为 1正在整改， 延时状态为 1未超时 的项
        //select yh_id, zg_id, pzsx from aq_yh_zg_v where zg='1' and lazy_zt='1' and yszt='1'
        List<AbstractCommonData> list = queryList("get_judg_zg");
        if (list == null || list.isEmpty()) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("查询到了 " + list.size() + " 条数据。");
            if (log.isDebugEnabled()) {
                for (AbstractCommonData zg : list) {
                    log.debug(zg);
                }
            }
        }
        Date expireDate = null;
        long nowTime = new Date().getTime();
        List<Object[]> zgArgs = new LinkedList<Object[]>();
        List<Object[]> yhArgs = new LinkedList<Object[]>();
        List<Object[]> newZgArgs = new LinkedList<Object[]>();

        for (AbstractCommonData zg : list) {
            expireDate = zg.getDateValue("pzsx");
            if (log.isDebugEnabled()) {
                log.debug(DateUtil.detaledFormat(expireDate));
            }
            if (log.isDebugEnabled()) {
                log.debug("时限：" + expireDate.getTime() + "， 当前时间:" + nowTime);
            }
            if (expireDate.getTime() < nowTime) {
                //如果比当前时间大
                zgArgs.add(new Object[]{zg.getStringValue("yh_id"),
                                        zg.getStringValue("zg_id")});
                yhArgs.add(new Object[]{"4", zg.getStringValue("yh_id")});
                newZgArgs.add(new Object[]{SystemUtil.getSerialNum(), zg.getStringValue("yh_id"), zg.getDateValue("pzsx")});
            }
        }
        if (log.isInfoEnabled()) {
            log.info("需要更新 " + zgArgs.size() + " 条数据。");
            if (log.isDebugEnabled()) {
                for (Object[] row : zgArgs) {
                    log.debug("==>" + StringUtil.connectArray(row, ","));
                }
                for (Object[] row : yhArgs) {
                    log.debug("==>" + StringUtil.connectArray(row, ","));
                }
            }
        }
        batchUpdate("update_zgjl_yh", zgArgs);
        batchUpdate("update_yhzt_yh", yhArgs);
        batchUpdate("save_zg", newZgArgs);
    }
}
