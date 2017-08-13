package com.ragas.java.sample.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendHTMLEmail {
	public static void main(final String[] args) {
		String from = "chandra.jagarlamudi@gmail.com";
		String to = "chandra.jagarlamudi@gmail.com";
		String subject = "Hello";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		Session session = Session.getDefaultInstance(props);

		try {
			InternetAddress fromAddress = new InternetAddress(from);
			InternetAddress toAddress = new InternetAddress(to);

			Message message = new MimeMessage(session);
			message.setFrom(fromAddress);
			message.setRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(subject);

			String sb = "<head>" + "<style type=\"text/css\">" + "  .red { color: #f00; }" + "</style>" + "</head>"
					+ "<h1 class=\"red\">" + message.getSubject() + "</h1>" + "<p>"
					+ "Lorem ipsum dolor sit amet, <em>consectetur</em> adipisicing elit, "
					+ "sed do eiusmod tempor incididunt ut labore et dolore magna <strong>" + "aliqua</strong>.</p>";
			message.setContent(sb, "text/html; charset=utf-8");
			message.saveChanges();

			// Send the message to the recipient. You also need to specify the
			// username
			// and password to authenticate to the mail server.
			Transport.send(message, "chandra.jagarlamudi", "********");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
