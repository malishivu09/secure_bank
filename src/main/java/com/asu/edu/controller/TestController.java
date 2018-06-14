package com.asu.edu.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asu.edu.appmodels.RequestPayment;
import com.asu.edu.appmodels.UserAppModel;
import com.asu.edu.daos.Account;
import com.asu.edu.daos.Otp;
import com.asu.edu.daos.OtpId;
import com.asu.edu.daos.Transactions;
import com.asu.edu.daos.TransactionsHome;
import com.asu.edu.daos.User;
import com.asu.edu.models.OTPgenerator;
import com.asu.edu.pki.Encrypt;
import com.asu.edu.pki.generatingCSR;
import com.asu.edu.services.AccountService;
import com.asu.edu.services.OtpService;
import com.asu.edu.services.TransactionService;
import com.asu.edu.services.UserService;
import com.asu.edu.utils.Constants;
import com.asu.edu.utils.UserAppModelMapper;

@Controller
public class TestController {

	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private OtpService otpService;

	private static final Logger logger = Logger
			.getLogger(TestController.class);
	@RequestMapping(value = "/register")
	public String Register(HttpServletRequest request, Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public ModelAndView addPerson(HttpServletRequest request) {

		 try {
			String remoteAddr = request.getRemoteAddr();
//			   ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
//			    reCaptcha.setPrivateKey("6LdlDf0SAAAAAIgzUE78UcILjezztVP0jPGXCQQ0");
			    
			    
			    User user = new User();

//			    String challenge = request.getParameter("recaptcha_challenge_field");
//			    String uresponse = request.getParameter("recaptcha_response_field");
//			    ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
			    ModelAndView model = new ModelAndView("alertview");
			  

			    
			    String name = request.getParameter("name");
			    String email = request.getParameter("email");
			    String address = request.getParameter("address");
			    String phoneNumber = request.getParameter("phonenumber");
			    
			    String username = request.getParameter("username");
			    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                
			    String password = request.getParameter("password");
			    String hashedPassword = passwordEncoder.encode(password);
			    String pii = request.getParameter("pii");
			    
			    
			    //validations
			    if(name == null || email == null  || address == null || phoneNumber == null || username == null || password == null || pii == null)
			    {
			    		model.addObject("message", "Invalid Request");
			    		return model;
			    }
			    
			    user.setName(name);
			    user.setEmail(email);
			    user.setAddress(address);
			    user.setPhoneNumber(phoneNumber);
			    user.setPublicKey("");
			    user.setPassword(hashedPassword);
			    user.setPii(Integer.parseInt(pii));
			    user.setUsername(username);
			    userService.saveUser(user);
			    
			    generatingCSR csr = new generatingCSR();
				csr.generateCSR(user.getUsername(), user.getName(), user.getEmail(), user.getAddress());
			    
			   /* if (reCaptchaResponse.isValid()) {
			      System.out.println("Answer was entered correctly!");
			      user.setEnabled(false); //by default for user coming from registration page
				user.setPublicKey(""); //empty public key by default, we have to add
				//user.setPii(-1);
				
				// UserHome userHome = new UserHome();
				userService.saveUser(user);
				generatingCSR csr = new generatingCSR();
				csr.generateCSR(user.getUsername(), user.getName(), user.getEmail(), user.getAddress());
				
				// put message based on saveUser
				model.addObject("message", "Registration Successful");

			    } else {
			      System.out.println("Answer is wrong");
			      model.addObject("message", "Error: Invalid Capcha value Entered");
			    }*/
				return model;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return null;
	}

//	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
//	public ModelAndView addPerson(@ModelAttribute("user") User user,
//			HttpServletRequest request) {
//
//		String remoteAddr = request.getRemoteAddr();
//		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
//		reCaptcha.setPrivateKey("6LdlDf0SAAAAAIgzUE78UcILjezztVP0jPGXCQQ0");
//
//		String challenge = request.getParameter("recaptcha_challenge_field");
//		String uresponse = request.getParameter("recaptcha_response_field");
//		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr,
//				challenge, uresponse);
//
//		if (reCaptchaResponse.isValid()) {
//			System.out.println("Answer was entered correctly!");
//		} else {
//			System.out.println("Answer is wrong");
//		}
//
//		user.setEnabled(false); // by default for user coming from registration
//								// page
//		user.setPublicKey(""); // empty public key by default, we have to add
//		// user.setPii(-1);
//
//		// UserHome userHome = new UserHome();
//		userService.saveUser(user);
//		ModelAndView model = new ModelAndView("alertview");
//		// put message based on saveUser
//		model.addObject("message", "Registration Successful");
//		return model;
//
//	}

	// @RequestMapping(value = "/UserHome/balance", method = RequestMethod.GET)
	// public String showBalance(Model model) {
	// model.addAttribute("user", this.userService.getUser());
	// model.addAttribute("listPersons",
	// this.accountService.getBalance(AccountNumber));
	// return "person";
	// }

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/index.html")
	public String showLogin(HttpServletRequest request, Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/")
	public String loginHome(HttpServletRequest request, Model model) {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		return "logout";
	}

	@RequestMapping(value = "/AdminHome")
	public String adminHome() {

		return "AdminHome";
	}

	@RequestMapping(value = "/UserHome")
	public String userHome(HttpServletRequest request, Model model) {
		try {
			HttpSession session = request.getSession();
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			System.out.println("i m here username : " + authentication.getName());
			Account acc = accountService
					.getAccountDetails(authentication.getName());
			
			if(acc == null){
				return "UserHome";
			}
			
			System.out.println("Balance = " + acc.getBalance());
			// Account acc1 = acc.findById(authentication.getName());
			model.addAttribute("balance", acc.getBalance());
			session.setAttribute("username", authentication.getName());

			return "UserHome";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failuremsg";
		}

	}


	@RequestMapping(value = "/UserHome/editExternalUserDetails")
	public ModelAndView editExternalUserDetails(HttpServletRequest request) {
		// HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = userService.getUserByUserName(authentication.getName());

		UserAppModelMapper mapper = new UserAppModelMapper();
		UserAppModel uAppModel = new UserAppModel();
		uAppModel = mapper.convertToUserAppModel(user);

		ModelAndView model = new ModelAndView("editExternalUserDetails");
		model.addObject("userDetails", uAppModel);
		return model;

	}

	public User convertToUser(UserAppModel user, String username) {
		User u = new User();

		u = userService.getUserByUserName(username);

		u.setAddress(user.getAddress());
		u.setEmail(user.getEmail());
		u.setName(user.getName());
		u.setPhoneNumber(user.getPhoneNumber());

		return u;
	}

	@RequestMapping(value = "/UserHome/viewExternalUserDetails")
	public ModelAndView viewExternalUserDetails(HttpServletRequest request) {
		// HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = userService.getUserByUserName(authentication.getName());
		// String username = session.getAttribute("")

		ModelAndView model = new ModelAndView("viewExternalUserDetails");
		model.addObject("userDetails", user);
		logger.info("view user details");
		return model;

	}

	@RequestMapping(value = "/UserHome/editExternalUserDetails/save", method = RequestMethod.POST)
	public ModelAndView saveEditedExternalUserDetails(
			@ModelAttribute("userDetails") UserAppModel user,
			HttpServletRequest request) {
		// HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		// User userName =
		// userService.getUserByUserName(authentication.getName());
		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phonenumber");

		String username = (String) session.getAttribute("username");
		
		//check in nulls , return invalids details model object
		
		
		
		User updateUser = userService.getUserByUserName(username);

		updateUser.setEmail(email);
		updateUser.setName(name);
		updateUser.setAddress(address);
		updateUser.setPhoneNumber(phoneNumber);

		userService.saveUser(updateUser);

		// model.addAttribute("contentUrl", "save_successful");
		ModelAndView model = new ModelAndView("alertview");
		model.addObject("message", "successfully saved");
		return model;
	}

	@RequestMapping(value = "/denied")
	public String denied() {
		return "denied";
	}
	@RequestMapping(value = {"/forgot"},method = RequestMethod.GET, params="user")
	public String forgot(HttpServletRequest request, @RequestParam("user") String user) {
		System.out.println(user);
		
		if(user==null)
			return "login";
		
		User u = this.userService.getUserByUserName(user);

		String OTP = OTPgenerator.generateOTP(user);
		int number = (int) (Math.random() * (999999 - 1000) + 1000);
		String test = String.valueOf(number); // Salt
		OTP = OTP + test;	
		String OTPdigit = null;
		if (!OTP.equals(null)) {
			OTPdigit = OTP.replaceAll("\\D", "");
			OTPdigit = OTPdigit.substring(0, 6);
			
			Otp otp = new Otp();
			OtpId otpId = new OtpId();
			otpId.setOtp(Integer.parseInt(OTPdigit));
			otpId.setUsername(user);
			otpId.setCreationTime(Calendar.getInstance().getTime());
			
			otp.setId(otpId);
			otpService.addOtp(otp);
			
			try {
				OTPgenerator.Send(Constants.EMAIL_USERNAME,
						Constants.EMAIL_PWD, u.getEmail(), "", "Otp",
						OTPdigit);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace(); //dont worry about email
			}
		} else {
			System.err.println("There was an error in retreiving the OTP");
		}
		
		
		return "forgot";
	}
	
	@RequestMapping(value = "/forgot/send" , method = RequestMethod.POST)
	public ModelAndView forget(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// System.out.println("i m here username : "+authentication.getName());
		// Account acc =
		// accountService.getAccountDetails(authentication.getName());
		// System.out.println("Balance = "+acc.getBalance());
		// Account acc1 = acc.findById(authentication.getName());
		String u=request.getParameter("u");
		String o=request.getParameter("o");
		String n=request.getParameter("n");
		System.out.print(u);
		System.out.print(o);
		System.out.print(n);

		ModelAndView model = new ModelAndView("alertview");
		if(u==null||o==null||n==null){
			model.addObject("message", "Invalid Input!");
			return model;
		}
		
		User user = this.userService.getUserByUserName(u);
		if(user == null){
			model.addObject("message", "Unknown user");
			return model;
		}else{
			List<Otp> otps = this.otpService.getOTPByUsename(u);
			if(otps.size() == 0){
				model.addObject("message", "Otp Error:Contact Admin");
				return model;
			}else{
				Integer oNumber = Integer.parseInt(o);
				Otp otp = otps.get(0);
				if(otp.getId().getOtp()==oNumber){
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String hashedPassword = passwordEncoder.encode(n);
					user.setPassword(hashedPassword); // new password
					this.userService.saveUser(user);
					model.addObject("message","Password updated succesfully");
					return model;
				}
			}
		}
		
		return model;
	}

	
	
	
	@RequestMapping(value = "/UserHome/credit")
	public ModelAndView credit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// System.out.println("i m here username : "+authentication.getName());
		// Account acc =
		// accountService.getAccountDetails(authentication.getName());
		// System.out.println("Balance = "+acc.getBalance());
		// Account acc1 = acc.findById(authentication.getName());

		ModelAndView model = new ModelAndView("credit");
		model.addObject("amount", "");

		return model;
	}

	@RequestMapping(value = "/UserHome/credit/send", method = RequestMethod.POST)
	public ModelAndView creditMoney(@RequestParam("amount") String amt,
			HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		ModelAndView output;

		double amount;
		double totalAmount;
		if (!(amt.equals(""))) {
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
			System.out.println(new Timestamp(date.getTime()));
			tr.setTransactionTime(new Timestamp(date.getTime()));
			tr.setTransactionType(Constants.TRANSACTION_TYPE_CREDIT);
			acc.getTransactionsesForAccountSource().add(tr);
			acc.getTransactionsesForAccountTarget().add(tr);
			System.out.println("balance" + acc.getBalance());
			System.out.println("acc number :" + acc.getAccountNumber());
			accountService.setBalance(acc);
			System.out.println("i m here");
			// transactionService.addTransaction(tr);
			// model.addAttribute("contentUrl", "credit_success");

			if (transactionService.addTransaction(tr)) {
				output = new ModelAndView("credit_success");
			} else {
				output = new ModelAndView("credit_failure");
			}
		} else {
			// model.addAttribute("contentUrl", "empty_amount");
			output = new ModelAndView("empty_amount");
		}

		// model.addAttribute("contentUrl", "credit");

		// session.setAttribute("username", authentication.getName());
		return output;
	}

	@RequestMapping(value = "/UserHome/debit")
	public ModelAndView debit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = userService.getUserByUserName((String) session
				.getAttribute("username"));
		String OTP = OTPgenerator.generateOTP(user.getUsername());
		int number = (int) (Math.random() * (999999 - 1000) + 1000);
		String test = String.valueOf(number); // Salt
		OTP = OTP + test;
		String OTPdigit = null;
		if (!OTP.equals(null)) {
			OTPdigit = OTP.replaceAll("\\D", "");
			OTPdigit = OTPdigit.substring(0, 6);
			
			Otp otp = new Otp();
			OtpId otpId = new OtpId();
			otpId.setOtp(Integer.parseInt(OTPdigit));
			otpId.setUsername(user.getUsername());
			otpId.setCreationTime(Calendar.getInstance().getTime());
			
			otp.setId(otpId);
			
			
			try {
				
				
				Encrypt e = new Encrypt();
				e.encrypt(user.getUsername(), OTPdigit);
				e.SendEncrypt(user.getUsername(), user.getEmail(), "", "Encrypted OTP [ATTACHED]", "");
				OTPgenerator.Send(Constants.EMAIL_USERNAME,
						Constants.EMAIL_PWD, user.getEmail(), "", "Otp",
						OTPdigit);
				otpService.addOtp(otp);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace(); //dont worry about email
			}
		} else {
			System.err.println("There was an error in retreiving the OTP");
		}

		ModelAndView output = new ModelAndView("debit");
		output.addObject("amount", "");

		return output;
	}

	@RequestMapping(value = "/UserHome/debit/send", method = RequestMethod.POST)
	public ModelAndView debitMoney(@RequestParam("debit_amount") String amt,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView model = null;
		double amount;
		double totalAmount;

		System.out.println(amt);
		if (!(amt.equals(""))) {
			amount = Double.parseDouble(amt);
			System.out.println("&&&" + amount);
			Account acc = accountService.getAccountDetails((String) session
					.getAttribute("username"));
			System.out.println("Balance = " + acc.getBalance());
			System.out.println(amt);
			if ((amount >0) && (amount <= acc.getBalance())) {
				

				Transactions tr = new Transactions();
				tr.setAccountByAccountSource(acc);
				tr.setAccountByAccountTarget(acc);
				
				if (amount > 1000.0) {
					tr.setIsCritical(true);
					tr.setTransactionStatus(Constants.TRANSACTION_STATUS_PENDING);
					
					
					
				} else {
					tr.setIsCritical(false);
					tr.setTransactionStatus(Constants.TRANSACTION_STATUS_COMPLETE);
					totalAmount = acc.getBalance() - amount;
					acc.setBalance(totalAmount);
					accountService.setBalance(acc);
					
				}

				
				tr.setTransactionAmount(amount);
				String transactionID = UUID.randomUUID().toString();

				tr.setTransactionId(transactionID);
				
				java.util.Date date = new java.util.Date();
				System.out.println(new Timestamp(date.getTime()));
				tr.setTransactionTime(new Timestamp(date.getTime()));
				tr.setTransactionType(Constants.TRANSACTION_TYPE_DEBIT);
				acc.getTransactionsesForAccountSource().add(tr);
				acc.getTransactionsesForAccountTarget().add(tr);
				/*
				 * System.out.println("balance" + acc.getBalance());
				 * System.out.println("acc number :" + acc.getAccountNumber());
				 */
				accountService.setBalance(acc);
				
				// transactionService.addTransaction(tr);
				// model.addAttribute("contentUrl", "credit_success");

				if (transactionService.addTransaction(tr)) {
					
					model = new ModelAndView("transfer_success");
					
					System.out.println("i m here");
					System.out.println("**");
					System.out.println(acc.getBalance());
				} else {
					model = new ModelAndView("debit_failure");
				}
			} else {
				model = new ModelAndView("insufficient_balance");
			}

		} else {
			model = new ModelAndView("empty_amount");
		}

		return model;
	}

	@RequestMapping(value = "/UserHome/transfer")
	public ModelAndView transfer(HttpServletRequest request) {
		HttpSession session = request.getSession();

		User user = userService.getUserByUserName((String) session
				.getAttribute("username"));
		
		String OTP = OTPgenerator.generateOTP(user.getUsername());
		int number = (int) (Math.random() * (999999 - 1000) + 1000);
		String test = String.valueOf(number); // Salt
		OTP = OTP + test;
		String OTPdigit = null;
		if (!OTP.equals(null)) {
			OTPdigit = OTP.replaceAll("\\D", "");
			OTPdigit = OTPdigit.substring(0, 6);
			
			Otp otp = new Otp();
			OtpId otpId = new OtpId();
			otpId.setOtp(Integer.parseInt(OTPdigit));
			otpId.setUsername(user.getUsername());
			otpId.setCreationTime(Calendar.getInstance().getTime());
			
			otp.setId(otpId);
			otpService.addOtp(otp);
			
			try {
				OTPgenerator.Send(Constants.EMAIL_USERNAME,
						Constants.EMAIL_PWD, user.getEmail(), "", "Otp",
						OTPdigit);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace(); //dont worry about email
			}
		} else {
			System.err.println("There was an error in retreiving the OTP");
		}

		ModelAndView model = new ModelAndView("transfer");
		model.addObject("amount", "");

		return model;
	}

	@RequestMapping(value = "/UserHome/transactionView")
	public ModelAndView transactionView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView model = null;

		Account acc = accountService.getAccountDetails((String) session
				.getAttribute("username"));

		List<Transactions> listTransaction = transactionService
				.getAllTransactionsRelatedToAccount(acc.getAccountNumber());
		model = new ModelAndView("transactional_view");
		model.addObject("listTransations", listTransaction);

		// session.setAttribute("username", authentication.getName());
		return model;
	}

	private boolean otpValidator(String otp, String username) {
		int otp_int = Integer.parseInt(otp);
		List<Otp> o = otpService.getOTPByUsename(username);
		if ((o.get(0).getId().getOtp()) == otp_int) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/UserHome/transfer/send", method = RequestMethod.POST)
	public ModelAndView transferMoney(
			@RequestParam("payee_account_number") String payee_account,
			@RequestParam("transfer_amount") String amt,
			@RequestParam("otp") String otp, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView model = null;
		double amount;
		double totalAmount;

		if ((!amt.equals("")) && !(payee_account.equals(""))
				&& !(otp.equals(""))) {
			if (otpValidator(otp, (String) session.getAttribute("username"))) {
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
							payee_acc.setBalance(payee_acc.getBalance()
									+ amount);
							accountService.setBalance(acc);
							accountService.setBalance(payee_acc);
						}

						tr_payer.setTransactionAmount(amount);
						String transactionID = UUID.randomUUID().toString();

						tr_payer.setTransactionId(transactionID);

						java.util.Date date = new java.util.Date();
						System.out.println(new Timestamp(date.getTime()));
						tr_payer.setTransactionTime(new Timestamp(date
								.getTime()));
						tr_payer.setTransactionType(Constants
								.getTransactionStatement(
										acc.getAccountNumber(),
										payee_acc.getAccountNumber()));

						System.out.println("balance" + acc.getBalance());
						System.out.println("acc number :"
								+ acc.getAccountNumber());

						if (transactionService.addTransaction(tr_payer)) {
							model = new ModelAndView("transfer_success");
						} else {
							model = new ModelAndView("transfer_failure");
						}
					} else if (amount < 0) {
						model = new ModelAndView("empty_amount");
					} else if ((amount <= acc.getBalance())) {
						model = new ModelAndView("insufficient_balance");
					} else {
						model = new ModelAndView("transfer_failure");
					}
				} else {
					model = new ModelAndView("incorrect_payee_account");
				}
			} else {
				model = new ModelAndView("incorrect_otp");

			}
		} else if (amt.equals("")) {
			model = new ModelAndView("empty_amount");
		} else if (payee_account.equals("")) {

			model = new ModelAndView("incorrect_payee_account");
		} else if (otp.equals("")) {

			model = new ModelAndView("empty_otp");
		}

		return model;
	}

	@RequestMapping(value = "/UserHome/requestPayment")
	public ModelAndView requestPayment(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView model = null;
		List<RequestPayment> listRequestPayment = new ArrayList<RequestPayment>();

		Account acc = accountService.getAccountDetails((String) session
				.getAttribute("username"));

		List<Transactions> listTransaction = transactionService
				.getAllPendingMerchantRequests(acc.getAccountNumber());
		if (listTransaction != null) {
			for (int i = 0; i < listTransaction.size(); i++) {
				RequestPayment r = new RequestPayment();
				r.setFromAccountNumber(listTransaction.get(i)
						.getAccountByAccountTarget().getAccountNumber());
				r.setAmount(listTransaction.get(i).getTransactionAmount() + "");
				r.setTransaction_id(listTransaction.get(i).getTransactionId());
				listRequestPayment.add(r);
			}
		}

		model = new ModelAndView("request_payment_user");
		model.addObject("listRequestPayment", listRequestPayment);

		// session.setAttribute("username", authentication.getName());
		return model;
	}

	@RequestMapping(value = "/UserHome/requestAction", method = RequestMethod.POST)
	public ModelAndView requestAction(HttpServletRequest request) {

		System.out.println("I m here in requestAction");
		HttpSession session = request.getSession();
		String payee_account = request.getParameter("id");
		String getDecision = request.getParameter("flag");
		String amt = request.getParameter("amount");
		String transactionid = request.getParameter("transaction");
		double totalAmount;
		ModelAndView model = null;

		if (getDecision.equals("0")) {
			//
			// yet to implement
			Transactions tr = transactionService
					.getTransactionById(transactionid);
			tr.setIsCritical(false);
			tr.setTransactionStatus(Constants.PAYMENT_REQUEST_USER_DECLINE);
			transactionService.addTransaction(tr);

		} else {
			if ((!amt.equals("")) && !(payee_account.equals(""))) {
				double amount = Double.parseDouble(amt);

				Account acc = accountService.getAccountDetails((String) session
						.getAttribute("username"));
				Account payee_acc = accountService
						.getAccountDetailsByAccountNo(payee_account);

				System.out.println("Balance = " + acc.getBalance());
				if (payee_acc != null) {
					if ((amount > 0) && (amount <= acc.getBalance())) {

						Transactions tr_payer = transactionService
								.getTransactionById(transactionid);

						if (amount > 1000.0) {
							tr_payer.setIsCritical(true);
							tr_payer.setTransactionStatus(Constants.PAYMENT_REQUEST_ADMIN_PENDING);
						} else {
							tr_payer.setIsCritical(false);
							tr_payer.setTransactionStatus(Constants.PAYMENT_REQUEST_USER_APPROVED_MONEY_TRANSFERRED);
							totalAmount = acc.getBalance() - amount;
							acc.setBalance(totalAmount);
							payee_acc.setBalance(payee_acc.getBalance()
									+ amount);
							accountService.setBalance(acc);
							accountService.setBalance(payee_acc);
						}

						System.out.println("balance" + acc.getBalance());
						System.out.println("acc number :"
								+ acc.getAccountNumber());

						if (transactionService.addTransaction(tr_payer)) {
							model = new ModelAndView("transfer_success");
						} else {
							model = new ModelAndView("transfer_failure");
						}
					} else if (amount < 0) {
						model = new ModelAndView("empty_amount");
					} else if ((amount <= acc.getBalance())) {
						model = new ModelAndView("insufficient_balance");
					} else {
						model = new ModelAndView("transfer_failure");
					}
				} else {
					model = new ModelAndView("incorrect_payee_account");
				}
			} else if (amt.equals("")) {
				model = new ModelAndView("empty_amount");
			} else if (payee_account.equals("")) {
				model = new ModelAndView("incorrect_payee_account");
			}

		}

		return model;
	}

	@RequestMapping(value = "/welcome")
	public ModelAndView handlerWelcomeRequest() {
		ModelAndView modelView = new ModelAndView("testwelcome");

		TransactionsHome transactionHome = new TransactionsHome();
		return modelView;
	}

	/*
	 * @RequestMapping(value = "/testsubmit") public ModelAndView
	 * handlerTestSubmitRequest(
	 * 
	 * @ModelAttribute Transactions transactions) { ModelAndView modelView = new
	 * ModelAndView("testsubmitresult"); modelView.addObject("testname",
	 * transactions.getAccountSource()); return modelView; }
	 */

}
