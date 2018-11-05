package com.capgemini.jdbc;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Excecute {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("enter id");
		int id = sc.nextInt();
		System.out.println("enter name");
		String name = sc.next();
		System.out.println("enter power");
		String power = sc.next();
		System.out.println("enter planet");
		String planet = sc.next();
		
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
			
			String query = "insert into marvel_table values(?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,id);
			pstmt.setString(2,name);
			pstmt.setString(3,power);
			pstmt.setString(4, planet);
	
			boolean res = pstmt.execute();
			
			
			if(res)
			{
				rs = pstmt.getResultSet();
				while(rs.next())
				{
					int id1 = rs.getInt("a_id");
					String name1 = rs.getString("a_name");
					String power1 = rs.getString("a_power");
					String planet1 = rs.getString("a_planet");
					
					System.out.println("********************");
					System.out.println("id ="+id1);
					System.out.println("name ="+name1);
					System.out.println("power = "+power1);
					System.out.println("planet ="+planet1);
					System.out.println("********************");
				}
			}
			else
			{
				int count = pstmt.getUpdateCount();
				if(count>0)
				{
					System.out.println("Successful");
				}
				else
				{
					System.out.println("failed");
				}
			
  			
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
				if(pstmt!=null)
				{
					try {
						pstmt.close();
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
