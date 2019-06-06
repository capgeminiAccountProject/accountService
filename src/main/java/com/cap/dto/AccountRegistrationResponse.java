package com.cap.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)

public class AccountRegistrationResponse {

	
	private String accountNumber;
	private boolean isSuccess;
	private String exceptionDetails;
	private List<Account> accountList;
	private Account account;

	
	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public boolean isSuccess() {
		return isSuccess;
	}


	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}


	public String getExceptionDetails() {
		return exceptionDetails;
	}


	public void setExceptionDetails(String exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}


	public List<Account> getAccountList() {
		return accountList;
	}


	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	/*
	 * public AccountRegistrationResponse(String accountNumber, boolean isSuccess,
	 * String exceptionDetails, List<Account> accountList, Account account) {
	 * 
	 * this.accountNumber = accountNumber; this.isSuccess = isSuccess;
	 * this.exceptionDetails = exceptionDetails; this.accountList = accountList;
	 * this.account = account;
	 * 
	 * }
	 */
	
}
