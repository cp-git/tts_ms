/**
 * @author  - Code Generator
 * @createdOn -  25/07/2023
 * @Description Entity class for Employee Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cpa.ttsms.dto.EmployeeAndEmployeePhotosDTO;
import com.cpa.ttsms.dto.EmployeeAndPasswordDTO;
import com.cpa.ttsms.dto.EmployeePasswordAndEmployeePhotosDTO;
import com.cpa.ttsms.entity.Employee;
import com.cpa.ttsms.entity.EmployeePhotos;
import com.cpa.ttsms.entity.Password;

/**
 * Service interface for managing employee-related operations.
 */
public interface EmployeeService {

	/**
	 * Updates an employee's information based on the provided Employee object.
	 *
	 * @param employee   The Employee object containing updated information.
	 * @param employeeId The ID of the employee to update.
	 * @return The updated Employee object.
	 */
	// Employee updateEmployeeByEmployeeId(Employee employee, int employeeId);

	/**
	 * Updates an employee's information, including a profile photo, based on the
	 * provided DTO.
	 *
	 * @param employeeAndEmployeePhotosDTO The DTO containing employee data and
	 *                                     photo information.
	 * @param employeeId                   The ID of the employee to update.
	 * @param file                         The profile photo file to update, or null
	 *                                     if not updating the photo.
	 * @return The updated Employee and photo DTO object, or null if an error
	 *         occurs.
	 */
	EmployeeAndEmployeePhotosDTO updateEmployeeByEmployeeId(EmployeeAndEmployeePhotosDTO employeeAndEmployeePhotosDTO,
			int employeeId, MultipartFile file);

	/**
	 * Saves a new employee along with their password information using the provided
	 * DTO.
	 *
	 * @param dto The EmployeeAndPasswordDTO object containing employee and password
	 *            details.
	 * @return The saved EmployeeAndPasswordDTO object.
	 */
	EmployeeAndPasswordDTO saveEmployeeAndPassword(EmployeeAndPasswordDTO dto);

	/**
	 * Creates a new employee with profile photo and password information.
	 *
	 * @param employeePasswordAndEmployeePhotosDTO The DTO containing employee data,
	 *                                             photo information, and password.
	 * @param file                                 The profile photo file.
	 * @return The created EmployeePasswordAndEmployeePhotosDTO or null if an error
	 *         occurs.
	 */
	EmployeePasswordAndEmployeePhotosDTO createEmployee(
			EmployeePasswordAndEmployeePhotosDTO employeePasswordAndEmployeePhotosDTO, MultipartFile file);

	/**
	 * Retrieves an Employee object and its associated Password information by
	 * employeeId.
	 *
	 * @param employeeId The ID of the employee to retrieve.
	 * @return The EmployeeAndPasswordDTO object containing employee and password
	 *         details.
	 */
	EmployeeAndPasswordDTO getEmployeeAndPasswordByEmployeeId(int employeeId);

	/**
	 * Retrieves an Employee object by employeeId.
	 *
	 * @param employeeId The ID of the employee to retrieve.
	 * @return The Employee object.
	 */
	Employee getEmployeeByEmployeeId(int employeeId);

	/**
	 * Retrieves a list of all employees and their associated password information.
	 *
	 * @return A list of objects containing employee and password details.
	 */
	List<Object> getAllEmployeesAndPasswords();

	/**
	 * Retrieves a list of all employees.
	 *
	 * @return A list of Employee objects.
	 */
	List<Object> getAllEmployees();

	/**
	 * Deletes an employee by their employeeId.
	 *
	 * @param employeeId The ID of the employee to delete.
	 * @return The number of employees deleted (usually 1 or 0).
	 */
	int deleteEmployeeByEmployeeId(int employeeId);

	/**
	 * Updates an employee's password by employeeId.
	 *
	 * @param password   The updated Password object.
	 * @param employeeId The ID of the employee to update.
	 * @return true if the password is updated successfully; false otherwise.
	 */
	Password updatePasswordByEmployeeId(Password password, int employeeId);

	/**
	 * Updates an employee's information and password by employeeId.
	 *
	 * @param dto        The EmployeeAndPasswordDTO object containing updated
	 *                   employee and password details.
	 * @param employeeId The ID of the employee to update.
	 * @return true if both employee and password are updated successfully; false
	 *         otherwise.
	 */
	boolean updateEmployeeAndPasswordByEmployeeId(EmployeeAndPasswordDTO dto, int employeeId);

	/**
	 * Retrieves a Password object based on a given username and password.
	 *
	 * @param username The username.
	 * @param password The password.
	 * @return The Password object.
	 */
	Password getPasswordByUsernameAndPassword(String username, String password);

	/**
	 * Retrieves a Password object based on a given username.
	 *
	 * @param username The username.
	 * @return The Password object.
	 */
	Password getPasswordObjectByUsername(String username);

	/**
	 * Updates the password for an employee by employeeId.
	 *
	 * @param employeeId The ID of the employee.
	 * @return true if the password is updated successfully; false otherwise.
	 */
	boolean updatePassword(int employeeId);

	EmployeePhotos getPhotosByEmployeeId(int employeeId);

	List<Object> getAllEmployeeOfCompanyByCompanyId(int companyId);

	List<Object> getAllEmployeeAndPasswordByCompanyId(int companyId);

	List<Object> getEmployeesOnBenchByCompanyId(int companyId);
	
	Password savePasswordForChangePassword(Password password);

	
}
