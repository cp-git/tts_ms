/**
 * @author - Code Generator
 * @createdOn 19/12/2023
 * @Description Controller class for leadgeneration
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

import com.cpa.ttsms.entity.leadgeneration;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.LeadGenerationService;

@RestController
@RequestMapping("/ttsms")
public class LeadGenerationController {

	@Autowired
	private LeadGenerationService leadgenerationService;;

	private ResourceBundle resourceBunde;
	private static Logger logger;

	LeadGenerationController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(LeadGenerationController.class);
	}

	@PostMapping("/leadgeneration")
	public ResponseEntity<Object> createLeadGeneration(@RequestBody LeadGeneration leadgeneration) throws CPException {
		logger.debug("Entering createLeadGeneration");
		logger.info("data of creating LeadGeneration  :" + leadgeneration.toString());

		LeadGeneration createdLeadGeneration = null;
		try {

			LeadGeneration toCheckLeadGeneration = leadgenerationService.getLeadGenerationBylocationId(leadgeneration.getleadgenerationlocationId());
			logger.debug("existing leadgeneration :" + toCheckLeadGeneration);

			if (toCheckLeadGeneration == null) {

			// TODO: Uncomment below 2 lines and change the method name as per your Entity class
			//	leadgeneration.setCreatedby("admin");
			//	leadgeneration.setUpdatedby("admin");

				createdLeadGeneration = leadgenerationService.createLeadGeneration(leadgeneration);
				logger.info("LeadGeneration created :" + createdLeadGeneration);

				return ResponseHandler.generateResponse(createdLeadGeneration, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed LeadGeneration creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/leadgeneration/{locationId}")
	public ResponseEntity<Object> getLeadGenerationBylocationId(@PathVariable("locationId") String locationId)
			throws CPException {
		logger.debug("Entering getLeadGenerationBylocationId");
		logger.info("entered user name :" + locationId);
		
		LeadGeneration leadgeneration = null;

		try {

			leadgeneration = leadgenerationService.getLeadGenerationBylocationId(locationId);
			logger.info("fetched LeadGeneration :" + leadgeneration);

			if (leadgeneration != null) {
				logger.debug("LeadGeneration fetched generating response");
				return ResponseHandler.generateResponse(leadgeneration, HttpStatus.OK);
			} else {
				logger.debug("LeadGeneration not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {

			logger.error("Failed getting leadgeneration : " + ex.getMessage());
			throw new CPException("err001", resourceBunde.getString("err001"));
		}

	}

	@GetMapping("/leadgeneration")
	public ResponseEntity<List<Object>> getAllLeadGenerations(@RequestParam(name = "locationId") String locationId)
			throws CPException {
		logger.debug("Entering getAllLeadGeneration");
		logger.info("Parameter  :" + locationId);
		
		List<Object> leadgenerations = null;

		try {

			if (locationId.equalsIgnoreCase("all")) {

				leadgenerations = leadgenerationService.getAllLeadGenerations();
				logger.info("Fetched all LeadGeneration :" + leadgenerations);

				return ResponseHandler.generateListResponse(leadgenerations, HttpStatus.OK);
			} else {

				logger.info(resourceBunde.getString("err002"));
				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}

		} catch (Exception ex) {

			logger.error("Failed getting all leadgenerations : " + ex.getMessage());
			throw new CPException("err002", resourceBunde.getString("err002"));

		}
	}

	@DeleteMapping("/leadgeneration/{locationId}")
	public ResponseEntity<Object> deleteLeadGenerationBylocationId(@PathVariable("locationId") String locationId) throws CPException {
		logger.debug("Entering deleteAuthUser");
		logger.info("entered deleteLeadGeneration  :" + locationId);
		//TODO - implement the business logic
		
		int count = 0;

		try {
			count = leadgenerationService.deleteLeadGenerationBylocationId(locationId);
			if (count >= 1) {
				logger.info("deleted LeadGeneration : locationId = " + locationId);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				logger.info(resourceBunde.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			logger.error("Failed to delete LeadGeneration :" + ex.getMessage());
			throw new CPException("err005", resourceBunde.getString("err005"));
		}
		

	}

	@PutMapping("/leadgeneration/{locationId}")
	public ResponseEntity<Object> updateLeadGenerationBylocationId(@RequestBody LeadGeneration leadgeneration,
			@PathVariable("locationId") String locationId) throws CPException {
		logger.debug("Entering updateLeadGeneration");
		logger.info("entered  updateLeadGeneration :" + leadgeneration);

		LeadGeneration updatedLeadGeneration = null;

		try { 
			updatedLeadGeneration = leadgenerationService.updateLeadGenerationBylocationId(leadgeneration, locationId);

			if (updatedLeadGeneration == null) {
				logger.info(resourceBunde.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				logger.info("updated leadgeneration : " + updatedLeadGeneration);
				return ResponseHandler.generateResponse(updatedLeadGeneration, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			logger.error("Failed update LeadGeneration : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}

}
