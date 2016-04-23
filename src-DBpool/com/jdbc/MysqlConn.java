package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.User;
import com.mysql.jdbc.PreparedStatement;

/*
 * 
 * @author->Ayetony.Miao
 * idea:To estimate a Mysql connection .
 * 
 * */




public class MysqlConn {

	/*public static void main(String[] args){
		
		//First step->loading the driver:"com.Mysql.jdbc.Driver"
		
		
      try {
		Class.forName("com.Mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
        System.out.println("Cannot access??");
	}
      
      
      //Second step->provide the JDBC URL
      //host:port/DatabaseName?user=root&password="".
      
      //If we have to use Chinese encoding,altered as below .
      //jdbc:mysql://localhost:3306/demo?user=root?password=123&useUnicode=true&characterEncoding=gbk
      
      String url="jdbc:mysql://127.0.0.1:3306/db_test";
      String username="root";
      String password="miao1990";
      
      
      
      
      try {
		Connection conn=DriverManager.getConnection(url, username, password);
		System.out.println("The mysql connectin is estimated."+new Date());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      

 }
	
	*/
	
	
	
	
	
  private static Connection getConn(){
		
		
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/db_test";
		String username="root";
		String password="miao1990";
		
		Connection conn=null;
		
		try {
			    Class.forName(driver);
			
				conn=DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
			    e.printStackTrace();
		}
		
		
		
		return conn;
		
	}
	
	
  
  private static int insert(User user){
	  
	  Connection conn=getConn();
	  int flag=0;
	  String sql="insert into tb_user(user_id,username,pwd) values(?,?,?)";
	  PreparedStatement pstmt;
	  
	  try {
		pstmt=(PreparedStatement) conn.prepareStatement(sql);
		pstmt.setInt(1, user.getUser_id());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getPwd());
		
		flag=pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return flag;
	  
  }
	
  
  
  private static int update(User user,String pwd){
	  
	  Connection conn=getConn();
	  int flag=0;
	  
	  String sql="update tb_user set pwd='"+pwd+"' where username='"+user.getName()+"'";
	  PreparedStatement pstmt=null;
	  
	  try {
		pstmt=(PreparedStatement) conn.prepareStatement(sql);
		flag=pstmt.executeUpdate();//commit
		pstmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  
	  
	  return flag;
  }
	
	
	private void getAll(){
		Connection conn=getConn();
		String sql="select * from tb_user";
		
		PreparedStatement pstmt;
		
		try {
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			//fetch the cols from result set.
			int col=rs.getMetaData().getColumnCount();//Sometime .cols mean columns in the table.
			System.out.println("*********************query all*************  "+col);
			while(rs.next())
			{
				
				for(int i=1;i<=col;i++)
				{
				    System.out.print(rs.getString(i)+"\t");
			
				}
				
				System.out.println(" ");//enter
				
			 }
			
		 } catch (SQLException e) {
			e.printStackTrace();
	     }
		
	
	}
	
	
	
	private static int delete(String name){
		
		Connection conn=getConn();
	    int flag=0;
	    String sql="delete from tb_user where username='"+name+"'";
	    PreparedStatement pstmt;
	    
	    try {
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			flag=pstmt.executeUpdate();
			pstmt.close();
		    conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    
	    return flag;
		
		
	}
	
	
	
	
	
	
	
	public static void main(String [] args)
	{
		User user=new User(1298,"MiaoDaYe","miao1990");
		
		MysqlConn.insert(user);
		MysqlConn.update(user,"MaybeRight");
	    new	MysqlConn().getAll();
		delete("MiaoDaYe");
		
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	

}
