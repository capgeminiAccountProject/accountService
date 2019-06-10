package com.cap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cap.dto.Account;
import com.cap.dto.AccountNumber;
import com.cap.dto.AccountRegistration;
import com.cap.dto.AccountRegistrationResponse;
import com.cap.dto.AuthJson;
import com.cap.dto.ResponseBean;
import com.cap.exception.ResourceNotFoundException;
import com.cap.repository.AccountCrudRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AccountService {

	@Autowired
	AccountCrudRepository accountCrudRepository;

	public AccountRegistrationResponse createAccount(AccountRegistration accountInputForm) {

		Account accountResponse = populateAccountEntity(accountInputForm);
		AccountNumber accNum = new AccountNumber(accountCrudRepository.save(accountResponse).getAccount_num());
		AccountRegistrationResponse response = new AccountRegistrationResponse();
		response.setAccountNumber(String.valueOf(accNum.getAccount_num()));
		response.setSuccess(true);
		log.info("Account Number Generated : " + accNum.getAccount_num());
		return response;
	}

	private Account populateAccountEntity(AccountRegistration accountInputForm) {

		Account accountEntity = new Account();
		accountEntity.setFirst_name(accountInputForm.getFirst_name());
		accountEntity.setLast_name(accountInputForm.getLast_name());
		accountEntity.setDob(accountInputForm.getDob());
		accountEntity.setEmail(accountInputForm.getEmail());
		accountEntity.setAccount_type(accountInputForm.getAccount_type());
		accountEntity.setHome_address(accountInputForm.getHome_address());
		accountEntity.setMailing_address(accountInputForm.getMailing_address());
		accountEntity.setMin_balance(accountInputForm.getMin_balance());
		accountEntity.setMobile_number(accountInputForm.getMobile_number());
		accountEntity.setSsn(accountInputForm.getSsn());

		return accountEntity;
	}

	public List<Account> getAccounts() {

		List<Account> accountList = new ArrayList<Account>();
		accountList = (List<Account>) accountCrudRepository.findAll();
		return accountList;

	}

	public String isTokenValid(String token) {
		final String uri = "https://token-001-fluent-klipspringer.cfapps.io/checktokenuser";
		ResponseBean responseBean = null;
		try {
			responseBean = new ResponseBean();
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("authorization", token);
			headers.setContentType(MediaType.APPLICATION_JSON);
			AuthJson authJson = new AuthJson();
			authJson.setEmail("aaj@gmail.com");
			authJson.setToken(token);
			ObjectMapper mapper = new ObjectMapper();

			String authJsonString = mapper.writeValueAsString(authJson);

			HttpEntity<String> entity = new HttpEntity<>(authJsonString, headers);
			log.info("validate token request: " + authJsonString);
			String response = restTemplate.postForObject(uri, entity, String.class);
			responseBean = mapper.readValue(response, ResponseBean.class);
			log.info("response status: " + responseBean.getStatus());
		} catch (Exception e) {
			responseBean.setStatus("fail");
		}
		return responseBean.getStatus();

	}

	public Account getAccountByAccountNum(int accountNum) {

		return accountCrudRepository.findById(accountNum)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNum", accountNum));

	}

	public Account updateAccount(int accountNum, AccountRegistration accountInputForm) {

		Account account = getAccountByAccountNum(accountNum);
		if (null != accountInputForm.getAccount_type()) {
			account.setAccount_type(accountInputForm.getAccount_type());
		}
		if (null != accountInputForm.getDob()) {
			account.setDob(accountInputForm.getDob());
		}
		if (null != accountInputForm.getEmail()) {
			account.setEmail(accountInputForm.getEmail());
		}
		if (null != accountInputForm.getFirst_name()) {
			account.setFirst_name(accountInputForm.getFirst_name());
		}
		if (null != accountInputForm.getLast_name()) {
			account.setLast_name(accountInputForm.getLast_name());
		}
		if (null != accountInputForm.getHome_address()) {
			account.setHome_address(accountInputForm.getHome_address());
		}
		if (null != accountInputForm.getMailing_address()) {
			account.setMailing_address(accountInputForm.getMailing_address());
		}
		if (null != accountInputForm.getMobile_number()) {
			account.setMobile_number(accountInputForm.getMobile_number());
		}
		if (null != accountInputForm.getSsn()) {
			account.setSsn(accountInputForm.getSsn());
		}

		Account updatedAccount = accountCrudRepository.save(account);
		return updatedAccount;
	}

	public boolean deleteAccount(int accountNum) {

		Account account = getAccountByAccountNum(accountNum);
		accountCrudRepository.delete(account);
		return true;

	}

}
