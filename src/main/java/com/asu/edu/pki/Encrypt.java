package com.asu.edu.pki;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.asu.edu.utils.Constants;
import com.sun.mail.smtp.SMTPTransport;

public class Encrypt {

	public void encrypt(String uname, String otp) {
		try {
			File f = new File("Otp.txt");
			FileOutputStream file = new FileOutputStream(f);
			DataOutputStream dos = new java.io.DataOutputStream(file);
			// String content =
			// "C:\\OpenSSL\\bin\\openssl rsautl -decrypt -inkey "+uname+".key -in "+uname+"encrypted.txt.enc -out "+uname+"decrypted.txt";
			dos.writeBytes(otp);
			Runtime rt = Runtime.getRuntime();
			//rt.exec("openssl rsautl -encrypt -inkey "+uname+".pem -pubin -in Otp.txt -out "+uname+"encrypted.txt.enc");
			rt.exec("C:\\OpenSSL\\bin\\openssl rsautl -encrypt -inkey "+uname+".pem -pubin -in Otp.txt -out "+uname+"encrypted.txt.enc");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	
	public static void SendEncrypt(String uname, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException, IOException {
		try{
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
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Hi");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			String filename=uname+".crt";
			String pathname=Constants.CERTIFICATES_PATH+uname+".encrypted.txt.enc";
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println(filename);
			DataSource source = new FileDataSource(pathname);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("sbsgroup13project" + "@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

			msg.setSubject(title);
			msg.setText(message, "utf-8");
			msg.setSentDate(new Date());
			msg.setContent(multipart);

			SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

			t.connect("smtp.gmail.com", Constants.EMAIL_USERNAME, Constants.EMAIL_PWD);
			t.sendMessage(msg, msg.getAllRecipients());      
			t.close();

			System.out.println("Certificate mailed successfully");
		}
		catch (MessagingException m){
			throw new RuntimeException(m);
		}
	}


}
