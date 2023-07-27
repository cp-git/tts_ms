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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.Employee;
import com.cpa.ttsms.entity.EmployeeAndPassword;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.EmployeeService;

@CrossOrigin
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

//	@PostMapping("/employee")
//	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) throws CPException {
//		logger.debug("Entering createEmployee");
//		logger.info("data of creating Employee  :" + employee.toString());
//
//		Employee createdEmployee = null;
//		try {
//
//			Employee toCheckEmployee = employeeService.getEmployeeByempId(employee.getEmployeeId());
//			logger.debug("existing employee :" + toCheckEmployee);
//
//			if (toCheckEmployee == null) {
//
//			// TODO: Uncomment below 2 lines and change the method name as per your Entity class
//			//	employee.setCreatedby("admin");
//			//	employee.setUpdatedby("admin");
//
//				createdEmployee = employeeService.createEmployee(employee);
//				logger.info("Employee created :" + createdEmployee);
//
//				return ResponseHandler.generateResponse(createdEmployee, HttpStatus.CREATED);
//
//			} else {
//
//				logger.error(resourceBunde.getString("err003"));
//				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
//			}
//
//		} catch (Exception ex) {
//			logger.error("Failed Employee creation : " + ex.getMessage());
//			throw new CPException("err003", resourceBunde.getString("err003"));
//		}
//	}

	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeAndPassword dto) throws CPException {
		logger.debug("Entering createEmployee");
		logger.info("data of creating Employee  :" + dto.toString());

		try {
			employeeService.saveEmployeeAndPassword(dto);
			return ResponseEntity.ok("Data saved successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving data.");
		}
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<EmployeeAndPassword> getEmployeeWithPasswordById(@PathVariable int employeeId) {
		EmployeeAndPassword dto = employeeService.getEmployeeAndPasswordByEmployeeId(employeeId);
		if (dto != null) {
			return ResponseEntity.ok(dto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Object>> getAllEmployeeAndPasswordData() {
		List<Object> employeePasswordDTOList = employeeService.getAllEmployeesAndPasswords();
		return ResponseEntity.ok(employeePasswordDTOList);
	}

//	@GetMapping("/employee/{empId}")
//	public ResponseEntity<Object> getEmployeeByempId(@PathVariable("empId") String empId)
//			throws CPException {
//		logger.debug("Entering getEmployeeByempId");
//		logger.info("entered user name :" + empId);
//		
//		Employee employee = null;
//
//		try {
//
//			employee = employeeService.getEmployeeByempId(empId);
//			logger.info("fetched Employee :" + employee);
//
//			if (employee != null) {
//				logger.debug("Employee fetched generating response");
//				return ResponseHandler.generateResponse(employee, HttpStatus.OK);
//			} else {
//				logger.debug("Employee not found");
//				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
//			}
//
//		} catch (Exception ex) {
//
//			logger.error("Failed getting employee : " + ex.getMessage());
//			throw new CPException("err001", resourceBunde.getString("err001"));
//		}
//
//	}
//
//	@GetMapping("/employee")
//	public ResponseEntity<List<Object>> getAllEmployees(@RequestParam(name = "empId") String empId)
//			throws CPException {
//		logger.debug("Entering getAllEmployee");
//		logger.info("Parameter  :" + empId);
//		
//		List<Object> employees = null;
//
//		try {
//
//			if (empId.equalsIgnoreCase("all")) {
//
//				employees = employeeService.getAllEmployees();
//				logger.info("Fetched all Employee :" + employees);
//
//				return ResponseHandler.generateListResponse(employees, HttpStatus.OK);
//			} else {
//
//				logger.info(resourceBunde.getString("err002"));
//				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
//			}
//
//		} catch (Exception ex) {
//
//			logger.error("Failed getting all employees : " + ex.getMessage());
//			throw new CPException("err002", resourceBunde.getString("err002"));
//
//		}
//	}
//
//	@DeleteMapping("/employee/{empId}")
//	public ResponseEntity<Object> deleteEmployeeByempId(@PathVariable("empId") String empId) throws CPException {
//		logger.debug("Entering deleteAuthUser");
//		logger.info("entered deleteEmployee  :" + empId);
//		//TODO - implement the business logic
//		
//		int count = 0;
//
//		try {
//			count = employeeService.deleteEmployeeByempId(empId);
//			if (count >= 1) {
//				logger.info("deleted Employee : empId = " + empId);
//				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
//			} else {
//				logger.info(resourceBunde.getString("err005"));
//				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
//			}
//
//		} catch (Exception ex) {
//			logger.error("Failed to delete Employee :" + ex.getMessage());
//			throw new CPException("err005", resourceBunde.getString("err005"));
//		}
//		
//
//	}
//
	@PutMapping("/employee/update/{employeeId}")
	public ResponseEntity<Object> updateEmployeeByempId(@RequestBody Employee employee,
			@PathVariable("employeeId") int employeeId) throws CPException {
		logger.debug("Entering updateEmployee");
		logger.info("entered  updateEmployee :" + employee);

		Employee updatedEmployee = null;

		try {
//			updatedEmployee = employeeService.updateEmployeeByEmployeeId(employee, employeeId);
//
//			if (updatedEmployee == null) {
//				logger.info(resourceBunde.getString("err004"));
//				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
//			} else {
//				logger.info("updated employee : " + updatedEmployee);
//				return ResponseHandler.generateResponse(updatedEmployee, HttpStatus.CREATED);
//			}
			return ResponseHandler.generateResponse(updatedEmployee, HttpStatus.CREATED);

		} catch (Exception ex) {
			logger.error("Failed update Employee : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}

}
