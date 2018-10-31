package com.wp.DTL645_Listener.dataUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteJDBC {
	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:DTL645.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
//			 String sql = "CREATE TABLE Electric " +
//			 "(ID INT PRIMARY KEY     NOT NULL," +
//			 "TIME TIMESTAMP,"+
//			 "SSZSZGL DOUBLE(17),"+
//			 "SSZYGGL DOUBLE(17),"+
//			 "AV DOUBLE(17),"+
//			 "BV DOUBLE(17),"+
//			 "CV DOUBLE(17),"+
//			 "AA DOUBLE(17),"+
//			 "BA DOUBLE(17),"+
//			 "CA DOUBLE(17),"+
//			 "ATTR1 DOUBLE(17),"+
//			 "ATTR2 DOUBLE(17),"+
//			 "ATTR3 DOUBLE(17),"+
//			 "ATTR4 DOUBLE(17),"+
//			 "ATTR5 DOUBLE(17),"+
//			 "ATTR6 DOUBLE(17),"+
//			 "ATTR7 VARCHAR(100),"+
//			 "ATTR8 VARCHAR(100))";
//			 String sql = "create index PRIMARY_KEY_A on Electric(id);";
			String sql = "insert into Electric values('1','2018-10-10 13:52:06.114','1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16')";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	
//	public static void main(String args[]) {
//		Connection conn = null;
//		Statement stmt = null;
//		long a = System.currentTimeMillis();
//		try {
//			Class.forName("org.sqlite.JDBC");
//			conn = DriverManager.getConnection("jdbc:sqlite:netWind.db");
//			System.out.println("Opened database successfully");
//
//			String sql = "insert into SEGMENT values(?,'2018-10-10 13:52:06.114','15.54','15.54','15.54','15.54','15.54','N,正北','15.0','N,正北','15.0','N,正北','15.0','N,正北')";
//            Statement prest = conn.createStatement();
//            conn.setAutoCommit(false);
//
//            for (int i = 1; i <= 100001; i++) {
//            	String sql2 = sql.replace("?", i+"");
//                prest.addBatch(sql2);
//            }
//            prest.executeBatch();
//            conn.commit();
//
//
//            conn.close();
//
//			
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		} finally {
//			long b = System.currentTimeMillis();
//			System.out.println("用时："+(b-a)/1000+"秒");
//		}
//		System.out.println("Table created successfully");
//	}
}
