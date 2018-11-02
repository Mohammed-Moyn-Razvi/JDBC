package com.capgemini.jdbc;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCFirstStep {
public static void main(String[] args) {
	
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
	//step :1
	java.sql.Driver driver = (java.sql.Driver)Class.forName("com.mysql.jdbc.Driver").newInstance();
	DriverManager.registerDriver(driver);
	
	FileReader fr=new FileReader("D:/DBCredential.properties");
	Properties properties=new Properties();
	properties.load(fr);
	
	
	
	//Estb the db conn via driver
	String dburl = "jdbc:mysql://localhost:3306/Avengers?";
	con = DriverManager.getConnection(dburl,properties);
	
	
	
	
	

	String query = "select * from marvel_table";
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);
	
	
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
		if(stmt!=null)
		{
			try {
				stmt.close();
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
