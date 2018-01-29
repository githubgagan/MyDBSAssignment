package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.repository.AccountsRepository;
import lombok.Getter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

	@Getter
	private final AccountsRepository accountsRepository;

	@Autowired
	public AccountsService(AccountsRepository accountsRepository) {
		this.accountsRepository = accountsRepository;
	}

	public void createAccount(Account account) {
		this.accountsRepository.createAccount(account);
	}

	public Account getAccount(String accountId) {
		return this.accountsRepository.getAccount(accountId);
	}

	public AccountsRepository getAccountsRepository() {
		return this.accountsRepository;
	}
	
	
	public Account transferAmount(String fromAccId , String toAccId , String amount) throws Exception {
		
		Account fromAcc = this.accountsRepository.getAccount(fromAccId);
		Account toAcc =  this.accountsRepository.getAccount(toAccId);
		
		BigDecimal amt = new BigDecimal(amount);
		Account acc= null;
		acc =transferOut(fromAcc ,amt);
		acc =transferIn(toAcc,amt);
		
		return acc;
	}
	
	

	public Account transferIn(Account account ,BigDecimal amount) throws Exception {
		
		return this.accountsRepository.transferIn(account, amount);

	}

	public Account transferOut(Account account , BigDecimal amount) throws Exception {
		return this.accountsRepository.transferOut(account, amount);
	}

}
