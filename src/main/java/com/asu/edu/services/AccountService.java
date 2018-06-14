package com.asu.edu.services;

import com.asu.edu.daos.Account;
import com.asu.edu.daos.AccountHome;

public interface AccountService {

	Account getAccountDetails(String username);
	Account getAccountDetailsByAccountNo(String accountNo);
	boolean setBalance(Account amount);
}
