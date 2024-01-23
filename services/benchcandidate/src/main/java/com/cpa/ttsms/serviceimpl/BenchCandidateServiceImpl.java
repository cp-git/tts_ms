/**
 * @author - Code Generator
 * @createdOn 17/01/2024
 * @Description Controller class for benchCandidate
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.BenchCandidateController;
import com.cpa.ttsms.entity.BenchCandidate;
import com.cpa.ttsms.repository.BenchCandidateRepo;
import com.cpa.ttsms.service.BenchCandidateService;

@Service
public class BenchCandidateServiceImpl implements BenchCandidateService {

	@Autowired
	private BenchCandidateRepo benchCandidateRepo;
	private static Logger logger;

	public BenchCandidateServiceImpl() {
		logger = Logger.getLogger(BenchCandidateServiceImpl.class);
	}

	/**
	 * @param : BenchCandidate benchCandidate
	 * @return : BenchCandidate createdBenchCandidate
	 * @description : For creating/inserting entry in benchCandidate table
	 */
	@Override
	public BenchCandidate createBenchCandidate(BenchCandidate benchCandidate) {
		logger.debug("Entering createBenchCandidate");
		BenchCandidate createdBenchCandidate = null;

		// benchCandidate.setBenchCandidateCreatedBy("admin");
		// benchCandidate.setBenchCandidateModifiedBy("admin");

		createdBenchCandidate = benchCandidateRepo.save(benchCandidate);
		logger.info("created BenchCandidate :" + createdBenchCandidate);
		return createdBenchCandidate;
	}

	/**
	 * @param : String benchcandidateid
	 * @return : BenchCandidate benchCandidate
	 * @description : For get entry in benchCandidate table
	 */
	@Override
	public BenchCandidate getBenchCandidateByBenchCandidateId(int benchcandidateid) {
		logger.debug("Entering getBenchCandidateByBenchCandidateId");

		BenchCandidate benchCandidate = benchCandidateRepo.findByBenchCandidateId(benchcandidateid);
		logger.info("Founded benchCandidate :" + benchCandidate);

		return benchCandidate;
	}

	/**
	 * @return : List<Object> benchCandidate
	 * @description : For fetching all benchCandidate which are active state from
	 *              benchCandidate table
	 */
	@Override
	public List<Object> getAllBenchCandidatesByCompanyId(int companyId) {
		logger.debug("Entering getAllBenchCandidates");

		List<Object> benchCandidates = benchCandidateRepo.findAllByCompanyId(companyId);
		logger.info("Fetched all active benchCandidate :" + benchCandidates);
		return benchCandidates;
	}

	/**
	 * @param : BenchCandidate to update
	 * @return : benchCandidate
	 * @description : For updating benchCandidate of benchCandidate table
	 */
	@Override
	public BenchCandidate updateBenchCandidateByBenchCandidateId(BenchCandidate benchCandidate, int benchcandidateid) {
		logger.debug("Entering updateBenchCandidate");

		BenchCandidate toUpdatedBenchCandidate = null;
		BenchCandidate updatedBenchCandidate = null;

		toUpdatedBenchCandidate = benchCandidateRepo.findByBenchCandidateId(benchcandidateid);
		logger.info("exisitng BenchCandidate :: " + toUpdatedBenchCandidate);

		if (toUpdatedBenchCandidate != null) {
			logger.debug("setting new data of BenchCandidate to exisitng BenchCandidate");

			if (benchCandidate.getBenchCandidateId() <= 0) {
				benchCandidate.setBenchCandidateId(benchcandidateid);
			}

			updatedBenchCandidate = benchCandidateRepo.save(benchCandidate);

			logger.info("updated BenchCandidate :" + updatedBenchCandidate);
		}

		return updatedBenchCandidate;
	}

	/**
	 * @param : String benchcandidateid
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of
	 *              BenchCandidate
	 * 
	 */
	@Override
	public int deleteBenchCandidateByBenchCandidateId(int benchcandidateid) {
		logger.debug("Entering deleteBenchCandidateByBenchCandidateId");

		int count = benchCandidateRepo.deleteByBenchCandidateId(benchcandidateid);
		logger.info("deleted BenchCandidate count : " + count);
		return count;
	}

}
