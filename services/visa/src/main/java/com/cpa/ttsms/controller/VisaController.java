/**
 * @author - Code Generator
 * @createdOn 12/12/2023
 * @Description Controller class for visa
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.Visa;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.VisaService;

@RestController
@RequestMapping("/ttsms")
public class VisaController {

	@Autowired
	private VisaService visaService;;

	private ResourceBundle resourceBunde;
	private static Logger logger;

	VisaController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(VisaController.class);
	}

	@PostMapping("/visa")
	public ResponseEntity<Object> createVisa(@RequestBody Visa visa) throws CPException {
		logger.debug("Entering createVisa");
		logger.info("data of creating Visa  :" + visa.toString());

		Visa createdVisa = null;
		try {

			Visa toCheckVisa = visaService.getVisaByvisaId(visa.getVisaId());
			logger.debug("existing visa :" + toCheckVisa);

			if (toCheckVisa == null) {

				// TODO: Uncomment below 2 lines and change the method name as per your Entity
				// class
				// visa.setCreatedby("admin");
				// visa.setUpdatedby("admin");

				createdVisa = visaService.createVisa(visa);
				logger.info("Visa created :" + createdVisa);

				return ResponseHandler.generateResponse(createdVisa, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed Visa creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/visa/{visaId}")
	public ResponseEntity<Object> getVisaByvisaId(@PathVariable("visaId") int visaId) throws CPException {
		logger.debug("Entering getVisaByvisaId");
		logger.info("entered user name :" + visaId);

		Visa visa = null;

		try {

			visa = visaService.getVisaByvisaId(visaId);
			logger.info("fetched Visa :" + visa);

			if (visa != null) {
				logger.debug("Visa fetched generating response");
				return ResponseHandler.generateResponse(visa, HttpStatus.OK);
			} else {
				logger.debug("Visa not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {

			logger.error("Failed getting visa : " + ex.getMessage());
			throw new CPException("err001", resourceBunde.getString("err001"));
		}

	}

	@GetMapping("/visas")
	public ResponseEntity<List<Object>> getAllVisas() throws CPException {
		logger.debug("Entering getAllVisa");

		List<Object> visas = null;

		try {
			visas = visaService.getAllVisas();

			if (visas != null && !visas.isEmpty()) {

				logger.info("Fetched all Visas: " + visas);
				return ResponseHandler.generateListResponse(visas, HttpStatus.OK);
			} else {

				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting all countries: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all countries");
		}
	}

	@DeleteMapping("/visa/{visaId}")
	public ResponseEntity<Object> deleteVisaByvisaId(@PathVariable("visaId") int visaId) throws CPException {
		logger.debug("Entering deleteAuthUser");
		logger.info("entered deleteVisa  :" + visaId);
		// TODO - implement the business logic

		int count = 0;

		try {
			count = visaService.deleteVisaByvisaId(visaId);
			if (count >= 1) {
				logger.info("deleted Visa : visaId = " + visaId);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				logger.info(resourceBunde.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			logger.error("Failed to delete Visa :" + ex.getMessage());
			throw new CPException("err005", resourceBunde.getString("err005"));
		}

	}

	@PutMapping("/visa/{visaId}")
	public ResponseEntity<Object> updateVisaByvisaId(@RequestBody Visa visa, @PathVariable("visaId") int visaId)
			throws CPException {
		logger.debug("Entering updateVisa");
		logger.info("entered  updateVisa :" + visa);

		Visa updatedVisa = null;

		try {
			updatedVisa = visaService.updateVisaByvisaId(visa, visaId);

			if (updatedVisa == null) {
				logger.info(resourceBunde.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				logger.info("updated visa : " + updatedVisa);
				return ResponseHandler.generateResponse(updatedVisa, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			logger.error("Failed update Visa : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}

}
