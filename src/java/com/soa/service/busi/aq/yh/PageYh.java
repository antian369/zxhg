/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.aq.yh;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import static com.soa.service.BaseService.runService;
import com.soa.util.SystemUtil;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 隐患分页查询
 * S12024
 * @author lianzt
 */
@Service
public class PageYh extends BaseService {

    private Date BEGIN = null;
    private final Logger log = LoggerFactory.getLogger(PageYh.class);

    @PostConstruct
    public void init() {
        try {
            BEGIN = DateUtil.parseDate("2013-01-01 00:00:00");
        } catch (Exception e) {
            log.warn("初始化日期出错：", e);
            BEGIN = new Date();
            BEGIN = DateUtil.addMonth(BEGIN, -120);
        }
    }

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        //select yh_id,yhmc,yhlb,yhms,yhjb,yhdw,yhdd,jcsj,lrr,lrsj,zt,ly,sum(if(lazy_zt='4',1,0)) lazy from aq_yh_zg_v where yhmc like concat('%',?,'%') and yhdw=nvl(?,yhdw) and jcsj between ? and ? and zt=nvl(?,zt) group by yh_id,yhmc,yhlb,yhms,yhjb,yhdw,yhdd,jcsj,lrr,lrsj,zt,ly having sum(if(lazy_zt='4',1,0)) = nvl(?,sum(if(lazy_zt='4',1,0)))
        Object[] args = new Object[6];
        args[0] = StringUtil.changeNull(in.getStringValue("yhmc"));
        args[1] = in.getStringValue("yhdw");
        args[2] = StringUtil.isNull(in.getStringValue("begin")) ? BEGIN : in.getDateValue("begin");
        args[3] = StringUtil.isNull(in.getStringValue("end")) ? new Date() : in.getDateValue("end");
        args[4] = in.getStringValue("zt");
        args[5] = StringUtil.isNull(in.getStringValue("lazy")) ? null : in.getIntValue("lazy");
        in.putStringValue("sql", "page_yh");
        in.putObjectValue("args", args);
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for (AbstractCommonData acd : list) {
            acd.putStringValue("lazy_desc", acd.getIntValue("lazy") == 1 ? "是" : "否");        //此处为分组函数计算出的值，所以不在参数表中保存
            acd.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhlb", acd.getStringValue("yhlb")));
            acd.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_info.yhjb", acd.getStringValue("yhjb")));
            acd.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_info.zt", acd.getStringValue("zt")));
            acd.putStringValue("ly_desc", SystemUtil.getColValueDesc("aq_yh_info.ly", acd.getStringValue("ly")));
        }
        out.putAll(page);
    }
}
