package com.capgemini.jdbc;

import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class CallableStmt {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		
		try
		{
			//Loads the Driver
			java.sql.Driver driver = (java.sql.Driver)Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			
			//Estb the db conn via driver
			FileReader fr = new FileReader("D:/DBCredential.properties");
			Properties prop = new Properties();
			prop.load(fr);
			String dburl = "jdbc:mysql://localhost:3306/Avengers";
			con = DriverManager.getConnection(dburl,prop);
			
			//Issue the sql query via conn
			
			String query = "call getAllAvengers()";
			cstmt = con.prepareCall(query);
		    rs = cstmt.executeQuery();
			
			 
			//4.Process the result returned by sql query
			while(rs.next())
			{
				int id = rs.getInt("a_id");
				String name = rs.getString("a_name");
				String power = rs.getString("a_power");
				String planet = rs.getString("a_planet");
				
				System.out.println("********************");
				System.out.println("id ="+id);
				System.out.println("name ="+name);
				System.out.println("power = "+power);
				System.out.println("planet ="+planet);
				System.out.println("********************");
			}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(cstmt!=null)
				{
					try {
						cstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(rs!=null)
				{
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		}
