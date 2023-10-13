/**
 * @author  - Code Generator
 * @developer - Akash
 * @createdOn -  24-07-2023
 * @Description Entity class for Status
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

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

	
	// Retrieves a status by their code
	public Status findByStatusCode(String statusCode);

	// Retrieve statuses by companyId
	List<Status> findByCompanyId(int companyId);

	public Status findByStatusCodeAndCompanyId(String statusCode, int companyId);
}
