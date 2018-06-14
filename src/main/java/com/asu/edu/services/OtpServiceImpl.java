package com.asu.edu.services;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.edu.daos.Otp;
import com.asu.edu.daos.OtpHome;
import com.asu.edu.daos.TransactionsHome;

@Service
public class OtpServiceImpl implements OtpService {

	@Autowired
	private OtpHome otpHome;

	
	public void setOtpHome(OtpHome otpHome) {
		this.otpHome = otpHome;
	}

	
	public List<Otp> getOTPByUsename(String username) {
		
		
		return otpHome.getOtpByUsername(username);
	}

	public void addOtp(Otp o) {
		// TODO Auto-generated method stub
		
		List<Otp>  delete = otpHome.findOtpByUsername(o.getId().getUsername());
		if(delete.size()>0)
			otpHome.delete(delete.get(0));
			
		otpHome.merge(o);
	}


	@Override
	public void deleteOtp(String username) {
		
		
	}

}
