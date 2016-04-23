package com.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;



public class TestSource {

	public static void main(String[] args) throws IOException {
	  
		
		Connection conn=null;
	
		
		try {
			
			
		
		    DBsource db=new SimpleSource();
		    conn=db.getConnection();
			System.out.println("do you like this properties way------------------------------");
	        db.closeConnection(conn);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
	
	     
	    }
		


   }
	
	
	
}