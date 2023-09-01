/**
 * @author  - Code Generator
 * @createdOn -  25-07-2023
 * @Description Entity class for Email Service
 * 
 */

package com.cpa.ttsms.service;

import com.cpa.ttsms.entity.Email;
import com.cpa.ttsms.entity.TaskNotificationEmail;

public interface EmailService {

	/**
	 * Method to send a simple email. This method takes an Email object containing
	 * recipient, message body, and subject, and sends a simple email using the
	 * provided details.
	 */
	String sendSimpleMail(Email email);

	String SendTaskNotification(TaskNotificationEmail notificationEmail);

}