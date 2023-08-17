/**
 * This interface defines the contract for the Reason Service, which is responsible for
 * managing reasons related to tasks in the application.
 * 
 * @author Akash
 * @createdOn 07-08-2023
 */
package com.cpa.ttsms.service;

import com.cpa.ttsms.entity.Reason;

public interface ReasonService {

	/**
	 * Creates a new reason and stores it in the system.
	 * 
	 * @param reason The Reason object to be created.
	 * @return The created Reason object.
	 */
	Reason createReason(Reason reason);

}
