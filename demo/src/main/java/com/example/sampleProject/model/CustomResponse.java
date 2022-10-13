package com.example.sampleProject.model;

import java.util.ArrayList;
import java.util.List;

import com.example.sampleProject.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class CustomResponse {

	@JsonProperty("Status")
	private String status;
	@JsonProperty("Error Description")
	private String error_des;
	@JsonProperty("Data")
	private String data;
	
	@JsonProperty("User Detail")
	private List<UserEntity> user = new ArrayList<>();
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError_des() {
		return error_des;
	}
	public void setError_des(String error_des) {
		this.error_des = error_des;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
	public List<UserEntity> getUser() {
		return user;
	}
	public void setUser(List<UserEntity> user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ErrorClass [error=" + status + ", error_des=" + error_des + ", data=" + data + "]";
	}
	
	
	
}
