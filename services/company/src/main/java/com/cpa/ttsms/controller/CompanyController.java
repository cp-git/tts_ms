/**
 * @author - Code Generator
 * @createdOn 24/07/2023
 * @Description Controller class for company
 * 
 */

package com.cpa.ttsms.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import com.cpa.ttsms.dto.CompanyAndCompanyPhotosDTO;
import com.cpa.ttsms.entity.Company;
import com.cpa.ttsms.entity.CompanyPhotos;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.CompanyService;

@CrossOrigin
@RestController
@RequestMapping("/ttsms")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	

	// The ResourceBundle is used to retrieve localized messages.
	private ResourceBundle resourceBundle;

	// Inject the value of 'file.base-path' from application.yml file
	@Value("${file.base-path}")
	private String basePath;
	
	// The logger is used for logging messages related to this class.
	private static Logger logger;

	CompanyController() {
		resourceBundle = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(CompanyController.class);
	}

	/**
	 * Creates a new company along with company photos based on the provided
	 * CompanyAndCompanyPhotosDTO object and an accompanying file, and generates an
	 * appropriate response.
	 *
	 * @param companyAndCompanyPhotosDTO - The DTO containing company details and
	 *                                   photos.
	 * @param file                       - The MultipartFile containing company
	 *                                   photos.
	 *
	 * @return ResponseEntity containing a CREATED status if the company was
	 *         successfully created, otherwise returns a BAD_REQUEST response.
	 *
	 * @throws CPException If there is an error while creating the company or
	 *                     generating the response.
	 */
	@PostMapping("/company")
	public ResponseEntity<Object> createCompany(
			@RequestPart("company") CompanyAndCompanyPhotosDTO companyAndCompanyPhotosDTO,
			@RequestParam("file") MultipartFile file) throws CPException {
		// Log that a request to create a company has been received
		logger.info("Received request to create company ");

		try {
			// Call the companyService to create a company with the provided data
			CompanyAndCompanyPhotosDTO createdCompanyAndCompanyPhotosDTO = companyService
					.createCompany(companyAndCompanyPhotosDTO, file);

			if (createdCompanyAndCompanyPhotosDTO != null) {
				// Generate a CREATED response with a success message
				return ResponseHandler.generateResponse(createdCompanyAndCompanyPhotosDTO, HttpStatus.CREATED);
			} else {
				// Generate a BAD_REQUEST response with an error message for a failed operation
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Failed to create company.");
			}
		} catch (Exception e) {
			// Log any exceptions that occur during the creation process
			logger.error(resourceBundle.getString("err003"));

			// Generate an INTERNAL_SERVER_ERROR response with an error message
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
		}
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
//	@PutMapping("/company/{code}")
//	public ResponseEntity<Object> updateCompanyByCompanyCode(@RequestBody Company company,
//			@PathVariable("code") String companyCode) throws CPException {
//
//		logger.info("Updating company by code : " + companyCode);
//
//		Company updatedCompany = null;
//
//		try {
//			// Call the companyService to perform the update operation.
//			updatedCompany = companyService.updateCompanyByCompanyCode(company, companyCode);
//
//			if (updatedCompany == null) {
//				// If the company not exists, return an error response.
//				logger.info(resourceBundle.getString("err004"));
//				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
//			} else {
//				// Return the updated company.
//				logger.info("Company updated : " + updatedCompany);
//				return ResponseHandler.generateResponse(updatedCompany, HttpStatus.OK);
//			}
//
//		} catch (Exception ex) {
//			// If an exception occurs, log the error and throw an CPException with an error
//			// code and message.
//			logger.error("Failed update Company : " + ex.getMessage());
//			throw new CPException("err004", resourceBundle.getString("err004"));
//
//		}
//
//	}

	/**
	 * Updates an existing company's information, including its logo, based on the
	 * company code.
	 *
	 * This endpoint handles PUT requests to update a company's data and logo.
	 *
	 * @param companyAndCompanyPhotosDTO The DTO containing updated company details
	 *                                   and logo information.
	 * @param file                       The updated logo file for the company, or
	 *                                   null if not updating the logo.
	 * @param companyCode                The code of the company to update.
	 * @return ResponseEntity containing the updated Company and logo DTO or an
	 *         error response.
	 */
	@PutMapping("/company/{code}")
	public ResponseEntity<Object> updateCompanyByCompanyCode(
			@RequestPart("company") CompanyAndCompanyPhotosDTO companyAndCompanyPhotosDTO,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@PathVariable("code") String companyCode) throws CPException {

		logger.info("Updating company by code: " + companyCode);

		// Call the service to update the company
		CompanyAndCompanyPhotosDTO updatedCompanyDTO = companyService
				.updateCompanyByCompanyCode(companyAndCompanyPhotosDTO, companyCode, file);

		if (updatedCompanyDTO != null) {
			// Return a success response with the updated company data
			return ResponseHandler.generateResponse(updatedCompanyDTO, HttpStatus.OK);
		} else {
			// Return a not found response if the company is not found or an error occurs
			// during update
			return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err006");
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
	/**
     * Retrieves company photos by company ID.
     *
     * @param id The ID of the company for which you want to retrieve photos.
     * @param response HttpServletResponse to set the content type.
     * @return ResponseEntity containing the company photos as a byte array, or a NOT_FOUND response if no photos exist.
     */
    @GetMapping(value = "/company/photos/{id}")
    public ResponseEntity<byte[]> getCompanyPhotosByCompanyId(@PathVariable("id") int id, HttpServletResponse response) {
        try {
        	Company myFile;
   		 myFile =companyService.getCompanyByCompanyId(id);
           System.out.println("-------------------------"+myFile);
           
           CompanyPhotos filename;
           filename=companyService.getPhotosByCompanyId(id);
           System.out.println("***********"+filename);
           
           String companyNameAndId=myFile.getCompanyName()+"_"+myFile.getCompanyId();
   		System.out.println("//////////////////////////"+companyNameAndId);
   		
   		String photoFilename=filename.getFileName();
   		System.out.println("/////////////*******"+photoFilename);
            // Retrieve the company photos by company ID from the database or any other data source
            // For this example, we assume the photos are stored in a directory on the server
            String folderPath = basePath+"/company/"+companyNameAndId+ "/"+photoFilename; // Replace with the actual folder path
            System.out.println(folderPath);
           //String fileName = "company_" + id + ".jpg"; // Modify the filename format if needed

            // Create a Resource object from the file
            Resource resource = new FileSystemResource(folderPath);

            if (resource.exists()) {
                // Determine the media type (MIME type) of the file
                String contentType = Files.probeContentType(resource.getFile().toPath());

                // Set the content type in the response
                response.setContentType(contentType);

                // Read the file content into a byte array
                byte[] photoBytes = Files.readAllBytes(resource.getFile().toPath());

                // Create a ResponseEntity with the photo bytes and a 200 OK status
                return ResponseEntity.ok(photoBytes);
            } else {
                // Return a 404 NOT_FOUND response if the photo does not exist
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            // Handle any exceptions that may occur during file retrieval
            e.printStackTrace();
            // Return an INTERNAL_SERVER_ERROR response in case of an error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    @GetMapping("/company/companyPhotos/{companyId}")
    public CompanyPhotos getPhotosByCompanyId(@PathVariable int companyId) {
        return companyService.getPhotosByCompanyId(companyId);
    }

}
