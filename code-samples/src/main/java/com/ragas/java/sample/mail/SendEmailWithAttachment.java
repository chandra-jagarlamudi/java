package com.ragas.java.sample.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * In this example we create a small program to send email with a file
 * attachment. To send message with attachment we need to create an email with
 * javax.mail.Multipart object which basically will contains the email text
 * message and then add a file to the second block, which both of them is an
 * object of javax.mail.internet.MimeBodyPart. In this example we also use the
 * javax.activation.FileDataSource.
 * 
 * @author cjagarlamudi
 *
 */
public class SendEmailWithAttachment {
	public static void main(final String[] args) {
		SendEmailWithAttachment demo = new SendEmailWithAttachment();
		demo.sendEmail();
	}

	public void sendEmail() {
		// Defines the E-Mail information.
		String from = "chandra.jagarlamudi@gmail.com";
		String to = "chandra.jagarlamudi@gmail.com";
		String subject = "Important Message";
		String bodyText = "This is a important message with attachment.";

		// The attachment file name.
		String attachmentName = "D:/Temp/hello.txt";

		// Creates a Session with the following properties.
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		Session session = Session.getDefaultInstance(props);

		try {
			InternetAddress fromAddress = new InternetAddress(from);
			InternetAddress toAddress = new InternetAddress(to);

			// Create an Internet mail msg.
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(fromAddress);
			msg.setRecipient(Message.RecipientType.TO, toAddress);
			msg.setSubject(subject);
			msg.setSentDate(new Date());

			// Set the email msg text.
			MimeBodyPart messagePart = new MimeBodyPart();
			messagePart.setText(bodyText);

			// Set the email attachment file
			FileDataSource fileDataSource = new FileDataSource(attachmentName);

			MimeBodyPart attachmentPart = new MimeBodyPart();
			attachmentPart.setDataHandler(new DataHandler(fileDataSource));
			attachmentPart.setFileName(fileDataSource.getName());

			// Create Multipart E-Mail.
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messagePart);
			multipart.addBodyPart(attachmentPart);

			msg.setContent(multipart);

			// Send the msg. Don't forget to set the username and password
			// to authenticate to the mail server.
			Transport.send(msg, "chandra.jagarlamudi", "********");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}