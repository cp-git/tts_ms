/**
 * @author - Code Generator
 * @createdOn 25/07/2023
 * @Description Controller class for employee
 * 
 */

package com.cpa.ttsms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.cpa.ttsms.dto.EmployeeAndEmployeePhotosDTO;
import com.cpa.ttsms.dto.EmployeeAndPasswordDTO;
import com.cpa.ttsms.dto.EmployeePasswordAndEmployeePhotosDTO;
import com.cpa.ttsms.dto.JwtResponse;
import com.cpa.ttsms.entity.Employee;
import com.cpa.ttsms.entity.EmployeePhotos;
import com.cpa.ttsms.entity.Password;
import com.cpa.ttsms.entity.RefreshToken;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.EmployeeService;
import com.cpa.ttsms.service.TokenHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;



@CrossOrigin
@RestController
@RequestMapping("/ttsms")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;;
	
	private final RestTemplate restTemplate;

	private ResourceBundle resourceBundle;
	

	private static Logger logger;
	 // Define base URL as a private static final String variable
    private static final String BASE_URL = "https://127.0.0.1:8443/security/";


	 

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
		


	
	    public EmployeeController( RestTemplate restTemplate) {

	      
	        System.out.println("Entered in Controller class");

	        resourceBundle = ResourceBundle.getBundle("ErrorMessage", Locale.US);
			logger = Logger.getLogger(EmployeeController.class);
			this.restTemplate = restTemplate;
	    }

	// Inject the value of 'file.base-path' from application.yml file
	@Value("${file.base-path}")
	private String basePath;


	
	


private String callCheckToken(String authHeader) throws ExpiredJwtException{
    try {
        // Extract the token from the Authorization header.
        String token = authHeader.substring(7);

        // Set the authorization header with the token.
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity with headers.
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Specify the complete URL for the checkToken endpoint using the base URL variable.
        String checkTokenUrl = BASE_URL + "token/checkToken";

        // Make the HTTP GET request to the checkToken endpoint.
        ResponseEntity<String> response = restTemplate.exchange(
                checkTokenUrl, HttpMethod.GET, entity, String.class);

        // Return the response body.
        return response.getBody();
    } catch (ExpiredJwtException ex) {
        // Handle expired token exception
        System.out.println("JWT Token expired: " + ex.getMessage());
        // Perform actions like refreshing token or redirecting to login page
        return null; // Or any appropriate action
    } catch (Exception ex) {
        // Handle other exceptions
        System.out.println("Exception occurred while checking token: " + ex.getMessage());
        return null; // Or any appropriate action
    }
}
	
	/**
	 * Creates a new employee along with their password information based on the
	 * provided EmployeePasswordAndEmployeePhotosDTO object and an accompanying
	 * file, and generates an appropriate response.
	 *
	 * @param employeePasswordAndEmployeePhotosDTO - The DTO containing the
	 *                                             employee's details and password
	 *                                             information.
	 * @param file                                 - The MultipartFile containing
	 *                                             employee photos.
	 *
	 * @return ResponseEntity containing a CREATED status if the employee was
	 *         successfully created, otherwise returns a BAD_REQUEST response.
	 *
	 * @throws CPException If there is an error while creating the employee or
	 *                     generating the response.
	 */
	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployee(
			@RequestPart("employee") EmployeePasswordAndEmployeePhotosDTO employeePasswordAndEmployeePhotosDTO,
			@RequestParam("file") MultipartFile file,@RequestHeader("Authorization") String authHeader) throws CPException {
		// Log the entry of the method
		logger.debug("Entering createEmployee");

		// Log information about the data received for creating an employee
		logger.info("Data of creating Employee: " + employeePasswordAndEmployeePhotosDTO.toString());

		try {
			callCheckToken(authHeader);
			// Call the employeeService to create an employee with the provided data
			EmployeePasswordAndEmployeePhotosDTO createdDTO = employeeService
					.createEmployee(employeePasswordAndEmployeePhotosDTO, file);

			// Log information about the created employee and password
			logger.info("Employee and password created: " + createdDTO);

			if (createdDTO != null) {
				// Generate a CREATED response with a success message
				return ResponseHandler.generateResponse(createdDTO, HttpStatus.CREATED);
			} else {
				// Generate a BAD_REQUEST response with an error message for a failed operation
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Failed to create employee.");
			}
		} catch (Exception e) {
			// Log any exceptions that occur during the creation process
			logger.error(resourceBundle.getString("err003"));

			// Generate an INTERNAL_SERVER_ERROR response with an error message
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
		}
	}

	/**
	 * Retrieves an EmployeeAndPasswordDTO object containing employee and password
	 * information based on the provided employeeId, and generates an appropriate
	 * response.
	 *
	 * @param employeeId - The ID of the employee to retrieve.
	 *
	 * @return ResponseEntity containing the EmployeeAndPasswordDTO object with an
	 *         OK status if the employee is found, otherwise returns a NOT_FOUND
	 *         response.
	 */

	/**
	 * 3 Retrieves a list of all employees and their associated password
	 * information.
	 *
	 * @return ResponseEntity containing the list of EmployeeAndPasswordDTO objects
	 *         with an OK status if successful.
	 * @throws CPException
	 */
	@GetMapping("/allemployee")
	public ResponseEntity<List<Object>> getAllEmployeeAndPasswordData(@RequestHeader("Authorization") String authHeader
) throws CPException {
		// Retrieve a list of all employees and their associated password information
		// using the employeeService
		List<Object> employees = null;
		try {
			callCheckToken(authHeader);
			employees = employeeService.getAllEmployeesAndPasswords();
			if (employees != null && !employees.isEmpty()) {

				logger.info("Fetched all Employee: " + employees);
				return ResponseHandler.generateListResponse(employees, HttpStatus.OK);
			} else {

				// logger.info(resourceBundle.getString("err002"));
				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting all employee: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all employee");
		}

	}


	/**
	 * Updates an employee's information and, optionally, their profile photo by
	 * their employeeId.
	 *
	 * This endpoint handles PUT requests to update an employee's data and photo.
	 *
	 * @param employeeAndEmployeePhotosDTO The DTO containing employee data and
	 *                                     photo information.
	 * @param file                         The profile photo file to update, or null
	 *                                     if not updating the photo.
	 * @param employeeId                   The ID of the employee to update.
	 * @return ResponseEntity containing the updated Employee and photo DTO or an
	 *         error response.
	 */
	@PutMapping("/employee/update/{employeeId}")
	public ResponseEntity<Object> updateEmployeeByEmployeeId(
			@RequestPart("employee") EmployeeAndEmployeePhotosDTO employeeAndEmployeePhotosDTO,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@PathVariable("employeeId") int employeeId,@RequestHeader("Authorization") String authHeader) throws CPException {

		logger.info("Updating employee by id: " + employeeAndEmployeePhotosDTO);
		callCheckToken(authHeader);
		// Call the service to update the employee
		EmployeeAndEmployeePhotosDTO updatedEmployeeDTO = employeeService
				.updateEmployeeByEmployeeId(employeeAndEmployeePhotosDTO, employeeId, file);

		if (updatedEmployeeDTO != null) {
			// Return a success response with the updated employee data
			return ResponseHandler.generateResponse(updatedEmployeeDTO, HttpStatus.OK);
		} else {
		
			return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err006");
		}
	}

	/**
	 * Deletes an employee based on the provided employeeId and generates an
	 * appropriate response.
	 *
	 * @param employeeId - The ID of the employee to be deleted.
	 *
	 * @return ResponseEntity containing NO_CONTENT status if the employee was
	 *         successfully deleted, otherwise returns an INTERNAL_SERVER_ERROR
	 *         response.
	 *
	 * @throws CPException If there is an error while deleting the employee or
	 *                     generating the response.
	 */
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<Object> deleteEmployeeByEmployeeId(@PathVariable("employeeId") int employeeId,@RequestHeader("Authorization") String authHeader)
			throws CPException {
		// Log the entry of the method
		logger.info("entered deleteEmployee  :" + employeeId);

		// Initialize the count to keep track of the number of employees deleted
		int count = 0;

		try {
			callCheckToken(authHeader);
			// Attempt to delete the employee using the employeeService
			count = employeeService.deleteEmployeeByEmployeeId(employeeId);
			if (count >= 1) {
				// If one or more employees were deleted, log the deletion and generate a
				// NO_CONTENT response
				logger.info("deleted Employee : empId = " + employeeId);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				// If no employee was deleted, log the error message and generate an
				// INTERNAL_SERVER_ERROR response
				logger.info(resourceBundle.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}
		} catch (Exception ex) {
			// Log any exceptions that occur during the deletion process
			logger.error("Failed to delete Employee :" + ex.getMessage());
			// Throw a custom CPException with error code "err005" and the corresponding
			// error message from the resource bundle
			throw new CPException("err005", resourceBundle.getString("err005"));
		}
	}

	/**
	 * Retrieves employee details based on the provided employeeId and generates an
	 * appropriate response.
	 *
	 * @param employeeId - The ID of the employee to retrieve.
	 *
	 * @return ResponseEntity containing the Employee details with a 200 OK status
	 *         if found, otherwise returns a NOT_FOUND response.
	 *
	 * @throws CPException If there is an error while fetching the employee or
	 *                     generating the response.
	 */
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Object> getEmployeeDetailsByEmployeeId(@PathVariable("employeeId") int employeeId
)
			throws CPException {
		// Log the entry of the method
		logger.debug("Entering getEmployeeByempId");
		logger.info("entered user name :" + employeeId);

		// Initialize the Employee object
		Employee employee = null;

		try {
		
			// Fetch the employee details using the employeeService
			employee = employeeService.getEmployeeByEmployeeId(employeeId);
			logger.info("fetched Employee :" + employee);

			// Check if employee details were found
			if (employee != null) {
				logger.debug("Employee fetched generating response");
				// Generate a response with the employee details and 200 OK status
				return ResponseHandler.generateResponse(employee, HttpStatus.OK);
			} else {
				logger.debug("Employee not found");
				// Generate a NOT_FOUND response if the employee details were not found
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}
		} catch (Exception ex) {
			// Log any exceptions that occur during the process
			logger.error("Failed getting employee : " + ex.getMessage());
			// Throw a custom CPException with error code "err001" and the corresponding
			// error message from the resource bundle
			throw new CPException("err001", resourceBundle.getString("err001"));
		}
	}

	@GetMapping("/companyemp/{companyId}")
	public ResponseEntity<List<Object>> getAllCompnayEmployeeByCompanyId(@PathVariable("companyId") int companyId,@RequestHeader("Authorization") String authHeader
)
			throws CPException {

		List<Object> employees = null;
		try {
			callCheckToken(authHeader);
			if (companyId >= 0) {
				employees = employeeService.getAllEmployeeOfCompanyByCompanyId(companyId);
				logger.info("Fetched all emp :" + employees);
				return ResponseHandler.generateListResponse(employees, HttpStatus.OK);
			} else {
				logger.info(resourceBundle.getString("err002"));
				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			logger.error("Failed getting all emp : " + ex.getMessage());
			throw new CPException("err002", resourceBundle.getString("err002"));
		}
	}

	/**
	 * Endpoint to update an employee's password by employeeId.
	 *
	 * @param password   - The Password object containing the updated password.
	 * @param employeeId - The ID of the employee whose password is being updated.
	 *
	 * @return A ResponseEntity representing the outcome of the password update
	 *         operation.
	 * @throws CPException If there's an error during the password update process.
	 */
	@PutMapping("/password/{employeeId}")
	public ResponseEntity<Object> updatePasswordByEmployeeId(@RequestBody Password password,
			@PathVariable("employeeId") int employeeId,@RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering updatePassword");
		logger.info("Entered  updatePassword :" + password);

		Password updatedPassword = null;
		try {
			callCheckToken(authHeader);
			// Update password by employeeId in the service layer.
			updatedPassword = employeeService.updatePasswordByEmployeeId(password, employeeId);

			if (updatedPassword == null) {
				// If update is unsuccessful, generate an error response.
				// logger.info(resourceBundle.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				// If update is successful, generate a success response with the updated

				logger.info("Updated Password: " + updatedPassword);
				return ResponseHandler.generateResponse(updatedPassword, HttpStatus.CREATED);
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed  updatePassword: " + ex.getMessage());
			throw new CPException("err004", "Error while updatingPassword");
		}
	}

	/**
	 * Endpoint to update an employee's password by employeeId.
	 *
	 * @param dto        - The EmployeeAndPasswordDTO containing the updated
	 *                   employee information and new password.
	 * @param employeeId - The ID of the employee whose password and information are
	 *                   being updated.
	 *
	 * @return A ResponseEntity representing the outcome of the update operation.
	 * @throws CPException If there's an error during the update process.
	 */

	@PutMapping("/emppass/{employeeId}")
	public ResponseEntity<Object> updateEmployeeAndPasswordByEmployeeId(@RequestBody EmployeeAndPasswordDTO dto,
			@PathVariable("employeeId") int employeeId,@RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering updateemployeepassword");
		logger.info("Entered  updateemployeepassword :" + dto);

		boolean updateEmployeePassword = false;

		try {
			callCheckToken(authHeader);
			updateEmployeePassword = employeeService.updateEmployeeAndPasswordByEmployeeId(dto, employeeId);

			if (!updateEmployeePassword) {
				// If update is unsuccessful, generate an error response.
				// logger.info(resourceBundle.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				// If update is successful, generate a success response.
				logger.info("Employee and password updated successfully.");
				return ResponseHandler.generateResponse(updateEmployeePassword, HttpStatus.CREATED);
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed update Employee: " + ex.getMessage());
			throw new CPException("err004", "Error while updating employee");
		}
	}

	@GetMapping("/allemployees")
	public ResponseEntity<List<Object>> getAllEmployees(@RequestHeader("Authorization") String authHeader) throws Exception {
		logger.debug("Entering getAllEmployees");
	   	String token = authHeader.substring(7);

		List<Object> employees = null;
		try {

			callCheckToken(authHeader);
			// Retrieve all active employees from the service layer.
			employees = employeeService.getAllEmployees();

			if (employees != null && !employees.isEmpty()) {
				// If active employees are found, generate a success response with the list of

				logger.info("Fetched all Employee: " + employees);
				return ResponseHandler.generateListResponse(employees, HttpStatus.OK);
			} else {

				// logger.info(resourceBundle.getString("err002"));
				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting all employees: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all employees");
		}
	}

	@GetMapping("/passworddto/{username}")
	public ResponseEntity<Object> getPasswordfromUsername(@PathVariable String username,@RequestHeader("Authorization") String authHeader
) throws CPException {
		logger.debug("Entering getPasswordfromUsername");

		Password password = null;
		try {
			callCheckToken(authHeader);
			// Retrieve all active employees from the service layer.
			password = employeeService.getPasswordObjectByUsername(username);

			if (password != null ) {
				// If active employees are found, generate a success response with the list of

				logger.info("Fetched PasswordDTO: " + password);
				return ResponseHandler.generateResponse(password, HttpStatus.OK);
			} else {

				// logger.info(resourceBundle.getString("err002"));
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err002");
			}
		} catch (Exception ex) {
			// Log and throw a custom exception for error response.
			logger.error("Failed getting all employees: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all employees");
		}
	}
	/**
	 * Endpoint to check the validity of a password for a given username.
	 *
	 * @param username - The username for which the password is being checked.
	 * @param password - The password to be checked.
	 *
	 * @return A Password object if the password is valid for the given username,
	 *         otherwise null.
	 * @throws CPException If there's an error while retrieving or checking the
	 *                     password.
	 */

	
	 @GetMapping("password/{username}/{password}")
	    public Map<String, Object> checkPassword(@PathVariable String username, @PathVariable String password) throws CPException {
	        Map<String, Object> response = new HashMap<>();
	        Password isPasswordValid = null;
	        try {
	            // Call the employeeService to validate the username and password.
	            isPasswordValid = employeeService.getPasswordByUsernameAndPassword(username, password);
	            System.out.println("Entered in Controller after service");
	            // If the password is valid, return the Password object.
	            if (isPasswordValid != null) {
	                // Call the endpoint to create a refresh token using RestTemplate
	                ResponseEntity<RefreshToken> refreshTokenResponse = restTemplate.postForEntity(
	                        BASE_URL + "token/create-refresh-token?username={username}",
	                        null,
	                        RefreshToken.class,
	                        username
	                );

	                RefreshToken refreshToken = refreshTokenResponse.getBody();

	                // Call the endpoint to generate an access token using RestTemplate
	                ResponseEntity<String> tokenResponse = restTemplate.postForEntity(
	                        BASE_URL + "token/generateToken",
	                        username,
	                        String.class
	                );

	                String token = tokenResponse.getBody();

	                JwtResponse jwtResponse = new JwtResponse();
	                jwtResponse.setAccessToken(token);
	                jwtResponse.setTokenUniqueID(refreshToken.getTokenUniqueID());

	                response.put("jwtResponse", jwtResponse);
	                response.put("isPasswordValid", isPasswordValid);

	                return response;
	            } else {
	                // If the password is not valid, return null.
	                return null;
	            }
	        } catch (Exception ex) {
	            // If an exception occurs while trying to validate the password,
	            // log the error message and throw a custom exception (CPException) with an
	            // error code and message.
	            logger.error("Failed generating access token: " + ex.getMessage());
	            throw new CPException("err002", "Error while retrieving all employees");
	        }
	    }

	

	
//	@GetMapping("password/{username}/{password}")
//	public Password checkPassword(@PathVariable String username, @PathVariable String password) throws CPException {
//		Password isPasswordValid = null;
//		try {
//			// Call the employeeService to validate the username and password.
//			isPasswordValid = employeeService.getPasswordByUsernameAndPassword(username, password);
//
//			// If the password is valid, return the Password object.
//			if (isPasswordValid != null) {
//		
//				 JwtResponse jwtResponse = new JwtResponse();
//				  String token = webClient.post()
//		                    .uri("/token/generateToken")
//		                    .body(BodyInserters.fromValue(username))
//		                    .retrieve()
//		                    .bodyToMono(String.class)
//		                    .block();
//				  
//				  jwtResponse.setAccessToken(token);
//				  jwtResponse.setToken("ghghjghghghgj");
//				 
//				return isPasswordValid;
//			} else {
//				// If the password is not valid, return null.
//				return null;
//			}
//		} catch (Exception ex) {
//			// If an exception occurs while trying to validate the password,
//			// log the error message and throw a custom exception (CPException) with an
//			// error code and message.
//			logger.error("Failed getting all employees: " + ex.getMessage());
//			throw new CPException("err002", "Error while retrieving all employees");
//		}
//	}

	/**
	 * Endpoint to handle password reset for a forgotten password.
	 *
	 * @param request - A Map containing username.
	 * @return A ResponseEntity indicating the success or failure of the password
	 *         reset.
	 */

	
	@PostMapping("/forgotpass")
	public ResponseEntity<Object> forgotPasswordByUsername(@RequestBody Map<String, String> request)
			throws CPException {

		try {
			// Extract the username from the request body
			String username = request.get("username");

			// Retrieve the Password object associated with the provided username
			Password password = employeeService.getPasswordObjectByUsername(username);
			logger.info("Fetched Password: " + password);

			// If no password object is found, return a bad request response with "Email not
			// found"
			if (password == null) {
//				return ResponseEntity.badRequest().body("Email not found");
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "err006");
			}

			// Retrieve the employee ID from the password object
			int employeeId = password.getEmployeeId();

			// Retrieve the Employee object associated with the employee ID
			Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);

			// If no employee object is found, return a bad request response with "Invalid
			// username"
			if (employee == null) {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "err007");
			}

			// Update the password for the employee
			if (!employeeService.updatePassword(employeeId)) {
				// Generate an internal server error response if the password update fails
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			// Return a successful response with HTTP status OK
			return ResponseHandler.generateResponse(HttpStatus.CREATED);

		} catch (Exception ex) {
			// Log and handle any exceptions that occur during the process
			logger.error("Failed getting all employees: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all employees");
		}
	}

	@GetMapping(value = "/employee/photos/{id}")
	public ResponseEntity<byte[]> getEmployeePhotosByEmployeeId(@PathVariable("id") int id,
			HttpServletResponse response
) {
		try {
			
			Employee myFile;
		
			myFile = employeeService.getEmployeeByEmployeeId(id);
			System.out.println("-------------------------" + myFile);

			EmployeePhotos filename;
			filename = employeeService.getPhotosByEmployeeId(id);
			System.out.println("***********" + filename);

			String employeeNameAndId = myFile.getFirstName() + "_" + myFile.getLastName() + "_"
					+ myFile.getEmployeeId();
			System.out.println("//////////////////////////" + employeeNameAndId);

			String photoFilename = filename.getFileName();
			System.out.println("/////////////*******" + photoFilename);
			// Retrieve the employee photos by employee ID from the database or any other
			// data
			// source
			// For this example, we assume the photos are stored in a directory on the
			// server
			String folderPath = basePath + "/employee/" + employeeNameAndId + "/" + photoFilename; // Replace with the
																									// actual folder
																									// path
			System.out.println(folderPath);
			// String fileName = "company_" + id + ".jpg"; // Modify the filename format if
			// needed

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

	@GetMapping("/employee/employeePhotos/{employeeId}")
	public EmployeePhotos getPhotosByEmployeeId(@PathVariable int employeeId) {
		return employeeService.getPhotosByEmployeeId(employeeId);
	}

	/**
	 * Retrieves a list of employees and their passwords based on the provided
	 * companyId and generates an appropriate response.
	 *
	 * @param companyId - The ID of the company for which employees and passwords
	 *                  are retrieved.
	 *
	 * @return ResponseEntity containing the list of employees and passwords with a
	 *         200 OK status if found, otherwise returns a NOT_FOUND response.
	 *
	 * @throws CPException If there is an error while fetching the employees or
	 *                     generating the response.
	 */

	


	@GetMapping("/comEmpPwd/{companyId}")
	public ResponseEntity<List<Object>> getAllCompnayEmployeeAndPasswordByCompanyId(
	        @PathVariable("companyId") int companyId, @RequestHeader("Authorization") String authHeader) throws CPException {

	    // Initialize a list to store employee data.
	    List<Object> employees = null;

	    try {
	        String checkToken = callCheckToken(authHeader);
	        System.out.println("***********   " + checkToken);
	        if (checkToken != null) {
	            System.out.println("***********   " + checkToken);
	            // Check if the companyId is a valid non-negative integer.
	            if (companyId >= 0) {
	                // Call a service method to fetch all employees and their passwords by
	                // companyId.
	                employees = employeeService.getAllEmployeeAndPasswordByCompanyId(companyId);

	                // Log a message indicating that employees were successfully fetched.
	                logger.info("Fetched all employees: " + employees);

	                // Generate a response with the list of employees and a HTTP status code of 200
	                // (OK).
	                return ResponseHandler.generateListResponse(employees, HttpStatus.OK);
	            } else {
	                // Log an error message indicating an invalid companyId.
	                logger.info(resourceBundle.getString("err002"));

	                // Generate a response with a HTTP status code of 404 (Not Found) and an error
	                // message.
	                return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
	            }
	        } else {
	            String errCodeString = new String();
	            errCodeString = "Token has expired";
	            
	            // If checkToken is null, return an unauthorized response with an appropriate message.
	            return ResponseHandler.generateListResponse(HttpStatus.UNAUTHORIZED, null);
	        }
	    }

	    catch (SignatureException ex) {
	        // Log an error message if token is expired.
	        logger.error("Token improper: " + ex.getMessage());

	        // Return 401 Unauthorized response.
	        return ResponseHandler.generateListResponse(HttpStatus.UNAUTHORIZED, null);
	    } 
	    catch (NullPointerException ex) {
	        // Log an error message if token is expired.
	        logger.error("Token expired: " + ex.getMessage());

	        // Return 401 Unauthorized response.
	        return ResponseHandler.generateListResponse(HttpStatus.UNAUTHORIZED, null);
	    } 
	    catch (Exception ex) {
	        // Log an error message if an exception occurs during the process.
	        logger.error("Failed getting all employees: " + ex.getMessage());
	        
	        return ResponseHandler.generateListResponse(HttpStatus.BAD_REQUEST, "bad REquest");

	        // Throw a custom exception with an error code and message.
	    }
	}

}
