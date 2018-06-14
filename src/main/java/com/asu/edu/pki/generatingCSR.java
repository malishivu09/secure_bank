package com.asu.edu.pki;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class generatingCSR {
	//gte these details from the database
	
	public static String state="AZ";
	public static String country="US";
	public static String commonName="cse545hy13-vm.vlab.asu.edu";

	public void generateCSR(String username, String organisation, String employer, String city){
		System.out.println("i m here 1");
		Runtime rt = Runtime.getRuntime();
		
		try {
			System.out.println("i m here 2");
			 //Build command
			//Process pr=rt.exec("openssl req -nodes -newkey rsa:2048 -nodes -keyout "+username+".key"+" -out "+username+".csr"+" -subj \"/C="+country+"/ST="+state+"/L= "+"/O="+organisation+"/OU="+employer+"/CN="+commonName);

			Process pr=rt.exec("C:\\OpenSSL\\bin\\openssl req -nodes -newkey rsa:2048 -nodes -keyout D:\\"+username+".key"+" -out D:\\"+username+".csr"+" -subj \"/C="+country+"/ST="+state+"/L= "+"/O="+organisation+"/OU="+employer+"/CN="+commonName);
	        System.out.println("i m here 3 :"+"C:\\OpenSSL\\bin\\openssl req -nodes -newkey rsa:2048 -nodes -keyout D:\\Projectgenerated\\"+username+".key"+" -out D:\\Projectgenerated\\"+username+".csr"+" -subj \"/C="+country+"/ST="+state+"/L= "+"/O="+organisation+"/OU="+employer+"/CN="+commonName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exception : "+e.getLocalizedMessage());
		}
	}

}
