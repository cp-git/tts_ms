/**
 * @author - Code Generator
 * @createdOn 18/12/2023
 * @Description Controller class for jobportal
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.JobPortal;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.JobPortalService;

@RestController
@RequestMapping("/ttsms")
@CrossOrigin
public class JobPortalController {

	@Autowired
	private JobPortalService jobportalService;;

	private ResourceBundle resourceBunde;
	private static Logger logger;

	JobPortalController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(JobPortalController.class);
	}

	@PostMapping("/jobportal")
	public ResponseEntity<Object> createJobPortal(@RequestBody JobPortal jobportal) throws CPException {
		logger.debug("Entering createJobPortal");
		logger.info("data of creating JobPortal  :" + jobportal.toString());

		JobPortal createdJobPortal = null;
		try {

			JobPortal toCheckJobPortal = jobportalService.getJobPortalByjobPortalId(jobportal.getPortalId());
			logger.debug("existing jobportal :" + toCheckJobPortal);

			if (toCheckJobPortal == null) {

				// TODO: Uncomment below 2 lines and change the method name as per your Entity
				// class
				// jobportal.setCreatedby("admin");
				// jobportal.setUpdatedby("admin");

				createdJobPortal = jobportalService.createJobPortal(jobportal);
				logger.info("JobPortal created :" + createdJobPortal);

				return ResponseHandler.generateResponse(createdJobPortal, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed JobPortal creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/jobportal/{portalId}")
	public ResponseEntity<Object> getJobPortalByjobPortalId(@PathVariable("portalId") int portalId) throws CPException {
		logger.debug("Entering getJobPortalByportalId");
		logger.info("entered user name :" + portalId);

		JobPortal jobportal = null;

		try {

			jobportal = jobportalService.getJobPortalByjobPortalId(portalId);
			logger.info("fetched JobPortal :" + jobportal);

			if (jobportal != null) {
				logger.debug("JobPortal fetched generating response");
				return ResponseHandler.generateResponse(jobportal, HttpStatus.OK);
			} else {
				logger.debug("JobPortal not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {

			logger.error("Failed getting jobportal : " + ex.getMessage());
			throw new CPException("err001", resourceBunde.getString("err001"));
		}

	}

	@GetMapping("/jobportal")
	public ResponseEntity<List<Object>> getAllJobPortals() throws CPException {
		logger.debug("Entering getAllJobPortal");
		// logger.info("Parameter :" + portalId);

		List<Object> jobportals = null;

		try {
			jobportals = jobportalService.getAllJobPortals();
			logger.info("Fetched all JobPortal :" + jobportals);

			if (jobportals != null && !jobportals.isEmpty()) {

				logger.info("Fetched all Visas: " + jobportals);
				return ResponseHandler.generateListResponse(jobportals, HttpStatus.OK);
			} else {

				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting all countries: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all countries");
		}
	}

	@DeleteMapping("/jobportal/{portalId}")
	public ResponseEntity<Object> deleteJobPortalByjobPortalId(@PathVariable("portalId") int portalId)
			throws CPException {
		logger.debug("Entering deleteAuthUser");
		logger.info("entered deleteJobPortal  :" + portalId);
		// TODO - implement the business logic

		int count = 0;

		try {
			count = jobportalService.deleteJobPortalByjobPortalId(portalId);
			if (count >= 1) {
				logger.info("deleted JobPortal : jobPortalId = " + portalId);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				logger.info(resourceBunde.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			logger.error("Failed to delete JobPortal :" + ex.getMessage());
			throw new CPException("err005", resourceBunde.getString("err005"));
		}

	}

	@PutMapping("/jobportal/{portalId}")
	public ResponseEntity<Object> updateJobPortalByjobPortalId(@RequestBody JobPortal jobportal,
			@PathVariable("portalId") int portalId) throws CPException {
		logger.debug("Entering updateJobPortal");
		logger.info("entered  updateJobPortal :" + jobportal);

		JobPortal updatedJobPortal = null;

		try {
			updatedJobPortal = jobportalService.updateJobPortalByjobPortalId(jobportal, portalId);

			if (updatedJobPortal == null) {
				logger.info(resourceBunde.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				logger.info("updated jobportal : " + updatedJobPortal);
				return ResponseHandler.generateResponse(updatedJobPortal, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			logger.error("Failed update JobPortal : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}

	@GetMapping("/jobportals/{companyId}")
	public ResponseEntity<List<Object>> getJobPortalByCompanyId(@PathVariable("companyId") int companyId)
			throws CPException {
		logger.debug("Entering getAllVisa");

		List<Object> portals = null;

		try {
			portals = jobportalService.getAllPortalsByCompanyId(companyId);

			if (portals != null && !portals.isEmpty()) {

				logger.info("Fetched all portals: " + portals);
				return ResponseHandler.generateListResponse(portals, HttpStatus.OK);
			} else {

				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.

			throw new CPException("err002", "Error while retrieving all portals");
		}
	}

}
