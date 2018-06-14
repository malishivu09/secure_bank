package com.asu.edu.services;

import java.util.List;

import com.asu.edu.appmodels.UserAppModel;
import com.asu.edu.daos.Roles;
import com.asu.edu.daos.User;

public interface RoleService {
	public boolean addRole(Roles role);
	public List<UserAppModel> getUserByRoles(String username);
}
