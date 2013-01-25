/*
 * 数据库操作包，提供常用的数据库操作对象
 */
package com.lianzt.jdbc;

import com.lianzt.bean.DbDate;
import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.StringUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 * spring jdbc的一个简单实现类，返回一个CommonData，结果集中的每一列都被当作String对象，CommonData中的key为结果集的列名称，为空的列不被放入结果集
 * @author lianzt
 */
public class ResultSetExtractorByNotNullCol implements
        ResultSetExtractor<AbstractCommonData> {

    /**
     * spring ResultSetExtractor 接口的实现
     * @param rs
     * @return
     * @throws SQLException
     * @throws DataAccessException
     */
    @Override
    public AbstractCommonData extractData(ResultSet rs) throws SQLException,
                                                               DataAccessException {
        if (rs.next()) {
            AbstractCommonData acd = DataConvertFactory.getInstanceEmpty();
            int colSize = rs.getMetaData().getColumnCount();//结果集列数
            String colName = "";
            for (int col = 0; col < colSize; col++) {
                //提取结果集每一列的名称，并转为小写
                colName = rs.getMetaData().getColumnName(col + 1).toLowerCase();
                //提取结果集每一列的值
                if (StringUtil.isNull(rs.getString(colName))) {
                    continue;
                }
                switch (rs.getMetaData().getColumnType(col + 1)) {
                    case Types.DATE:
                    case Types.TIME:
                    case Types.TIMESTAMP:
                        acd.putDateValue(colName, new DbDate(rs.getTimestamp(colName)));
                        break;
                    default:
                        acd.putStringValue(colName, rs.getString(colName));
                        break;
                }
            }
            return acd;
        } else {
            return null;
        }
    }
}
