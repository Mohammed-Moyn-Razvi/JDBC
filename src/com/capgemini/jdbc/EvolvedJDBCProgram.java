package com.dev.caps.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EvolvedJDBCProgram {

	public static void main(String[] args) {
		String dbUrl="jdbc:mysql://localhost:3306/caps_htd"
				+ "?user=root&password=root";
		String sql = "select * from demo_tab";
		{

			try(Connection con = DriverManager.getConnection(dbUrl);
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sql))
			{
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
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
