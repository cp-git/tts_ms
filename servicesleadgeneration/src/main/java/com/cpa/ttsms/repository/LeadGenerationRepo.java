/**
 * @author  - Code Generator
 * @createdOn -  19/12/2023
 * @Description Entity class for LeadGeneration
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.LeadGeneration;

@Repository
public interface LeadGenerationRepo extends JpaRepository<LeadGeneration, Integer> {

	public LeadGeneration findByleadgenerationlocationId(String locationid);

	public List<Object> findByleadgenerationIsActiveTrue();

	@Transactional
	@Modifying
	@Query(value = "UPDATE leadrepo SET is_active=false WHERE locationid = ?1", nativeQuery = true)
	public int deleteleadgenerationBylocationId(String locationid);

}
