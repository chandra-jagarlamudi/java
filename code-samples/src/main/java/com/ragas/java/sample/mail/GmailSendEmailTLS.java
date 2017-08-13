package com.ragas.java.sample.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The following example show you how to send email using Gmail SMTP via TLS
 * connection. The username and password is use to authenticate you against the
 * Gmail.
 * 
 * The configuration / properties used for connection to the Gmail SMTP is
 * defined in the createConfiguration() method. The properties include
 * information such as the SMTP host address, port number, etc.
 * 
 * @author cjagarlamudi
 *
 */
public class GmailSendEmailTLS {
	private static final String USERNAME = "chandra.jagarlamudi";
	private static final String PASSWORD = "********";

	public static void main(final String[] args) throws Exception {
		// Email information such as from, to, subject and contents.
		String mailFrom = "chandra.jagarlamudi@gmail.com";
		String mailTo = "chandra.jagarlamudijava@gmail.com";
		String mailSubject = "TLS - Gmail Send Email Demo";
		String mailText = "TLS - Gmail Send Email Demo";

		GmailSendEmailTLS gmail = new GmailSendEmailTLS();
		gmail.sendMail(mailFrom, mailTo, mailSubject, mailText);
	}

	private void sendMail(final String mailFrom, final String mailTo, final String mailSubject, final String mailText) throws Exception {

		Properties config = createConfiguration();

		// Creates a mail session. We need to supply username and
		// password for Gmail authentication.
		Session session = Session.getInstance(config, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(GmailSendEmailTLS.USERNAME, GmailSendEmailTLS.PASSWORD);
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
		Transport.send(message, GmailSendEmailTLS.USERNAME, GmailSendEmailTLS.PASSWORD);
	}

	private Properties createConfiguration() {
		return new Properties() {
			{
				put("mail.smtp.auth", "true");
				put("mail.smtp.host", "smtp.gmail.com");
				put("mail.smtp.port", "587");
				put("mail.smtp.starttls.enable", "true");
			}
		};
	}
}
