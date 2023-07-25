
/**
 * @author  - Code Generator
 * @createdOn -  24/07/2023
 * @Description Entity class for Status Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.Status;

public interface StatusService {

	/**
	 * Creates a new status in the system.
	 * 
	 * @param status The Status object containing the status details.
	 * @return The created Status object.
	 */
	Status createStatus(Status status);

	/**
	 * Gets a status by their Id.
	 * 
	 * @param id The id of the status to get.
	 * @return The status object corresponding to the provided code.
	 */
	Status getStatusById(int id);

	/**
	 * Gets all status in the system.
	 * 
	 * @return A list of all status objects.
	 */
	List<Object> getAllStatus();

	/**
	 * Updates an existing status in the system.
	 * 
	 * @param id     The id of the status to update.
	 * @param status The updated status object.
	 * @return The updated status object.
	 */
	Status updateStatusById(Status status, int id);

	/**
	 * Deletes a status by their id.
	 * 
	 * @param id The id of the status to delete.
	 * @return The number of rows affected by the delete operation.
	 */
	boolean deleteStatusById(int id);

}