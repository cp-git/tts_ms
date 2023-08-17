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
import com.cpa.ttsms.entity.Password;

public interface EmployeeService {

	// Updates an employee's information based on the provided Employee object
	Employee updateEmployeeByEmployeeId(Employee employee, int employeeId);

	// Saves a new employee along with their password information using the provided
	// DTO.
	EmployeeAndPasswordDTO saveEmployeeAndPassword(EmployeeAndPasswordDTO dto);

	// Retrieves an Employee object and its associated Password information by
	// employeeId.
	EmployeeAndPasswordDTO getEmployeeAndPasswordByEmployeeId(int employeeId);

	// Retrieves an Employee object by employeeId.
	Employee getEmployeeByEmployeeId(int employeeId);

	// Retrieves a list of all employees and their associated password information.
	List<Object> getAllEmployeesAndPasswords();

	// Retrieves a list of all employees and their associated password information.
	List<Object> getAllEmployees();

	// Deletes an employee by their employeeId.
	int deleteEmployeeByEmployeeId(int employeeId);

	Password updatePasswordByEmployeeId(Password password, int employeeId);

	boolean updateEmployeeAndPasswordByEmployeeId(EmployeeAndPasswordDTO dto, int employeeId);

	// This method retrieves a Password object based on a given username and
	// password.
	Password getUsernameAndPasswordByUsernameAndPassword(String username, String password);

	// Retrieve data By username
	Password getPasswordObjectByUsername(String username);

	// update password By employeeId
	boolean updatePassword(int employeeId);

}