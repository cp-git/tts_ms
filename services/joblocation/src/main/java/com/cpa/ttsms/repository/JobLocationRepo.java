/**
 * @author  - Code Generator
 * @createdOn -  19/12/2023
 * @Description Entity class for JobLocation
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.JobLocation;

@Repository
public interface JobLocationRepo extends JpaRepository<JobLocation, Integer> {

	public JobLocation findByLocationId(int locationid);

	public List<JobLocation> findAll();

	public List<JobLocation> findByCompanyId(int companyId);

//	@Transactional
//	@Modifying
//	@Query(value = "UPDATE joblocation SET is_active=false WHERE locationid = ?1", nativeQuery = true)
//	public int deleteJobLocationBylocationId(String locationid);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM joblocation WHERE locationid = ?", nativeQuery = true)
	public int deleteJobLocationById(int locationId);

}
