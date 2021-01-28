package com.tywh.erp.util;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DbUtil {

 //   private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static Map<String, Connection> connMap = new HashMap<>();

    public static Connection getConnection() throws Exception {

//        Connection conn = threadLocal.get();;
//        if (conn == null) {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://*:1433;databasename=perpfx01";
//            String user = "sa";
//            String password = "*";
//            conn = DriverManager.getConnection(url, user, password);
//            threadLocal.set(conn);
//        }i
        Connection conn = connMap.get("conn");
        if (conn == null) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://*:1433;databasename=perpfx01";
            String user = "sa";
            String password = "*";
            conn = DriverManager.getConnection(url, user, password);
            connMap.put("conn", conn);
        }

        return conn;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
//                threadLocal.remove();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
