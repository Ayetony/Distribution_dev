package com.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DBPool implements DBsource{
	private  Properties props;
	private  String driver;
	private  String url;
	private  String username;
	private  String password;
	ArrayList<Connection> connections=null;
	private  int max;
	
	public DBPool(){
		
		this("jdbc.properties");
		
	}
	
	
	
	public DBPool(String configFile) {
		
		props=new Properties();
		
		try {
			props.load(new FileInputStream("src/"+configFile));
			driver=props.getProperty("db_test.driver");
			url=props.getProperty("db_test.url");
			username=props.getProperty("db_test.user");
			password=props.getProperty("db_test.pwd");
			max=Integer.parseInt(props.getProperty("pool.max"));
			
			connections=new ArrayList<Connection>();
			
			
		    
		} catch (IOException   e) {
	
			e.printStackTrace();
		}
		
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	
	}



	@Override
	public synchronized Connection getConnection() throws SQLException {
		if(connections.size()==0){
			return DriverManager.getConnection(url, username, password);
			
		}else{
			
		    Connection	lastIndex=connections.get(connections.size()-1);

		    return lastIndex;			
	    }
		
   
	}
	@Override
	public synchronized void closeConnection(Connection conn) throws SQLException {
        		
		 if(connections.size()==max){
			 
			 conn.close();
			 
		 }else{
			 
			 connections.add(conn);
		 }
		
	}
	
	
	
	

}
