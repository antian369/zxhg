/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.jdbc;

import com.lianzt.util.*;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * 分页工具
 * 如果查询的SQL语句对有空值的列或有重复值的列排序，会导致不能正常分页
 * @author lianzt
 */
public class CutPage implements RowMapper<AbstractCommonData> {

    private static final Logger log = Logger.getLogger(CutPage.class);
    /**
     * 记录总数
     */
    private int count;
    /**
     * 每页显示记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int pageCount;
    /**
     * 当前页数
     */
    private int page;
    /**
     * 当前页面的记录数
     */
    private int nowSize;
    /**
     * 当前页面显示的起始页码
     */
    private int beginPage;
    /**
     * 当前页面显示的结束页码
     */
    private int endPage;
    /**
     * 查询语句
     */
    private String sql;
    /**
     * 索引列
     */
    private String indexCol;
    /**
     * 列名
     */
    private String[] cols;
    /**
     * 参数名
     */
    private Object[] args;
    /**
     * 分布过程中必要的提示
     */
    private String desc;
    /**
     * 查询结果
     */
    private List result;
    /**
     * 每页显示记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 20;
    /**
     * 每页显示的页码数
     */
    public static final int DEFAULT_PAGE_COUNT = 10;

    /**
     * 构造一条带参的查询sql语句，例如select * from table_name where col = ?
     * @param sql 需要分页的sql语句
     * @param args 语句中的参数
     */
    public CutPage(String sql, Object... args) {
        this(sql);
        this.args = args;
    }

    /**
     * 构造一条不带参的查询语句
     * @param sql 需要分页的sql语句
     */
    public CutPage(String sql) {
        this.pageSize = DEFAULT_PAGE_SIZE; // 每页显示记录数
        this.sql = sql;
    }

    /**
     * 生成分页查询的语句
     * @return
     */
    private String cutPageSql() {
        return sql + " limit ?, ?";
    }

    /**
     * 分页查询
     * @param page 页码
     * @param jdbcTemplate 数据库访问类
     * @param rowMapper Spring RowMapper接口
     * @param indexCol 统计时使用的索引列
     * @return 分页之后的结果
     */
    public CutPage getResult(int page, JdbcTemplate jdbcTemplate,
                             RowMapper<?> rowMapper, String indexCol) {
        this.indexCol = indexCol;
        return getResult(page, jdbcTemplate, rowMapper);
    }

    /**
     * 获取某一页的记录
     * @param page 页码
     * @param rowMapper spring jdbcTemplate中的RowMapper
     */
    public CutPage getResult(int page, JdbcTemplate jdbcTemplate,
                             RowMapper<?> rowMapper) {
        this.count = getResultCount(jdbcTemplate); // 记录总数
        pageCount = (count - 1) / pageSize + 1;  // 总页数
        if (page < 1) {
            desc = "页码小于 1 ， 显示第一页。";
            page = 1;
        } else if (page > pageCount) {
            desc = "页码大于总页数， 显示最后一页。";
            page = pageCount;
        } else {
            this.page = page; // 当前页数
        }
        this.page = page;
        //设置开始与结束页码
        if (pageCount < DEFAULT_PAGE_COUNT) {
            beginPage = 1;
        } else {
            beginPage = page - (DEFAULT_PAGE_COUNT / 2); //当前页面显示的起始页码
            if (beginPage < 1) {
                beginPage = 1;
            }
        }
        this.endPage = beginPage + DEFAULT_PAGE_COUNT;      //当前页面显示的结束页码
        if (endPage > pageCount) {
            endPage = pageCount;
            beginPage = endPage - DEFAULT_PAGE_COUNT;
            if (beginPage < 1) {
                beginPage = 1;
            }
        }

        Object[] tempArgs = null;
        if (args != null) {
            tempArgs = new Object[args.length + 2];
            System.arraycopy(args, 0, tempArgs, 0, args.length);        //把开始与结束的页面加入到参数数组中
        } else {
            tempArgs = new Object[2];
        }
        tempArgs[tempArgs.length - 2] = (page - 1) * pageSize;        //开始
        tempArgs[tempArgs.length - 1] = pageSize;    //结果

        //查询结果
        if (log.isDebugEnabled()) {
            log.debug("分页查询的sql语句：" + cutPageSql());
        }
        result = jdbcTemplate.query(cutPageSql(), rowMapper, tempArgs);
        nowSize = result.size();
        if (log.isDebugEnabled()) {
            log.debug("\n分页信息：" + this);
        }
        return this;
    }

    /**
     * 获取某一页的记录
     * @param page 页码
     */
    public CutPage getResult(int page, JdbcTemplate jdbcTemplate) {
        return getResult(page, jdbcTemplate, this);
    }

    /**
     * 使用默认的RowMapper进行分页查询
     * @param page 页码
     * @param jdbcTemplate 数据库访问类
     * @param indexCol 列索引
     * @return 分页后的结果
     */
    public CutPage getResult(int page, JdbcTemplate jdbcTemplate,
                             String indexCol) {
        this.indexCol = indexCol;
        return getResult(page, jdbcTemplate);
    }

    /**
     * 总记录数
     * @param jdbcTemplate
     * @return
     */
    private int getResultCount(JdbcTemplate jdbcTemplate) {
        String countSql = "";
        if (StringUtil.isNull(indexCol)) {
            countSql = "select count(*) from (" + sql + ") _a";
        } else {
            countSql = "select count(" + indexCol + ") from (" + sql + ") _a";
        }
        if (log.isDebugEnabled()) {
            log.debug("统计总记录数的sql语句：" + countSql);
        }
        return jdbcTemplate.queryForInt(countSql, args);
    }

    /**
     * Spring RowMapper 接口的实现
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public AbstractCommonData mapRow(ResultSet rs, int i) throws SQLException {
        AbstractCommonData acd = DataConvertFactory.getInstanceEmpty();
        if (cols == null) {
            int colSize = rs.getMetaData().getColumnCount();        //结果集列数
            cols = new String[colSize];
            for (int col = 0; col < colSize; col++) {
                //提取结果集每一列的名称
                cols[col] = rs.getMetaData().getColumnName(col + 1).toLowerCase();
            }
        }
        for (String c : cols) {
            //提取结果集每一列的值
            acd.putStringValue(c, rs.getString(c));
        }
        return acd;
    }

    /**
     * 当前页面显示的起始页码
     * @return
     */
    public int getBeginPage() {
        return beginPage;
    }

    /**
     * 记录总数
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * 当前页面显示的结束页码
     * @return
     */
    public int getEndPage() {
        return endPage;
    }

    /**
     * 当前页面的记录数
     * @return
     */
    public int getNowSize() {
        return nowSize;
    }

    /**
     * 当前页数
     * @return
     */
    public int getPage() {
        return page;
    }

    /**
     * 总页数
     * @return
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * 每页显示记录数
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置页面容量
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 查询语句
     * @return
     */
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * sql参数
     * @return
     */
    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    /**
     * 分布过程中必要的提示
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 查询结果
     * @return
     */
    public List getResult() {
        return result;
    }

    /**
     * 获取结果集的列名
     * @return
     */
    public String[] getCols() {
        return cols;
    }

    /**
     * 获取索引列
     * @return
     */
    public String getIndexCol() {
        return indexCol;
    }

    /**
     * 设置索引列，如果查询语句中有索引，设置该列可以提高查询效率
     * @param indexCol
     */
    public void setIndexCol(String indexCol) {
        this.indexCol = indexCol;
    }

    @Override
    public String toString() {
        return "索引列：" + indexCol + "\t总记录数 : " + count + "\t总页数:" + pageCount + " \t单页最大容量:" + pageSize + "\t当前页数:" + page
               + "\t当前页容量:" + nowSize + "\t开始页码:" + beginPage + "\t结束页码:" + endPage + "\t提示:" + desc;
    }
}
