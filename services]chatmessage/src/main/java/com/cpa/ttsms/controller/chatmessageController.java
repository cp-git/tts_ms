/**
 * @author - Code Generator
 * @createdOn 22-05-2024
 * @Description Controller class for ]chatmessage
 * 
 */

package com.cpa.ttsms.controller;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.ChatMessage;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.chatmessageService;

@RestController
@RequestMapping("/ttsms")
public class chatmessageController {

	@Autowired
	private chatmessageService ]chatmessageService;;

	private ResourceBundle resourceBunde;
	private static Logger logger;

	chatmessageController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(chatmessageController.class);
	}

	@PostMapping("/chatmessage")
	public ResponseEntity<Object> createchatmessage(@RequestBody chatmessage ]chatmessage) throws CPException {
		logger.debug("Entering createchatmessage");
		logger.info("data of creating chatmessage  :" + ]chatmessage.toString());

		chatmessage createdchatmessage = null;
		try {

			chatmessage toCheckchatmessage = ]chatmessageService.getchatmessageByid(]chatmessage.getChatMessageid());
			logger.debug("existing ]chatmessage :" + toCheckchatmessage);

			if (toCheckchatmessage == null) {

			// TODO: Uncomment below 2 lines and change the method name as per your Entity class
			//	]chatmessage.setCreatedby("admin");
			//	]chatmessage.setUpdatedby("admin");

				createdchatmessage = ]chatmessageService.createchatmessage(]chatmessage);
				logger.info("chatmessage created :" + createdchatmessage);

				return ResponseHandler.generateResponse(createdchatmessage, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed chatmessage creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/chatmessage/{id}")
	public ResponseEntity<Object> getchatmessageByid(@PathVariable("id") String id)
			throws CPException {
		logger.debug("Entering getchatmessageByid");
		logger.info("entered user name :" + id);
		
		chatmessage ]chatmessage = null;

		try {

			]chatmessage = ]chatmessageService.getchatmessageByid(id);
			logger.info("fetched chatmessage :" + ]chatmessage);

			if (]chatmessage != null) {
				logger.debug("chatmessage fetched generating response");
				return ResponseHandler.generateResponse(]chatmessage, HttpStatus.OK);
			} else {
				logger.debug("chatmessage not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {

			logger.error("Failed getting ]chatmessage : " + ex.getMessage());
			throw new CPException("err001", resourceBunde.getString("err001"));
		}

	}

	@GetMapping("/chatmessage")
	public ResponseEntity<List<Object>> getAllchatmessages(@RequestParam(name = "id") String id)
			throws CPException {
		logger.debug("Entering getAllchatmessage");
		logger.info("Parameter  :" + id);
		
		List<Object> ]chatmessages = null;

		try {

			if (id.equalsIgnoreCase("all")) {

				]chatmessages = ]chatmessageService.getAllchatmessages();
				logger.info("Fetched all chatmessage :" + ]chatmessages);

				return ResponseHandler.generateListResponse(]chatmessages, HttpStatus.OK);
			} else {

				logger.info(resourceBunde.getString("err002"));
				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}

		} catch (Exception ex) {

			logger.error("Failed getting all ]chatmessages : " + ex.getMessage());
			throw new CPException("err002", resourceBunde.getString("err002"));

		}
	}

	@DeleteMapping("/chatmessage/{id}")
	public ResponseEntity<Object> deletechatmessageByid(@PathVariable("id") String id) throws CPException {
		logger.debug("Entering deleteAuthUser");
		logger.info("entered deletechatmessage  :" + id);
		//TODO - implement the business logic
		
		int count = 0;

		try {
			count = ]chatmessageService.deletechatmessageByid(id);
			if (count >= 1) {
				logger.info("deleted chatmessage : id = " + id);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				logger.info(resourceBunde.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			logger.error("Failed to delete chatmessage :" + ex.getMessage());
			throw new CPException("err005", resourceBunde.getString("err005"));
		}
		

	}

	@PutMapping("/chatmessage/{id}")
	public ResponseEntity<Object> updatechatmessageByid(@RequestBody chatmessage ]chatmessage,
			@PathVariable("id") String id) throws CPException {
		logger.debug("Entering updatechatmessage");
		logger.info("entered  updatechatmessage :" + ]chatmessage);

		chatmessage updatedchatmessage = null;

		try { 
			updatedchatmessage = ]chatmessageService.updatechatmessageByid(]chatmessage, id);

			if (updatedchatmessage == null) {
				logger.info(resourceBunde.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				logger.info("updated ]chatmessage : " + updatedchatmessage);
				return ResponseHandler.generateResponse(updatedchatmessage, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			logger.error("Failed update chatmessage : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}

}
