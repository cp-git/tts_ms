/**
 * This interface defines the contract for the Reason Service, which is responsible for
 * managing reasons related to tasks in the application.
 * 
 * @author Akash
 * @createdOn 07-08-2023
 */
package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.Reason;

public interface ReasonService {

	/**
	 * Creates a new reason and stores it in the system.
	 * 
	 * @param reason The Reason object to be created.
	 * @return The created Reason object.
	 */
	Reason createReason(Reason reason);

	/**
	 * Retrieves a list of reasons associated with a specific task.
	 * 
	 * @param taskId The ID of the task for which reasons are to be retrieved.
	 * @return A list of Reason objects associated with the specified task.
	 */
	List<Object> getReasonsByTaskId(int taskId);

	/**
	 * Retrieves a list of all reasons stored in the system.
	 * 
	 * @return A list of all Reason objects.
	 */
	List<Object> getAllReasons();
}
