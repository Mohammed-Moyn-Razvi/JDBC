package com.dev.caps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCModification {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			//Load the Driver
			java.sql.Driver div = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Driver Loaded....");

			//Get Connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/caps_htd"
					+ "?user=root&password=root";
			conn = DriverManager.getConnection(dbUrl);
			System.out.println("Connection Established.....");
			System.out.println("*******************************");

			//Issue the sql qurey via conn.
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the RegNo....");
			int regno = Integer.parseInt(sc.nextLine());
			System.out.println("Enter the new email...");
			String nemail = sc.nextLine();
			System.out.println("Enter the Password...");
			String pass = sc.nextLine();

			String query = "UPDATE demo_tab SET e_mail=?"
					+ " where regno=? AND password=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(2, regno);
			pstmt.setString(1,nemail);
			pstmt.setString(3,pass);
			sc.close();

			int count = pstmt.executeUpdate();

			//Process the Results..
			if(count>0) {
				System.out.println("Data Successfully Upgraded....");
			}else {
				System.out.println("Something went wrong...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {        //Close all the JDBC objects...
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

