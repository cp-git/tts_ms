/**
 * @author - Code Generator
 * @createdOn 25/07/2023
 * @Description Controller class for employee
 * 
 */

package com.cpa.ttsms.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.dto.EmployeeAndPasswordDTO;
import com.cpa.ttsms.entity.Employee;
import com.cpa.ttsms.entity.Password;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.EmployeeService;

@CrossOrigin
@RestController
@RequestMapping("/ttsms")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;;

	private ResourceBundle resourceBundle;
	private static Logger logger;

	EmployeeController() {
		resourceBundle = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(EmployeeController.class);
	}

	/**
	 * Creates a new employee along with their password information based on the
	 * provided EmployeeAndPasswordDTO object, and generates an appropriate
	 * response.
	 *
	 * @param dto - The EmployeeAndPasswordDTO object containing the details of the
	 *            employee and password to be created.
	 *
	 * @return ResponseEntity containing a CREATED status if the employee was
	 *         successfully created, otherwise returns an INTERNAL_SERVER_ERROR
	 *         response.
	 *
	 * @throws CPException If there is an error while creating the employee or
	 *                     generating the response.
	 */
	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeAndPasswordDTO dto) throws CPException {
		// Log the entry of the method
		logger.debug("Entering createEmployee");
		logger.info("data of creating Employee: " + dto.toString());

		try {
			// Attempt to create the new employee along with their password using the
			// employeeService
			EmployeeAndPasswordDTO createdDTO = employeeService.saveEmployeeAndPassword(dto);
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
	public ResponseEntity<List<Object>> getAllEmployeeAndPasswordData() throws CPException {
		// Retrieve a list of all employees and their associated password information
		// using the employeeService
		List<Object> employees = null;
		try {
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
	 * Updates an employee based on the provided employeeId and Employee object, and
	 * generates an appropriate response.
	 *
	 * @param employee   - The Employee object containing the updated details of the
	 *                   employee.
	 * @param employeeId - The ID of the employee to be updated.
	 *
	 * @return ResponseEntity containing the updated Employee details with a CREATED
	 *         status if successful, otherwise returns an INTERNAL_SERVER_ERROR
	 *         response.
	 *
	 * @throws CPException If there is an error while updating the employee or
	 *                     generating the response.
	 */
	@PutMapping("/employee/update/{employeeId}")
	public ResponseEntity<Object> updateEmployeeByEmployeeId(@RequestBody Employee employee,
			@PathVariable("employeeId") int employeeId) throws CPException {
		// Log the entry of the method
		logger.debug("Entering updateEmployee");
		logger.info("entered  updateEmployee :" + employee);

		// Initialize the updatedEmployee to hold the result of the update
		Employee updatedEmployee = null;

		try {
			// Attempt to update the employee using the employeeService
			updatedEmployee = employeeService.updateEmployeeByEmployeeId(employee, employeeId);

			if (updatedEmployee == null) {
				// If the updatedEmployee is null, log the error message and generate an
				// INTERNAL_SERVER_ERROR response
				logger.info(resourceBundle.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				// If the employee was successfully updated, log the updated employee details
				// and generate a CREATED response
				logger.info("updated employee : " + updatedEmployee);
				return ResponseHandler.generateResponse(updatedEmployee, HttpStatus.CREATED);
			}
		} catch (Exception ex) {
			// Log any exceptions that occur during the update process
			logger.error("Failed update Employee : " + ex.getMessage());
			// Throw a custom CPException with error code "err004" and the corresponding
			// error message from the resource bundle
			throw new CPException("err004", resourceBundle.getString("err004"));
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
	public ResponseEntity<Object> deleteEmployeeByEmployeeId(@PathVariable("employeeId") int employeeId)
			throws CPException {
		// Log the entry of the method
		logger.info("entered deleteEmployee  :" + employeeId);

		// Initialize the count to keep track of the number of employees deleted
		int count = 0;

		try {
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
	public ResponseEntity<Object> getEmployeeDetailsByEmployeeId(@PathVariable("employeeId") int employeeId)
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
			@PathVariable("employeeId") int employeeId) throws CPException {
		logger.debug("Entering updatePassword");
		logger.info("Entered  updatePassword :" + password);

		Password updatedPassword = null;
		try {
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
			@PathVariable("employeeId") int employeeId) throws CPException {
		logger.debug("Entering updateemployeepassword");
		logger.info("Entered  updateemployeepassword :" + dto);

		boolean updateEmployeePassword = false;

		try {

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
	public ResponseEntity<List<Object>> getAllEmployees() throws CPException {
		logger.debug("Entering getAllEmployees");

		List<Object> employees = null;
		try {
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
	public Password checkPassword(@PathVariable String username, @PathVariable String password) throws CPException {
		Password isPasswordValid = null;
		try {
			// Call the employeeService to validate the username and password.
			isPasswordValid = employeeService.getUsernameAndPasswordByUsernameAndPassword(username, password);

			// If the password is valid, return the Password object.
			if (isPasswordValid != null) {
				return isPasswordValid;
			} else {
				// If the password is not valid, return null.
				return null;
			}
		} catch (Exception ex) {
			// If an exception occurs while trying to validate the password,
			// log the error message and throw a custom exception (CPException) with an
			// error code and message.
			logger.error("Failed getting all employees: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all employees");
		}
	}

	/**
	 * Endpoint to handle password reset for a forgotten password.
	 *
	 * @param request - A Map containing username.
	 * @return A ResponseEntity indicating the success or failure of the password
	 *         reset.
	 */

	@PostMapping("/forgotpass")
	public ResponseEntity<String> forgotPasswordByUsername(@RequestBody Map<String, String> request)
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
				return ResponseEntity.badRequest().body("Email not found");
			}

			// Retrieve the employee ID from the password object
			int employeeId = password.getEmployeeId();

			// Retrieve the Employee object associated with the employee ID
			Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);

			// If no employee object is found, return a bad request response with "Invalid
			// username"
			if (employee == null) {
				return ResponseEntity.badRequest().body("Invalid username");
			}

			// Update the password for the employee
			if (!employeeService.updatePassword(employeeId)) {
				// Generate an internal server error response if the password update fails
				ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			// Return a successful response with HTTP status OK
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception ex) {
			// Log and handle any exceptions that occur during the process
			logger.error("Failed getting all employees: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all employees");
		}
	}

}
