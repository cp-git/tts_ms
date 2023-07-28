/**
 * @author - Code Generator
 * @createdOn 25/07/2023
 * @Description Controller class for employee
 * 
 */

package com.cpa.ttsms.controller;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.dto.EmployeeAndPasswordDTO;
import com.cpa.ttsms.entity.Employee;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.EmployeeService;

@RestController
@RequestMapping("/ttsms")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;;

	private ResourceBundle resourceBunde;
	private static Logger logger;

	EmployeeController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(EmployeeController.class);
	}

	/**
	 * Creates a new employee along with their password information based on the provided EmployeeAndPasswordDTO object,
	 * and generates an appropriate response.
	 *
	 * @param dto - The EmployeeAndPasswordDTO object containing the details of the employee and password to be created.
	 *
	 * @return ResponseEntity containing a CREATED status if the employee was successfully created,
	 *         otherwise returns an INTERNAL_SERVER_ERROR response.
	 *
	 * @throws CPException If there is an error while creating the employee or generating the response.
	 */
	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeAndPasswordDTO dto) throws CPException {
	    // Log the entry of the method
	    logger.debug("Entering createEmployee");
	    logger.info("data of creating Employee  :" + dto.toString());

	    try {
	        // Attempt to create the new employee along with their password using the employeeService
	        employeeService.saveEmployeeAndPassword(dto);
	        logger.info("Employee created :" + dto);
	        // Generate a CREATED response
	        return ResponseHandler.generateResponse(HttpStatus.CREATED);
	    } catch (Exception e) {
	        // Log any exceptions that occur during the creation process
	        logger.error(resourceBunde.getString("err003"));
	        // Generate an INTERNAL_SERVER_ERROR response with an error message
	        return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
	    }
	}

	
	

	
	
	/**
	 * Retrieves an EmployeeAndPasswordDTO object containing employee and password information
	 * based on the provided employeeId, and generates an appropriate response.
	 *
	 * @param employeeId - The ID of the employee to retrieve.
	 *
	 * @return ResponseEntity containing the EmployeeAndPasswordDTO object with an OK status if the employee is found,
	 *         otherwise returns a NOT_FOUND response.
	 */
	@GetMapping("/employeepass/{employeeId}")
	public ResponseEntity<EmployeeAndPasswordDTO> getEmployeeWithPasswordById(@PathVariable int employeeId) {
	    // Retrieve the EmployeeAndPasswordDTO using the employeeService
	    EmployeeAndPasswordDTO dto = employeeService.getEmployeeAndPasswordByEmployeeId(employeeId);
	    if (dto != null) {
	        // If the EmployeeAndPasswordDTO is not null, generate a response with the DTO and a 200 OK status
	        return ResponseEntity.ok(dto);
	    } else {
	        // If the EmployeeAndPasswordDTO is null, generate a NOT_FOUND response
	        return ResponseEntity.notFound().build();
	    }
	}

	
	/**
	 * Retrieves a list of all employees and their associated password information.
	 *
	 * @return ResponseEntity containing the list of EmployeeAndPasswordDTO objects with an OK status if successful.
	 */
	@GetMapping("/allemployee")
	public ResponseEntity<List<Object>> getAllEmployeeAndPasswordData() {
	    // Retrieve a list of all employees and their associated password information using the employeeService
	    List<Object> employeePasswordDTOList = employeeService.getAllEmployeesAndPasswords();
	    
	    // Generate a response with the list of EmployeeAndPasswordDTO objects and 200 OK status
	    return ResponseEntity.ok(employeePasswordDTOList);
	}


	/**
	 * Updates an employee based on the provided employeeId and Employee object, and generates an appropriate response.
	 *
	 * @param employee   - The Employee object containing the updated details of the employee.
	 * @param employeeId - The ID of the employee to be updated.
	 *
	 * @return ResponseEntity containing the updated Employee details with a CREATED status if successful,
	 *         otherwise returns an INTERNAL_SERVER_ERROR response.
	 *
	 * @throws CPException If there is an error while updating the employee or generating the response.
	 */
	@PutMapping("/employee/update/{employeeId}")
	public ResponseEntity<Object> updateEmployeeByempId(@RequestBody Employee employee,
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
	            // If the updatedEmployee is null, log the error message and generate an INTERNAL_SERVER_ERROR response
	            logger.info(resourceBunde.getString("err004"));
	            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
	        } else {
	            // If the employee was successfully updated, log the updated employee details and generate a CREATED response
	            logger.info("updated employee : " + updatedEmployee);
	            return ResponseHandler.generateResponse(updatedEmployee, HttpStatus.CREATED);
	        }
	    } catch (Exception ex) {
	        // Log any exceptions that occur during the update process
	        logger.error("Failed update Employee : " + ex.getMessage());
	        // Throw a custom CPException with error code "err004" and the corresponding error message from the resource bundle
	        throw new CPException("err004", resourceBunde.getString("err004"));
	    }
	}

	
	/**
	 * Deletes an employee based on the provided employeeId and generates an appropriate response.
	 *
	 * @param employeeId - The ID of the employee to be deleted.
	 *
	 * @return ResponseEntity containing NO_CONTENT status if the employee was successfully deleted,
	 *         otherwise returns an INTERNAL_SERVER_ERROR response.
	 *
	 * @throws CPException If there is an error while deleting the employee or generating the response.
	 */
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<Object> deleteEmployeeByempId(@PathVariable("employeeId") int employeeId) throws CPException {
	    // Log the entry of the method
	    logger.info("entered deleteEmployee  :" + employeeId);

	    // Initialize the count to keep track of the number of employees deleted
	    int count = 0;

	    try {
	        // Attempt to delete the employee using the employeeService
	        count = employeeService.deleteEmployeeByEmployeeId(employeeId);
	        if (count >= 1) {
	            // If one or more employees were deleted, log the deletion and generate a NO_CONTENT response
	            logger.info("deleted Employee : empId = " + employeeId);
	            return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
	        } else {
	            // If no employee was deleted, log the error message and generate an INTERNAL_SERVER_ERROR response
	            logger.info(resourceBunde.getString("err005"));
	            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
	        }
	    } catch (Exception ex) {
	        // Log any exceptions that occur during the deletion process
	        logger.error("Failed to delete Employee :" + ex.getMessage());
	        // Throw a custom CPException with error code "err005" and the corresponding error message from the resource bundle
	        throw new CPException("err005", resourceBunde.getString("err005"));
	    }
	}

	
	
	/**
	 * Retrieves employee details based on the provided employeeId and generates an appropriate response.
	 *
	 * @param employeeId - The ID of the employee to retrieve.
	 *
	 * @return ResponseEntity containing the Employee details with a 200 OK status if found, otherwise returns a NOT_FOUND response.
	 *
	 * @throws CPException If there is an error while fetching the employee or generating the response.
	 */
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Object> getEmployeeDetailsByEmployeeIde(@PathVariable("employeeId") int employeeId) throws CPException {
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
	        // Throw a custom CPException with error code "err001" and the corresponding error message from the resource bundle
	        throw new CPException("err001", resourceBunde.getString("err001"));
	    }
	}

}
