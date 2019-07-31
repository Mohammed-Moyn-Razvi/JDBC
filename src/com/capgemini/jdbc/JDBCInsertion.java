package com.dev.caps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCInsertion {

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
			System.out.println("Enter First Name...");
			String fname = sc.nextLine();
			System.out.println("Enter Last Name...");
			String lname = sc.nextLine();
			System.out.println("Enter the E-mail...");
			String email = sc.nextLine();
			System.out.println("Enter the Password...");
			String pass = sc.nextLine();

			String query = "INSERT INTO demo_tab"
					+ " values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, regno);
			pstmt.setString(2,fname);
			pstmt.setString(3,lname);
			pstmt.setString(4,email);
			pstmt.setString(5,pass);
			sc.close();

			int count = pstmt.executeUpdate();

			//Process the Results..
			if(count>0) {
				System.out.println("Data Successfully Inserted....");
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

