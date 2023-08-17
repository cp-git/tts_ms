/**
 * @author - Code Generator
 * @createdOn 25-07-2023
 * @Description Controller class for email
 * 
 */

package com.cpa.ttsms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.Email;
import com.cpa.ttsms.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {
	@Autowired
	private EmailService emailService;

	// Sending a simple Email
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody Email email) {
		String status = emailService.sendSimpleMail(email);

		return status;
	}
}