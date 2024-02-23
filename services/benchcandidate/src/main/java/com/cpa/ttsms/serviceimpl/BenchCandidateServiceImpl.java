/**
 * @author - Code Generator
 * @createdOn 17/01/2024
 * @Description Controller class for benchCandidate
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.BenchCandidateController;
import com.cpa.ttsms.entity.BenchCandidate;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.repository.BenchCandidateRepo;
import com.cpa.ttsms.service.BenchCandidateService;

@Service
public class BenchCandidateServiceImpl implements BenchCandidateService {

	@Autowired
	private BenchCandidateRepo benchCandidateRepo;

	private static Logger logger;

	private ResourceBundle resourceBunde;

	public BenchCandidateServiceImpl() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(BenchCandidateServiceImpl.class);
	}

	/**
	 * @param : BenchCandidate benchCandidate
	 * @return : BenchCandidate createdBenchCandidate
	 * @throws CPException
	 * @description : For creating/inserting entry in benchCandidate table
	 */
	@Override
	public BenchCandidate createBenchCandidate(BenchCandidate benchCandidate) throws CPException {
		logger.debug("Entering createBenchCandidate");
		BenchCandidate createdBenchCandidate = null;

//		BenchCandidate existing = benchCandidateRepo.findByEmail(benchCandidate.getEmail());
//
//		if (existing != null) {
//			throw new CPException("err006", resourceBunde.getString("err006"));
//		}
		try {
			createdBenchCandidate = benchCandidateRepo.save(benchCandidate);
			logger.info("created BenchCandidate :" + createdBenchCandidate);
			return createdBenchCandidate;
		} catch (DataIntegrityViolationException ex) {
			// To handle different error message for different scenarios
			String errorMessage = "";
			// constraints_unique_benchcandidateemail is the unique constraint name from
			// database table for email
			if (ex.getMessage().contains("constraints_unique_benchcandidateemail")) {
				logger.debug("constraints_unique_benchcandidateemail");
				errorMessage = resourceBunde.getString("err006");
				throw new CPException("err006", errorMessage);
			}
			if (ex.getMessage().contains("constraints_unique_benchcandidatecontactno")) {
				logger.debug("constraints_unique_benchcandidatecontactno");
				errorMessage = resourceBunde.getString("err008");
				throw new CPException("err008", errorMessage);
			}
		}
		return null;

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
	 * @throws CPException
	 * @description : For updating benchCandidate of benchCandidate table
	 */
	@Override
	public BenchCandidate updateBenchCandidateByBenchCandidateId(BenchCandidate benchCandidate, int benchcandidateid)
			throws CPException {
		logger.debug("Entering updateBenchCandidate");

		BenchCandidate toUpdatedBenchCandidate = null;
		BenchCandidate updatedBenchCandidate = null;

		toUpdatedBenchCandidate = benchCandidateRepo.findByBenchCandidateId(benchcandidateid);

		logger.info("exisitng BenchCandidate :: " + toUpdatedBenchCandidate);

		if (toUpdatedBenchCandidate != null) {
			logger.debug("setting new data of BenchCandidate to exisitng BenchCandidate");

//			if (benchCandidate.getBenchCandidateId() <= 0) {
//				benchCandidate.setBenchCandidateId(benchcandidateid);
//			}

			try {

				updatedBenchCandidate = benchCandidateRepo.save(benchCandidate);
				logger.info("created BenchCandidate :" + updatedBenchCandidate);
				return updatedBenchCandidate;

			} catch (DataIntegrityViolationException ex) {
				// To handle different error message for different scenarios
				String errorMessage = "";
				// constraints_unique_benchcandidateemail is the unique constraint name from
				// database table for email
				if (ex.getMessage().contains("constraints_unique_benchcandidateemail")) {
					logger.debug("constraints_unique_benchcandidateemail");
					errorMessage = resourceBunde.getString("err009");
					throw new CPException("err009", errorMessage);
				}

				if (ex.getMessage().contains("constraints_unique_benchcandidatecontactno")) {
					logger.debug("constraints_unique_benchcandidatecontactno");
					errorMessage = resourceBunde.getString("err011");
					throw new CPException("err011", errorMessage);
				}

			}

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
