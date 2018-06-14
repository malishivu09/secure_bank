package com.asu.edu.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class RoleHandler implements AuthenticationSuccessHandler,  AuthenticationFailureHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		//type of user
		boolean isUser = false;
        boolean isAdmin = false;
        boolean isInternalUser = false;
        boolean isMerchant=false;
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("Admin")) {
            	isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("User")) {
            	isUser = true;
                break;
            }else if (grantedAuthority.getAuthority().equals("InternalUser")) {
            	isInternalUser = true;
                break;
            }
            else if (grantedAuthority.getAuthority().equals("Merchant")) {
            	isMerchant = true;
                break;
        }
        }
 
        if (isUser) {
        	response.sendRedirect("UserHome");
        } else if (isAdmin) {
        	response.sendRedirect("AdminHome");
        } else if(isInternalUser) {
        		response.sendRedirect("InternalHome");
        	
       }else if (isMerchant){
    	   response.sendRedirect("MerchantHome");
       }
        else {
            throw new IllegalStateException();
        }
		
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest session,
			HttpServletResponse arg1, AuthenticationException arg2)
			throws IOException, ServletException {
		
		
	}
	
	

}
