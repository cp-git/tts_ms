/**
 * @author  - Code Generator
 * @createdOn -  24/07/2023
 * @Description Entity class for Company Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.Company;

public interface CompanyService {

	/**
	 * Creates a new company in the system.
	 * 
	 * @param company The Company object containing the company details.
	 * @return The created Company object.
	 */
	Company createCompany(Company company);

	/**
	 * Gets a company by their code.
	 * 
	 * @param companyid The id of the company to get.
	 * @return The Company object corresponding to the provided code.
	 */
	Company getCompanyById(int id);

	/**
	 * Gets all companies in the system.
	 * 
	 * @return A list of all Company objects.
	 */
	List<Object> getAllCompanies();

	/**
	 * Updates an existing company in the system.
	 * 
	 * @param companycode The code of the company to update.
	 * @param company     The updated Company object.
	 * @return The updated Company object.
	 */
	Company updateCompanyByCompanyCode(Company company, String companyCode);

	/**
	 * Deletes a company by their code.
	 * 
	 * @param companycode The code of the company to delete.
	 * @return The number of rows affected by the delete operation.
	 */
	boolean deleteCompanyByCompanyCode(String companyCode);

}