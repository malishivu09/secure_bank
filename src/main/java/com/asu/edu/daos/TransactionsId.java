package com.asu.edu.daos;

// Generated Oct 20, 2014 11:31:48 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * TransactionsId generated by hbm2java
 */
public class TransactionsId implements java.io.Serializable {

	private String accountSource;
	private String accountTarget;
	private double transactionAmount;
	private String transactionId;
	private String transactionType;
	private String transactionStatus;
	private boolean isCritical;
	private Date transactionTime;

	public TransactionsId() {
	}

	public TransactionsId(String accountSource, String accountTarget,
			double transactionAmount, String transactionId,
			String transactionType, String transactionStatus,
			boolean isCritical, Date transactionTime) {
		this.accountSource = accountSource;
		this.accountTarget = accountTarget;
		this.transactionAmount = transactionAmount;
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.isCritical = isCritical;
		this.transactionTime = transactionTime;
	}

	public String getAccountSource() {
		return this.accountSource;
	}

	public void setAccountSource(String accountSource) {
		this.accountSource = accountSource;
	}

	public String getAccountTarget() {
		return this.accountTarget;
	}

	public void setAccountTarget(String accountTarget) {
		this.accountTarget = accountTarget;
	}

	public double getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionStatus() {
		return this.transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public boolean isIsCritical() {
		return this.isCritical;
	}

	public void setIsCritical(boolean isCritical) {
		this.isCritical = isCritical;
	}

	public Date getTransactionTime() {
		return this.transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TransactionsId))
			return false;
		TransactionsId castOther = (TransactionsId) other;

		return ((this.getAccountSource() == castOther.getAccountSource()) || (this
				.getAccountSource() != null
				&& castOther.getAccountSource() != null && this
				.getAccountSource().equals(castOther.getAccountSource())))
				&& ((this.getAccountTarget() == castOther.getAccountTarget()) || (this
						.getAccountTarget() != null
						&& castOther.getAccountTarget() != null && this
						.getAccountTarget()
						.equals(castOther.getAccountTarget())))
				&& (this.getTransactionAmount() == castOther
						.getTransactionAmount())
				&& ((this.getTransactionId() == castOther.getTransactionId()) || (this
						.getTransactionId() != null
						&& castOther.getTransactionId() != null && this
						.getTransactionId()
						.equals(castOther.getTransactionId())))
				&& ((this.getTransactionType() == castOther
						.getTransactionType()) || (this.getTransactionType() != null
						&& castOther.getTransactionType() != null && this
						.getTransactionType().equals(
								castOther.getTransactionType())))
				&& ((this.getTransactionStatus() == castOther
						.getTransactionStatus()) || (this
						.getTransactionStatus() != null
						&& castOther.getTransactionStatus() != null && this
						.getTransactionStatus().equals(
								castOther.getTransactionStatus())))
				&& (this.isIsCritical() == castOther.isIsCritical())
				&& ((this.getTransactionTime() == castOther
						.getTransactionTime()) || (this.getTransactionTime() != null
						&& castOther.getTransactionTime() != null && this
						.getTransactionTime().equals(
								castOther.getTransactionTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAccountSource() == null ? 0 : this.getAccountSource()
						.hashCode());
		result = 37
				* result
				+ (getAccountTarget() == null ? 0 : this.getAccountTarget()
						.hashCode());
		result = 37 * result + (int) this.getTransactionAmount();
		result = 37
				* result
				+ (getTransactionId() == null ? 0 : this.getTransactionId()
						.hashCode());
		result = 37
				* result
				+ (getTransactionType() == null ? 0 : this.getTransactionType()
						.hashCode());
		result = 37
				* result
				+ (getTransactionStatus() == null ? 0 : this
						.getTransactionStatus().hashCode());
		result = 37 * result + (this.isIsCritical() ? 1 : 0);
		result = 37
				* result
				+ (getTransactionTime() == null ? 0 : this.getTransactionTime()
						.hashCode());
		return result;
	}

}
