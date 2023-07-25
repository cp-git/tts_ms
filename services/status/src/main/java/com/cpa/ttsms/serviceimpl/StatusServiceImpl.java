/**
 * @author - Code Generator
 * @createdOn 24-07-2023
 * @Description Controller class for status
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.StatusController;
import com.cpa.ttsms.entity.Status;
import com.cpa.ttsms.repository.StatusRepo;
import com.cpa.ttsms.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepo statusRepo;
	private static Logger logger;

	public StatusServiceImpl() {
		logger = Logger.getLogger(StatusServiceImpl.class);
	}

	/**
	 * Create a new status in the repository.
	 * 
	 * @param : Status object containing the status details.
	 * @return : The newly created status object if successful, otherwise null.
	 */
	@Override
	public Status createStatus(Status status) {
		logger.debug("Entering createStatus");
		Status createdStatus = null;
		// Check if the status already exists in the repository.
		Status existingStatus = statusRepo.findByStatusCode(status.getStatusCode());

		if (existingStatus == null) {
			// If the status does not already exist, create it.
			createdStatus = statusRepo.save(status);
			logger.info("Status created successfully for code :" + status.getStatusCode());
		} else {
			logger.warn("Status already exists with code :" + status.getStatusCode());
		}

		return createdStatus;
	}

	/**
	 * 
	 * Retrieved a status in the repository
	 * 
	 * @param id - Integer value containing id of status.
	 * 
	 * @return The Retrieved status object if successful, otherwise null.
	 */
	@Override

	public Status getStatusById(int id) {
		logger.info("Retrieving status with ID: " + id);

		Status status = null;
		// Call the userRepository to retrieve the status by id
		status = statusRepo.findByStatusId(id);

		if (status == null) {
			logger.warn("No status found with ID: " + id);
		} else {
			logger.info("Retrieved status: " + id);
		}

		return status;
	}

	/**
	 * Gets all status in the system.
	 * 
	 * @return A list of all Status objects.
	 */

	@Override
	public List<Object> getAllStatus() {
		logger.debug("Entering getAllStatus");

		List<Status> status = statusRepo.findAll();

		// Create a new list to store objects
		List<Object> objectStatus = new ArrayList<>(status); // Convert the list of Status objects to a list of generic
																// Objects.
		logger.info("Fetched all active status :" + objectStatus);
		return objectStatus;
	}

	/**
	 * Updates an existing status in the system.
	 * 
	 * @param id     The id of the status to update.
	 * @param status The updated Status object.
	 * @return The updated Status object.
	 */
	@Override
	public Status updateStatusById(Status status, int id) {
		logger.debug("Entering updateStatus");

		Status toStatusAlreadyPresent = null;
		Status updatedStatus = null;

		// Check if the status exists in the database
		toStatusAlreadyPresent = statusRepo.findByStatusId(id);
		logger.info("exisitng Status :: " + toStatusAlreadyPresent);

		if (toStatusAlreadyPresent != null) {
			logger.debug("setting new data of Status to exisitng Status");
			toStatusAlreadyPresent.setStatusCode(status.getStatusCode());
			toStatusAlreadyPresent.setStatusDescription(status.getStatusDescription());
			updatedStatus = statusRepo.save(toStatusAlreadyPresent);

			logger.info("updated Status :" + updatedStatus);
		}

		return updatedStatus;
	}

	/**
	 * deletes the status with the given id.
	 *
	 * @param id The id of the status to delete.
	 * @return True if the status was successfully deleted, false otherwise.
	 */
	@Override
	public boolean deleteStatusById(int id) {
		logger.debug("Entering deleteStatusById");

		int deletedCount = statusRepo.deleteStatusById(id);
		logger.info("deleted Status count : " + deletedCount);
		// If a status is successfully deleted, return true; otherwise, return false
		if (deletedCount > 0) {
			logger.info("Status has been deleted with id: " + id);
			return true;
		} else {
			logger.warn("Status not found or could not be deleted with id : " + id);
			return false;
		}
	}

}
