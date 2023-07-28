package com.cpa.ttsms.service;

public interface PasswordService {

	/**
	 * Get the employee ID (empid) associated with the given username.
	 *
	 * @param username The username for which to fetch the employee ID.
	 * @return The employee ID (empid) associated with the username.
	 * @throws Exception If the employee ID cannot be fetched for the provided
	 *                   username.
	 */
	int getEmpIdFromUsername(String username) throws Exception;

}
