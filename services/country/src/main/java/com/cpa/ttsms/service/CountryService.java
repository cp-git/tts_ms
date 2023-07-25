/**
 * @author  - Code Generator
 * @createdOn -  24/07/2023
 * @Description Entity class for Country Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.Country;

public interface CountryService {

	// Creates a new country record in the database.
	public Country createCountry(Country country);

	// Retrieves a country by its unique country code.
	public Country getCountryByCountryCode(int countryCode);

	// Retrieves a list of all countries in the database. The returned list may
	// contain generic Objects.
	public List<Object> getAllCountries();

	// Updates an existing country record in the database based on the provided
	// country code.
	// The updated country information is contained in the 'country' parameter.
	public Country updateCountryByCountryCode(Country country, int countryCode);

	// Deletes a country from the database using its unique country code.
	public int deleteCountryByCountryCode(int countryCode);

}