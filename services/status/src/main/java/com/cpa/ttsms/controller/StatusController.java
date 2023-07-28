/**
 * @author - Code Generator
 * @developer - Akash
 * @createdOn 24-07-2023
 * @Description Controller class for status
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

import com.cpa.ttsms.entity.Status;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.StatusService;

@CrossOrigin
@RestController
@RequestMapping("/ttsms")
public class StatusController {

	@Autowired
	private StatusService statusService;

	// The ResourceBundle is used to retrieve localized messages.
	private ResourceBundle resourceBundle;

	// The logger is used for logging messages related to this class.
	private static Logger logger;

	StatusController() {
		resourceBundle = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(StatusController.class);
	}

	/**
	 * Create a new status.
	 * 
	 * @param status The status to create.
	 * @return The newly created status.
	 * @throws CPException If there was an error creating the status.
	 */
	@PostMapping("/status")
	public ResponseEntity<Object> createStatus(@RequestBody Status status) throws CPException {
		logger.debug("Entering createStatus");
		logger.info("Received request to create status for code : " + status.getStatusCode());

		Status createdStatus = null;
		try {
			// If the status doesn't exist, create it.
			createdStatus = statusService.createStatus(status);

			if (createdStatus == null) {
				// If the status already exists, return an error response.
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			} else {
				// Return the newly created status.
				return ResponseHandler.generateResponse(createdStatus, HttpStatus.CREATED);

			}

		} catch (Exception ex) {
			// Log the error and throw an CPException with an error code and message.
			logger.error("Failed to create status: " + ex.getMessage());
			throw new CPException("err003", resourceBundle.getString("err003"));
		}
	}

	/**
	 * Retrieves a status by their ID.
	 *
	 * @param statusId The ID of the status to retrieve.
	 * @return The status with the specified StatusId, or a 404 Not Found response
	 *         if no such status exists.
	 * @throws CPException
	 */

	@GetMapping("/status/{id}")
	public ResponseEntity<Object> getStatusByStatusId(@PathVariable("id") int statusId) throws CPException {
		logger.info("Received request to retrieve status with ID: " + statusId);
		try {

			Status status = statusService.getStatusByStatusId(statusId);
			if (status == null) {
				logger.warn("No status found with ID: " + statusId);

				// If the status not found/ exists, return an error response.
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			} else {
				logger.info("Retrieved status with ID: " + statusId);

				// returns retrieved status.
				return ResponseHandler.generateResponse(status, HttpStatus.OK);
			}

		} catch (Exception ex) {
			// Log the error and throw an CPException with an error code and message.
			logger.error("Failed to create status: " + ex.getMessage());
			throw new CPException("err001", resourceBundle.getString("err001"));
		}
	}

	/**
	 * 
	 * This method handles the GET request to retrieve all status
	 * 
	 * @throws CPException If an error occurs during the operation, an CPException
	 *                     with an error code and message is thrown
	 */
	@GetMapping("/allstatus")
	public ResponseEntity<List<Object>> getAllStatus() throws CPException {
		logger.info("Getting all status");
		List<Object> status = null;

		try {
			// Call the statusService to retrieve all status
			status = statusService.getAllStatus();

			// If no status are found, return a response with a warning status and errorcode
			if (status.isEmpty()) {
				logger.warn("No status found");
				return ResponseHandler.generateListResponse(HttpStatus.NO_CONTENT, "err002");
			} else {
				// Log the number of status being returned and return them with a success
				// status
				logger.info("Returning status: " + status.size());
				return ResponseHandler.generateListResponse(status, HttpStatus.OK);
			}

		} catch (Exception ex) {
			// If an exception occurs, log the error and throw an CPException with an error
			// code and message.
			logger.error("Failed to get all status: " + ex.getMessage());
			throw new CPException("err002", resourceBundle.getString("err002"));
		}
	}

	/**
	 * deletes a status by status statusId.
	 * 
	 * @param statusId The statusId of the status to be deleted.
	 * @return ResponseEntity with NO_CONTENT status code on successful deletion or
	 *         INTERNAL_SERVER_ERROR on failure.
	 * @throws CPException if an error occurs while performing the operation.
	 */
	@DeleteMapping("/status/{id}")
	public ResponseEntity<Object> deleteStatusByStatusId(@PathVariable("id") int statusId) throws CPException {
		logger.info("Deleting status by statusId : " + statusId);

		boolean success = false;

		try {

			// Call the statusService to perform the hard delete operation.
			success = statusService.deleteStatusByStatusId(statusId);
			if (success) {
				// Return a response with NO_CONTENT status code if the operation is successful.
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				// Return a response with INTERNAL_SERVER_ERROR status code if the operation
				// fails.
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			// If an exception occurs, log the error and throw an CPException with an error
			// code and message.
			logger.error("Failed to delete status : " + ex.getMessage());
			throw new CPException("err005", resourceBundle.getString("err005"));
		}
	}

	/**
	 * Update a status by their statusId.
	 * 
	 * @param statusId The statusId of the status to update
	 * @param status   the updated status object
	 * @return the updated status object, or null if the status was not found
	 */
	@PutMapping("/status/{id}")
	public ResponseEntity<Object> updateStatusByStatusId(@RequestBody Status status, @PathVariable("id") int statusId)
			throws CPException {
		logger.info("Updating status by code : " + statusId);

		Status updatedStatus = null;

		try {
			// Call the statusService to perform the update operation.
			updatedStatus = statusService.updateStatusByStatusId(status, statusId);

			if (updatedStatus == null) {
				// If the status not exists, return an error response.
				logger.info(resourceBundle.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				// Return the updated status.
				logger.info("updated status : " + updatedStatus);
				return ResponseHandler.generateResponse(updatedStatus, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			// If an exception occurs, log the error and throw an CPException with an error
			// code and message.
			logger.error("Failed update Status : " + ex.getMessage());
			throw new CPException("err004", resourceBundle.getString("err004"));

		}

	}

}
