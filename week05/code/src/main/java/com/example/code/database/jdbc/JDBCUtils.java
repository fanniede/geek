package com.example.code.database.jdbc;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCUtils {
    private static String drivername;
    private static String url;
    private static String user;
    private static String password;
    private static Connection connection = null;

    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println();
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find mysql jdbc driver");
            e.printStackTrace();
            return null;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
            if (connection != null) {
                System.out.println("Connection successful!");
                return connection;
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 关闭JDBC相关资源
     * @param con
     * @param sta
     * @param rs
     */
    public static void closeResource(Connection con,Statement sta,ResultSet rs) {
        try {
            if(con!=null) {
                con.close();
            }
            if(sta!=null) {
                sta.close();
            }
            if(rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过静态代码块，初始化数据库连接配置数据，并且注册数据库驱动
     */
    static {
        try {
            Properties pr = new Properties();
            //通过读取Properties文件给属性赋值，即每次使用该工具类都会读取最新配置进行连接
            pr.load(new FileInputStream(new File("jdbc_config.properties")));
            drivername = pr.getProperty("drivername");
            url = pr.getProperty("url");
            user = pr.getProperty("user");
            password = pr.getProperty("password");
            Class.forName(drivername);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接异常，请检查配置数据");
        }
    }

    /**
     * 获取数据库连接对象
     * @return
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接异常，请检查配置数据");
        }
        return con;
    }


}
