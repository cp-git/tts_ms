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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

	@Autowired
	RestTemplate restTemplate;
	
	private ResourceBundle resourceBunde;
	private static Logger logger;
//    private static final String BASE_URL = "http://127.0.0.1:8010/";
    private static final String BASE_URL = "https://127.0.0.1:8443/";


	BenchCandidateController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(BenchCandidateController.class);
	}
	
	  private String callCheckToken(String authHeader) {
	        try {
	            // Extract the token from the Authorization header.
	            String token = authHeader.substring(7);

	            // Set the authorization header with the token.
	            HttpHeaders headers = new HttpHeaders();
	            headers.set("Authorization", "Bearer " + token);
	            headers.setContentType(MediaType.APPLICATION_JSON);

	            // Create the request entity with headers.
	            HttpEntity<String> entity = new HttpEntity<>(headers);

	            // Specify the complete URL for the checkToken endpoint using the base URL variable.
	            String checkTokenUrl = BASE_URL + "security/token/checkToken";

	            // Make the HTTP GET request to the checkToken endpoint.
	            ResponseEntity<String> response = restTemplate.exchange(
	                    checkTokenUrl, HttpMethod.GET, entity, String.class);

	            // Return the response body.
	            return response.getBody();
	        } catch (Exception ex) {
	            // Handle exceptions if any.
	            return null;
	        }
	    }

	@PostMapping("/benchcandidate")
	public ResponseEntity<Object> createBenchCandidate(@RequestBody BenchCandidate benchCandidate,@RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering createBenchCandidate");
		logger.info("data of creating BenchCandidate  :" + benchCandidate.toString());
		try {
			callCheckToken(authHeader);
			BenchCandidate createdBenchCandidate = benchCandidateService.createBenchCandidate(benchCandidate);
			if (createdBenchCandidate == null) {
				logger.error(resourceBunde.getString("err007"));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resourceBunde.getString("err007"));
			} else {
				logger.info("created BenchCandidate :" + createdBenchCandidate);
				return ResponseEntity.status(HttpStatus.CREATED).body(createdBenchCandidate);
			}

		} catch (CPException ex) {
			System.out.println("hello " + ex.getErrorCode());
			String errorMessage = resourceBunde.getString(ex.getErrorCode());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	@GetMapping("/benchcandidate/{benchCandidateId}")
	public ResponseEntity<Object> getBenchCandidateByBenchCandidateId(
			@PathVariable("benchCandidateId") int benchCandidateId, @RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering getBenchCandidateBybenchCandidateId");
		logger.info("entered user name :" + benchCandidateId);

		BenchCandidate benchCandidate = null;

		try {
			callCheckToken(authHeader);
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
			@RequestParam(name = "companyid") int companyId,@RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering getAllBenchCandidatesByCompanyId");
		logger.info("Parameter  :" + companyId);

		List<Object> benchCandidates = null;

		try {

			callCheckToken(authHeader);
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
			@PathVariable("benchCandidateId") int benchCandidateId,@RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering deleteAuthUser");
		logger.info("entered deleteBenchCandidate  :" + benchCandidateId);
		// TODO - implement the business logic

		int count = 0;

		try {
			callCheckToken(authHeader);
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
			@PathVariable("benchCandidateId") int benchCandidateId,@RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering updateBenchCandidate");
		logger.info("entered  updateBenchCandidate :" + benchCandidate);

		try {
			callCheckToken(authHeader);
			BenchCandidate createdBenchCandidate = benchCandidateService.createBenchCandidate(benchCandidate);
			if (createdBenchCandidate == null) {
				logger.error(resourceBunde.getString("err010"));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resourceBunde.getString("err010"));
			} else {
				logger.info("Update BenchCandidate :" + createdBenchCandidate);
				return ResponseEntity.status(HttpStatus.CREATED).body(createdBenchCandidate);
			}

		} catch (CPException ex) {
			String errorMessage = resourceBunde.getString(ex.getErrorCode());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}

	}

}
