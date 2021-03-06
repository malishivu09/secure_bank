package com.asu.edu.daos;

// Generated Nov 7, 2014 3:07:06 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * OtpId generated by hbm2java
 */
public class OtpId implements java.io.Serializable {

	private String username;
	private int otp;
	private Date creationTime;

	public OtpId() {
	}

	public OtpId(String username, int otp, Date creationTime) {
		this.username = username;
		this.otp = otp;
		this.creationTime = creationTime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getOtp() {
		return this.otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public Date getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OtpId))
			return false;
		OtpId castOther = (OtpId) other;

		return ((this.getUsername() == castOther.getUsername()) || (this
				.getUsername() != null && castOther.getUsername() != null && this
				.getUsername().equals(castOther.getUsername())))
				&& (this.getOtp() == castOther.getOtp())
				&& ((this.getCreationTime() == castOther.getCreationTime()) || (this
						.getCreationTime() != null
						&& castOther.getCreationTime() != null && this
						.getCreationTime().equals(castOther.getCreationTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result + this.getOtp();
		result = 37
				* result
				+ (getCreationTime() == null ? 0 : this.getCreationTime()
						.hashCode());
		return result;
	}

}
