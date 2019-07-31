package com.dev.caps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class JDBCCallable {

	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		try {
			//Load The Driver...
			java.sql.Driver div = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Dirver Loaded....");
			
			//Get Connection via Driver...
			
			//Version 1 of getConnection(); 
			String url ="jdbc:mysql://localhost:3306/caps_htd"
					+ "?user=root&password=root";
			conn = DriverManager.getConnection(url);
			
			conn = DriverManager.getConnection(url);
			System.out.println("Connection Established....");
			System.out.println("*****************************");
			
			//Issue the sql queries...
			String query = "call getAllAvengers()";
			cstmt = conn.prepareCall(query);
			rs = cstmt.executeQuery();
			
			//Process the results returned...
			while(rs.next()){
				int regno = rs.getInt("regno");
				String fname = rs.getString("f_name");
				String lname = rs.getString("l_name");
				String email = rs.getString("e_mail");
				System.out.println(regno);
				System.out.println(fname);
				System.out.println(lname);
				System.out.println(email);
				System.out.println("-------------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}finally {        //Close all the JDBC objects...
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(cstmt!=null) {
					try {
						cstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(rs!=null) {
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

}
