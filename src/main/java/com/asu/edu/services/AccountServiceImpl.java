package com.asu.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asu.edu.daos.Account;
import com.asu.edu.daos.AccountHome;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountHome accountHome;
	
	public void setaccountHome(AccountHome accountHome) {
		this.accountHome = accountHome;
	}
	

	public Account getAccountDetails(String username) {

		return this.accountHome.findByUsername(username);
		//return new AccountHome();
	}


	@Override
	public Account getAccountDetailsByAccountNo(String accountNo) {
		// TODO Auto-generated method stub
		return this.accountHome.findByAccountNo(accountNo);
	}


	@Override
	public boolean setBalance(Account amount) {
		// TODO Auto-generated method stub
		this.accountHome.attachDirty(amount);
		return true;
	}


	

}
