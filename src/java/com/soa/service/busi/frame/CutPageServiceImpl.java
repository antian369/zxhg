/*
 * 框架级别的服务，一般不被客户端直接调用，只被其它业务服务重用。例如：分页查询
 */
package com.soa.service.busi.frame;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.jdbc.CutPage;
import com.soa.service.BaseService;
import com.lianzt.util.LogUtil;
import com.lianzt.util.StringUtil;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 * 分页查询服务<br />
 * service_code : S10001<br />
 * 请求参数：sql (String) :　查询语句, args (Object[]) : 参数 （可选）, page (int) : 页码（可选）, page_size (int) : 每页显示记录数，默认20 （可选）, index_col(String) : 索引列的名称(可选), row_mapper (Object) : spring RowsMpper (可选)<br />
 * 响应数据：count (int) : 记录总数， page_size (int) : 每页显示记录数, page_count (int) : 总页数, page (int) : 当前页数,  now_size (int) : 当前页面的记录数,
 * begin_page (int) : 当前页面显示的起始页码, end_page (int) : 当前页面显示的结束页码, desc (String) : 分布过程中必要的提示, result (array) : 查询结果
 * @author lianzt
 */
@Service
public class CutPageServiceImpl extends BaseService {

    private static final String[] KEYS = new String[]{"sql", "查询语句"};
    private static final Logger log = Logger.getLogger(CutPageServiceImpl.class);

    @Override
    public String[] keys() {
        return KEYS;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        Object[] args = (Object[]) in.getObjectValue("args");
        Integer page = null;
        try {
            page = in.getIntValue("page");
        } catch (Exception e) {
            log.warn("错误的页码数：" + in.getStringValue("page"));
        }
        if (page == null) {
            page = 0;
        }
        if (log.isDebugEnabled()) {
            log.debug("查询第 " + page + " 页！");
        }
        Integer pageSize = in.getIntValue("page_size");
        if (pageSize == null) {
            pageSize = CutPage.DEFAULT_PAGE_SIZE;
        }
        if (log.isDebugEnabled()) {
            log.debug("要查询的sql语句 ：" + getSql(in.getStringValue("sql")) + "\nsql参数：" + StringUtil.connectArray(args, ","));
        }
        RowMapper rowMapper = (RowMapper) in.getObjectValue("row_mapper");
        if (rowMapper == null) {
            rowMapper = rowMapperByCol;
        }
        String indexCol = in.getStringValue("index_col");
        //构造分页查询工具
        CutPage cp = new CutPage(getSql(in.getStringValue("sql")), args);
        //开始分页查询
        Date begin = new Date();
        cp = cp.getResult(page, getJdbcTemplate(), rowMapper, indexCol);
        long sqlTime = new Date().getTime() - begin.getTime();
        log.info("分析日志\n['log-cutpage-time']\t{sql:'" + in.getStringValue("sql") + "', args:'" + StringUtil.connectArray(args, ",") + "', database:'local', _runTime:" + sqlTime + "}");
        //构造响应数据
        out.putIntValue("count", cp.getCount());
        out.putIntValue("page_count", cp.getPageCount());
        out.putIntValue("page", cp.getPage());
        out.putIntValue("now_size", cp.getNowSize());
        out.putIntValue("begin_page", cp.getBeginPage());
        out.putIntValue("end_page", cp.getEndPage());
        out.putStringValue("desc", cp.getDesc());
        out.putArrayValue("result", cp.getResult());
    }
}
