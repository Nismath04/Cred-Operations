package com.example.sampleProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="user_email",nullable = false , updatable = true)
	private String email;
	
	@JsonIgnore
	@Column(name="user_password",nullable = false )
	private String password;
	
	@Column(name="user_age",nullable = false )
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", email=" + email + ", password=" + password + ", age=" + age + "]";
	}
	
	
	

}
