package com.asu.edu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.asu.edu.appmodels.UserAppModel;
import com.asu.edu.daos.Mappings;
import com.asu.edu.daos.MappingsHome;

public class MappingServiceImpl implements MappingService {

	@Autowired
	private MappingsHome mappingsHome;
	
	public void setMappingsHome(MappingsHome mappingsHome) {
		this.mappingsHome = mappingsHome;
	}


	@Override
	public boolean saveMapping(Mappings id) {
		try{
			mappingsHome.attachDirty(id);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public List<List<Mappings>> getAllMapping() {
		List<List<Mappings>> mappings = new ArrayList<List<Mappings>>();
		List<Mappings> map = mappingsHome.getAll();
		
		List<Mappings> pending = new ArrayList<Mappings>();
		List<Mappings> active = new ArrayList<Mappings>();
		
		for (Mappings m : map) {
			if(!m.isStatus()){
				pending.add(m);
			}else{
				active.add(m);
			}
		}
		mappings.add(pending);
		mappings.add(active);
		
		return mappings;
	}


	@Override
	public boolean deleteMapping(Mappings id) {
		this.mappingsHome.delete(id);
		return true;
	}

	
	

	@Override
	public List<UserAppModel> getAllowedUserFor(String username) {
		List<Mappings> allowedUser = this.mappingsHome.getAllowedUserFor(username);
		System.out.println("allowed users are "+allowedUser.size());
		List<UserAppModel> allowedUsers = new ArrayList<UserAppModel>();
		for (int i = 0; i < allowedUser.size(); i++) {
			if(allowedUser.get(i).isStatus())
			{
				UserAppModel user = new UserAppModel();
				user.setName(allowedUser.get(i).getUserByExternalUsername().getUsername());
				allowedUsers.add(user);
			}
		}
		
		return allowedUsers;
	}


	@Override
	public Mappings getExistingPendingMapping(String internalUsername,
			String externalUsername) {
		
		List<Mappings> mappings = this.mappingsHome.getAll();
		List<Mappings> filtered = new ArrayList<Mappings>();
		for (int i = 0; i < mappings.size(); i++) {
			Mappings map = mappings.get(i);
			if(map.getUserByExternalUsername().getUsername().equals(externalUsername)&&map.getUserByInternalUsername().getUsername().equals(internalUsername)&&!map.isStatus())
					filtered.add(map);
		}
		if(filtered.size()==0)
			return null;
		
		return filtered.get(0);
	}


	@Override
	public Mappings getExistingMapping(String internalUsername,
			String externalUsername) {
		List<Mappings> mappings = this.mappingsHome.getAll();
		List<Mappings> filtered = new ArrayList<Mappings>();
		for (int i = 0; i < mappings.size(); i++) {
			Mappings map = mappings.get(i);
			if(map.getUserByExternalUsername().getUsername().equals(externalUsername)&&map.getUserByInternalUsername().getUsername().equals(internalUsername))
					filtered.add(map);
		}
		if(filtered.size()==0)
			return null;
		
		return filtered.get(0);
		
	}





}
 