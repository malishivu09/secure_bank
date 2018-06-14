package com.asu.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.edu.daos.AccountHome;
import com.asu.edu.daos.Transactions;
import com.asu.edu.daos.TransactionsHome;


@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionsHome transactionsHome;

	
	public void setTransactionsHome(TransactionsHome transactionHome) {
		this.transactionsHome = transactionHome;
	}
	
	@Override
	public boolean addTransaction(Transactions transactions) {
		try{
			transactionsHome.attachDirty(transactions);;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Transactions> getAllPendingTransactions() {
		
		try{
			return transactionsHome.findAllPending();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Transactions getTransactionById(String transactionId) {
		
		 List<Transactions> out = transactionsHome.findByTransactionID(transactionId);
		
		 if(out.size() == 1) //should be one, transacitonID is unique
		 {
			 return out.get(0);
		 }
		return null;
	}

	@Override
	public void deleteTransaction(Transactions transaction) {
		transactionsHome.delete(transaction);
	}

	@Override
	public List<Transactions> getAllTransactionsRelatedToAccount(
			String accountNumber) {
		try{
			return transactionsHome.getAllTransactionRelatedToUser(accountNumber);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Transactions> getAllPendingMerchantRequests(
			String fromAccountNum) {
		try{
			return transactionsHome.findAllMerchantPayment(fromAccountNum);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public boolean setTransactionComplete(String id) {
		
		return false;
	}
	
	

	
	
}
