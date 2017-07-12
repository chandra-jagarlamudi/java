package com.ragas.java.sample.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * In this example you will use the SSL connection to connect to Gmail SMTP
 * server. The difference with is in the properties / configuration that we use
 * to create the mail session object.
 * 
 * There is a chance that you can get an error that tell you cannot connect to
 * the smtp.gmail.com port 465. If this is the case please check your connection
 * to the server using telnet command. The command is telnet smtp.gmail.com 465,
 * and see if there is a reply.
 * 
 * @author cjagarlamudi
 *
 */
public class GmailSendEmailSSL {
	private static final String USERNAME = "kodejava";
	private static final String PASSWORD = "********";

	public static void main(String[] args) throws Exception {
		// Email information such as from, to, subject and contents.
		String mailFrom = "kodejava@gmail.com";
		String mailTo = "kodejava@gmail.com";
		String mailSubject = "SSL - Gmail Send Email Demo";
		String mailText = "SSL - Gmail Send Email Demo";

		GmailSendEmailSSL gmail = new GmailSendEmailSSL();
		gmail.sendMail(mailFrom, mailTo, mailSubject, mailText);
	}

	private void sendMail(String mailFrom, String mailTo, String mailSubject, String mailText) throws Exception {

		Properties config = createConfiguration();

		// Creates a mail session. We need to supply username and
		// password for Gmail authentication.
		Session session = Session.getInstance(config, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(GmailSendEmailSSL.USERNAME, GmailSendEmailSSL.PASSWORD);
			}
		});

		// Creates email message
		Message message = new MimeMessage(session);
		message.setSentDate(new Date());
		message.setFrom(new InternetAddress(mailFrom));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
		message.setSubject(mailSubject);
		message.setText(mailText);

		// Send a message
		Transport.send(message, GmailSendEmailSSL.USERNAME, GmailSendEmailSSL.PASSWORD);
	}

	private Properties createConfiguration() {
		return new Properties() {
			{
				put("mail.smtp.host", "smtp.gmail.com");
				put("mail.smtp.auth", "true");
				put("mail.smtp.port", "465");
				put("mail.smtp.socketFactory.port", "465");
				put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			}
		};
	}
}
