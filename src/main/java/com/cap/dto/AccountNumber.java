package com.cap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountNumber {

	private int account_num;


	public AccountNumber(int account_num) {
		super();
		this.account_num = account_num;
	}
}
