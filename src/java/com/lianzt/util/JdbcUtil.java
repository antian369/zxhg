/*
 * Copyright 2011-2020 the original author or authors.
 * jdbc操作对象的常用操作
 */
package com.lianzt.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lianzt
 */
public class JdbcUtil {

    /**
     * 关闭数据库连接
     * @param con
     */
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
            }
        }
    }

    /**
     *
     * @param stat
     */
    public static void close(Statement stat) {
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException ex) {
            }
        }
    }

    public static void close(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
            }
        }
    }

    public static void close(CallableStatement cs) {
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }
}
