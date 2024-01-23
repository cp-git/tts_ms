/**
 * @author  - Code Generator
 * @createdOn -  17/01/2024
 * @Description Entity class for BenchCandidate Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.BenchCandidate;

public interface BenchCandidateService {

	BenchCandidate createBenchCandidate(BenchCandidate benchCandidate);

	BenchCandidate getBenchCandidateByBenchCandidateId(int benchcandidateid);

	List<Object> getAllBenchCandidatesByCompanyId(int companyId);

	BenchCandidate updateBenchCandidateByBenchCandidateId(BenchCandidate benchCandidate, int benchcandidateid);

	int deleteBenchCandidateByBenchCandidateId(int benchcandidateid);

}