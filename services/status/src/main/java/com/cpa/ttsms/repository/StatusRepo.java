/**
 * @author  - Code Generator
 * @createdOn -  24-07-2023
 * @Description Entity class for Status
 * 
 */

package com.cpa.ttsms.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status, Integer> {

	// Retrieves a status by their statusId
	public Status findByStatusId(int statusId);

	// delete status by their statusId
	@Transactional
	@Modifying
	@Query(value = "Delete from status WHERE id = ?1", nativeQuery = true)
	public int deleteStatusByStatusId(int statusId);

	// Retrieves a status by their code
	public Status findByStatusCode(String statusCode);

}
