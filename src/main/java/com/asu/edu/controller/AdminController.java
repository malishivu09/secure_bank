package com.asu.edu.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.asu.edu.appmodels.UserAppModel;
import com.asu.edu.daos.Account;
import com.asu.edu.daos.Mappings;
import com.asu.edu.daos.Roles;
import com.asu.edu.daos.RolesId;
import com.asu.edu.daos.Transactions;
import com.asu.edu.daos.User;
import com.asu.edu.pki.generatingCRT;
import com.asu.edu.services.AccountService;
import com.asu.edu.services.MappingService;
import com.asu.edu.services.RoleService;
import com.asu.edu.services.TransactionService;
import com.asu.edu.services.UserService;
import com.asu.edu.utils.Constants;
import com.asu.edu.utils.UserAppModelMapper;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private MappingService mappingService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private RoleService rolesService;

	private static final Logger logger = Logger
			.getLogger(AdminController.class);

	@RequestMapping(value = "AdminHome/viewAdminUserDetails", method = RequestMethod.GET)
	public String viewAdminUserDetails1(HttpServletRequest request, Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		// System.out.println("i m here username : "+authentication.getName());
		User user = userService.getUserByUserName(authentication.getName());
		model.addAttribute("phonenumber", user.getPhoneNumber());
		model.addAttribute("username", user.getName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("address", user.getAddress());
		model.addAttribute("name", user.getName());

		System.out.println(user.getPhoneNumber());
		System.out.println(user.getAddress());
		System.out.println(user.getEmail());

		logger.info("View Admin details");
		return "viewAdminUserDetails";
	}

	@RequestMapping(value = "AdminHome/editAdminUserDetails", method = RequestMethod.GET)
	public String editAdminUserDetails(HttpServletRequest request, Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		// System.out.println("i m here username : "+authentication.getName());
		User user = userService.getUserByUserName(authentication.getName());
		model.addAttribute("phonenumber", user.getPhoneNumber());
		model.addAttribute("username", user.getName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("address", user.getAddress());
		model.addAttribute("name", user.getName());
		model.addAttribute("phonenumber", user.getPhoneNumber());
		logger.info("edit Admin User Details");
		return "editAdminUserDetails";
	}

	@RequestMapping(value = "AdminHome/externalUserRequest", method = RequestMethod.GET)
	public ModelAndView externalUserRequest(HttpServletRequest request,
			Model model) {
		List<User> users = userService.getAllInActiveUser();
		List<UserAppModel> userModel = new ArrayList<UserAppModel>();
		UserAppModelMapper mapper = new UserAppModelMapper();
		for (int i = 0; i < users.size(); i++) {
			userModel.add(mapper.convertToUserAppModel(users.get(i)));
		}
		logger.info("external user request");
		ModelAndView out = new ModelAndView("adminExternalUserRequest");
		out.addObject("users", userModel);
		return out;
	}

	// internalUserRequest
	@RequestMapping(value = "AdminHome/internalUserRequest", method = RequestMethod.GET)
	public ModelAndView internalUserRequest(HttpServletRequest request,
			Model model) {
		List<List<Mappings>> mappings = mappingService.getAllMapping();

		ModelAndView out = new ModelAndView("internalExternalUserRequest");
		out.addObject("pendingMapping", mappings.get(0));
		out.addObject("approvedMapping", mappings.get(1));
		logger.info("internal user request");
		return out;
	}

	// getPendingTransactions
	@RequestMapping(value = "AdminHome/pendingTransactions", method = RequestMethod.GET)
	public ModelAndView getPendingTransactions(HttpServletRequest request,
			Model model) {
		List<Transactions> transactionPending = transactionService
				.getAllPendingTransactions();
		ModelAndView out = new ModelAndView("adminTransactionsPending");
		out.addObject("transactions", transactionPending);
		logger.info("view pending transaction");
		return out;
	}

	// manage internal user - sowmya

	@RequestMapping(value = "AdminHome/manageInternalUser", method = RequestMethod.GET)
	public String manageInternalUser(HttpServletRequest request, Model model) {
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			return "manageInternalUser";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failuremsg";
		}

	}

	@RequestMapping(value = "AdminHome/addInternalUser", method = RequestMethod.GET)
	public String addInternalUser1(HttpServletRequest request, Model model) {
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			logger.info("add internal user");
			return "addInternalUser";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failuremsg";
		}
		
	}

	@RequestMapping(value = "AdminHome/selectDeleteUser", method = RequestMethod.GET)
	public ModelAndView selectDeleteUser(HttpServletRequest request, Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		// String username = authentication.getName();

		List<UserAppModel> userList = rolesService
				.getUserByRoles("InternalUser");

		ModelAndView output = new ModelAndView("selectDeleteUser");
		output.addObject("users", userList);
		logger.info("delete user");
		return output;
	}

	@RequestMapping(value = "AdminHome/selectDeleteUser", method = RequestMethod.POST)
	public ModelAndView selectDeleteUser1(HttpServletRequest request,
			Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = request.getParameter("username");
		User user = userService.getUserByUserName(username);
		userService.deleteUser(user);

		ModelAndView out1 = new ModelAndView("alertview");
		out1.addObject("message", "Successfully edited !");
		return out1;
	}

	@RequestMapping(value = "AdminHome/addInternalUser", method = RequestMethod.POST)
	public ModelAndView addInternalUser(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();

			// get update parameters
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			boolean enabled = true;
			int pii = 233;
			String publicKey = request.getParameter("publickey");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phonenumber");

			ModelAndView model = new ModelAndView("alertview");

			if (name == null || email == null || address == null
					|| phoneNumber == null || username == null
					|| password == null) {
				model.addObject("message", "Invalid Request");
				return model;
			}

			User user = new User();
			user.setAddress(address);
			user.setEmail(email);
			user.setEnabled(true);
			user.setName(name);
			user.setPassword(hashedPassword);
			user.setPhoneNumber(phoneNumber);
			user.setPii(pii);
			user.setPublicKey(publicKey);
			user.setUsername(username);

			userService.saveUser(user);
			// get custom message from userService

			Roles role = new Roles();
			RolesId roleId = new RolesId();
			roleId.setRole("InternalUser");
			roleId.setUsername(username);
			role.setId(roleId);

			// add the role

			rolesService.addRole(role);

			model.addObject("message", "Successfully edited !");
			return model;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ModelAndView model = new ModelAndView("alertview");
			model.addObject("message",
					"Error: Something went Wrong. Sorry, We are looking into it!");
			e.printStackTrace();
			return model;
		}
	}

	// actionExternalUser -- approve or decline
	@RequestMapping(value = "AdminHome/actionExternalUser", method = RequestMethod.POST)
	public ModelAndView actionExternalUserRequest(HttpServletRequest request,
			Model model) {

		String username = request.getParameter("user");
		String isApproved = request.getParameter("flag");
		String role = request.getParameter("role");
		
		User u = new User();
		u = userService.getUserByUserName(username);

		try{
			//generate the certificate
			//and generate batch file
			//mail the code
			generatingCRT crt = new generatingCRT();
			crt.generateCRT(username);
			crt.SendFile(username, u.getEmail(), "", "CERTIFCATE", "");
			
		}catch(Exception e)
		{
			e.printStackTrace();
			//print stack trace and don't worry
		}
		
		userService.manageUser(username, isApproved, role);

		ModelAndView out = new ModelAndView("alertview");
		out.addObject("message", username + isApproved);
		return out;
	}

	// actionTransaction
	@RequestMapping(value = "AdminHome/actionTransaction", method = RequestMethod.POST)
	public ModelAndView actionTransaction(HttpServletRequest request) {

		String id = request.getParameter("id");
		String isApproved = request.getParameter("flag");
		double amount;
		double totalAmount;
		ModelAndView model = new ModelAndView("alertview");

		Transactions tr = transactionService.getTransactionById(id);

		if (isApproved.equals("1")) {
			// peform credit debit based on transaction type

			if (tr.getTransactionType().equalsIgnoreCase("debit")) {

				// peform debit on source account
				Account accFromTr = tr.getAccountByAccountSource();

				// account =
				// this.accountService.getAccountDetailsByAccountNo(account.getAccountNumber());

				// perform credit on target account
				Account target = tr.getAccountByAccountTarget();

				amount = tr.getTransactionAmount();

				System.out.println("Balance = " + accFromTr.getBalance());

				Account acc = this.accountService
						.getAccountDetailsByAccountNo(accFromTr
								.getAccountNumber());
				if (amount <= acc.getBalance()) {
					totalAmount = acc.getBalance() - amount;
					acc.setBalance(totalAmount);

					tr.setIsCritical(false);

					tr.setTransactionStatus(Constants.TRANSACTION_STATUS_COMPLETE);

					accountService.setBalance(acc);
					System.out.println("i m here");
					// transactionService.addTransaction(tr);
					// model.addAttribute("contentUrl", "credit_success");

					if (transactionService.addTransaction(tr)) {
						model.addObject("message", "Approved!!");
					} else {
						model.addObject("message", "Failed to approve!!");
					}
				} else {
					model.addObject("message", "Insufficient balance");
				}

				//

			} else if (tr.getTransactionType().contains("Transfered")) {

				amount = tr.getTransactionAmount();

				Account accFromTr = tr.getAccountByAccountSource();
				Account payee_accFromTr = tr.getAccountByAccountTarget();

				Account acc = this.accountService
						.getAccountDetailsByAccountNo(accFromTr
								.getAccountNumber());
				Account payee_acc = this.accountService
						.getAccountDetailsByAccountNo(payee_accFromTr
								.getAccountNumber());

				// System.out.println("Balance = " + acc.getBalance());
				if (payee_acc != null) {
					if ((amount > 0) && (amount <= acc.getBalance())) {

						tr.setIsCritical(false);
						tr.setTransactionStatus(Constants.TRANSACTION_STATUS_COMPLETE);
						totalAmount = acc.getBalance() - amount;
						acc.setBalance(totalAmount);
						payee_acc.setBalance(payee_acc.getBalance() + amount);
						accountService.setBalance(acc);
						accountService.setBalance(payee_acc);

						System.out.println("balance" + acc.getBalance());
						System.out.println("acc number :"
								+ acc.getAccountNumber());

						if (transactionService.addTransaction(tr)) {
							model.addObject("message", "Transfer approved!!");
						} else {
							model.addObject("message", "Transfer failed!!");
						}
					} else if (amount < 0) {
						model.addObject("message", "Amount is less than zero");

					} else if ((amount <= acc.getBalance())) {
						model.addObject("message", "Insufficient balance!!");

					} else {
						model.addObject("message", "Transfer failed!!");
						model = new ModelAndView("transfer_failure");
					}
				} else {
					model.addObject("message", "incorrect_payee_account");

				}
			} else if (tr.getTransactionType().contains("Requested payment")) {
				amount = tr.getTransactionAmount();

				Account accFromTr = tr.getAccountByAccountSource();
				Account payee_accFromTr = tr.getAccountByAccountTarget();

				Account acc = this.accountService
						.getAccountDetailsByAccountNo(accFromTr
								.getAccountNumber());
				Account payee_acc = this.accountService
						.getAccountDetailsByAccountNo(payee_accFromTr
								.getAccountNumber());
				System.out.println("Balance = " + acc.getBalance());
				if (payee_acc != null) {
					if ((amount > 0) && (amount <= acc.getBalance())) {

						tr.setIsCritical(false);
						tr.setTransactionStatus(Constants.PAYMENT_REQUEST_ADMIN_APPROVED);
						totalAmount = acc.getBalance() - amount;
						acc.setBalance(totalAmount);
						payee_acc.setBalance(payee_acc.getBalance() + amount);
						accountService.setBalance(acc);
						accountService.setBalance(payee_acc);

						if (transactionService.addTransaction(tr)) {
							model.addObject("message", "Transfer approved!!");
						} else {
							model.addObject("message", "Transfer failed!!");
						}
					} else if (amount < 0) {
						model.addObject("message", "Amount is less than zero");

					} else if ((amount <= acc.getBalance())) {
						model.addObject("message", "Insufficient balance!!");

					} else {
						model.addObject("message", "Transfer failed!!");
						model = new ModelAndView("transfer_failure");
					}
				}
			} else {
				model.addObject("message", "incorrect_payee_account");
			}

		} else {
			// in case of decline, delete transaction
			if (tr.getTransactionType().equalsIgnoreCase("debit")) {
				tr.setIsCritical(false);
				tr.setTransactionStatus(Constants.PAYMENT_REQUEST_ADMIN_DECLINE);
				transactionService.addTransaction(tr);
			} else if (tr.getTransactionType().equalsIgnoreCase("Transfered")) {
				tr.setIsCritical(false);
				tr.setTransactionStatus(Constants.CRITICAL_TRANSACTION_REJECTED_TRANSFER);
				transactionService.addTransaction(tr);
			} else if (tr.getTransactionType().equalsIgnoreCase(
					"Requested payment")) {
				tr.setIsCritical(false);
				tr.setTransactionStatus(Constants.PAYMENT_REQUEST_ADMIN_DECLINE);
				transactionService.addTransaction(tr);
			}

		}

		return model;
	}

	// actionInternalUser -- approve or decline
	@RequestMapping(value = "AdminHome/actionInternalUser", method = RequestMethod.POST)
	public ModelAndView actionInternalUserRequest(HttpServletRequest request,
			Model model) {

		String usernameInternal = request.getParameter("internal");
		String usernameExternal = request.getParameter("external");
		String isApproved = request.getParameter("flag");

		// do it using id
		Mappings mapping = this.mappingService.getExistingPendingMapping(usernameInternal,
				usernameExternal);

		ModelAndView out = new ModelAndView("alertview");

		if (isApproved.equals("1")) {
			mapping.setStatus(true);
			mappingService.saveMapping(mapping); // there should be primary key
		} else {
			mappingService.deleteMapping(mapping);
		}

		out.addObject("message", "Processed");
		return out;
	}

	@RequestMapping(value = "AdminHome/downloadLogFile", method = RequestMethod.POST)
	public ModelAndView downloadLogFile(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		List<String> downloadFile = new ArrayList<String>();
		// System.out.println("i m here username : "+authentication.getName());
		// D:\SSProject\software-security-group13\SecureBankingMain\target\tomcat\logs\myapp.log
		File file = new File("myapp.log");
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (input.hasNext()) {
			String nextLine = input.nextLine();
			if (nextLine.startsWith("INFO : com.asu.edu.controller."))
				// System.out.println(nextLine);
				downloadFile.add(nextLine);
		}

		input.close();
		ModelAndView model = new ModelAndView("downloadLogFile");
		model.addObject("syslog", downloadFile);
		return model;
	}

	@RequestMapping(value = "AdminHome/editAdminUserDetails", method = RequestMethod.POST)
	public ModelAndView editInternalUserDetails(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			User user = userService.getUserByUserName(authentication.getName());

			// get update parameters
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phonenumber");

			ModelAndView model = new ModelAndView("alertview");

			if (name == null || email == null || address == null
					|| phoneNumber == null) {
				model.addObject("message", "Invalid Request");
				return model;
			}

			// validate input --
			System.out.println(email);
			System.out.println(name);
			System.out.println(address);
			System.out.println(phoneNumber);

			// after validate database

			user.setEmail(email);
			user.setName(name);
			user.setAddress(address);
			user.setPhoneNumber(phoneNumber);

			userService.saveUser(user);
			// get custom message from userService

			// model.addObject("message", "Successfully edited !");
			logger.info("edit Admin User Details");
			return model;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ModelAndView model = new ModelAndView("alertview");
			model.addObject("message",
					"Error: Something went Wrong. Sorry, We are looking into it!");
			e.printStackTrace();
			return model;
		}
	}

	@RequestMapping(value = "AdminHome/piiInformation", method = RequestMethod.POST)
	public ModelAndView getPiiInformation(HttpServletRequest request) {

		// get custom message from userService
		ModelAndView model = new ModelAndView("alertview");
		model.addObject("message", "Successfully edited !");
		return model;
	}

}
