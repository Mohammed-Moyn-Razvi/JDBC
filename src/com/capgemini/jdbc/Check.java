package com.capgemini.jdbc;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Check {
public static void main(String[] args) {
	
	try
	{
	    File f = new File("D:/dbc.PROPERTIES");	
	    FileReader fr = new FileReader(f);
	    Properties p = new Properties();
	    p.load(fr);
	    System.out.println(p.getProperty("user"));
	    System.out.println(p.getProperty("password"));
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
}
	
	
	
}
