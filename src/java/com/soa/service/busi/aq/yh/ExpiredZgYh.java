/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 定时修改隐患状态
 * P12013
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
        //select yh_id, zg_id, sqsx, pzsx from aq_yh_zgjl where zgjg is null and fcsj is not null
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
        final List<Object[]> zgjlArgs = new LinkedList<Object[]>();
        final List<Object[]> yhArgs = new LinkedList<Object[]>();
        for (AbstractCommonData zg : list) {
            if (zg.getDateValue("pzsx") == null) {
                expireDate = zg.getDateValue("sqsx");   //如果批准时限为空，取申请时限判断
            } else {
                expireDate = zg.getDateValue("pzsx");
            }
            if (log.isDebugEnabled()) {
                log.debug(DateUtil.detaledFormat(expireDate));
            }
            if (expireDate != null) {
                if (log.isDebugEnabled()) {
                    log.debug("时限：" + expireDate.getTime() + "， 当前时间:" + nowTime);
                }
                if (expireDate.getTime() < nowTime) {
                    //如果比当前时间大
                    zgjlArgs.add(new String[]{"5", zg.getStringValue("yh_id"),
                                              zg.getStringValue("zg_id")});
                    yhArgs.add(new String[]{"2", zg.getStringValue("yh_id")});
                }
            }
        }
        if (log.isInfoEnabled()) {
            log.info("需要更新 " + zgjlArgs.size() + " 条数据。");
            if (log.isDebugEnabled()) {
                for (Object[] row : zgjlArgs) {
                    log.debug("==>" + StringUtil.connectArray(row, ","));
                }
                for (Object[] row : yhArgs) {
                    log.debug("==>" + StringUtil.connectArray(row, ","));
                }
            }
        }
        batchUpdate("update_zgjl_yh", zgjlArgs);
        batchUpdate("update_yhzt_yh", yhArgs);
    }
}
