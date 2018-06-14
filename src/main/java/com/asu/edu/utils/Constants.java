package com.asu.edu.utils;

public class Constants {
	
	 public static final String TRANSACTION_STATUS_COMPLETE = "transaction_complete";
	 public static final String TRANSACTION_STATUS_PENDING = "transaction_pending";
	 public static final String TRANSACTION_STATUS_PENDING_CERT_REQUIRED = "transaction_pending : Waiting for user upload certicate ";
	 public static final String TRANSACTION_TYPE_CREDIT = "credit";
	 public static final String TRANSACTION_TYPE_DEBIT = "debit";
	 public static final String TRANSACTION_TYPE_DEBIT_CERT = "debit_c";
	 public static final String PAYMENT_REQUEST_USER_PENDING = "Merchant request : Waiting for user Approval";
	 public static final String PAYMENT_REQUEST_ADMIN_PENDING = "Merchant request : Waiting for Admin Approval";
	 public static final String PAYMENT_REQUEST_USER_APPROVED = "Merchant request : User Approved";
	 public static final String PAYMENT_REQUEST_USER_APPROVED_MONEY_TRANSFERRED = "Merchant request : User Approved, amount transferred";
	 public static final String PAYMENT_REQUEST_ADMIN_APPROVED = "Merchant request : Admin Approved, amount transferred";
	 public static final String PAYMENT_REQUEST_USER_DECLINE = "Merchant request : Request rejected by user";
	 public static final String PAYMENT_REQUEST_ADMIN_DECLINE = "Merchant request : Request rejected by admin";
	 public static final String CRITICAL_TRANSACTION_REJECTED_DEBIT = "Withdrawal rejected by admin";
	 public static final String CRITICAL_TRANSACTION_REJECTED_TRANSFER = "Transfer rejected by admin";
	 public static final String EMAIL_USERNAME = "sbsgroup13project";
	 public static final String EMAIL_PWD = "sbsgroup13";
	 public static final String CERTIFICATES_PATH = "";
	 

	 public static String getTransactionStatement(String fromAcc, String toAcc)
	 {
		 return "Transfered from "+fromAcc+" to "+toAcc;
	 }
	 
	 public static String getRequestStatement(String fromAcc, String toAcc)
	 {
		 return "Requested payment from "+fromAcc+" to "+toAcc;
	 }
	 
	 

}
