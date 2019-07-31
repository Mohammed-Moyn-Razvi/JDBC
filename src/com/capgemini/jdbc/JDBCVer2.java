package com.dev.caps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCVer2 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			//Load The Driver...
			java.sql.Driver div = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Dirver Loaded....");

			//Version 2 of getConnection();
			String url = "jdbc:mysql://localhost:3306/caps_htd";
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the user...");
			String user = sc.nextLine();
			System.out.println("Enter the password...");
			String pass = sc.nextLine();
			conn = DriverManager.getConnection(url, user, pass);
			sc.close();

			conn = DriverManager.getConnection(url,user,pass);
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
