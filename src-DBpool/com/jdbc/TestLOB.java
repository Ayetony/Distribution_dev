package com.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestLOB {

	public static void main(String[] args) throws IOException {
        
		DBsource dbsource=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		
		try {
			
			
			//fetching the file .
			dbsource=new SimpleSource();
			conn=dbsource.getConnection();
			File file=new File("src/065.JPG");
			int length=(int) file.length();
			InputStream fin=new FileInputStream(file);
			
			//place the file into database
			pstmt=conn.prepareStatement("insert into tb_file values(?,?,?)");
			pstmt.setInt(1, 1234);
			pstmt.setString(2,"n2ewICO");
			pstmt.setBinaryStream(3, fin, length);
			pstmt.executeUpdate();
			pstmt.clearParameters();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
				
			}
			
			
		}
		
		
		Statement stmt=null;
		//query all from tb_file.
		try {
			conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		try {
			stmt=conn.createStatement();
			ResultSet set=stmt.executeQuery("select * from tb_file");
			set.next();
			String filename2=set.getString(2);
			Blob blob=set.getBlob(3);
			File file2=new File("src/"+filename2+".bak");
			FileOutputStream fos=new FileOutputStream(file2);
			fos.write(blob.getBytes(1,(int) blob.length()));
			System.out.println(filename2);
			fos.flush();
			fos.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			
			if(stmt!=null){
				
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			try {
				dbsource.closeConnection(conn);
 
			 } catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}		
		
		
		
		
		
    		

	}

}
