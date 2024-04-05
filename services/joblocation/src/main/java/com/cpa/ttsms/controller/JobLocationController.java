/**
 * @author - Code Generator
 * @createdOn 19/12/2023
 * @Description Controller class for joblocation
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cpa.ttsms.entity.JobLocation;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.JobLocationService;

@RestController
@RequestMapping("/ttsms")
@CrossOrigin
public class JobLocationController {

	@Autowired
	private JobLocationService joblocationService;;

	@Autowired
	private RestTemplate restTemplate;
	
	private ResourceBundle resourceBunde;
	private static Logger logger;
    private static final String BASE_URL = "http://127.0.0.1:8010/";

	JobLocationController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(JobLocationController.class);
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
	  
	@PostMapping("/joblocation")
	public ResponseEntity<Object> createJobLocation(@RequestBody JobLocation joblocation,@RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering createJobLocation");
		logger.info("data of creating JobLocation  :" + joblocation.toString());

		JobLocation createdJobLocation = null;
		try {
			callCheckToken(authHeader);
			JobLocation toCheckJobLocation = joblocationService.getJobLocationBylocationId(joblocation.getLocationId());
			logger.debug("existing joblocation :" + toCheckJobLocation);

			if (toCheckJobLocation == null) {

				createdJobLocation = joblocationService.createJobLocation(joblocation);
				logger.info("JobLocation created :" + createdJobLocation);

				return ResponseHandler.generateResponse(createdJobLocation, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed JobLocation creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/joblocation/{locationId}")
	public ResponseEntity<Object> getJobLocationBylocationId(@PathVariable("locationId") int locationId,@RequestHeader("Authorization") String authHeader
)
			throws CPException {
		logger.debug("Entering getJobLocationBylocationId");
		logger.info("entered user name :" + locationId);

		JobLocation joblocation = null;

		try {
			callCheckToken(authHeader);
			joblocation = joblocationService.getJobLocationBylocationId(locationId);
			logger.info("fetched JobLocation :" + joblocation);

			if (joblocation != null) {
				logger.debug("JobLocation fetched generating response");
				return ResponseHandler.generateResponse(joblocation, HttpStatus.OK);
			} else {
				logger.debug("JobLocation not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {

			logger.error("Failed getting joblocation : " + ex.getMessage());
			throw new CPException("err001", resourceBunde.getString("err001"));
		}

	}

	@GetMapping("/joblocation")
	public ResponseEntity<List<Object>> getAllJobLocations(@RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering getAllJobLocation");

		List<Object> joblocations = null;

		try {
			callCheckToken(authHeader);
			joblocations = joblocationService.getAllJobLocations();

			if (joblocations != null && !joblocations.isEmpty()) {

				logger.info("Fetched all locations: " + joblocations);
				return ResponseHandler.generateListResponse(joblocations, HttpStatus.OK);
			} else {

				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting all countries: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all countries");
		}
	}

	@DeleteMapping("/joblocation/{locationId}")
	public ResponseEntity<Object> deleteJobLocationBylocationId(@PathVariable("locationId") int locationId,@RequestHeader("Authorization") String authHeader
)
			throws CPException {
		logger.debug("Entering deleteAuthUser");
		logger.info("entered deleteJobLocation  :" + locationId);
		// TODO - implement the business logic

		int count = 0;

		try {
			callCheckToken(authHeader);
			count = joblocationService.deleteJobLocationBylocationId(locationId);
			if (count >= 1) {
				logger.info("deleted JobLocation : locationId = " + locationId);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				logger.info(resourceBunde.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			logger.error("Failed to delete JobLocation :" + ex.getMessage());
			throw new CPException("err005", resourceBunde.getString("err005"));
		}

	}

	@PutMapping("/joblocation/{locationId}")
	public ResponseEntity<Object> updateJobLocationBylocationId(@RequestBody JobLocation joblocation,
			@PathVariable("locationId") int locationId,@RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering updateJobLocation");
		logger.info("entered  updateJobLocation :" + joblocation);

		JobLocation updatedJobLocation = null;

		try {
			callCheckToken(authHeader);
			updatedJobLocation = joblocationService.updateJobLocationBylocationId(joblocation, locationId);

			if (updatedJobLocation == null) {
				logger.info(resourceBunde.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				logger.info("updated joblocation : " + updatedJobLocation);
				return ResponseHandler.generateResponse(updatedJobLocation, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			logger.error("Failed update JobLocation : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}

	@GetMapping("/locations/{companyId}")
	public ResponseEntity<List<Object>> getJobLocationByCompanyId(@PathVariable("companyId") int companyId,@RequestHeader("Authorization") String authHeader)
			throws CPException {
		logger.debug("Entering getAllVisa");

		List<Object> locations = null;

		try {
			callCheckToken(authHeader);
			locations = joblocationService.getJobLocationByCompanyId(companyId);

			if (locations != null && !locations.isEmpty()) {

				logger.info("Fetched all location: " + locations);
				return ResponseHandler.generateListResponse(locations, HttpStatus.OK);
			} else {

				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting all portals: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all portals");
		}
	}

}
