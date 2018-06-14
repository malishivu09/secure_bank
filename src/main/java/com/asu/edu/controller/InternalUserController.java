package com.asu.edu.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.asu.edu.appmodels.UserAppModel;
import com.asu.edu.daos.Account;
import com.asu.edu.daos.Mappings;
import com.asu.edu.daos.Transactions;
import com.asu.edu.daos.User;
import com.asu.edu.services.AccountService;
import com.asu.edu.services.MappingService;
import com.asu.edu.services.TransactionService;
import com.asu.edu.services.UserService;
import com.asu.edu.utils.Constants;

@Controller
public class InternalUserController {

	private static String name = "UserHome/";
	private static final Logger logger = Logger
			.getLogger(InternalUserController.class);
	// @RequestMapping(value = "/home", method = RequestMethod.GET)
	// public String home(Locale locale, Model model) {
	// //logger.info("Welcome home! The client locale is {}.", locale);
	// Date date = new Date();
	// DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
	// DateFormat.LONG, locale);
	//
	// String formattedDate = dateFormat.format(date);
	//
	// model.addAttribute("serverTime", formattedDate );
	//
	// return "home";
	// }
	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private MappingService mappingService;
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/InternalHome", method = RequestMethod.GET)
	public String internalHome(HttpServletRequest request, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("getWelcome is executed!");
		}
		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		System.out.println("i m here username : " + authentication.getName());
		User user = userService.getUserByUserName(authentication.getName());

		System.out.println(user.getName());
		System.out.println(user.getPhoneNumber());

		model.addAttribute("phonenumber", user.getPhoneNumber());

		logger.info("inside internal user home");

		return "InternalHome";
	}

	@RequestMapping(value = "InternalHome/viewInternalUserDetails", method = RequestMethod.GET)
	public String viewInternalUserDetails1(HttpServletRequest request,
			Model model) {
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			// System.out.println("i m here username : "+authentication.getName());
			User user = userService.getUserByUserName(authentication.getName());
			model.addAttribute("phonenumber", user.getPhoneNumber());
			model.addAttribute("username", user.getName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("address", user.getAddress());
			model.addAttribute("name", user.getName());
			logger.error("This is Error message", new Exception("Testing"));
			model.addAttribute("phonenumber", user.getPhoneNumber());

			return "viewInternalUserDetails";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failuremsg";

		}
	}

	@RequestMapping(value = "InternalHome/editInternalUserDetails", method = RequestMethod.GET)
	public String editInternalUserDetails(HttpServletRequest request,
			Model model) {
		try {
			HttpSession session = request.getSession();
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

			return "editInternalUserDetails";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failuremsg";
		}
	}

	@RequestMapping(value = "InternalHome/performTransactionInternalUser", method = RequestMethod.POST)
	public String performTrasactionInternalUser(HttpServletRequest request,
			Model model) {
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			System.out.println("i m here in perform");
			Account account = accountService
					.getAccountDetailsByAccountNo(request
							.getParameter("accountNo"));

			double bal = account.getBalance();
			double amount = new Double(request.getParameter("amount"));

			if (request.getParameter("type").equals("debit")) {
				if (bal < amount) {
					System.out.println("low bal");

				} else {
					System.out.println("success");
					account.setBalance(bal - amount);
					accountService.setBalance(account);

				}

			} else {
				// credit balance
				System.out.println(" balance credited");
				account.setBalance(bal + amount);
				accountService.setBalance(account);

			}

			Transactions tr = new Transactions();
			tr.setAccountByAccountSource(account);
			tr.setAccountByAccountTarget(account);
			// will take care of this later
			tr.setIsCritical(false);
			tr.setTransactionAmount(amount);
			String transactionID = UUID.randomUUID().toString();

			tr.setTransactionId(transactionID);
			tr.setTransactionStatus(Constants.TRANSACTION_STATUS_COMPLETE);
			java.util.Date date = new java.util.Date();
			System.out.println(new Timestamp(date.getTime()));
			tr.setTransactionTime(new Timestamp(date.getTime()));
			tr.setTransactionType(Constants.TRANSACTION_TYPE_CREDIT);
			account.getTransactionsesForAccountSource().add(tr);
			account.getTransactionsesForAccountTarget().add(tr);
			System.out.println("balance" + account.getBalance());
			System.out.println("acc number :" + account.getAccountNumber());
			accountService.setBalance(account);
			System.out.println("i m here");
			// transactionService.addTransaction(tr);
			// model.addAttribute("contentUrl", "credit_success");

			if (transactionService.addTransaction(tr)) {
				model.addAttribute("contentUrl", "credit_success");
			} else {
				model.addAttribute("contentUrl", "credit_failure");
			}

			return "performTransactionInternalUser";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failuremsg";

		}

	}

	@RequestMapping(value = "InternalHome/performTransactionInternalUser", method = RequestMethod.GET)
	public String performTrasactionInternalUser1(HttpServletRequest request,
			Model model) {
		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		return "performTransactionInternalUser";
	}

	@RequestMapping(value = "InternalHome/viewUserDetails", method = RequestMethod.GET)
	public String viewUserDetails11(HttpServletRequest request, Model model) {
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();

			User user = userService.getUserByUserName(authentication.getName());
			model.addAttribute("phonenumber", user.getPhoneNumber());
			model.addAttribute("username", user.getName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("address", user.getAddress());
			model.addAttribute("name", user.getName());

			model.addAttribute("phonenumber", user.getPhoneNumber());

			return "viewUserDetails";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failuremsg";

		}
	}

	@RequestMapping(value = "InternalHome/editInternalUserDetails", method = RequestMethod.POST)
	public ModelAndView editInternalUserDetails1(HttpServletRequest request) {
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

			// after validate database

			user.setEmail(email);
			user.setName(name);
			user.setAddress(address);
			user.setPhoneNumber(phoneNumber);

			userService.saveUser(user);
			// get custom message from userService

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

	@RequestMapping(value = "InternalHome/viewInternalUserDetails")
	public String viewInternalUserDetails(HttpServletRequest request,
			Model model) {
		return "viewInternalUserDetails";

	}

	@RequestMapping(value = "InternalHome/selectUser", method = RequestMethod.GET)
	public ModelAndView selectUser(HttpServletRequest request, Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = authentication.getName();

		List<UserAppModel> userList = mappingService
				.getAllowedUserFor(username);

		ModelAndView output = new ModelAndView("selectUser");
		output.addObject("users", userList);

		return output;

	}

	// @RequestMapping(value="/viewUserTransaction", method = RequestMethod.GET)
	// public ModelAndView viewUserTransactions(HttpServletRequest request,
	// Model model)
	// {
	// Authentication authentication =
	// SecurityContextHolder.getContext().getAuthentication();
	// String username = authentication.getName();
	//
	//
	// List<UserAppModel> userList = mappingService.getAllowedUserFor(username);
	//
	//
	// ModelAndView output = new ModelAndView("selectUser");
	// output.addObject("users", userList);
	//
	// return output;
	//
	// }
	@RequestMapping(value = "InternalHome/viewUserDetails", method = RequestMethod.POST)
	public ModelAndView viewUserDetails1(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = request.getParameter("username");

		User user = userService.getUserByUserName(username);

		ModelAndView model = new ModelAndView("viewUserDetails");

		model.addObject("name", user.getName());

		model.addObject("phonenumber", user.getPhoneNumber());
		model.addObject("username", user.getName());
		model.addObject("email", user.getEmail());
		model.addObject("address", user.getAddress());

		// System.out.println( model.addObject("name", user.getName()));
		return model;

	}

	@RequestMapping(value = "InternalHome/viewUserTransactions", method = RequestMethod.POST)
	public ModelAndView getPendingTransactions(HttpServletRequest request,
			Model model) {
		String username = request.getParameter("accountNo");
		Account account = accountService.getAccountDetails(username);
		String accountNumber = account.getAccountNumber();
		List<Transactions> transactions = transactionService
				.getAllTransactionsRelatedToAccount(accountNumber);
		ModelAndView out = new ModelAndView("viewUserTransactions");
		out.addObject("transactions", transactions);
		return out;
	}

	@RequestMapping(value = "InternalHome/requestFromInternalUser", method = RequestMethod.POST)
	public ModelAndView requestFromInternalUser(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("alertview");
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			System.out.println("i m here in request");
			// Account account =
			// accountService.getAccountDetailsByAccountNo(request.getParameter("accountNo"));
			String internalUsername = authentication.getName();
			String externalUsername = request.getParameter("username");
			String accessType = request.getParameter("requestType");

			Mappings mapping = mappingService.getExistingMapping(
					internalUsername, externalUsername);

			if (mapping != null) {;
				mapping.setAccessType(accessType); //update access type
				mapping.setStatus(false);
			} else {
				mapping = new Mappings();
				mapping.setAccessType(accessType);
				User intenalU = new User();
				intenalU.setUsername(internalUsername);
				User externU = new User();
				externU.setUsername(externalUsername);

				mapping.setStatus(false);
				mapping.setUserByExternalUsername(externU);
				mapping.setUserByInternalUsername(intenalU);
			}

			mappingService.saveMapping(mapping);

			model.addObject("message", "Added Succesfully");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("message", "Failed");
			return model;
		}
	}

	@RequestMapping(value = "InternalHome/requestFromInternalUser", method = RequestMethod.GET)
	public String requestFromInternalUser1(HttpServletRequest request,
			Model model) {
		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		return "requestFromInternalUser";
	}

}
