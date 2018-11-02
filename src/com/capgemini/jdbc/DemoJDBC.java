package com.capgemini.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DemoJDBC {
public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	System.out.println("enter the jar class");
	String classname = sc.next();
	System.out.println("enter username");
	String user = sc.next();
	System.out.println("Enter password");
	String pass = sc.next();
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	try
	{
	//1.Loads the driver class
		java.sql.Driver driver = (java.sql.Driver)Class.forName
				(classname).newInstance();
		DriverManager.registerDriver(driver);
	
	//2.Estb the db conn via driver
	String dburl = "jdbc:mysql://localhost:3306/Avengers?";
	con = DriverManager.getConnection(dburl,user,pass);
	//con = DriverManager.getConnection(dburl);
	
	//3.Issue the sql query via conn
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
