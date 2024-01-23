/**
 * @author  - Code Generator
 * @createdOn -  17/01/2024
 * @Description Entity class for BenchCandidate
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.BenchCandidate;

@Repository
public interface BenchCandidateRepo extends JpaRepository<BenchCandidate, Integer> {

	public BenchCandidate findByBenchCandidateId(int benchcandidateid);

	public List<Object> findAllByCompanyId(int companyId);

	@Transactional
	@Modifying
	public int deleteByBenchCandidateId(int benchcandidateid);

}
