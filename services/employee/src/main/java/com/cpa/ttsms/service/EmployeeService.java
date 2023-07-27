/**
 * @author  - Code Generator
 * @createdOn -  25/07/2023
 * @Description Entity class for Employee Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.Employee;
import com.cpa.ttsms.entity.EmployeeAndPassword;

public interface EmployeeService {

	Employee createEmployee(Employee employee);

	Employee getEmployeeByempId(int employeeId);

	// List<Object> getAllEmployees();

	Employee updateEmployeeByEmployeeId(Employee employee, int employeeId);

	// int deleteEmployeeByempId(int employeeId);

	EmployeeAndPassword saveEmployeeAndPassword(EmployeeAndPassword dto);

	EmployeeAndPassword getEmployeeAndPasswordByEmployeeId(int employeeId);

	List<Object> getAllEmployeesAndPasswords();

}