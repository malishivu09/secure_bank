package com.asu.edu.services;

import java.util.List;

import com.asu.edu.daos.Transactions;


public interface TransactionService {
	
	public boolean addTransaction(Transactions transactions);
	
	public List<Transactions> getAllPendingTransactions();
	
	public List<Transactions> getAllTransactionsRelatedToAccount(String accountNumber);
	
	public List<Transactions> getAllPendingMerchantRequests(String fromAccountNum);
	
	public Transactions getTransactionById(String transactionId);

	public void deleteTransaction(Transactions transaction);
	
	public boolean setTransactionComplete(String id);
	
}
