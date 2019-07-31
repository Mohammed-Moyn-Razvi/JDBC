package com.dev.caps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCVer4 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			//Load The Driver...
			/*Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded..");*/
			
			//Get Connection via Driver...
			//Version 1 of getConnection(); 
			String url ="jdbc:mysql://localhost:3306/caps_htd"
					+ "?user=root&password=root";
			conn = DriverManager.getConnection(url);

			conn = DriverManager.getConnection(url);
			System.out.println("Connection Established....");
			System.out.println("*****************************");

			//Issue the sql queries...
			String query = "SELECT * FROM demo_tab";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

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
				if(stmt!=null) {
					try {
						stmt.close();
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
