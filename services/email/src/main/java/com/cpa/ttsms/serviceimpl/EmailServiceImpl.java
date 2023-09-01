/**
 * @author - Code Generator
 * @createdOn 25-07-2023
 * @Description Controller class for email
 * 
 */

package com.cpa.ttsms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.EmailController;
import com.cpa.ttsms.entity.Email;
import com.cpa.ttsms.entity.TaskNotificationEmail;
import com.cpa.ttsms.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	// Autowire the JavaMailSender to send emails
	@Autowired
	private JavaMailSender javaMailSender;

	// Get the sender's email address from configuration properties
	@Value("${spring.mail.username}")
	private String sender;

	public String sendSimpleMail(Email email) {

		try {

			// Create a new SimpleMailMessage instance to compose the email
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setFrom(sender); // Set the sender's email address
			mailMessage.setTo(email.getRecipient()); // Set the recipient's email address
			mailMessage.setText(email.getMsgBody()); // Set the email's message body
			mailMessage.setSubject(email.getSubject()); // Set the email's subject

			// Sending the mail using the JavaMailSender
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending Mail";
		}
	}

	public String SendTaskNotification(TaskNotificationEmail notificationEmail) {
		try {

			// Create a new SimpleMailMessage instance to compose the email
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setFrom(sender); // Set the sender's email address
			// mailMessage.setTo(email.getRecipient()); // Set the recipient's email address
			mailMessage.setTo(notificationEmail.getRecipient().toArray(new String[0])); // Convert the list to an array
			mailMessage.setText(notificationEmail.getMsgBody()); // Set the email's message body
			mailMessage.setSubject(notificationEmail.getSubject()); // Set the email's subject

			// Sending the mail using the JavaMailSender
			javaMailSender.send(mailMessage);
			return "Task Notification Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending Mail";
		}
	}

}