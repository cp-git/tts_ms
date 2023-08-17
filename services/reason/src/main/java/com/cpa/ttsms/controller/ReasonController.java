/**
 * Author:akash
 * Created on: 07-08-2023
 * Description: Controller class for managing reasons
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.Reason;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.ReasonService;

@CrossOrigin
@RestController
@RequestMapping("/ttsms")
public class ReasonController {

	@Autowired
	private ReasonService reasonService;

	private ResourceBundle resourceBundle;
	private static Logger logger;

	ReasonController() {
		resourceBundle = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(ReasonController.class);
	}

	/**
	 * Handles the creation of a new reason.
	 *
	 * @param reason The Reason object to be created. Received in the request body.
	 * @return A ResponseEntity containing the result of the operation.
	 * @throws CPException
	 */
	@PostMapping("/reason")
	public ResponseEntity<Object> createReason(@RequestBody Reason reason) throws CPException {
		logger.debug("Entering createReason");
		logger.info("Received request to create reason: " + reason.toString());

		try {
			// Attempt to create the reason.
			Reason newReason = reasonService.createReason(reason);

			if (newReason != null) {
				// Return a successful response with the newly created reason.
				logger.info("Reason created successfully: " + newReason.getReasonText());
				return ResponseHandler.generateResponse(newReason, HttpStatus.OK);
			} else {
				// Return an error response if reason creation failed.
				logger.error("Failed to create reason.");
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}
		} catch (Exception ex) {
			// Log the error and return an error response with an appropriate status code
			// and message.
			logger.error("Error while creating reason: " + ex.getMessage());
			throw new CPException("err003", resourceBundle.getString("err003"));
		}
	}

	/**
	 * Retrieves a list of reasons associated with a specific task.
	 *
	 * @param taskId The ID of the task for which reasons are to be retrieved.
	 * @return A ResponseEntity containing the list of reasons.
	 * @throws CPException
	 */
	@GetMapping("/reason/{taskId}")
	public ResponseEntity<List<Object>> getReasonsByTaskId(@PathVariable("taskId") int taskId) throws CPException {
		logger.info("Getting reasons for Task ID: " + taskId);
		List<Object> reasons = null;

		try {
			// Call the reasonService to retrieve reasons for the specified task ID
			reasons = reasonService.getReasonsByTaskId(taskId);

			if (reasons.isEmpty()) {
				// If no reasons are found, return a response with a warning status and error
				// code
				logger.warn("No reasons found for Task ID: " + taskId);
				return ResponseHandler.generateListResponse(HttpStatus.NO_CONTENT, "err005");
			} else {
				// Log the number of reasons being returned and return them with a success
				// status
				logger.info("Returning reasons for Task ID " + taskId + ": " + reasons.size());
				return ResponseHandler.generateListResponse(reasons, HttpStatus.OK);
			}

		} catch (Exception ex) {
			// If an exception occurs, log the error and return an error response with an
			// appropriate status code and message.
			logger.error("Failed to get reasons for Task ID " + taskId + ": " + ex.getMessage());
			throw new CPException("err001", resourceBundle.getString("err001"));
		}
	}

	/**
	 * Retrieves a list of all reasons.
	 *
	 * @return A ResponseEntity containing the list of reasons.
	 * @throws CPException If an error occurs during the operation.
	 */
	@GetMapping("/allreasons")
	public ResponseEntity<List<Object>> getAllReasons() throws CPException {
		logger.info("Getting all reasons");
		List<Object> reasons = null;

		try {
			// Call the reasonService to retrieve all reasons
			reasons = reasonService.getAllReasons();

			if (reasons.isEmpty()) {
				// If no reasons are found, return a response with a warning status and error
				// code
				logger.warn("No reasons found");
				return ResponseHandler.generateListResponse(HttpStatus.NO_CONTENT, "err004");
			} else {
				// Log the number of reasons being returned and return them with a success
				// status
				logger.info("Returning reasons: " + reasons.size());
				return ResponseHandler.generateListResponse(reasons, HttpStatus.OK);
			}

		} catch (Exception ex) {
			// If an exception occurs, log the error and throw a CPException with an error
			// code and message.
			logger.error("Failed to get all reasons: " + ex.getMessage());
			throw new CPException("err004", resourceBundle.getString("err004"));
		}
	}

}
