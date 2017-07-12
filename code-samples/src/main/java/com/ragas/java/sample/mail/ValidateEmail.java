package com.ragas.java.sample.mail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * This code snippet shows you how to validate an email address using the
 * javax.mail.internet.InternetAddress class. The validate() method throws a
 * javax.mail.internet.AddressException when the email address passed to the
 * constructor is not a valid email address.
 * 
 * @author cjagarlamudi
 *
 */
public class ValidateEmail {
	public static void main(String[] args) {
		ValidateEmail demo = new ValidateEmail();

		String email = "kodejava@gmail.com";
		boolean isValid = demo.validateEmail(email);
		demo.printStatus(email, isValid);

		email = "kodejava.gmail";
		isValid = demo.validateEmail(email);
		demo.printStatus(email, isValid);
	}

	private boolean validateEmail(String email) {
		boolean isValid = false;
		try {
			// Create InternetAddress object and validated the supplied
			// address which is this case is an email address.
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return isValid;
	}

	private void printStatus(String email, boolean valid) {
		System.out.println(email + " is " + (valid ? "a" : "not a") + " valid email address");
	}
}
