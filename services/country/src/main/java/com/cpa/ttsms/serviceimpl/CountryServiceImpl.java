/**
 * @author - Code Generator
 * @createdOn 24/07/2023
 * @Description Controller class for country
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.CountryController;
import com.cpa.ttsms.entity.Country;
import com.cpa.ttsms.repository.CountryRepo;
import com.cpa.ttsms.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepo countryRepo;
	private static Logger logger;

	public CountryServiceImpl() {
		logger = Logger.getLogger(CountryServiceImpl.class);
	}

	/**
	 * Create a new country entry in the Country table.
	 *
	 * @param country - The Country object containing the details of the country to
	 *                be created.
	 *
	 * @return The newly created Country object if successful, otherwise null.
	 */
	@Override
	public Country createCountry(Country country) {
		logger.debug("Entering createCountry");
		Country createdCountry = null; // Initialize a variable to store the newly created country.
		createdCountry = countryRepo.save(country); // Save the country object to the database and get the created
													// entity.
		logger.info("Created Country: " + createdCountry); // Log the created country for information.

		return createdCountry; // Return the newly created country object.
	}

	/**
	 * Retrieve a country from the Country table based on the provided country code.
	 *
	 * @param countryCode - The unique country code used to search for the country.
	 *
	 * @return The found Country object if a match is found, otherwise null.
	 */
	@Override
	public Country getCountryByCountryCode(int countryCode) {
		logger.debug("Entering getCountryBycode");

		Country country = countryRepo.findByCountryCode(countryCode); // Retrieve the country from the database by
																		// country code.
		logger.info("Found Country: " + country);

		return country; // Return the found country object.
	}

	/**
	 * Fetch all active countries from the Country table.
	 *
	 * @return A List containing all the active Country objects.
	 */
	@Override
	public List<Object> getAllCountries() {
		logger.debug("Entering getAllCountries");

		List<Country> countries = countryRepo.findAll(); // Retrieve all active countries from the database.
		logger.info("Fetched all active countries: " + countries);

		// Create a new list to store objects
		List<Object> objects = new ArrayList<>(countries); // Convert the list of Country objects to a list of generic
															// Objects.

		logger.info("Fetched all active countries: " + countries);
		return objects; // Return the list of generic Objects.
	}

	/**
	 * Update an existing country record in the Country table.
	 *
	 * @param country - The Country object containing the updated country details.
	 * @param code    - The country code used to find the country to be updated.
	 *
	 * @return The updated Country object if the update is successful, otherwise
	 *         null.
	 */
	@Override
	public Country updateCountryByCountryCode(Country country, int code) {
		logger.debug("Entering updateCountry");

		Country toCountryAlreadyPresent = null; // Initialize a variable to store the existing country to be updated.
		Country updatedCountry = null; // Initialize a variable to store the updated country.

		toCountryAlreadyPresent = countryRepo.findByCountryCode(code); // Retrieve the existing country by country code.
		logger.info("Existing Country: " + toCountryAlreadyPresent);

		if (toCountryAlreadyPresent != null) { // Check if the existing country is found.
			logger.debug("Setting new data of Country to existing Country");

			// Update the existing country's data with the new data from the input country
			// object.
			toCountryAlreadyPresent.setCountryName(country.getCountryName());
			toCountryAlreadyPresent.setCountryCode(country.getCountryCode());

			updatedCountry = countryRepo.save(toCountryAlreadyPresent); // Save the updated country to the database.
			logger.info("Updated Country: " + updatedCountry);
		}

		return updatedCountry; // Return the updated country object if successful, otherwise null.
	}

	/**
	 * Soft delete a country record from the Country table.
	 *
	 * @param code - The country code used to find the country to be deleted.
	 *
	 * @return The count of records updated (usually 1 if successful).
	 */
	@Override
	public int deleteCountryByCountryCode(int code) {
		logger.debug("Entering deleteCountryBycode");

		int count = countryRepo.deleteCountryByCode(code); // Delete the country record by country code and get the
															// count of affected records.
		logger.info("Deleted Country count: " + count);

		return count; // Return the count of deleted records.
	}

}
