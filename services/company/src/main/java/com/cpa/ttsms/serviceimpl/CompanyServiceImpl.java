/**
 * @author - Code Generator
 * @createdOn 24/07/2023
 * @Description Controller class for company
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.cpa.ttsms.entity.Status;
import com.cpa.ttsms.repository.CompanyPhotosRepo;
import com.cpa.ttsms.repository.CompanyRepo;
import com.cpa.ttsms.repository.StatusRepo;
import com.cpa.ttsms.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	// private final String UPLOAD_FILE_URL =
	// "http://127.0.0.1:8080/uploadfile/ttsms/upload";

	// Inject the value of 'file.upload-url' from application.properties or
	// application.yml
	@Value("${file.upload-url}")
	private String UPLOAD_FILE_URL;

	// Inject the value of 'file.base-path' from application.yml file
	@Value("${file.base-path}")
	private String basePath;

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private StatusRepo statusRepo;

	@Autowired
	private CompanyPhotosRepo companyPhotosRepo;
	private static Logger logger;

	private final RestTemplate restTemplate;

	static {
		// for 127.0.0.1 testing only
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				if (hostname.equals("127.0.0.1")) {
					return true;
				}
				return false;
			}
		});
	}

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
		logger.info("created company : " + company.toString());

		// Insert the status records after successfully creating a company
		if (createdCompany != null) {
			insertStatusRecords(createdCompany.getCompanyId());
		}

		try {

			tempFile = File.createTempFile("temp", file.getOriginalFilename());
			logger.info("file : " + file.getOriginalFilename());
			file.transferTo(tempFile);
			logger.info("multipart file transfered to file object");
			// extracting extension from original file
			extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			logger.info("extension : " + extension);
			// modifying file name with company name and id with extesnion
			String modifiedFileName = company.getCompanyName() + "_" + company.getCompanyId() + extension;
			logger.info("modifiedFileName : " + modifiedFileName);
			// adding data of company photo in table
			CompanyPhotos companyPhotos = new CompanyPhotos(companyAndCompanyPhotosDTO.getPhotoId(),
					company.getCompanyId(), modifiedFileName);
			CompanyPhotos createdCompanyPhotoObject = companyPhotosRepo.save(companyPhotos);
			logger.info("createdCompanyPhotoObject : " + createdCompanyPhotoObject);
			// building form-data to pass in request for uploading file
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("filename", modifiedFileName);
			map.add("file", new FileSystemResource(tempFile));
			map.add("folder", "company/" + company.getCompanyName() + "_" + company.getCompanyId());
			logger.info("map : " + map);
			// seeting content type for header
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			logger.info("headers : " + headers);
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			logger.info("requestEntity : ");
			// calling api for uploading file
			ResponseEntity<String> response = restTemplate.postForEntity(UPLOAD_FILE_URL, requestEntity, String.class);
			logger.info("response : " + response.getStatusCode());
			if (response.getStatusCode() == HttpStatus.OK) {
				logger.info("inside  : " + response.getStatusCode());
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
	// @Override
	// public Company updateCompanyByCompanyCode(Company company, String
	// companyCode) {
	// logger.info("Updating company by companyCode : " + companyCode);
	//
	// // Check if the company exists in the database
	// Company existingCompany = companyRepo.findByCompanyCode(companyCode);
	//
	// // If the company does not exist, return null
	// if (existingCompany == null) {
	// logger.warn("Company not found with Code: " + companyCode);
	// return null;
	// }
	// // If the company exists, update their details and save to the database
	// else {
	//
	// existingCompany.setCompanyCode(company.getCompanyCode());
	// existingCompany.setCompanyName(company.getCompanyName());
	// existingCompany.setCompanyContactEmail(company.getCompanyContactEmail());
	// existingCompany.setCompanyContactPhone(company.getCompanyContactPhone());
	// existingCompany.setCompanyAddress(company.getCompanyAddress());
	// existingCompany.setCompanyZip(company.getCompanyZip());
	// existingCompany.setCompanyCountryId(company.getCompanyCountryId());
	//
	// // Save the updated company to the database
	// Company updatedCompany = companyRepo.save(existingCompany);
	//
	// logger.info("Company updated successfully");
	// return updatedCompany;
	// }
	// }

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

	/**
	 * Updates an existing company's information, including its logo, based on the
	 * company code.
	 *
	 * @param companyAndCompanyPhotosDTO The DTO containing updated company details
	 *                                   and photo information.
	 * @param companyCode                The code of the company to update.
	 * @param file                       The updated logo file for the company, or
	 *                                   null if not updating the logo.
	 * @return The updated Company and photo DTO object, or null if an error occurs.
	 */
	@Transactional
	@Override
	public CompanyAndCompanyPhotosDTO updateCompanyByCompanyCode(CompanyAndCompanyPhotosDTO companyAndCompanyPhotosDTO,
			String companyCode, MultipartFile file) {

		logger.debug("Entering updateCompanyByCompanyCode");

		// Check if the company with the given company code exists
		Company existingCompany = companyRepo.findByCompanyCode(companyCode);

		if (existingCompany == null) {
			logger.warn("Company not found with Code: " + companyCode);
			return null;
		}

		try {
			// Update the company details
			existingCompany.setCompanyCode(companyAndCompanyPhotosDTO.getCompanyCode());
			existingCompany.setCompanyName(companyAndCompanyPhotosDTO.getCompanyName());
			existingCompany.setCompanyContactEmail(companyAndCompanyPhotosDTO.getCompanyContactEmail());
			existingCompany.setCompanyContactPhone(companyAndCompanyPhotosDTO.getCompanyContactPhone());
			existingCompany.setCompanyAddress(companyAndCompanyPhotosDTO.getCompanyAddress());
			existingCompany.setCompanyZip(companyAndCompanyPhotosDTO.getCompanyZip());
			existingCompany.setCompanyCountryId(companyAndCompanyPhotosDTO.getCompanyCountryId());

			// Save the updated company
			Company updatedCompany = companyRepo.save(existingCompany);

			// Check if a file is provided for updating the photo
			if (file != null) {
				// Handle photo update logic here
				File tempFile = null;
				// Creating a temporary file to store the uploaded photo
				tempFile = File.createTempFile("temp", file.getOriginalFilename());
				file.transferTo(tempFile);

				CompanyPhotos existingCompanyPhotos = companyPhotosRepo.findByCompanyId(updatedCompany.getCompanyId());

				String folderName = "company/" + existingCompany.getCompanyName() + "_"
						+ existingCompany.getCompanyId();

				deleteFilesFromFolder(basePath + "/" + folderName);

				// Extracting the extension from the original file
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

				// Modifying the file name with the company name and ID with extension
				String modifiedFileName = updatedCompany.getCompanyName() + "_" + updatedCompany.getCompanyId()
						+ extension;

				if (existingCompanyPhotos != null) {
					// Update the photo details in the companyAndCompanyPhotosDTO
					existingCompanyPhotos.setFileName(modifiedFileName);
					// Set the companyPhotosId in the existingCompanyPhotos object
				}

				CompanyPhotos createdCompanyPhotoObject = companyPhotosRepo.save(existingCompanyPhotos);

				// Building form-data to pass in the request for uploading the file
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("filename", modifiedFileName);
				map.add("file", new FileSystemResource(tempFile));
				map.add("folder", folderName);

				// Setting content type for the header
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.MULTIPART_FORM_DATA);

				HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

				// Calling the API for uploading the file
				ResponseEntity<String> response = restTemplate.postForEntity(UPLOAD_FILE_URL, requestEntity,
						String.class);

				if (response.getStatusCode() != HttpStatus.OK) {
					logger.error("Error uploading data to remote microservice: " + response.getStatusCodeValue());

					return null;
				}
			}

			return companyAndCompanyPhotosDTO;
		} catch (Exception e) {
			logger.error("Error while updating company: " + e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Helper method to delete files from a folder.
	 *
	 * @param folderPath The path to the folder containing files to be deleted.
	 */
	private void deleteFilesFromFolder(String folderPath) {
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					file.delete();
				}
			}
		} else {
			System.out.println("Folder does not exist or is not a directory.");
		}
	}

	@Override
	public CompanyPhotos getPhotosByCompanyId(int companyId) {
		// TODO Auto-generated method stub
		return companyPhotosRepo.findByCompanyId(companyId);
	}

	private void insertStatusRecords(int companyId) {
		// Define the status codes and their corresponding status orders
		Map<String, Integer> statusOrderMap = new HashMap<>();
		statusOrderMap.put("Created", 1);
		statusOrderMap.put("In-progress", 2);
		statusOrderMap.put("Done", 3);
		statusOrderMap.put("Cancelled", 4);

		// You can insert the statuses using a loop or individually, depending on your
		// requirements.
		String[] statusCodes = { "Created", "In-progress", "Done", "Cancelled" };
		for (String statusCode : statusCodes) {
			// Create a new status record and set its properties
			Status status = new Status();
			status.setStatusCode(statusCode);
			status.setStatusDescription("Description for " + statusCode);
			status.setStatusOrder(statusOrderMap.get(statusCode)); // Set the status order
			status.setCompanyId(companyId);

			if (statusCode.equals("In-progress")) {
				status.setActualStartDate(true);
			} else if (statusCode.equals("Done") || statusCode.equals("Cancelled")) {
				status.setActualStartDate(true);
				status.setActualEndDate(true);
				status.setFinalStatus(true);
			}

			// Save the status record to the database
			statusRepo.save(status);
		}
	}

}
