package com.revature.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {
	private String from;

	private String to;

	private static final String SMTP_USERNAME = "AKIAISVEONILBPVIT76Q";
	private static final String SMTP_PASSWORD = "ArV0Pzh63t3QzpnexDG+93174q1L8hC1rNCscxWeNF0Y";

	private static final String HOST = "email-smtp.us-east-1.amazonaws.com";

	private static final int PORT = 587;

	private String subject;

	private String body;

	public int sendEmail() throws Exception {
		if(from == null || to == null || subject == null || body == null) {
			return -1;
		}

		// Create a Properties object to contain connection configuration information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT); 
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "email-smtp.us-east-1.amazonaws.com");
		props.put("mail.smtp.auth", "true");

		// Create a Session object to represent a mail session with the specified properties. 
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information. 
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
		msg.setContent(body,"text/html");

		// Add a configuration set header. Comment or delete the 
		// next line if you are not using a configuration set
		// msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try
		{
			System.out.println("Sending...");

			// Connect to Amazon SES using the SMTP username and password you specified above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
			
			// Close and terminate the connection.
			transport.close();
			
			return 1;
		}
		catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
			
			// Close and terminate the connection.
			transport.close();
			
			return 0;
		}
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
