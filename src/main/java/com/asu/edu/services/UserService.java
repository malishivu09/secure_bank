package com.asu.edu.services;

import java.util.List;

import com.asu.edu.daos.User;



public interface UserService {
	
	public User getUserByUserName(String username);
	
	public void saveUser(User user);
	
	public List<User> getAllInActiveUser();

	public void manageUser(String username, String isApproved,String role);
	
	public void deleteUser(User user);

}
