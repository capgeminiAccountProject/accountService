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
	public AccountRegistrationResponse createAccount(@RequestBody AccountRegistration accountInputForm) {
		logger.error("In account Registration");
		AccountRegistrationResponse accountRegistrationResponse = new AccountRegistrationResponse();
		accountRegistrationResponse.setSuccess(false);
		if (null != accountInputForm) {

			accountRegistrationResponse = accountService.createAccount(accountInputForm);
		}

		return accountRegistrationResponse;

	}
	
	@GetMapping(value = "/accounts")
	public List<Account> getAllAccounts() {
		logger.error("In find All Accounts");
		List<Account> accountList= accountService.getAccounts();
		return accountList;

	}
	
	@GetMapping(value = "/accounts/{accountNum}")
	public Account getAccountByAccountNum(@PathVariable(value = "accountNum") int accountNum) {
		logger.error("In get Account by Account Number");
		Account account= accountService.getAccountByAccountNum(accountNum);
		return account;

	}

	@PutMapping(value = "/accounts/{accountNum}")
	public Account updateAccount(@PathVariable(value = "accountNum") int accountNum, @RequestBody AccountRegistration accountInputForm) {
		logger.error("In Update Account");
		Account updatedAccount= accountService.updateAccount(accountNum, accountInputForm);
		return updatedAccount;

	}
	
	@DeleteMapping(value = "/accounts/{accountNum}")
	public AccountRegistrationResponse deleteAccount(@PathVariable(value = "accountNum") int accountNum) {
		logger.error("In Delete Account");
		AccountRegistrationResponse accountRegistrationResponse = new AccountRegistrationResponse();
		
		boolean isDeleted = accountService.deleteAccount(accountNum);
		accountRegistrationResponse.setSuccess(isDeleted);
		accountRegistrationResponse.setAccountNumber(accountNum);
		return accountRegistrationResponse;

	}
}
