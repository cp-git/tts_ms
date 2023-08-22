/**
 * @author - Code Generator
 * @createdOn 24/07/2023
 * @Description Controller class for company
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
import org.springframework.web.multipart.MultipartFile;

import com.cpa.ttsms.dto.CompanyAndCompanyPhotosDTO;
import com.cpa.ttsms.entity.Company;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/ttsms")
public class CompanyController {

	@Autowired
	private CompanyService companyService;;

	// The ResourceBundle is used to retrieve localized messages.
	private ResourceBundle resourceBundle;

	// The logger is used for logging messages related to this class.
	private static Logger logger;

	CompanyController() {
		resourceBundle = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(CompanyController.class);
	}

	/**
	 * Create a new company.
	 * 
	 * @param company The company to create.
	 * @return The newly created company.
	 * @throws CPException             If there was an error creating the company.
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@PostMapping("/company")
	public ResponseEntity<Object> createCompany(@RequestParam("company") String companyData,
			@RequestParam("file") MultipartFile file) throws CPException {
		logger.info("Received request to create company ");
		System.out.println(file.getOriginalFilename());
		// for convertin data into json object
		ObjectMapper objectMapper = new ObjectMapper();

		CompanyAndCompanyPhotosDTO companyAndCompanyPhotosDTO = null;
		CompanyAndCompanyPhotosDTO createCompanyAndCompanyPhotosDTO = null;

		try {

			// converting string into object
			companyAndCompanyPhotosDTO = objectMapper.readValue(companyData, CompanyAndCompanyPhotosDTO.class);

			if (companyAndCompanyPhotosDTO != null) {

				// If the company doesn't exist, create it.
				createCompanyAndCompanyPhotosDTO = companyService.createCompany(companyAndCompanyPhotosDTO, file);

				if (createCompanyAndCompanyPhotosDTO != null) {
					// Return the updated companyDTO.
					return ResponseHandler.generateResponse(createCompanyAndCompanyPhotosDTO, HttpStatus.CREATED);
				}
			}
		} catch (Exception ex) {
			// Log the error and throw an CPException with an error code and message.
			logger.error("Failed to create company: " + ex.getMessage());
			throw new CPException("err003", resourceBundle.getString("err003"));
		}
		return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
	}

	/**
	 * Retrieves a company by their ID.
	 *
	 * @param id The ID of the company to retrieve.
	 * @return The company with the specified Id, or a 404 Not Found response if no
	 *         such company exists.
	 * @throws CPException
	 */
	@GetMapping("/company/{id}")
	public ResponseEntity<Object> getCompanyByCompanyId(@PathVariable("id") int id) throws CPException {
		logger.info("Received request to retrieve company with ID: " + id);

		try {

			Company company = companyService.getCompanyByCompanyId(id);
			if (company == null) {
				logger.warn("No company found with ID: " + id);

				// If the company not found/ exists, return an error response.
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			} else {
				logger.info("Retrieved company with ID: " + id);

				// returns retrieved company.
				return ResponseHandler.generateResponse(company, HttpStatus.OK);
			}

		} catch (Exception ex) {
			// Log the error and throw an CPException with an error code and message.
			logger.error("Failed to create company: " + ex.getMessage());
			throw new CPException("err001", resourceBundle.getString("err001"));
		}
	}

	/**
	 * deletes a company by company code.
	 * 
	 * @param companyCode The code of the company to be deleted.
	 * @return ResponseEntity with NO_CONTENT status code on successful deletion or
	 *         INTERNAL_SERVER_ERROR on failure.
	 * @throws CPException if an error occurs while performing the operation.
	 */
	@DeleteMapping("/company/{code}")
	public ResponseEntity<Object> deleteCompanyByCompanyCode(@PathVariable("code") String companyCode)
			throws CPException {
		logger.info("Deleting company by companyCode : " + companyCode);

		boolean success = false;

		try {

			// Call the companyService to perform the soft delete operation.
			success = companyService.deleteCompanyByCompanyCode(companyCode);
			if (success) {
				// Return a response with NO_CONTENT status code if the operation is successful.
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				// Return a response with INTERNAL_SERVER_ERROR status code if the operation
				// fails.
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			// If an exception occurs, log the error and throw an CPException with an error
			// code and message.
			logger.error("Failed to delete company : " + ex.getMessage());
			throw new CPException("err005", resourceBundle.getString("err005"));
		}

	}

	/**
	 * Update a company by their code.
	 * 
	 * @param companyCode the code of the company to update
	 * @param company     the updated company object
	 * @return the updated company object, or null if the company was not found
	 */
	@PutMapping("/company/{code}")
	public ResponseEntity<Object> updateCompanyByCompanyCode(@RequestBody Company company,
			@PathVariable("code") String companyCode) throws CPException {

		logger.info("Updating company by code : " + companyCode);

		Company updatedCompany = null;

		try {
			// Call the companyService to perform the update operation.
			updatedCompany = companyService.updateCompanyByCompanyCode(company, companyCode);

			if (updatedCompany == null) {
				// If the company not exists, return an error response.
				logger.info(resourceBundle.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				// Return the updated company.
				logger.info("Company updated : " + updatedCompany);
				return ResponseHandler.generateResponse(updatedCompany, HttpStatus.OK);
			}

		} catch (Exception ex) {
			// If an exception occurs, log the error and throw an CPException with an error
			// code and message.
			logger.error("Failed update Company : " + ex.getMessage());
			throw new CPException("err004", resourceBundle.getString("err004"));

		}

	}

	/**
	 * 
	 * This method handles the GET request to retrieve all companies
	 * 
	 * @throws CPException If an error occurs during the operation, an CPException
	 *                     with an error code and message is thrown
	 */
	@GetMapping("/company/all")
	public ResponseEntity<List<Object>> getAllCompany() throws CPException {
		logger.info("Getting all companies");
		List<Object> companies = null;

		try {

			// Call the companyService to retrieve all companies
			companies = companyService.getAllCompanies();

			// If no companies are found, return a response with a warning status and error
			// code
			if (companies.isEmpty()) {
				logger.warn("No companies found");
				return ResponseHandler.generateListResponse(HttpStatus.NO_CONTENT, "err002");
			} else {
				// Log the number of companies being returned and return them with a success
				// status
				logger.info("Returning companies: " + companies.size());
				return ResponseHandler.generateListResponse(companies, HttpStatus.OK);
			}

		} catch (Exception ex) {
			// If an exception occurs, log the error and throw an CPException with an error
			// code and message.
			logger.error("Failed to get all companies: " + ex.getMessage());
			throw new CPException("err002", resourceBundle.getString("err002"));
		}
	}

}
