package com.example.sampleProject.model;

public class User {

	private String email;
	private String password;
	private int age;
	
	
	public User(String email, String password, int age) {
		super();
		this.email = email;
		this.password = password;
		this.age = age;
	}
	
	public User() {
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
