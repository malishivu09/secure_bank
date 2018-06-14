package com.asu.edu.services;

import java.util.List;

import com.asu.edu.appmodels.UserAppModel;
import com.asu.edu.daos.Mappings;

public interface MappingService {

	public boolean saveMapping(Mappings id);
	
	public List<List<Mappings>> getAllMapping();
	
	public boolean deleteMapping(Mappings id);

	public List<UserAppModel> getAllowedUserFor(String username);
	
	public Mappings getExistingPendingMapping(String internalUsername,String externalUsername);
	
	public Mappings getExistingMapping(String internalUsername,String externalUsername);
}
