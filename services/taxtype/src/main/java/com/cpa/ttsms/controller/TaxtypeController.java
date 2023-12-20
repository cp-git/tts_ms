/**
 * @author - Code Generator
 * @createdOn 18/12/2023
 * @Description Controller class for taxtype
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
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.Taxtype;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.TaxtypeService;

@RestController
@RequestMapping("/ttsms")
@CrossOrigin
public class TaxtypeController {

	@Autowired
	private TaxtypeService taxtypeService;;

	private ResourceBundle resourceBunde;
	private static Logger logger;

	TaxtypeController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(TaxtypeController.class);
	}

	@PostMapping("/taxtype")
	public ResponseEntity<Object> createTaxtype(@RequestBody Taxtype taxtype) throws CPException {
		logger.debug("Entering createTaxtype");
		logger.info("data of creating Taxtype  :" + taxtype.toString());

		Taxtype createdTaxtype = null;
		try {

			Taxtype toCheckTaxtype = taxtypeService.getTaxtypeBytaxtypeid(taxtype.getTaxTypeId());
			logger.debug("existing taxtype :" + toCheckTaxtype);

			if (toCheckTaxtype == null) {

				createdTaxtype = taxtypeService.createTaxtype(taxtype);
				logger.info("Taxtype created :" + createdTaxtype);

				return ResponseHandler.generateResponse(createdTaxtype, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed Taxtype creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/taxtype/{taxtypeid}")
	public ResponseEntity<Object> getTaxtypeBytaxtypeid(@PathVariable("taxtypeid") int taxtypeid) throws CPException {
		logger.debug("Entering getTaxtypeBytaxtypeid");
		logger.info("entered user name :" + taxtypeid);

		Taxtype taxtype = null;

		try {

			taxtype = taxtypeService.getTaxtypeBytaxtypeid(taxtypeid);
			logger.info("fetched Taxtype :" + taxtype);

			if (taxtype != null) {
				logger.debug("Taxtype fetched generating response");
				return ResponseHandler.generateResponse(taxtype, HttpStatus.OK);
			} else {
				logger.debug("Taxtype not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {

			logger.error("Failed getting taxtype : " + ex.getMessage());
			throw new CPException("err001", resourceBunde.getString("err001"));
		}

	}

	@GetMapping("/taxtypes")
	public ResponseEntity<List<Object>> getAllTaxtypes() throws CPException {
		logger.debug("Entering getAllTaxtype");
		// logger.info("Parameter :" + taxtypeid);

		List<Object> taxtypes = null;

		try {
			taxtypes = taxtypeService.getAllTaxtypes();

			if (taxtypes != null && !taxtypes.isEmpty()) {

				logger.info("Fetched all Visas: " + taxtypes);
				return ResponseHandler.generateListResponse(taxtypes, HttpStatus.OK);
			} else {

				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting all countries: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all countries");
		}
	}

	@DeleteMapping("/taxtype/{taxtypeid}")
	public ResponseEntity<Object> deleteTaxtypeBytaxtypeid(@PathVariable("taxtypeid") int taxtypeid)
			throws CPException {
		logger.debug("Entering deleteAuthUser");
		logger.info("entered deleteTaxtype  :" + taxtypeid);
		// TODO - implement the business logic

		int count = 0;

		try {
			count = taxtypeService.deleteTaxtypeBytaxtypeid(taxtypeid);
			if (count >= 1) {
				logger.info("deleted Taxtype : taxtypeid = " + taxtypeid);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				logger.info(resourceBunde.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			logger.error("Failed to delete Taxtype :" + ex.getMessage());
			throw new CPException("err005", resourceBunde.getString("err005"));
		}

	}

	@GetMapping("/taxtypes/{companyId}")
	public ResponseEntity<List<Object>> getAllTaxTypeByCompanyId(@PathVariable("companyId") int companyId)
			throws CPException {
		logger.debug("Entering getAllTaxtype");
		// logger.info("Parameter :" + taxtypeid);

		List<Object> taxtypes = null;

		try {
			taxtypes = taxtypeService.getAllTaxtypeByCompanyId(companyId);

			if (taxtypes != null && !taxtypes.isEmpty()) {

				logger.info("Fetched all Visas: " + taxtypes);
				return ResponseHandler.generateListResponse(taxtypes, HttpStatus.OK);
			} else {

				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting all portals: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all portals");
		}
	}

	@PutMapping("/taxtype/{taxtypeid}")
	public ResponseEntity<Object> updateTaxtypeBytaxtypeid(@RequestBody Taxtype taxtype,
			@PathVariable("taxtypeid") int taxtypeid) throws CPException {
		logger.debug("Entering updateTaxtype");
		logger.info("entered  updateTaxtype :" + taxtype);

		Taxtype updatedTaxtype = null;

		try {
			updatedTaxtype = taxtypeService.updateTaxtypeBytaxtypeid(taxtype, taxtypeid);

			if (updatedTaxtype == null) {
				logger.info(resourceBunde.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				logger.info("updated taxtype : " + updatedTaxtype);
				return ResponseHandler.generateResponse(updatedTaxtype, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			logger.error("Failed update Taxtype : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}

}
