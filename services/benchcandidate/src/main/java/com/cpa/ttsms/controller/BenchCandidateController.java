/**
 * @author - Code Generator
 * @createdOn 17/01/2024
 * @Description Controller class for benchCandidate
 * 
 */

package com.cpa.ttsms.controller;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.BenchCandidate;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.BenchCandidateService;

@RestController
@RequestMapping("/ttsms")
@CrossOrigin
public class BenchCandidateController {

	@Autowired
	private BenchCandidateService benchCandidateService;;

	private ResourceBundle resourceBunde;
	private static Logger logger;

	BenchCandidateController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(BenchCandidateController.class);
	}

	@PostMapping("/benchcandidate")
	public ResponseEntity<Object> createBenchCandidate(@RequestBody BenchCandidate benchCandidate) throws CPException {
		logger.debug("Entering createBenchCandidate");
		logger.info("data of creating BenchCandidate  :" + benchCandidate.toString());

		BenchCandidate createdBenchCandidate = null;
		try {

			BenchCandidate toCheckBenchCandidate = benchCandidateService
					.getBenchCandidateByBenchCandidateId(benchCandidate.getBenchCandidateId());
			logger.debug("existing benchCandidate :" + toCheckBenchCandidate);

			if (toCheckBenchCandidate == null) {

				// TODO: Uncomment below 2 lines and change the method name as per your Entity
				// class
				// benchCandidate.setCreatedby("admin");
				// benchCandidate.setUpdatedby("admin");

				createdBenchCandidate = benchCandidateService.createBenchCandidate(benchCandidate);
				logger.info("BenchCandidate created :" + createdBenchCandidate);

				return ResponseHandler.generateResponse(createdBenchCandidate, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed BenchCandidate creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/benchcandidate/{benchCandidateId}")
	public ResponseEntity<Object> getBenchCandidateByBenchCandidateId(
			@PathVariable("benchCandidateId") int benchCandidateId) throws CPException {
		logger.debug("Entering getBenchCandidateBybenchCandidateId");
		logger.info("entered user name :" + benchCandidateId);

		BenchCandidate benchCandidate = null;

		try {

			benchCandidate = benchCandidateService.getBenchCandidateByBenchCandidateId(benchCandidateId);
			logger.info("fetched BenchCandidate :" + benchCandidate);

			if (benchCandidate != null) {
				logger.debug("BenchCandidate fetched generating response");
				return ResponseHandler.generateResponse(benchCandidate, HttpStatus.OK);
			} else {
				logger.debug("BenchCandidate not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {

			logger.error("Failed getting benchCandidate : " + ex.getMessage());
			throw new CPException("err001", resourceBunde.getString("err001"));
		}

	}

	@GetMapping("/benchcandidate")
	public ResponseEntity<List<Object>> getAllBenchCandidatesByCompanyId(
			@RequestParam(name = "companyid") int companyId) throws CPException {
		logger.debug("Entering getAllBenchCandidatesByCompanyId");
		logger.info("Parameter  :" + companyId);

		List<Object> benchCandidates = null;

		try {

			if (companyId > 0) {

				benchCandidates = benchCandidateService.getAllBenchCandidatesByCompanyId(companyId);
				logger.info("Fetched all BenchCandidate :" + benchCandidates);

				return ResponseHandler.generateListResponse(benchCandidates, HttpStatus.OK);
			} else {

				logger.info(resourceBunde.getString("err002"));
				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}

		} catch (Exception ex) {

			logger.error("Failed getting all benchCandidates : " + ex.getMessage());
			throw new CPException("err002", resourceBunde.getString("err002"));

		}
	}

	@DeleteMapping("/benchcandidate/{benchCandidateId}")
	public ResponseEntity<Object> deleteBenchCandidateByBenchCandidateId(
			@PathVariable("benchCandidateId") int benchCandidateId) throws CPException {
		logger.debug("Entering deleteAuthUser");
		logger.info("entered deleteBenchCandidate  :" + benchCandidateId);
		// TODO - implement the business logic

		int count = 0;

		try {
			count = benchCandidateService.deleteBenchCandidateByBenchCandidateId(benchCandidateId);
			if (count >= 1) {
				logger.info("deleted BenchCandidate : BenchCandidateId = " + benchCandidateId);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				logger.info(resourceBunde.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			logger.error("Failed to delete BenchCandidate :" + ex.getMessage());
			throw new CPException("err005", resourceBunde.getString("err005"));
		}

	}

	@PutMapping("/benchcandidate/{benchCandidateId}")
	public ResponseEntity<Object> updateBenchCandidateByBenchCandidateId(@RequestBody BenchCandidate benchCandidate,
			@PathVariable("benchCandidateId") int benchCandidateId) throws CPException {
		logger.debug("Entering updateBenchCandidate");
		logger.info("entered  updateBenchCandidate :" + benchCandidate);

		BenchCandidate updatedBenchCandidate = null;

		try {
			updatedBenchCandidate = benchCandidateService.updateBenchCandidateByBenchCandidateId(benchCandidate,
					benchCandidateId);

			if (updatedBenchCandidate == null) {
				logger.info(resourceBunde.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				logger.info("updated benchCandidate : " + updatedBenchCandidate);
				return ResponseHandler.generateResponse(updatedBenchCandidate, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			logger.error("Failed update BenchCandidate : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}

}
