package com.asu.edu.utils;

import com.asu.edu.appmodels.UserAppModel;
import com.asu.edu.daos.User;
import com.asu.edu.daos.UserHome;

public class UserAppModelMapper {
	
	public UserAppModel convertToUserAppModel(User user)
	{
		UserAppModel u = new UserAppModel();
		
		u.setAddress(user.getAddress());
		u.setEmail(user.getEmail());
		u.setName(user.getName());
		u.setPhoneNumber(user.getPhoneNumber());
		
		//for identifying users uniquely
		u.setUsername(user.getUsername());
		
		return u;
	}

	public User convertToUser(UserAppModel user, String username)
	{
		User u = new User();
		
		UserHome uh = new UserHome();
		
		u = uh.findByUserName(username);
		
		u.setAddress(user.getAddress());
		u.setEmail(user.getEmail());
		u.setName(user.getName());
		u.setPhoneNumber(user.getPhoneNumber());
		
		return u;
	}
	
}
