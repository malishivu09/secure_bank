package com.asu.edu.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asu.edu.daos.Account;
import com.asu.edu.daos.AccountHome;
import com.asu.edu.daos.Roles;
import com.asu.edu.daos.RolesHome;
import com.asu.edu.daos.RolesId;
import com.asu.edu.daos.User;
import com.asu.edu.daos.UserHome;
import com.asu.edu.pki.generatingCRT;
import com.asu.edu.utils.Constants;
import com.asu.edu.utils.Utils;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserHome userHome;
	@Autowired
	private RolesHome rolesHome;
	@Autowired
	private AccountHome accountHome;
	
	public void setUserHome(UserHome userHome) {
		this.userHome = userHome;
	}
	
	public void setRolesHome(RolesHome rolesHome){
		this.rolesHome = rolesHome;
	}
	
	public void setAccountHome(AccountHome accountHome){
		this.accountHome = accountHome;
	}
	
	@Override
	public User getUserByUserName(String username) {
		
		return this.userHome.findByUserName(username);
	}

	@Override
	public void saveUser(User user) {
		try{
			 this.userHome.merge(user);
			
		}
		catch(Exception e)
		{
			//return false;
		}
		
	}

	@Override
	public List<User> getAllInActiveUser() {
		return this.userHome.findAllInActive();
	}

	//admin service for approving or declining user
//	@Override
//	public void manageUser(String username, String isApproved,String roleString) {
//		User user = this.userHome.findByUserName(username);
//		
//		if(user!=null){
//			System.out.println("User details "+user.getName());
//			System.out.println("User details "+user.getUsername());
//		}
//			
//		System.out.println(user);
//		
//		if(isApproved.equals("1")){ //approved
//			//approve the user, add User role , and create account for him
//			if(!user.isEnabled()){
//				user.setEnabled(true);
//				this.userHome.attachDirty(user); //enable the user 
//				
//				//Generate account for user
//				Account account = new Account();
//				account.setAccountNumber(Utils.getUniqueNumber());
//				account.setAccountType("");
//				account.setBalance(0);
//				account.setComments("");
//				java.util.Date date= new java.util.Date();
//				account.setCreationTime(new Timestamp(date.getTime()));
//				account.setIsBlocked(false);
//				account.setUser(user);
//				
//				//create account
//				accountHome.merge(account);
//				
//				Roles role = new Roles();
//				RolesId roleId = new RolesId();
//				roleId.setRole(roleString);
//				roleId.setUsername(username);
//			
//				role.setId(roleId);
//	
//				//add the role
//				this.rolesHome.attachDirty(role);
//				
//			}else{
//				//if user is already enabled, this should not happen unless bug is found
//			}
//			
//		}else{
//			//if rejected delete the user
//			userHome.delete(user);
//		}
//	}
	
	@Override
	public void manageUser(String username, String isApproved,String roleString) {
		User user = this.userHome.findByUserName(username);
		
		if(user!=null){
			System.out.println("User details "+user.getName());
			System.out.println("User details "+user.getUsername());
		}
			
		System.out.println(user);
		
		if(isApproved.equals("1")){ //approved
			//approve the user, add User role , and create account for him
			if(!user.isEnabled()){
				user.setEnabled(true);
				this.userHome.attachDirty(user); //enable the user 
				
				//Generate account for user
				Account account = new Account();
				account.setAccountNumber(Utils.getUniqueNumber());
				account.setAccountType("");
				account.setBalance(0);
				account.setComments("");
				java.util.Date date= new java.util.Date();
				account.setCreationTime(new Timestamp(date.getTime()));
				account.setIsBlocked(false);
				account.setUser(user);
				
				/*generatingCRT obj = new generatingCRT();
				obj.generateCRT(username);
				try {
					obj.Send(Constants.EMAIL_USERNAME,username, Constants.EMAIL_PWD, user.getEmail(), "", "The certificate is attached", "Please save the attached certificate");
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				//create account
				accountHome.merge(account);
				
				Roles role = new Roles();
				RolesId roleId = new RolesId();
				roleId.setRole(roleString);
				roleId.setUsername(username);
			
				role.setId(roleId);
	
				//add the role
				this.rolesHome.attachDirty(role);
				
			}else{
				//if user is already enabled, this should not happen unless bug is found
			}
			
		}else{
			//if rejected delete the user
			userHome.delete(user);
		}
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userHome.delete(user);
	}

}
