/**
 * @author - Code Generator
 * @createdOn 24/07/2023
 * @Description Controller class for country
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

import com.cpa.ttsms.entity.Country;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.CountryService;

@RestController
@RequestMapping("/ttsms")
@CrossOrigin
public class CountryController {

	@Autowired
	private CountryService countryService;;

	private ResourceBundle resourceBundle;
	private static Logger logger;

	CountryController() {
		resourceBundle = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(CountryController.class);
	}

	/**
	 * Create a new country.
	 *
	 * @param country The country to create.
	 * @return The newly created country.
	 * @throws CPException If there was an error creating the country.
	 */
	@PostMapping("/country")
	public ResponseEntity<Object> createCountry(@RequestBody Country country) throws CPException {
		logger.debug("Entering createCountry");
		logger.info("Data of creating Country: " + country.toString());

		Country createdCountry = null;
		try {
			// Check if the country with the provided country code already exists in the
			// database.
			Country toCheckCountry = countryService.getCountryByCountryCode(country.getCountryCode());
			logger.debug("Existing country: " + toCheckCountry);

			if (toCheckCountry == null) {
				// If the country does not exist, create the country and return the created
				// country in the response.
				createdCountry = countryService.createCountry(country);
				logger.info("Country created: " + createdCountry);
				return ResponseHandler.generateResponse(createdCountry, HttpStatus.CREATED);
			} else {
				// If the country already exists, return an error response.
				logger.error("err003");
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed Country creation: " + ex.getMessage());
			throw new CPException("err003", "Error while creating country");
		}
	}
	
	

	/**
	 * Retrieve a country by its unique country code.
	 *
	 * @param countryCode The unique country code used to search for the country.
	 * @return The found Country object if a match is found, otherwise null.
	 * @throws CPException If there was an error retrieving the country.
	 */
	@GetMapping("/country/{code}")
	public ResponseEntity<Object> getCountryBycode(@PathVariable("code") int countryCode) throws CPException {
		logger.debug("Entering getCountryBycode");
		logger.info("Entered country code: " + countryCode);

		Country country = null;
		try {
			// Retrieve the country by country code from the service layer.
			country = countryService.getCountryByCountryCode(countryCode);
			logger.info("Fetched Country: " + country);

			if (country != null) {
				// If the country is found, generate a success response with the found country.
				logger.debug("Country fetched, generating response");
				return ResponseHandler.generateResponse(country, HttpStatus.OK);
			} else {
				// If the country is not found, generate an error response.
				logger.debug("Country not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting country: " + ex.getMessage());
			throw new CPException("err001", "Error while retrieving country");
		}
	}

	/**
	 * Fetch all active countries from the Country table.
	 *
	 * @return A list containing all the active Country objects.
	 * @throws CPException If there was an error retrieving the countries.
	 */
	@GetMapping("/country/allcountry")
	public ResponseEntity<List<Object>> getAllCountries() throws CPException {
		logger.debug("Entering getAllCountry");

		List<Object> countries = null;
		try {
			// Retrieve all active countries from the service layer.
			countries = countryService.getAllCountries();

			if (countries != null && !countries.isEmpty()) {
				// If active countries are found, generate a success response with the list of
				// countries.
				logger.info("Fetched all Country: " + countries);
				return ResponseHandler.generateListResponse(countries, HttpStatus.OK);
			} else {
				// If no active countries are found, generate an error response.
				logger.info(resourceBundle.getString("err002"));
				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting all countries: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all countries");
		}
	}

	/**
	 * Delete a country by its unique country code.
	 *
	 * @param countryCode The unique country code used to find the country to be
	 *                    deleted.
	 * @return A response entity with no content if the deletion is successful.
	 * @throws CPException If there was an error deleting the country.
	 */
	@DeleteMapping("/country/{code}")
	public ResponseEntity<Object> deleteCountryBycode(@PathVariable("code") int countryCode) throws CPException {
		logger.debug("Entering deleteCountryBycode");
		logger.info("Entered deleteCountry: " + countryCode);

		int count = 0;
		try {
			// Delete the country by country code in the service layer and get the count of
			// deleted records.
			count = countryService.deleteCountryByCountryCode(countryCode);

			if (count >= 1) {
				// If deletion is successful (at least one record deleted), generate a success
				// response with no content.
				logger.info("Deleted Country: code = " + countryCode);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				// If no records are deleted, generate an error response.
				logger.info(resourceBundle.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed to delete Country: " + ex.getMessage());
			throw new CPException("err005", "Error while deleting country");
		}
	}

	/**
	 * Update an existing country record in the Country table.
	 *
	 * @param country The updated country details.
	 * @param code    The unique country code used to find the country to be
	 *                updated.
	 * @return The updated Country object if the update is successful, otherwise an
	 *         error response.
	 * @throws CPException If there was an error updating the country.
	 */
	@PutMapping("/country/{code}")
	public ResponseEntity<Object> updateCountryBycode(@RequestBody Country country, @PathVariable("code") int code)
			throws CPException {
		logger.debug("Entering updateCountry");
		logger.info("Entered  updateCountry :" + country);

		Country updatedCountry = null;
		try {
			// Update the country by country code in the service layer.
			updatedCountry = countryService.updateCountryByCountryCode(country, code);

			if (updatedCountry == null) {
				// If update is unsuccessful, generate an error response.
				logger.info(resourceBundle.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				// If update is successful, generate a success response with the updated
				// country.
				logger.info("Updated Country: " + updatedCountry);
				return ResponseHandler.generateResponse(updatedCountry, HttpStatus.CREATED);
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed update Country: " + ex.getMessage());
			throw new CPException("err004", "Error while updating country");
		}
	}
}
