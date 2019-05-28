package com.org.cap.dto;

public class AccountNumber {

	private int account_num;

	public int getAccount_num() {
		return account_num;
	}

	public void setAccount_num(int account_num) {
		this.account_num = account_num;
	}
	
	public AccountNumber(int account_num) {
		super();
		this.account_num = account_num;
	}
}
