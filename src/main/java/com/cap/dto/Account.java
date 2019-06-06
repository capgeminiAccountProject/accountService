package com.cap.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "user_account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int account_num;
	
	private String first_name;

	private String last_name;
	
	private String ssn;
	
	private String dob;

	private String email;

	private String mobile_number;

	private String home_address;

	private String mailing_address;

	private String account_type;

	private double min_balance;

	/*
	 * 
	 * public int getAccount_num() { return account_num; }
	 * 
	 * 
	 * public void setAccount_num(int account_num) { this.account_num = account_num;
	 * }
	 * 
	 * 
	 * public String getFirst_name() { return first_name; }
	 * 
	 * 
	 * public String getDob() { return dob; }
	 * 
	 * public void setDob(String dob) { this.dob = dob; }
	 * 
	 * public void setFirst_name(String first_name) { this.first_name = first_name;
	 * }
	 * 
	 * public String getLast_name() { return last_name; }
	 * 
	 * public void setLast_name(String last_name) { this.last_name = last_name; }
	 * 
	 * public String getSsn() { return ssn; }
	 * 
	 * public void setSsn(String ssn) { this.ssn = ssn; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * public String getMobile_number() { return mobile_number; }
	 * 
	 * public void setMobile_number(String mobile_number) { this.mobile_number =
	 * mobile_number; }
	 * 
	 * public String getHome_address() { return home_address; }
	 * 
	 * public void setHome_address(String home_address) { this.home_address =
	 * home_address; }
	 * 
	 * public String getMailing_address() { return mailing_address; }
	 * 
	 * public void setMailing_address(String mailing_address) { this.mailing_address
	 * = mailing_address; }
	 * 
	 * public String getAccount_type() { return account_type; }
	 * 
	 * public void setAccount_type(String account_type) { this.account_type =
	 * account_type; }
	 * 
	 * public double getMin_balance() { return min_balance; }
	 * 
	 * public void setMin_balance(double min_balance) { this.min_balance =
	 * min_balance; }
	 * 
	 */
	
}
