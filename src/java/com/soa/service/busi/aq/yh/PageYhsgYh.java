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
 * 隐患收购分页查询 S12010 说明：隐患管理分类下的查询，查询隐患收购视图 参数：zt yhlb yhdw yhjb
 *
 * @author Asus
 */
@Service
public class PageYhsgYh extends BaseService {

    private Date BEGIN = null;
    private final Logger log = LoggerFactory.getLogger(PageYhsgYh.class);

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
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
            AbstractCommonData out, AbstractCommonData outHead) {
        //select * from aq_yh_yhsg where zt=nvl(?,zt) and yhdw=nvl(?,yhdw) and yhjb=nvl(?,yhjb) and yhlb=nvl(?,yhlb) and fxsj between ? and ?  order by djsj desc
        Object[] args = new Object[6];
        args[0] = in.getStringValue("zt");
        args[1] = in.getStringValue("yhdw");
        args[2] = in.getStringValue("yhjb");
        args[3] = in.getStringValue("yhlb");
        args[4] = StringUtil.isNull(in.getStringValue("begin")) ? BEGIN : in.getDateValue("begin");
        args[5] = StringUtil.isNull(in.getStringValue("end")) ? new Date() : in.getDateValue("end");
        in.putStringValue("sql", "page_yhsg_yh");
        in.putObjectValue("args", args);
        AbstractCommonData page = runService(in, "S10001");     //分页查询
        List<AbstractCommonData> list = page.getArrayValue("result");
        for (AbstractCommonData yh : list) {
            yh.putStringValue("yhlb_desc", SystemUtil.getColValueDesc("aq_yh_yhsg.yhlb", yh.getStringValue("yhlb")));
            yh.putStringValue("yhjb_desc", SystemUtil.getColValueDesc("aq_yh_yhsg.yhjb", yh.getStringValue("yhjb")));
            yh.putStringValue("zt_desc", SystemUtil.getColValueDesc("aq_yh_yhsg.zt", yh.getStringValue("zt")));
        }
        out.putAll(page);
    }
}
