package com.db.awmd.challenge.repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class AccountsRepositoryInMemory implements AccountsRepository {

  private final Map<String, Account> accounts = new ConcurrentHashMap<>();

  @Override
  public void createAccount(Account account) throws DuplicateAccountIdException {
    Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
    if (previousAccount != null) {
      throw new DuplicateAccountIdException(
        "Account id " + account.getAccountId() + " already exists!");
    }
  }

  @Override
  public Account getAccount(String accountId) {
    return accounts.get(accountId);
  }

  @Override
  public void clearAccounts() {
    accounts.clear();
  }

@Override
public Account transferOut(Account account, BigDecimal amount) throws Exception {
	
	if (amount.compareTo(BigDecimal.ZERO) >= 0) {
		if (account.getBalance().compareTo(amount) >= 0) {
			 account.setBalance(account.getBalance().subtract(amount));
		} else {
			throw new Exception("Invalid transfer");
		}
	} else {
		throw new Exception("Invalid transfer");
	}
	return account;
}
	


@Override
public Account transferIn(Account account, BigDecimal amount) throws Exception {
	
	if (amount.compareTo(BigDecimal.ZERO) >= 0) {
		account.setBalance(account.getBalance().add(amount));
	} else {
		
		throw new Exception("Invalid transfer");
	}
	return account;
	
}

}
