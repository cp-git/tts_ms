/**
 * @author  - Code Generator
 * @developer - Akash
 * @createdOn -  24-07-2023
 * @Description Entity class for Status
 * 
 */

package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status, Integer> {

	// Retrieves a status by their statusId
	public Status findByStatusCodeIgnoreCase(String statusCode);

}
