package com.asu.edu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.edu.appmodels.UserAppModel;
import com.asu.edu.daos.Mappings;
import com.asu.edu.daos.Roles;
import com.asu.edu.daos.RolesHome;
import com.asu.edu.daos.User;
import com.asu.edu.daos.UserHome;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RolesHome rolesHome;

	@Autowired
	private UserHome userHome;
	
	
	public void setRolesHome(RolesHome rolesHome) {
		this.rolesHome = rolesHome;
	}
	
	@Override
	public boolean addRole(Roles role) {
		try{
		this.rolesHome.attachDirty(role);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public List<UserAppModel> getUserByRoles(String role) {
		// TODO Auto-generated method stub
		List<Roles> c = rolesHome.findByRoles(role);
		List<UserAppModel> allowedUsers = new ArrayList<UserAppModel>();
		for (int i = 0; i < c.size(); i++) {
			UserAppModel user = new UserAppModel();
			user.setUsername(c.get(i).getId().getUsername());
			allowedUsers.add(user);
		}
		
		return allowedUsers;

	}

}
