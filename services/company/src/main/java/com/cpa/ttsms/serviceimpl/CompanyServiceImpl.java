/**
 * @author - Code Generator
 * @createdOn 24/07/2023
 * @Description Controller class for company
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.CompanyController;
import com.cpa.ttsms.entity.Company;
import com.cpa.ttsms.repository.CompanyRepo;
import com.cpa.ttsms.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepo companyRepo;
	private static Logger logger;

	public CompanyServiceImpl() {
		logger = Logger.getLogger(CompanyServiceImpl.class);
	}

	/**
	 * Create a new company in the repository.
	 * 
	 * @param : Company object containing the company details.
	 * @return : The newly created company object if successful, otherwise null.
	 */
	@Override
	public Company createCompany(Company company) {
		logger.debug("Entering createCompany");
		Company createdCompany = null;

		// Check if the company already exists in the repository.
		Company existingCompany = companyRepo.findByCompanyCode(company.getCompanyCode());

		if (existingCompany == null) {
			// If the company does not already exist, create it.
			createdCompany = companyRepo.save(company);
			logger.info("Company created successfully for code :" + company.getCompanyCode());
		} else {
			logger.warn("Company already exists with code :" + company.getCompanyCode());
		}

		return createdCompany;
	}

	/**
	 * 
	 * Retrieved a company in the repository
	 * 
	 * @param id - Integer value containing id of company.
	 * 
	 * @return The Retrieved company object if successful, otherwise null.
	 */
	@Override
	public Company getCompanyById(int id) {

		logger.info("Retrieving company with ID: " + id);

		Company company = null;
		// Call the companyRepository to retrieve the company by id
		company = companyRepo.findByCompanyId(id);

		if (company == null) {
			logger.warn("No company found with ID: " + id);
		} else {
			logger.info("Retrieved company: " + id);
		}

		return company;
	}

	/**
	 * Gets all companies in the system.
	 * 
	 * @return A list of all Company objects.
	 */
	@Override
	public List<Object> getAllCompanies() {
		logger.debug("Entering getAllCompanys");

		List<Company> companies = companyRepo.findAll();
		List<Object> objectCompanies = new ArrayList<>(companies);

		logger.info("Fetched all active company :" + objectCompanies);

		return objectCompanies;
	}

	/**
	 * Updates an existing company in the system.
	 * 
	 * @param companycode The code of the company to update.
	 * @param company     The updated Company object.
	 * @return The updated Company object.
	 */
	@Override
	public Company updateCompanyByCompanyCode(Company company, String companyCode) {
		logger.info("Updating company by companyCode : " + companyCode);

		// Check if the company exists in the database
		Company existingCompany = companyRepo.findByCompanyCode(companyCode);

		// If the company does not exist, return null
		if (existingCompany == null) {
			logger.warn("Company not found with Code: " + companyCode);
			return null;
		}
		// If the company exists, update their details and save to the database
		else {

			existingCompany.setCompanyCode(company.getCompanyCode());
			existingCompany.setCompanyName(company.getCompanyName());
			existingCompany.setCompanyContactEmail(company.getCompanyContactEmail());
			existingCompany.setCompanyContactPhone(company.getCompanyContactPhone());
			existingCompany.setCompanyAddress(company.getCompanyAddress());
			existingCompany.setCompanyZip(company.getCompanyZip());
			existingCompany.setCompanyCountryId(company.getCompanyCountryId());

			// Save the updated company to the database
			Company updatedCompany = companyRepo.save(existingCompany);

			logger.info("Company updated successfully");
			return updatedCompany;
		}
	}

	/**
	 * deletes the company with the given code
	 *
	 * @param companyCode The code of the company to delete.
	 * @return True if the company was successfully deleted, false otherwise.
	 */
	@Override
	public boolean deleteCompanyByCompanyCode(String companyCode) {

		// Log the deletion of company by id
		logger.info("Deleting company by code : " + companyCode);

		// Call the DeleteCompanyByCode method of companyRepository to delete the
		// company with the given Company
		int deletedCount = companyRepo.deleteByCompanyCode(companyCode);

		// If a company is successfully deleted, return true; otherwise, return false
		if (deletedCount > 0) {
			logger.info("Company has been deleted with code: " + companyCode);
			return true;
		} else {
			logger.warn("Company not found or could not be deleted with code : " + companyCode);
			return false;
		}
	}

}
