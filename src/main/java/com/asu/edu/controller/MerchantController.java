package com.asu.edu.controller;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asu.edu.appmodels.UserAppModel;
import com.asu.edu.daos.Account;
import com.asu.edu.daos.Transactions;
import com.asu.edu.daos.User;
import com.asu.edu.services.AccountService;
import com.asu.edu.services.RoleService;
import com.asu.edu.services.TransactionService;
import com.asu.edu.services.UserService;
import com.asu.edu.utils.Constants;
import com.asu.edu.utils.UserAppModelMapper;

@Controller
public class MerchantController {

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
	private TransactionService transactionService;
	@Autowired
	private RoleService rolesService;
	 

	/*
	 * public void setUserService(UserService userService) { this.userService =
	 * userService; }
	 */

	// public void setAccountService(AccountService accountService) {
	// this.accountService = accountService;
	// }
	
	public User convertToUser(UserAppModel user, String username) {
		User u = new User();

		u = userService.getUserByUserName(username);

		u.setAddress(user.getAddress());
		u.setEmail(user.getEmail());
		u.setName(user.getName());
		u.setPhoneNumber(user.getPhoneNumber());

		return u;
	}

	@RequestMapping(value = "/MerchantHome", method = RequestMethod.GET)
	public String merchantHome(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		// System.out.println("i m here username : "+authentication.getName());
		User user = userService.getUserByUserName(authentication.getName());

		System.out.println(user.getName());
		System.out.println(user.getPhoneNumber());
		session.setAttribute("username", authentication.getName());

		model.addAttribute("phonenumber", user.getPhoneNumber());

		return "MerchantHome";
	}

	@RequestMapping(value = "/MerchantHome/viewMerchantDetails", method = RequestMethod.GET)
	public ModelAndView viewMerchantDetails1(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = userService.getUserByUserName(authentication.getName());

		ModelAndView model = new ModelAndView("viewExternalUserDetails");
		//model.addAttribute("contentUrl", "viewExternalUserDetails");
		model.addObject("userDetails", user);
		return model;
	}

	@RequestMapping(value = "/MerchantHome/editMerchantDetails", method = RequestMethod.GET)
	public ModelAndView editMerchantDetails(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = userService.getUserByUserName(authentication.getName());

		UserAppModelMapper mapper = new UserAppModelMapper();
		UserAppModel uAppModel = new UserAppModel();
		uAppModel = mapper.convertToUserAppModel(user);
		
		ModelAndView model = new ModelAndView("editMerchantDetails");
		model.addObject("userDetails", uAppModel);
		
		return model;
	}
	
	@RequestMapping(value = "/MerchantHome/editMerchantDetails/save", method = RequestMethod.POST)
	public ModelAndView editInternalUserDetails1(HttpServletRequest request){
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 User user = userService.getUserByUserName(authentication.getName());
			 
			 //get update parameters
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phonenumber");

			ModelAndView model = new ModelAndView("alertview");
			
			
			if(name == null || email == null  || address == null || phoneNumber == null )
			{
					model.addObject("message", "Invalid Request");
					return model;
			}
			
			
			//validate input  -- 
			System.out.println(email);
			System.out.println(name);
			System.out.println(address);
			System.out.println(phoneNumber);
			
			//after validate database
			
			user.setEmail(email);
			user.setName(name);
			user.setAddress(address);
			user.setPhoneNumber(phoneNumber);
			
			userService.saveUser(user);
			//get custom message from userService
			
			model.addObject("message", "Successfully edited !");
			return model;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ModelAndView model = new ModelAndView("alertview");
			model.addObject("message", "Error: Something went Wrong. Sorry, We are looking into it!");
			e.printStackTrace();
			return model;
		}
	}

	/*
	 * @RequestMapping(value="/performTransactionInternalUser", method =
	 * RequestMethod.POST) public String
	 * performTrasactionInternalUser(HttpServletRequest request){ HttpSession
	 * session = request.getSession(); Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication();
	 * System.out.println("i m here in perform"); Account account =
	 * accountService
	 * .getAccountDetailsByAccountNo(request.getParameter("accountNo"));
	 * 
	 * double bal = account.getBalance(); double amount = new
	 * Double(request.getParameter("amount"));
	 * if(request.getParameter("type").equals("debit")) { if(bal < amount) {
	 * System.out.println("low bal");
	 * 
	 * } else { System.out.println("success"); account.setBalance(bal-amount);
	 * accountService.setBalance(account);
	 * 
	 * 
	 * }
	 * 
	 * }else{ //credit balance System.out.println(" balance credited");
	 * account.setBalance(bal+amount); accountService.setBalance(account);
	 * 
	 * }
	 * 
	 * return "performTransactionInternalUser"; }
	 */

	
	@RequestMapping(value = "/MerchantHome/viewMerchantDetails")
	public String viewMerchantDetails(HttpServletRequest request, Model model) {

		return "viewMerchantDetails";

	}
	
	
	@RequestMapping(value = "/MerchantHome/credit" )
	public ModelAndView credit(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			ModelAndView model = new ModelAndView("credit_merchant");
			//model.addAttribute("contentUrl", "credit_merchant");
			//model.addAttribute("amount", "");
			return model;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ModelAndView model = new ModelAndView("alertview");
			model.addObject("message", "Error: Something went Wrong. Sorry, We are looking into it!");
			e.printStackTrace();
			return model;
		}
	}

	@RequestMapping(value = "/MerchantHome/credit/send" , method = RequestMethod.POST)
	public ModelAndView creditMoney(@RequestParam("amount_merchant") String amt,
			HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			ModelAndView model ;
			double amount;
			double totalAmount;
			if(!(amt.equals("")))
			{
				amount = Double.parseDouble(amt);

				Account acc = accountService.getAccountDetails((String) session
						.getAttribute("username"));
				System.out.println("Balance = " + acc.getBalance());
				totalAmount = amount + acc.getBalance();
				acc.setBalance(totalAmount);

				Transactions tr = new Transactions();
				tr.setAccountByAccountSource(acc);
				tr.setAccountByAccountTarget(acc);

				tr.setIsCritical(false);
				tr.setTransactionAmount(amount);
				String transactionID = UUID.randomUUID().toString();

				tr.setTransactionId(transactionID);
				tr.setTransactionStatus(Constants.TRANSACTION_STATUS_COMPLETE);
				
				java.util.Date date = new java.util.Date();
				tr.setTransactionTime(new Timestamp(date.getTime()));
				tr.setTransactionType(Constants.TRANSACTION_TYPE_CREDIT);
				acc.getTransactionsesForAccountSource().add(tr);
				acc.getTransactionsesForAccountTarget().add(tr);
				
				accountService.setBalance(acc);
			
				if (transactionService.addTransaction(tr)) {
					model= new ModelAndView("credit_success");
				} else {
					model= new ModelAndView("credit_failure");
				}
				
			}
			else{
				model= new ModelAndView("empty_amount");
			}
			return model;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			ModelAndView model = new ModelAndView("alertview");
			model.addObject("message", "Error: Something went Wrong. Sorry, We are looking into it!");
			e.printStackTrace();
			return model;
		}
	}
	
	@RequestMapping(value = "/MerchantHome/debit")
	public ModelAndView debit(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			ModelAndView model = new ModelAndView("debit_merchant");
			model.addObject("amount", "");
			return model;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ModelAndView model = new ModelAndView("alertview");
			model.addObject("message", "Error: Something went Wrong. Sorry, We are looking into it!");
			e.printStackTrace();
			return model;
		}
	}

	@RequestMapping(value = "/MerchantHome/debit/send" , method = RequestMethod.POST)
	public ModelAndView debitMoney(@RequestParam("debit_amount") String amt,
			HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			double amount;
			double totalAmount;
			ModelAndView model;
			
			if(!(amt.equals("")))
			{
				amount = Double.parseDouble(amt);
				Account acc = accountService.getAccountDetails((String) session
						.getAttribute("username"));
				System.out.println("Balance = " + acc.getBalance());
				if (amount <= acc.getBalance()) {
					totalAmount = acc.getBalance() - amount;
					acc.setBalance(totalAmount);
					Transactions tr = new Transactions();
					tr.setAccountByAccountSource(acc);
					tr.setAccountByAccountTarget(acc);

					tr.setIsCritical(false);
					tr.setTransactionAmount(amount);
					String transactionID = UUID.randomUUID().toString();

					tr.setTransactionId(transactionID);
					tr.setTransactionStatus(Constants.TRANSACTION_STATUS_COMPLETE);
					java.util.Date date = new java.util.Date();
					System.out.println(new Timestamp(date.getTime()));
					tr.setTransactionTime(new Timestamp(date.getTime()));
					tr.setTransactionType(Constants.TRANSACTION_TYPE_DEBIT);
					acc.getTransactionsesForAccountSource().add(tr);
					acc.getTransactionsesForAccountTarget().add(tr);
					System.out.println("balance" + acc.getBalance());
					System.out.println("acc number :" + acc.getAccountNumber());
					accountService.setBalance(acc);
					System.out.println("i m here");
					// transactionService.addTransaction(tr);
					// model.addAttribute("contentUrl", "credit_success");

					if (transactionService.addTransaction(tr)) {
						model= new ModelAndView("debit_success");
					} else {
						model= new ModelAndView("debit_failure");
					}
				} else {
					model= new ModelAndView("insufficient_balance");
				}

				
			}
			else
			{
				model=new ModelAndView("empty_amount");
			}
			
			return model;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			ModelAndView model = new ModelAndView("alertview");
			model.addObject("message", "Error: Something went Wrong. Sorry, We are looking into it!");
			e.printStackTrace();
			return model;
		}
	}

	
	@RequestMapping(value = "/MerchantHome/transferMerchant")
	public String transfer(HttpServletRequest request, Model model) {
		return "transfer_merchant";
	}
	
	@RequestMapping(value = "/MerchantHome/transfer/send" , method = RequestMethod.POST)
	public ModelAndView transferMoney(
			@RequestParam("payee_account_number") String payee_account,
			@RequestParam("transfer_amount") String amt,
			HttpServletRequest request) {
		ModelAndView model =  null;
		try {
			HttpSession session = request.getSession();
			
			double amount;
			double totalAmount;
			
			if((!amt.equals(""))&&!(payee_account.equals("")))
			{
				amount = Double.parseDouble(amt);

				Account acc = accountService.getAccountDetails((String) session
						.getAttribute("username"));
				Account payee_acc = accountService
						.getAccountDetailsByAccountNo(payee_account);
				System.out.println("Balance = " + acc.getBalance());
				if (payee_acc != null) {
					if ((amount > 0) && (amount <= acc.getBalance())) {
				
						Transactions tr_payer = new Transactions();
						tr_payer.setAccountByAccountSource(acc);
						tr_payer.setAccountByAccountTarget(payee_acc);
						
						if (amount > 1000.0) {
							tr_payer.setIsCritical(true);
							tr_payer.setTransactionStatus(Constants.TRANSACTION_STATUS_PENDING);
						} else {
							tr_payer.setIsCritical(false);
							tr_payer.setTransactionStatus(Constants.TRANSACTION_STATUS_COMPLETE);
							totalAmount = acc.getBalance() - amount;
							acc.setBalance(totalAmount);
							payee_acc.setBalance(payee_acc.getBalance() + amount);
							accountService.setBalance(acc);
							accountService.setBalance(payee_acc);
						}

						tr_payer.setTransactionAmount(amount);
						String transactionID = UUID.randomUUID().toString();

						tr_payer.setTransactionId(transactionID);
						
						java.util.Date date = new java.util.Date();
						System.out.println(new Timestamp(date.getTime()));
						tr_payer.setTransactionTime(new Timestamp(date.getTime()));
						tr_payer.setTransactionType(Constants.getTransactionStatement(acc.getAccountNumber(), payee_acc.getAccountNumber()));
						
						System.out.println("balance" + acc.getBalance());
						System.out.println("acc number :" + acc.getAccountNumber());
									
						if (transactionService.addTransaction(tr_payer)) {
							model = new ModelAndView("transfer_success");
							//model.addAttribute("contentUrl", "transfer_success");
						} else {
							model = new ModelAndView("transfer_failure");
							//model.addAttribute("contentUrl", "transfer_failure");
						}
					} else if(amount < 0) {
						model = new ModelAndView("empty_amount");
						//model.addAttribute("contentUrl", "empty_amount");
					}
					else if((amount <= acc.getBalance())) {
						model = new ModelAndView("insufficient_balance");
						//model.addAttribute("contentUrl", "insufficient_balance");
					}
					else
					{
						model = new ModelAndView("transfer_failure");
						//model.addAttribute("contentUrl", "transfer_failure");
					}
				} else {
					model = new ModelAndView("incorrect_payee_account");
					//model.addAttribute("contentUrl", "incorrect_payee_account");
				}
			}
			else if(amt.equals(""))
			{
				model = new ModelAndView("empty_amount");
				//model.addAttribute("contentUrl", "empty_amount");
			}
			else if(payee_account.equals(""))
			{
				model = new ModelAndView("incorrect_payee_account");
				//model.addAttribute("contentUrl", "incorrect_payee_account");
			}

			return model;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch(Exception e){
			ModelAndView modela = new ModelAndView("alertview");
			modela.addObject("message", "Error: Something went Wrong. Sorry, We are looking into it!");
			e.printStackTrace();
			return modela;
			
		}
		return model;
	}
	
	@RequestMapping(value = "/MerchantHome/transactionView")
	public ModelAndView transactionView(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			ModelAndView model = new ModelAndView("transactional_view");
			Account acc = accountService.getAccountDetails((String) session
					.getAttribute("username"));
			
			List<Transactions> listTransaction = transactionService.getAllTransactionsRelatedToAccount(acc.getAccountNumber()); 
			//model.addAttribute("contentUrl", "transactional_view");
			//model.addAttribute("listTransations", listTransaction);
			Collections.reverse(listTransaction);
			model.addObject("listTransations",listTransaction);
			session.setAttribute("username", authentication.getName());
			return model;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ModelAndView model = new ModelAndView("alertview");
			model.addObject("message", "Error: Something went Wrong. Sorry, We are looking into it!");
			e.printStackTrace();
			return model;
			
			
		}
	}
	
	@RequestMapping(value = "/MerchantHome/requestPayment")
	public String requestPayment(HttpServletRequest request, Model model) {
		try {
			HttpSession session = request.getSession();
//	ModelAndView
//	model.addAttribute("contentUrl", "request_payment");
			//model.addAttribute("amount", "");
			return "request_payment";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failuremsg";
		}
	}
	

	@RequestMapping(value = "/MerchantHome/requestPayment/send" , method = RequestMethod.POST)
	public ModelAndView requestMoney(
			@RequestParam("payee_account_number") String payee_account,
			@RequestParam("transfer_amount") String amt,
			HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			double amount;
			double totalAmount;
			ModelAndView model = null;
			
			if((!amt.equals(""))&&!(payee_account.equals("")))
			{
				amount = Double.parseDouble(amt);

				Account acc = accountService.getAccountDetails((String) session
						.getAttribute("username"));
				Account payee_acc = accountService
						.getAccountDetailsByAccountNo(payee_account);
				System.out.println("Balance = " + acc.getBalance());
				if (payee_acc != null) {
					
					
				//List<>	roleService.getUserByRoles(payee_acc.getUser().getUsername());
					if ((amount > 0)) {
				
						Transactions tr_payer = new Transactions();
						tr_payer.setAccountByAccountSource(payee_acc);
						tr_payer.setAccountByAccountTarget(acc);
						
						if (amount > 1000.0) {
							tr_payer.setIsCritical(true);
							tr_payer.setTransactionStatus(Constants.PAYMENT_REQUEST_USER_PENDING);
						} else {
							tr_payer.setIsCritical(false);
							tr_payer.setTransactionStatus(Constants.PAYMENT_REQUEST_USER_PENDING);
						}

						tr_payer.setTransactionAmount(amount);
						String transactionID = UUID.randomUUID().toString();

						tr_payer.setTransactionId(transactionID);
						
						java.util.Date date = new java.util.Date();
						System.out.println(new Timestamp(date.getTime()));
						tr_payer.setTransactionTime(new Timestamp(date.getTime()));
						tr_payer.setTransactionType(Constants.getRequestStatement(payee_acc.getAccountNumber(), acc.getAccountNumber()));
						
						System.out.println("balance" + acc.getBalance());
						System.out.println("acc number :" + acc.getAccountNumber());
									
						if (transactionService.addTransaction(tr_payer)) {
							model = new ModelAndView("request_success");
						} else {
							model = new ModelAndView("request_failure");
						}
					} else if(amount < 0) {
						model = new ModelAndView("empty_amount");
					}
					else
					{
						model = new ModelAndView("transfer_failure");
					}
				} else {
					model = new ModelAndView("incorrect_payee_account");
				}
			}
			else if(amt.equals(""))
			{
				model = new ModelAndView("empty_amount");
			}
			else if(payee_account.equals(""))
			{
				model = new ModelAndView("incorrect_payee_account");
			}
			return model;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		ModelAndView model = new ModelAndView("alertview");
		model.addObject("message", "Error: Something went Wrong. Sorry, We are looking into it!");
		
		return model;
	}
	
	
}
