/**
 * @author - Code Generator
 * @createdOn 17/01/2024
 * @Description Controller class for hiringCompany
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

import com.cpa.ttsms.entity.HiringCompany;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.HiringCompanyService;

@RestController
@RequestMapping("/ttsms/hiringcompany")
@CrossOrigin
public class HiringCompanyController {

	@Autowired
	private HiringCompanyService hiringCompanyService;;

	private ResourceBundle resourceBunde;

	private static Logger logger;

	HiringCompanyController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(HiringCompanyController.class);
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createHiringCompany(@RequestBody HiringCompany hiringCompany) throws CPException {
		logger.debug("Entering createHiringCompany");
		logger.info("data of creating HiringCompany  :" + hiringCompany.toString());

		HiringCompany createdHiringCompany = null;
		try {

			HiringCompany toCheckHiringCompany = hiringCompanyService
					.getHiringCompanyByHiringCompanyId(hiringCompany.getHiringCompanyId());
			logger.debug("existing hiringCompany :" + toCheckHiringCompany);

			if (toCheckHiringCompany == null) {

				createdHiringCompany = hiringCompanyService.createHiringCompany(hiringCompany);
				logger.info("HiringCompany created :" + createdHiringCompany);

				return ResponseHandler.generateResponse(createdHiringCompany, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed HiringCompany creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/{hiringCompanyId}")
	public ResponseEntity<Object> getHiringCompanyByHiringCompanyId(
			@PathVariable("hiringCompanyId") int hiringCompanyId) throws CPException {
		logger.debug("Entering getHiringCompanyByhiringCompanyId");
		logger.info("entered user name :" + hiringCompanyId);

		HiringCompany hiringCompany = null;

		try {

			hiringCompany = hiringCompanyService.getHiringCompanyByHiringCompanyId(hiringCompanyId);
			logger.info("fetched HiringCompany :" + hiringCompany);

			if (hiringCompany != null) {
				logger.debug("HiringCompany fetched generating response");
				return ResponseHandler.generateResponse(hiringCompany, HttpStatus.OK);
			} else {
				logger.debug("HiringCompany not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {

			logger.error("Failed getting hiringCompany : " + ex.getMessage());
			throw new CPException("err001", resourceBunde.getString("err001"));
		}

	}

	@GetMapping("/all")
	public ResponseEntity<List<Object>> getAllHiringCompanys(@RequestParam(name = "companyId") int companyId)
			throws CPException {
		logger.debug("Entering getAllHiringCompany");
		logger.info("Parameter  :" + companyId);

		List<Object> hiringCompanys = null;

		try {

			if (companyId > 0) {

				hiringCompanys = hiringCompanyService.getAllHiringCompanysByCompanyId(companyId);
				logger.info("Fetched all HiringCompany :" + hiringCompanys);

				return ResponseHandler.generateListResponse(hiringCompanys, HttpStatus.OK);
			} else {

				logger.info(resourceBunde.getString("err002"));
				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}

		} catch (Exception ex) {

			logger.error("Failed getting all hiringCompanys : " + ex.getMessage());
			throw new CPException("err002", resourceBunde.getString("err002"));

		}
	}

	@DeleteMapping("/{hiringCompanyId}")
	public ResponseEntity<Object> deleteHiringCompanyByHiringCompanyId(
			@PathVariable("hiringCompanyId") int hiringCompanyId) throws CPException {
		logger.debug("Entering deleteAuthUser");
		logger.info("entered deleteHiringCompany  :" + hiringCompanyId);
		// TODO - implement the business logic

		int count = 0;

		try {
			count = hiringCompanyService.deleteHiringCompanyByHiringCompanyId(hiringCompanyId);
			if (count >= 1) {
				logger.info("deleted HiringCompany : HiringCompanyId = " + hiringCompanyId);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				logger.info(resourceBunde.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			logger.error("Failed to delete HiringCompany :" + ex.getMessage());
			throw new CPException("err005", resourceBunde.getString("err005"));
		}

	}

	@PutMapping("/{hiringCompanyId}")
	public ResponseEntity<Object> updateHiringCompanyByHiringCompanyId(@RequestBody HiringCompany hiringCompany,
			@PathVariable("hiringCompanyId") int hiringCompanyId) throws CPException {
		logger.debug("Entering updateHiringCompany");
		logger.info("entered  updateHiringCompany :" + hiringCompany);

		HiringCompany updatedHiringCompany = null;

		try {
			updatedHiringCompany = hiringCompanyService.updateHiringCompanyByHiringCompanyId(hiringCompany,
					hiringCompanyId);

			if (updatedHiringCompany == null) {
				logger.info(resourceBunde.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				logger.info("updated hiringCompany : " + updatedHiringCompany);
				return ResponseHandler.generateResponse(updatedHiringCompany, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			logger.error("Failed update HiringCompany : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}

}
