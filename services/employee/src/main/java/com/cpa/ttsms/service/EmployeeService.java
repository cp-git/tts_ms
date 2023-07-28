/**
 * @author  - Code Generator
 * @createdOn -  25/07/2023
 * @Description Entity class for Employee Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.dto.EmployeeAndPasswordDTO;
import com.cpa.ttsms.entity.Employee;

public interface EmployeeService {

	// Updates an employee's information based on the provided Employee object
	Employee updateEmployeeByEmployeeId(Employee employee, int employeeId);
	
	// Saves a new employee along with their password information using the provided DTO.
	EmployeeAndPasswordDTO saveEmployeeAndPassword(EmployeeAndPasswordDTO dto); 
	
	// Retrieves an Employee object and its associated Password information by employeeId.
	EmployeeAndPasswordDTO getEmployeeAndPasswordByEmployeeId(int employeeId); 
	
	// Retrieves an Employee object by employeeId.
	Employee getEmployeeByEmployeeId(int employeeId);
	
	// Retrieves a list of all employees and their associated password information.
	List<Object> getAllEmployeesAndPasswords(); 
	
	// Deletes an employee by their employeeId.
	int deleteEmployeeByEmployeeId(int employeeId); 
	

}