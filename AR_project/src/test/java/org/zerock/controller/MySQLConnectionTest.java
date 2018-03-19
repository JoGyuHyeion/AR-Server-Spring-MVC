package org.zerock.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {

/*	private static final String DRIVER = 
			"com.mysql.jdbc.Driver";
	private static final String URL = 
			"jdbc:mysql://127.0.0.1:3306/ar_server";
	private static final String USER = 
			"ar_server";
	private static final String PW = 
			"password";
			*/
	private static final String DRIVER = 
			"com.mysql.jdbc.Driver";
	private static final String URL = 
			//"jdbc:mysql://203.247.255.237:3306/ar?useSSL=false";
			//"jdbc:mysql://127.0.0.1:3306/ar?useSSL=false";
			"jdbc:mysql://127.0.0.1:3306/roboprint?useSSL=false";
	private static final String USER = 
			"ar_server";
	private static final String PW = 
			"password";
			
	
	@Test
	public void testConnection() throws Exception{
		
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			
			System.out.println(con);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
