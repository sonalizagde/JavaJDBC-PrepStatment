package com.conn.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connectionn {
	private static String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	private static String URL="jdbc:mysql:///collegedb";
	private static String USER = "root";
	private static String PASSWORD = "root";
	
	static {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection()  {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void closeAll(ResultSet rs, PreparedStatement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		try {
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
		try {
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		}
	}

}
