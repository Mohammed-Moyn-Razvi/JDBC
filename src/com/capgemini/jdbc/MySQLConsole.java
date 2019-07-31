package com.dev.caps.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.Driver; //required for jdbc to connect to mySql database


public class MySQLConsole {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");

			//2
			String dbUrl="jdbc:mysql://localhost:3306/caps_htd"
					+ "?user=root&password=root";
			con = DriverManager.getConnection(dbUrl); 
			System.out.println("Connected...");
			
			//3&4
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Query:");
			String query = sc.nextLine();
			sc.close();

			stmt = con.createStatement();
			
			boolean b = stmt.execute(query);
			int count;
			
			if(b) {
				rs = stmt.getResultSet();
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
			}
				else  {
					count = stmt.getUpdateCount();
					System.out.println("Query OK "+count+" rows affected.");
				}
					
				}
		catch (Exception e) {
			e.printStackTrace();
		}finally {					//5
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				} 
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}



