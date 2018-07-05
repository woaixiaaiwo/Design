package com.boge.hotels.jdbc;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class ConnectionFactory {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
	private static String username = "root";
	private static String password = "root";
    
    public static Connection getConn(){
    	return Conn.conn;
    }
    
    private static class Conn{
    	private static Connection conn = null;
    	static{
    		try {
                Class.forName(driver); //classLoader,加载对应驱动
                conn = (Connection) DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
    }
}
