package com.ragas.java.sample.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDebugDemo {
	public static void main(final String[] args) {
		MailDebugDemo.sendMail("chandra.jagarlamudi@gmail.com", "chandra.jagarlamudi@gmail.com",
				"Debug Demo", "Mail Debug Demo");
	}

	private static void sendMail(final String mailFrom, final String mailTo, final String mailSubject,
			final String mailText) {
		Properties props = new Properties() {{
			put("mail.smtp.auth", "true");
			put("mail.smtp.host", "smtp.gmail.com");
			put("mail.smtp.port", "587");
			put("mail.smtp.starttls.enable", "true");
			put("mail.debug", "true");
		}};

		// Creates a mail session. We need to supply username and
		// password for GMail authentication.
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kodejava", "********");
			}
		});

		try {
			// Creates email message
			Message message = new MimeMessage(session);
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(mailFrom));
			message.setRecipient(Message.RecipientType.TO,
					new InternetAddress(mailTo));
			message.setSubject(mailSubject);
			message.setText(mailText);

			// Send a message
			Transport.send(message, "chandra.jagarlamudi", "********");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
