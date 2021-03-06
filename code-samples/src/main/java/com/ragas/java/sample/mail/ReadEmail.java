package com.ragas.java.sample.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class ReadEmail {
	public static final String USERNAME = "chandra.jagarlamudi";
	public static final String PASSWORD = "**********";

	public static void main(final String[] args) throws Exception {
		// 1. Setup properties for the mail session.
		Properties props = new Properties();
		props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.pop3.socketFactory.fallback", "false");
		props.put("mail.pop3.socketFactory.port", "995");
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.host", "pop.gmail.com");
		props.put("mail.pop3.user", ReadEmail.USERNAME);
		props.put("mail.store.protocol", "pop3");

		// 2. Creates a javax.mail.Authenticator object.
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ReadEmail.USERNAME, ReadEmail.PASSWORD);
			}
		};

		// 3. Creating mail session.
		Session session = Session.getDefaultInstance(props, auth);

		// 4. Get the POP3 store provider and connect to the store.
		Store store = session.getStore("pop3");
		store.connect("pop.gmail.com", ReadEmail.USERNAME, ReadEmail.PASSWORD);

		// 5. Get folder and open the INBOX folder in the store.
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);

		// 6. Retrieve the messages from the folder.
		Message[] messages = inbox.getMessages();
		for (Message message : messages) {
			message.writeTo(System.out);
		}

		// 7. Close folder and close store.
		inbox.close(false);
		store.close();
	}
}
