/**

+ * @author - Code Generator
 * @createdOn 25/07/2023
 * @Description Controller class for employee
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

import com.cpa.ttsms.dto.EmailDTO;
import com.cpa.ttsms.dto.EmployeeAndEmployeePhotosDTO;
import com.cpa.ttsms.dto.EmployeeAndPasswordDTO;
import com.cpa.ttsms.dto.EmployeePasswordAndEmployeePhotosDTO;
//import com.cpa.ttsms.controller.EmployeeController;
import com.cpa.ttsms.entity.Employee;
import com.cpa.ttsms.entity.EmployeePhotos;
import com.cpa.ttsms.entity.Password;
import com.cpa.ttsms.repository.EmployeePhotosRepo;
import com.cpa.ttsms.repository.EmployeeRepo;
import com.cpa.ttsms.repository.PasswordRepo;
import com.cpa.ttsms.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// private final String email_URL = "http://localhost:8080/email/sendMail";
	// private final String UPLOAD_FILE_URL =
	// "http://localhost:8080/uploadfile/ttsms/upload";

	@Value("${email.url}")
	private String email_URL;

	@Value("${upload.file.url}")
	private String UPLOAD_FILE_URL;

	private final RestTemplate restTemplate;

	// Inject the value of 'file.base-path' from application.yml file
	@Value("${file.base-path}")
	private String basePath;

	@Autowired
	private EmployeeRepo employeeRepo;
	private static Logger logger;

	@Autowired
	private EmployeePhotosRepo employeePhotosRepo;
	@Autowired

	private PasswordRepo passwordRepository;

	public EmployeeServiceImpl(RestTemplate restTemplate) {
		logger = Logger.getLogger(EmployeeServiceImpl.class);
		this.restTemplate = restTemplate;
	}

	/**
	 * Create a new country entry in the Country table.
	 *
	 * @param country - The Country object containing the details of the country to
	 *                be created.
	 *
	 * @return The newly created Country object if successful, otherwise null.
	 */

//	@Override
//	public Employee updateEmployeeByEmployeeId(Employee employee, int employeeId) {
//		// TODO Auto-generated method stub
//		logger.debug("Entering updateEmployee");
//
//		// Initialize variables
//		Employee toUpdatedEmployee = null;
//		Employee updatedEmployee = null;
//
//		// Find the existing employee based on the provided employeeId
//		toUpdatedEmployee = employeeRepo.findByEmployeeId(employeeId);
//		logger.info("existing Employee :: " + toUpdatedEmployee);
//
//		// Check if an employee with the given ID exists
//		if (toUpdatedEmployee != null) {
//			logger.debug("setting new data of Employee to existing Employee");
//
//			// Update the existing employee's data with the provided employee data
//			toUpdatedEmployee.setCountryId(employee.getCountryId());
//			toUpdatedEmployee.setCompanyId(employee.getCompanyId());
//			toUpdatedEmployee.setFirstName(employee.getFirstName());
//			toUpdatedEmployee.setLastName(employee.getLastName());
//			toUpdatedEmployee.setBirthDate(employee.getBirthDate());
//			toUpdatedEmployee.setEmployeeEmail(employee.getEmployeeEmail());
//
//			// Save the updated employee in the database
//			updatedEmployee = employeeRepo.save(toUpdatedEmployee);
//
//			logger.info("updated Employee :" + updatedEmployee);
//		}
//
//		return updatedEmployee;
//	}

	/**
	 * Saves the employee and their password information.
	 *
	 * @param dto - The EmployeeAndPasswordDTO object containing the details of the
	 *            employee and password to be saved.
	 *
	 * @return The original EmployeeAndPasswordDTO object if successful.
	 */

	@Transactional
	@Override
	public EmployeeAndPasswordDTO saveEmployeeAndPassword(EmployeeAndPasswordDTO dto) {
		// TODO Auto-generated method stub

		// Create a new Employee object and set its properties from the provided DTO
		Employee employee = new Employee();
		employee.setEmployeeId(dto.getEmployeeId());
		employee.setCountryId(dto.getCountryId());
		employee.setCompanyId(dto.getCompanyId());
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setAdmin(dto.isAdmin());
		employee.setShowAllTasks(dto.isShowAllTasks());
		employee.setOnBench(dto.isOnBench());

//	    // Parse the date string from the DTO and set it as the employee's birth date
//	    Date dob;
//	    try {
//	        dob = new SimpleDateFormat("yyyy-dd-MM").parse(dto.getBirthDate());
//	        employee.setBirthDate(dob);
//	    } catch (ParseException e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	    }

		employee.setBirthDate(dto.getBirthDate());
		employee.setEmployeeEmail(dto.getEmployeeEmail());

		// Save the employee in the database
		Employee createdEmployee = employeeRepo.save(employee);

		// Create a new Password object and set its properties from the DTO
		Password password = new Password();
		password.setEmployeeId(employee.getEmployeeId());
		password.setPasswordId(dto.getEmployeeId());
		password.setUsername(dto.getUsername());
		password.setPassword(dto.getPassword());

		// Save the password in the database
		Password createdPassword = passwordRepository.save(password);

		if (createdEmployee != null && createdPassword != null) {
			dto.setEmployeeId(createdEmployee.getEmployeeId());
			dto.setPasswordId(createdPassword.getEmployeeId());
			return dto;
		} else {
			return null;
		}

	}

	/**
	 * Retrieve an Employee object and its associated Password information by
	 * employeeId.
	 *
	 * @param employeeId - The ID of the employee to retrieve.
	 *
	 * @return The EmployeeAndPasswordDTO object containing the employee and
	 *         password information if found, otherwise null.
	 */
	@Override
	public EmployeeAndPasswordDTO getEmployeeAndPasswordByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		// Find the employee by their ID in the database
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		if (employee.isPresent()) {
			EmployeeAndPasswordDTO dto = new EmployeeAndPasswordDTO();
			Employee emp = employee.get();
			// Find the associated password by employeeId
			Password password = passwordRepository.findByEmployeeId(employeeId);
			if (password != null) {
				// If password exists, populate the EmployeeAndPasswordDTO object
				dto.setEmployeeId(emp.getEmployeeId());
				dto.setCountryId(emp.getCountryId());
				dto.setCompanyId(emp.getCompanyId());
				dto.setFirstName(emp.getFirstName());
				dto.setLastName(emp.getLastName());
				dto.setBirthDate(emp.getBirthDate());
				// Format dob as a String (assuming it's already in Date format in the entity)
//	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	            dto.setBirthDate(dateFormat.format(emp.getBirthDate()));
				dto.setPasswordId(password.getPasswordId());
				dto.setEmployeeEmail(emp.getEmployeeEmail());
				dto.setUsername(password.getUsername());
				dto.setPassword(password.getPassword());
				return dto;
			}
		}
		return null;
	}

	/**
	 * Retrieves a list of all employees and their associated password information.
	 *
	 * @return List of EmployeeAndPasswordDTO objects containing employee and
	 *         password information.
	 */
	@Override
	public List<Object> getAllEmployeesAndPasswords() {
		// TODO Auto-generated method stub
		List<Object> employeePasswordDTOList = new ArrayList<>();
		List<Employee> employees = employeeRepo.findAll();

		for (Employee emp : employees) {
			// Find the associated password by employeeId
			Password password = passwordRepository.findByEmployeeId(emp.getEmployeeId());
			if (password != null) {
				// If password exists, populate the EmployeeAndPasswordDTO object
				EmployeeAndPasswordDTO dto = new EmployeeAndPasswordDTO();
				dto.setEmployeeId(emp.getEmployeeId());
				dto.setCountryId(emp.getCountryId());
				dto.setCompanyId(emp.getCompanyId());
				dto.setFirstName(emp.getFirstName());
				dto.setLastName(emp.getLastName());
				dto.setAdmin(emp.isAdmin());
				dto.setShowAllTasks(emp.isShowAllTasks());
				// Format dob as a String (assuming it's already in Date format in the entity)
				// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dto.setPasswordId(password.getPasswordId());
				dto.setBirthDate(emp.getBirthDate());
				// dto.setBirthDate(emp.getBirthDate().toString());
				dto.setEmployeeEmail(emp.getEmployeeEmail());
				dto.setUsername(password.getUsername());
				dto.setPassword(password.getPassword());

				employeePasswordDTOList.add(dto);
			}
		}

		return employeePasswordDTOList;
	}

	/**
	 * Deletes an employee by employeeId.
	 *
	 * @param employeeId - The ID of the employee to be deleted.
	 *
	 * @return The number of employees deleted (should be 0 or 1).
	 */
	@Override
	public int deleteEmployeeByEmployeeId(int employeeId) {
		logger.debug("Entering deleteEmployeeByempId");

		// Delete the employee by their ID in the database
		int count = employeeRepo.deleteEmployeeByEmployeeId(employeeId);
		logger.info("deleted Employee count : " + count);
		return count;
	}

	/**
	 * Retrieve an Employee object by employeeId.
	 *
	 * @param employeeId - The ID of the employee to retrieve.
	 *
	 * @return The Employee object if found, otherwise null.
	 */
	@Override
	public Employee getEmployeeByEmployeeId(int employeeId) {
		logger.debug("Entering getEmployeeByempId");

		// Find the employee by their ID in the database
		Employee employee = employeeRepo.findByEmployeeId(employeeId);
		logger.info("Founded employee :" + employee);

		return employee;
	}

	@Override
	public Password updatePasswordByEmployeeId(Password password, int employeeId) {
		// TODO Auto-generated method stub
		Password toUpdatePassword = null;
		Password updatedPassword = null;

		toUpdatePassword = passwordRepository.findByEmployeeId(employeeId);

		if (toUpdatePassword != null) {

//			toUpdatePassword.setUsername(password.getUsername());
			toUpdatePassword.setPassword(password.getPassword());
			toUpdatePassword.setForgotPassword(false);

			updatedPassword = passwordRepository.save(toUpdatePassword);
			logger.info("updated Employee :" + updatedPassword);
		}

		return updatedPassword;
	}

	@Override
	@Transactional
	public boolean updateEmployeeAndPasswordByEmployeeId(EmployeeAndPasswordDTO dto, int employeeId) {
		// TODO Auto-generated method stub

		Employee toUpdatedEmployee = null;
		Employee updatedEmployee = null;
		Password toUpdatePassword = null;
		Password updatedPassowrd = null;

		boolean isSuccess = false;
		toUpdatedEmployee = employeeRepo.findByEmployeeId(employeeId);

		if (toUpdatedEmployee != null) {

			toUpdatedEmployee.setCountryId(dto.getCountryId());
			toUpdatedEmployee.setCompanyId(dto.getCompanyId());
			toUpdatedEmployee.setFirstName(dto.getFirstName());
			toUpdatedEmployee.setLastName(dto.getLastName());
			// toUpdatedEmployee.setBirthDate(employee.getBirthDate());
			toUpdatedEmployee.setEmployeeEmail(dto.getEmployeeEmail());
			toUpdatedEmployee.setAdmin(dto.isAdmin());
			toUpdatedEmployee.setShowAllTasks(dto.isShowAllTasks());
			toUpdatedEmployee.setOnBench(dto.isOnBench());

			updatedEmployee = employeeRepo.save(toUpdatedEmployee);

		}
		toUpdatePassword = passwordRepository.findByEmployeeId(employeeId);

		if (toUpdatePassword != null) {
			toUpdatePassword.setUsername(dto.getUsername());

			toUpdatePassword.setPassword(dto.getPassword());
			updatedPassowrd = passwordRepository.save(toUpdatePassword);

		}

		if (updatedEmployee != null && updatedPassowrd != null) {
			isSuccess = true;
		}
		return isSuccess;
	}

	/**
	 * Retrieve a list of generic Objects representing all employees.
	 *
	 * @return A list containing generic Objects representing all employees.
	 */
	@Override
	public List<Object> getAllEmployees() {
		// TODO Auto-generated method stub
		logger.debug("Entering getAllCountries");

		List<Employee> employees = employeeRepo.findAll(); // Retrieve all active countries from the database.
		logger.info("Fetched all: " + employees);

		// Create a new list to store objects
		List<Object> objects = new ArrayList<>(employees); // Convert the list of Country objects to a list of generic
															// Objects.

		logger.info("Fetched all " + employees);
		return objects; // Return the list of generic Objects.
	}

	/**
	 * Retrieve a Password object using the provided username and password.
	 *
	 * @param username - The username for authentication.
	 * @param password - The password for authentication.
	 *
	 * @return The retrieved Password object if authentication is successful,
	 *         otherwise null.
	 */
	@Override
	public Password getPasswordByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub

		// Retrieve the Password object from the repository based on the provided
		// username and password.
		Password object = passwordRepository.findByUsernameAndPassword(username, password);

		// Log information about the retrieved object for debugging purposes.
		logger.info("Found Password: " + object);

		// Return the retrieved Password object, which might be null if no match is
		// found.
		return object;
	}

	/**
	 * Update the password for the specified employee.
	 *
	 * @param employeeId - The ID of the employee for whom the password needs to be
	 *                   updated.
	 * @return true if the password update is successful, false otherwise.
	 */
	@Override
	public boolean updatePassword(int employeeId) {
		// Retrieve the existing Password object for the given employee ID
		Password password = passwordRepository.findByEmployeeId(employeeId);

		// Generate a new random password
		String oldPassword = password.getPassword();
		password.setForgotPassword(true);
		passwordRepository.save(password);
//		String oldPassword = generateRandomPassword();
//		password.setPassword(oldPassword);

		// Update the password value in the Password object
		// password.setPassword(newPassword);

		// Save the updated Password object to the repository
		// passwordRepository.save(password);

		// Retrieve employee information based on the employee ID
		Employee employee = employeeRepo.findByEmployeeId(employeeId);

		// Extract necessary information for sending the password reset email
		String employeeEmail = employee.getEmployeeEmail();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();

		// Compose the email message body
		String msgBody = "Dear " + firstName + " " + lastName + ",\n\n" + "Your password for Task Tracking System is: "
				+ oldPassword + "\n" + "Please log in and ensure the security of your account.\n\n" + "Best regards,\n"
				+ "TTS Admin";

		logger.info("Fetched Employee Email: " + employeeEmail);

		// Create an EmailDTO object to send the email
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setRecipient(employeeEmail); // Set the recipient's email address
		emailDTO.setMsgBody(msgBody); // Set the email's message body
		emailDTO.setSubject("Password Reset for Task Tracking System"); // Set the email's subject

		// Send the email using a REST call
		ResponseEntity<String> response = restTemplate.postForEntity(email_URL, emailDTO, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			String responseBody = response.getBody();
			return true; // Password update and email sending were successful
		} else {
			// Handle error cases where the email sending failed

			return false; // Password update was successful, but email sending failed
		}
	}

	private String generateRandomPassword() {
		// Generate a random 8-digit password
		StringBuilder password = new StringBuilder();
		Random random = new Random();
		String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
		String NUMBERS = "0123456789";
		String SPECIAL_CHARS = "@#$&";

		String ALL_CHARS = UPPERCASE_CHARS + LOWERCASE_CHARS + NUMBERS + SPECIAL_CHARS;
		// SecureRandom randomPass = new SecureRandom();
		for (int i = 0; i < 8; i++) {
			int randomIndex =0;
			char randomChar;
			int rem = i % 4;
			switch (rem) {
			case 0:
				randomIndex = random.nextInt(ALL_CHARS.length());
				randomChar = UPPERCASE_CHARS.charAt(randomIndex);
				password.append(randomChar);
				break;
			case 1:
				randomIndex = random.nextInt(ALL_CHARS.length());
				randomChar = SPECIAL_CHARS.charAt(randomIndex);
				password.append(randomChar);
				break;
			case 2:
				randomIndex = random.nextInt(ALL_CHARS.length());
				randomChar = LOWERCASE_CHARS.charAt(randomIndex);
				password.append(randomChar);
				break;
			case 3:
				randomIndex = random.nextInt(ALL_CHARS.length());
				randomChar = NUMBERS.charAt(randomIndex);
				password.append(randomChar);
				break;
			}
		
		}

		return password.toString();
	}

	@Override
	public Password getPasswordObjectByUsername(String username) {
		// TODO Auto-generated method stub
		Password password = passwordRepository.findByUsername(username);
		String oldPassword = generateRandomPassword();
		password.setPassword(oldPassword);
		passwordRepository.save(password);
		return password;
	}

	/**
	 * Creates a new employee along with their profile photo and password.
	 *
	 * @param employeePasswordAndEmployeePhotosDTO The DTO containing employee data,
	 *                                             photo information, and password.
	 * @param file                                 The profile photo file.
	 * @return EmployeePasswordAndEmployeePhotosDTO containing created employee data
	 *         or null if an error occurs.
	 */
	@Override
	@Transactional
	public EmployeePasswordAndEmployeePhotosDTO createEmployee(
			EmployeePasswordAndEmployeePhotosDTO employeePasswordAndEmployeePhotosDTO, MultipartFile file) {

		// Log method entry
		logger.debug("Entering createEmployee");

		Employee createdEmployee = null;
		File tempFile = null;
		String extension = null;

		// Create an Employee object with the provided DTO data
		Employee employee = new Employee(employeePasswordAndEmployeePhotosDTO.getEmployeeId(),
				employeePasswordAndEmployeePhotosDTO.getCountryId(),
				employeePasswordAndEmployeePhotosDTO.getCompanyId(),
				employeePasswordAndEmployeePhotosDTO.getFirstName(), employeePasswordAndEmployeePhotosDTO.getLastName(),
				employeePasswordAndEmployeePhotosDTO.getBirthDate(),
				employeePasswordAndEmployeePhotosDTO.getEmployeeEmail(), employeePasswordAndEmployeePhotosDTO.isAdmin(),
				employeePasswordAndEmployeePhotosDTO.isShowAllTasks(),
				employeePasswordAndEmployeePhotosDTO.isOnBench());

		// Save the employee data to the database

		createdEmployee = employeeRepo.save(employee);

		try {
			// Create a temporary file to store the uploaded profile photo
			tempFile = File.createTempFile("temp", file.getOriginalFilename());
			file.transferTo(tempFile);

			// Extract the file extension from the original file name
			extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

			// Modify the file name with employee name and id with extension
			String modifiedFileName = employee.getFirstName() + "_" + employee.getLastName() + "_"
					+ employee.getEmployeeId() + extension;

			// Create an EmployeePhotos object and save it to the database
			EmployeePhotos employeePhotos = new EmployeePhotos(employeePasswordAndEmployeePhotosDTO.getPhotoId(),
					createdEmployee.getEmployeeId(), modifiedFileName);
			EmployeePhotos createdEmployeePhotoObject = employeePhotosRepo.save(employeePhotos);

			// Build form-data to pass in the request for uploading the file
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("filename", modifiedFileName);
			map.add("file", new FileSystemResource(tempFile));
			map.add("folder", "employee/" + employee.getFirstName() + "_" + employee.getLastName() + "_"
					+ employee.getEmployeeId());

			// Set the content type for the header
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

			// Call an API for uploading the file (you should replace UPLOAD_FILE_URL with
			// the actual URL)
			ResponseEntity<String> response = restTemplate.postForEntity(UPLOAD_FILE_URL, requestEntity, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				// Now, create and save the password for the employee
				Password password = new Password(0, // Assign an appropriate value for the passwordId, or generate it as
													// needed
						createdEmployee.getEmployeeId(), employeePasswordAndEmployeePhotosDTO.getUsername(),
						employeePasswordAndEmployeePhotosDTO.getPassword(),
						employeePasswordAndEmployeePhotosDTO.isForgotPassword());

				// Save the password to the database
				passwordRepository.save(password);

				// Set created IDs to the DTO
				employeePasswordAndEmployeePhotosDTO.setPhotoId(createdEmployeePhotoObject.getEmployeePhotosId());
				employeePasswordAndEmployeePhotosDTO.setEmployeeId(createdEmployee.getEmployeeId());

				return employeePasswordAndEmployeePhotosDTO; // Return the created employee data
			} else {
				logger.error("Error uploading data to remote microservice: " + response.getStatusCodeValue());
				return null; // Return null in case of an error during file upload
			}
		} catch (Exception e) {
			logger.error("Error while processing data: " + e.getMessage(), e);
			return null; // Return null in case of any other exceptions
		}
	}

	@Transactional
	@Override
	public EmployeeAndEmployeePhotosDTO updateEmployeeByEmployeeId(
			EmployeeAndEmployeePhotosDTO employeeAndEmployeePhotosDTO, int employeeId, MultipartFile file) {
		logger.debug("Entering updateEmployeeByEmployeeId");

		// Check if the employee with the given employee Id exists
		Employee existingEmployee = employeeRepo.findByEmployeeId(employeeId);

		if (existingEmployee == null) {
			logger.warn("Employee not found with Id: " + employeeId);
			return null;
		}

		try {
			// Update the Employee details
			existingEmployee.setCountryId(employeeAndEmployeePhotosDTO.getCountryId());
			existingEmployee.setCompanyId(employeeAndEmployeePhotosDTO.getCompanyId());
			existingEmployee.setFirstName(employeeAndEmployeePhotosDTO.getFirstName());
			existingEmployee.setLastName(employeeAndEmployeePhotosDTO.getLastName());
			existingEmployee.setBirthDate(employeeAndEmployeePhotosDTO.getBirthDate());
			existingEmployee.setEmployeeEmail(employeeAndEmployeePhotosDTO.getEmployeeEmail());
			existingEmployee.setAdmin(employeeAndEmployeePhotosDTO.isAdmin());

			existingEmployee.setShowAllTasks(employeeAndEmployeePhotosDTO.getShowAllTasks());
			System.out.println(existingEmployee.toString());
			existingEmployee.setOnBench(employeeAndEmployeePhotosDTO.isOnBench());
			// Save the updated employee
			Employee updatedEmployee = employeeRepo.save(existingEmployee);

			// Check if a file is provided for updating the photo
			if (file != null) {
				// Handle photo update logic here
				File tempFile = null;
				// Creating a temporary file to store the uploaded photo
				tempFile = File.createTempFile("temp", file.getOriginalFilename());
				file.transferTo(tempFile);

				EmployeePhotos existingEmployeePhotos = employeePhotosRepo
						.findByEmployeeId(updatedEmployee.getEmployeeId());

				String folderName = "employee/" + existingEmployee.getFirstName() + "_" + existingEmployee.getLastName()
						+ "_" + existingEmployee.getEmployeeId();

				deleteFilesFromFolder(basePath + "/" + folderName);

				// Extracting the extension from the original file
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

				// Modifying the file name with the firstName and lastName and ID with extension
				String modifiedFileName = updatedEmployee.getFirstName() + "_" + updatedEmployee.getLastName() + "_"
						+ updatedEmployee.getEmployeeId() + extension;

				if (existingEmployeePhotos != null) {
					// Update the photo details in the employeeAndEmployeePhotosDTO
					existingEmployeePhotos.setFileName(modifiedFileName);
					// Set the employeePhotosId in the existingEmployeePhotos object
				}

				EmployeePhotos createdEmployeePhotoObject = employeePhotosRepo.save(existingEmployeePhotos);
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
			return employeeAndEmployeePhotosDTO;
		} catch (Exception e) {
			logger.error("Error while updating company: " + e.getMessage(), e);
			return null;
		}
	}

	// Helper method to delete files from a folder
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
	public EmployeePhotos getPhotosByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return employeePhotosRepo.findByEmployeeId(employeeId);
	}

	@Override
	public List<Object> getAllEmployeeOfCompanyByCompanyId(int companyId) {
		List<Object> employees = null;
		List<Employee> employee = employeeRepo.findByCompanyId(companyId);
		employees = new ArrayList<Object>(employee);
		return employees;
	}

	@Override
	public List<Object> getAllEmployeeAndPasswordByCompanyId(int companyId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		List<Object> employeePasswordDTOList = new ArrayList<>();
		List<Employee> employees = employeeRepo.findByCompanyId(companyId);

		for (Employee emp : employees) {
			// Find the associated password by employeeId
			Password password = passwordRepository.findByEmployeeId(emp.getEmployeeId());
			if (password != null) {
				// If password exists, populate the EmployeeAndPasswordDTO object
				EmployeeAndPasswordDTO dto = new EmployeeAndPasswordDTO();
				dto.setEmployeeId(emp.getEmployeeId());
				dto.setCountryId(emp.getCountryId());
				dto.setCompanyId(emp.getCompanyId());
				dto.setFirstName(emp.getFirstName());
				dto.setLastName(emp.getLastName());
				dto.setAdmin(emp.isAdmin());
				dto.setShowAllTasks(emp.isShowAllTasks());
				dto.setOnBench(emp.isOnBench());
				// Format dob as a String (assuming it's already in Date format in the entity)
				// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dto.setPasswordId(password.getPasswordId());
				dto.setBirthDate(emp.getBirthDate());
				// dto.setBirthDate(emp.getBirthDate().toString());
				dto.setEmployeeEmail(emp.getEmployeeEmail());
				dto.setUsername(password.getUsername());
				dto.setPassword(password.getPassword());

				employeePasswordDTOList.add(dto);
			}
		}

		return employeePasswordDTOList;
	}

	@Override
	public List<Object> getEmployeesOnBenchByCompanyId(int companyId) {
		// TODO Auto-generated method stub

		List<Employee> employeeOnBench = employeeRepo.findByCompanyIdAndIsOnBenchTrue(companyId);
		List<Object> listEmployees = new ArrayList<>(employeeOnBench);
		System.out.println(listEmployees);
		return listEmployees;
	}

	@Override
	public Password savePasswordForChangePassword(Password password) {
		// TODO Auto-generated method stub

		Password newPassword = new Password();
		newPassword.setEmployeeId(password.getEmployeeId());
		newPassword.setPassword(password.getPassword());
		newPassword.setUsername(password.getUsername());
		newPassword.setForgotPassword(false);

		Password savePassword = passwordRepository.save(newPassword);
		return savePassword;
	}

}
