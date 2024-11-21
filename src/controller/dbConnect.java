package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnect {
	public Connection getConnection() throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dburl = "jdbc:sqlserver://DUONG2724:1433;databaseName=thuchanhjava;trustServerCertificate=true;";
			String userName = "sa";
			String password = "dsh272k4";
			conn = DriverManager.getConnection(dburl, userName, password);
			System.out.println("ket noi thanh cong");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Không thể kết nối đến cơ sở dữ liệu: " + e.getMessage());
		}
		return conn;
	}

}
