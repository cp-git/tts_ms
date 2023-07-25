/**
 * @author  - Code Generator
 * @createdOn -  24/07/2023
 * @Description Entity class for Country
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, Integer> {

	// This method retrieves a Country object based on the provided country code.
	public Country findByCountryCode(int countryCode);

	// This method retrieves a list of all countries.
	public List<Country> findAll();

	// This method deletes a country from the database based on the provided country
	// code
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM country WHERE code = ?", nativeQuery = true)
	public int deleteCountryByCode(int countryCode);

}
