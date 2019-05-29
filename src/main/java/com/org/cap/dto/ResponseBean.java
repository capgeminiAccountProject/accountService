package com.org.cap.dto;

public class ResponseBean {

	private String token;
	private String status;
	private String tokenException;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTokenException() {
		return tokenException;
	}
	public void setTokenException(String tokenException) {
		this.tokenException = tokenException;
	}
}
