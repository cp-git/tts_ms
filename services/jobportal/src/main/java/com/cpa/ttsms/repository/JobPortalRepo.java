/**
 * @author  - Code Generator
 * @createdOn -  18/12/2023
 * @Description Entity class for JobPortal
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.JobPortal;

@Repository
public interface JobPortalRepo extends JpaRepository<JobPortal, Integer> {

	public JobPortal findByPortalId(int portalId);

	public List<JobPortal> findAll();

//	@Transactional
//	@Modifying
//	@Query(value = "UPDATE jobportal SET is_active=false WHERE jobportalid = ?1", nativeQuery = true)
//	public int deleteJobPortalByjobPortalId(String jobportalid);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM jobportal WHERE portalid = ?", nativeQuery = true)
	public int deleteJobPortalById(int portalId);

}
