package com.jdbc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class SimpleSource implements DBsource {

	
	private Properties props;
	private String url;
	private String user;
	private String pwd;
	
	
	
	public SimpleSource ()throws SQLException,ClassNotFoundException, IOException{
		
		this("jdbc.properties");
		
		
	}
	
	
	public SimpleSource(String configFile) throws IOException,ClassNotFoundException{
		
		
		File dir=new File("");
		
		System.out.println(dir.getAbsolutePath());
		
		
		props=new Properties();
		props.load(new BufferedInputStream(new FileInputStream("src/"+configFile)));
		url=props.getProperty("db_test.url");
		user=props.getProperty("db_test.user");
		pwd=props.getProperty("db_test.pwd");
		
		Class.forName(props.getProperty("db_test.driver"));
		
		
 	}


	@Override
	public Connection getConnection() throws SQLException {
        
		return DriverManager.getConnection(url, user, pwd);
	}

	@Override
	public void closeConnection(Connection conn) throws SQLException {
		
	    conn.close();	
	}

}
