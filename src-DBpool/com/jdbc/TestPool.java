package com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestPool {

	public static void main(String[] args) {

		DBsource source=new DBPool();
		try {
			Connection conn1=source.getConnection();//start using a database connection.
			conn1.close();//put it back into database connection pool.
			Connection conn2=source.getConnection();
			
			System.out.println(conn1==conn2);
			
			
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
