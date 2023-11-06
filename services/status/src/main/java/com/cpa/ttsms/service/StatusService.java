
/**
 * @author  - Code Generator
 * @developer - Akash
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
	 * Gets a status by their StatusId.
	 * 
	 * @param statusId The statusId of the status to get.
	 * @return The status object corresponding to the provided code.
	 */
	Status getStatusByStatusId(int statusId);

	/**
	 * Gets all status in the system.
	 * 
	 * @return A list of all status objects.
	 */
	List<Object> getAllStatus();

	/**
	 * Updates an existing status in the system.
	 * 
	 * @param statusId The statusId of the status to update.
	 * @param status   The updated status object.
	 * @return The updated status object.
	 */
	Status updateStatusByStatusId(Status status, int statusId);

	/**
	 * Deletes a status by their statusId.
	 * 
	 * @param statusId The statusId of the status to delete.
	 * @return The number of rows affected by the delete operation.
	 */
	boolean deleteStatusByStatusId(int statusId);

	List<Status> getStatusesByCompanyId(int companyId);
}