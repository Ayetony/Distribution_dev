package com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBsource {
	
     public Connection getConnection() throws SQLException;
     public void closeConnection(Connection conn) throws SQLException;

}
