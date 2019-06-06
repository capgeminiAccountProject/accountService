package com.cap.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
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

import com.cap.dto.Account;
import com.cap.dto.AccountRegistration;
import com.cap.dto.AccountRegistrationResponse;
import com.cap.service.AccountService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRegistrationController {

	@Autowired
	AccountService accountService;

	@PostMapping(value = "/create")
	public AccountRegistrationResponse createAccount(@RequestBody AccountRegistration accountInputForm,
			@RequestHeader String authorization) {
		log.info("In account Registration");
		AccountRegistrationResponse accountRegistrationResponse = null;
		try {
			accountRegistrationResponse = new AccountRegistrationResponse();
			String isvalidToken = accountService.isTokenValid(authorization);
			if (isvalidToken.equalsIgnoreCase("success")) {
				accountRegistrationResponse = accountService.createAccount(accountInputForm);
				accountRegistrationResponse.setSuccess(true);
			} else {
				accountRegistrationResponse.setExceptionDetails("Token cannot be validated");
				accountRegistrationResponse.setSuccess(false);
			}
		} catch (Exception ex) {
			log.error("Exception occurred while created new Account "+ ExceptionUtils.getStackTrace(ex));
			accountRegistrationResponse
					.setExceptionDetails("Runtime Exception Occurred: " + ex.getMessage());
			accountRegistrationResponse.setSuccess(false);
		}

		return accountRegistrationResponse;

	}

	@GetMapping(value = "/accounts")
	public AccountRegistrationResponse getAllAccounts(@RequestHeader String authorization) {
		log.info("In find All Accounts");
		AccountRegistrationResponse accountRegistrationResponse = null;
		try {
			accountRegistrationResponse = new AccountRegistrationResponse();
			String isvalidToken = accountService.isTokenValid(authorization);
			if (isvalidToken.equalsIgnoreCase("success")) {
				List<Account> accountList = accountService.getAccounts();
				accountRegistrationResponse.setAccountList(accountList);
				accountRegistrationResponse.setSuccess(true);
			} else {
				accountRegistrationResponse.setExceptionDetails("Token cannot be validated");
				accountRegistrationResponse.setSuccess(false);
			}
		} catch (Exception ex) {
			log.error("Exception occurred while getting all account details" + ExceptionUtils.getStackTrace(ex));
			accountRegistrationResponse
					.setExceptionDetails("Runtime Exception Occurred: " + ex.getMessage());
			accountRegistrationResponse.setSuccess(false);
		}
		return accountRegistrationResponse;

	}

	@GetMapping(value = "/accounts/{accountNum}")
	public AccountRegistrationResponse getAccountByAccountNum(@PathVariable(value = "accountNum") int accountNum,
			@RequestHeader String authorization) {
		log.info("In get Account by Account Number");
		AccountRegistrationResponse accountRegistrationResponse = null;
		try {
			accountRegistrationResponse = new AccountRegistrationResponse();
			String isvalidToken = accountService.isTokenValid(authorization);
			if (isvalidToken.equalsIgnoreCase("success")) {
				Account account = accountService.getAccountByAccountNum(accountNum);
				accountRegistrationResponse.setAccount(account);
				accountRegistrationResponse.setSuccess(true);
			} else {
				accountRegistrationResponse.setExceptionDetails("Token cannot be validated");
				accountRegistrationResponse.setSuccess(false);
			}
		} catch (Exception ex) {
			log.error("Exception occurred while getting account details by Account number "+ ExceptionUtils.getStackTrace(ex));
			accountRegistrationResponse
			.setExceptionDetails("Runtime Exception Occurred: " + ex.getMessage());
			accountRegistrationResponse.setSuccess(false);
		}
		return accountRegistrationResponse;

	}

	@PutMapping(value = "/accounts/{accountNum}")
	public AccountRegistrationResponse updateAccount(@PathVariable(value = "accountNum") int accountNum,
			@RequestBody AccountRegistration accountInputForm, @RequestHeader String authorization) {
		log.info("In Update Account");
		AccountRegistrationResponse accountRegistrationResponse = null;
		try {
			accountRegistrationResponse = new AccountRegistrationResponse();
			String isvalidToken = accountService.isTokenValid(authorization);
			if (isvalidToken.equalsIgnoreCase("success")) {
				Account updatedAccount = accountService.updateAccount(accountNum, accountInputForm);
				accountRegistrationResponse.setAccount(updatedAccount);
				accountRegistrationResponse.setSuccess(true);
			} else {
				accountRegistrationResponse.setExceptionDetails("Token cannot be validated");
				accountRegistrationResponse.setSuccess(false);
			}
		} catch (Exception ex) {
			log.error("Exception occurred while created new Account "+ ExceptionUtils.getStackTrace(ex));
			accountRegistrationResponse
			.setExceptionDetails("Runtime Exception Occurred: " + ex.getMessage());
			accountRegistrationResponse.setSuccess(false);
		}
		return accountRegistrationResponse;

	}

	@DeleteMapping(value = "/accounts/{accountNum}")
	public AccountRegistrationResponse deleteAccount(@PathVariable(value = "accountNum") int accountNum,
			@RequestHeader String authorization) {
		log.info("In Delete Account");
		AccountRegistrationResponse accountRegistrationResponse = null;
		try {
			accountRegistrationResponse = new AccountRegistrationResponse();
			String isvalidToken = accountService.isTokenValid(authorization);
			if (isvalidToken.equalsIgnoreCase("success")) {
				boolean isDeleted = accountService.deleteAccount(accountNum);
				accountRegistrationResponse.setSuccess(isDeleted);
				accountRegistrationResponse.setAccountNumber(String.valueOf(accountNum));
			} else {
				accountRegistrationResponse.setExceptionDetails("Token cannot be validated");
				accountRegistrationResponse.setSuccess(false);
			}
		} catch (Exception ex) {
			log.error("Exception occurred while deleting account "+ ExceptionUtils.getStackTrace(ex));
			accountRegistrationResponse
			.setExceptionDetails("Runtime Exception Occurred: " + ex.getMessage());
			accountRegistrationResponse.setSuccess(false);
		}
		return accountRegistrationResponse;

	}
}
