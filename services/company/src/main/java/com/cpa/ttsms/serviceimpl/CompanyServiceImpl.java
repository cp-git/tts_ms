/**
 * @author - Code Generator
 * @createdOn 24/07/2023
 * @Description Controller class for company
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.cpa.ttsms.dto.CompanyAndCompanyPhotosDTO;
//import com.cpa.ttsms.controller.CompanyController;
import com.cpa.ttsms.entity.Company;
import com.cpa.ttsms.entity.CompanyPhotos;
import com.cpa.ttsms.repository.CompanyPhotosRepo;
import com.cpa.ttsms.repository.CompanyRepo;
import com.cpa.ttsms.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final String UPLOAD_FILE_URL = "http://localhost:8090/uploadfile/ttsms/upload";

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private CompanyPhotosRepo companyPhotosRepo;
	private static Logger logger;

	private final RestTemplate restTemplate;

	public CompanyServiceImpl(RestTemplate restTemplate) {
		logger = Logger.getLogger(CompanyServiceImpl.class);
		this.restTemplate = restTemplate;
	}

	/**
	 * Create a new company in the repository.
	 * 
	 * @param : Company object containing the company details.
	 * @return : The newly created company object if successful, otherwise null.
	 */
	@Transactional
	@Override
	public CompanyAndCompanyPhotosDTO createCompany(CompanyAndCompanyPhotosDTO companyAndCompanyPhotosDTO,
			MultipartFile file) {

		logger.debug("Entering createCompany");

		Company createdCompany = null;
		File tempFile = null;
		String extension = null;

		// creating company object to insert data in table
		Company company = new Company(companyAndCompanyPhotosDTO.getCompanyId(),
				companyAndCompanyPhotosDTO.getCompanyCode(), companyAndCompanyPhotosDTO.getCompanyName(),
				companyAndCompanyPhotosDTO.getCompanyContactEmail(),
				companyAndCompanyPhotosDTO.getCompanyContactPhone(), companyAndCompanyPhotosDTO.getCompanyAddress(),
				companyAndCompanyPhotosDTO.getCompanyZip(), companyAndCompanyPhotosDTO.getCompanyCountryId());

		createdCompany = companyRepo.save(company);

		try {

			tempFile = File.createTempFile("temp", file.getOriginalFilename());
			file.transferTo(tempFile);

			// extracting extension from original file
			extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

			// modifying file name with company name and id with extesnion
			String modifiedFileName = company.getCompanyName() + "_" + company.getCompanyId() + extension;

			// adding data of company photo in table
			CompanyPhotos companyPhotos = new CompanyPhotos(companyAndCompanyPhotosDTO.getPhotoId(),
					company.getCompanyId(), modifiedFileName);
			CompanyPhotos createdCompanyPhotoObject = companyPhotosRepo.save(companyPhotos);

			// building form-data to pass in request for uploading file
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("filename", modifiedFileName);
			map.add("file", new FileSystemResource(tempFile));
			map.add("folder", "company/" + company.getCompanyName() + "_" + company.getCompanyId());

			// seeting content type for header
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

			// calling api for uploading file
			ResponseEntity<String> response = restTemplate.postForEntity(UPLOAD_FILE_URL, requestEntity, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {

				// setting created ids to dto s
				companyAndCompanyPhotosDTO.setPhotoId(createdCompanyPhotoObject.getCompanyPhotosId());
				companyAndCompanyPhotosDTO.setCompanyId(createdCompany.getCompanyId());

				return companyAndCompanyPhotosDTO;
			} else {

				logger.error("Error uploading data to remote microservice: " + response.getStatusCodeValue());
				return null;
			}
		} catch (Exception e) {

			logger.error("Error while processing data: " + e.getMessage(), e);
			return null;
		}
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
	public Company getCompanyByCompanyId(int id) {

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
