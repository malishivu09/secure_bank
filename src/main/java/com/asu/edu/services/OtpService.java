package com.asu.edu.services;

import java.util.List;

import com.asu.edu.daos.Otp;

public interface OtpService {

	
	public List<Otp> getOTPByUsename(String username);
	
	public void addOtp(Otp o);
	
	public void deleteOtp(String username);
	
}
