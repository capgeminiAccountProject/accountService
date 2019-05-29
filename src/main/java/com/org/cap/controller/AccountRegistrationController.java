package com.org.cap.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cap.dto.Account;
import com.org.cap.dto.AccountRegistration;
import com.org.cap.dto.AccountRegistrationResponse;
import com.org.cap.service.AccountService;

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(AccountRegistrationController.class);

	@Autowired
	AccountService accountService;

	@PostMapping(value = "/create")
	public AccountRegistrationResponse createAccount(@RequestBody AccountRegistration accountInputForm, @RequestHeader String authorization){
		logger.error("In account Registration");
		
		AccountRegistrationResponse accountRegistrationResponse = new AccountRegistrationResponse();
		String isvalidToken = accountService.isTokenValid(authorization);
		if (isvalidToken.equalsIgnoreCase("success")) {
			accountRegistrationResponse = accountService.createAccount(accountInputForm);
			accountRegistrationResponse.setSuccess(true);
		}else {
			accountRegistrationResponse.setExceptionDetails("Token cannot be validated");
			accountRegistrationResponse.setSuccess(false);
		}
		return accountRegistrationResponse;

	}
	
	@GetMapping(value = "/accounts")
	public AccountRegistrationResponse getAllAccounts(@RequestHeader String authorization){
		logger.error("In find All Accounts");
		
		AccountRegistrationResponse accountRegistrationResponse = new AccountRegistrationResponse();
		String isvalidToken = accountService.isTokenValid(authorization);
		if (isvalidToken.equalsIgnoreCase("success")) {
		List<Account> accountList= accountService.getAccounts();
		accountRegistrationResponse.setAccountList(accountList);
		accountRegistrationResponse.setSuccess(true);
		}else {
			accountRegistrationResponse.setExceptionDetails("Token cannot be validated");
			accountRegistrationResponse.setSuccess(false);
		}
		return accountRegistrationResponse;

	}
	
	@GetMapping(value = "/accounts/{accountNum}")
	public AccountRegistrationResponse getAccountByAccountNum(@PathVariable(value = "accountNum") int accountNum, @RequestHeader String authorization) {
		logger.error("In get Account by Account Number");
		
		AccountRegistrationResponse accountRegistrationResponse = new AccountRegistrationResponse();
		String isvalidToken = accountService.isTokenValid(authorization);
		if (isvalidToken.equalsIgnoreCase("success")) {
		Account account= accountService.getAccountByAccountNum(accountNum);
		accountRegistrationResponse.setAccount(account);
		accountRegistrationResponse.setSuccess(true);
		}else {
			accountRegistrationResponse.setExceptionDetails("Token cannot be validated");
			accountRegistrationResponse.setSuccess(false);
		}
		return accountRegistrationResponse;

	}

	@PutMapping(value = "/accounts/{accountNum}")
	public AccountRegistrationResponse updateAccount(@PathVariable(value = "accountNum") int accountNum, @RequestBody AccountRegistration accountInputForm, @RequestHeader String authorization){
		logger.error("In Update Account");
		AccountRegistrationResponse accountRegistrationResponse = new AccountRegistrationResponse();
		String isvalidToken = accountService.isTokenValid(authorization);
		if (isvalidToken.equalsIgnoreCase("success")) {
		Account updatedAccount= accountService.updateAccount(accountNum, accountInputForm);
		accountRegistrationResponse.setAccount(updatedAccount);
		accountRegistrationResponse.setSuccess(true);
		}else {
			accountRegistrationResponse.setExceptionDetails("Token cannot be validated");
			accountRegistrationResponse.setSuccess(false);
		}
		return accountRegistrationResponse;

	}
	
	@DeleteMapping(value = "/accounts/{accountNum}")
	public AccountRegistrationResponse deleteAccount(@PathVariable(value = "accountNum") int accountNum, @RequestHeader String authorization){
		logger.error("In Delete Account");
		
		AccountRegistrationResponse accountRegistrationResponse = new AccountRegistrationResponse();
		String isvalidToken = accountService.isTokenValid(authorization);
		if (isvalidToken.equalsIgnoreCase("success")) {
		boolean isDeleted = accountService.deleteAccount(accountNum);
		accountRegistrationResponse.setSuccess(isDeleted);
		accountRegistrationResponse.setAccountNumber(String.valueOf(accountNum));
		}else {
			accountRegistrationResponse.setExceptionDetails("Token cannot be validated");
			accountRegistrationResponse.setSuccess(false);
		}
		return accountRegistrationResponse;

	}
}
