package com.bean;

public class User {

	 private int user_id;
	 private String name;
	 private String pwd;
	 
	 
	 
	
	 
	 
	 
	 //构造函数
	 
	public User(int user_id,String name,String pwd){
		
		this.user_id=user_id;
		this.name=name;
		this.pwd=pwd;
	}
	
	
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
