package com.asu.edu.models;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import sun.misc.BASE64Encoder;
import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class OTPgenerator{
	public static String generateOTP(String username){

		String mykey = username; //Select username from database here
		int number = (int) (Math.random() * (999999-1000) + 1000);
		String test = String.valueOf(number); //Salt 
		String encrypted = null;

		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec secret = new SecretKeySpec(mykey.getBytes(),"HmacSHA1");
			mac.init(secret);
			byte[] digest = mac.doFinal(test.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			encrypted = encoder.encode(digest); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return encrypted;
	}
   
    public static void Send(final String username, final String password, String recipientEmail, String title, String message) throws AddressException, MessagingException {
    	OTPgenerator.Send(username, password, recipientEmail, "", title, message);
    }

    
    public static void Send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress("sbsgroup13project" + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

        t.connect("smtp.gmail.com", username, password);
        t.sendMessage(msg, msg.getAllRecipients());      
        t.close();
    }


//	public static void main(String args[]){
//		String OTP = OTPgenerator.generateOTP();
//		int number = (int) (Math.random() * (999999-1000) + 1000);
//		String test = String.valueOf(number); //Salt 
//		OTP = OTP+test;
//		String OTPdigit = null;
//		if(!OTP.equals(null))
//		{
//			OTPdigit=OTP.replaceAll("\\D", "");
//			OTPdigit=OTPdigit.substring(0, 6);
//		}
//		else
//			System.err.println("There was an error in retreiving the OTP");
//		//Obtain the email id of the user from the database
//		
//		try {
//			OTPgenerator.Send("sbsgroup13project", "sbsgroup13", "nsunkesu@asu.edu", "", "Your OTP is here", "Please enter you One time password in the following link within the next 600 seconds: \n"+OTPdigit+"\n");
//		} catch (AddressException e) {
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//		
//	}
}