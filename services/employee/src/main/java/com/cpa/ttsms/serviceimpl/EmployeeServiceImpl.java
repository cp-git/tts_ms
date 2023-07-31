/**
 * @author - Code Generator
 * @createdOn 25/07/2023
 * @Description Controller class for employee
 * 
 */

package com.cpa.ttsms.serviceimpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpa.ttsms.dto.EmployeeAndPasswordDTO;

//import com.cpa.ttsms.controller.EmployeeController;
import com.cpa.ttsms.entity.Employee;
import com.cpa.ttsms.entity.Password;
import com.cpa.ttsms.repository.EmployeeRepo;
import com.cpa.ttsms.repository.PasswordRepo;
import com.cpa.ttsms.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	private static Logger logger;
	
	@Autowired
	private PasswordRepo passwordRepository;

	public EmployeeServiceImpl() {
		logger = Logger.getLogger(EmployeeServiceImpl.class);
	}

	/**
	 * Create a new country entry in the Country table.
	 *
	 * @param country - The Country object containing the details of the country to
	 *                be created.
	 *
	 * @return The newly created Country object if successful, otherwise null.
	 */
	
	
	@Override
	public Employee updateEmployeeByEmployeeId(Employee employee, int employeeId) {
	    // TODO Auto-generated method stub
	    logger.debug("Entering updateEmployee");

	    // Initialize variables
	    Employee toUpdatedEmployee = null;
	    Employee updatedEmployee = null;

	    // Find the existing employee based on the provided employeeId
	    toUpdatedEmployee = employeeRepo.findByEmployeeId(employeeId);
	    logger.info("existing Employee :: " + toUpdatedEmployee);

	    // Check if an employee with the given ID exists
	    if (toUpdatedEmployee != null) {
	        logger.debug("setting new data of Employee to existing Employee");

	        // Update the existing employee's data with the provided employee data
	        toUpdatedEmployee.setCountryId(employee.getCompanyId());
	        toUpdatedEmployee.setCompanyId(employee.getCompanyId());
	        toUpdatedEmployee.setFirstName(employee.getFirstName());
	        toUpdatedEmployee.setLastName(employee.getLastName());
	        toUpdatedEmployee.setBirthDate(employee.getBirthDate());
	        toUpdatedEmployee.setEmployeeEmail(employee.getEmployeeEmail());

	        // Save the updated employee in the database
	        updatedEmployee = employeeRepo.save(toUpdatedEmployee);

	        logger.info("updated Employee :" + updatedEmployee);
	    }

	    return updatedEmployee;
	}

	/**
	 * Saves the employee and their password information.
	 *
	 * @param dto - The EmployeeAndPasswordDTO object containing the details of the employee and password to be saved.
	 *
	 * @return The original EmployeeAndPasswordDTO object if successful.
	 */
	
	@Transactional
	@Override
	public EmployeeAndPasswordDTO saveEmployeeAndPassword(EmployeeAndPasswordDTO dto) {
	    // TODO Auto-generated method stub
		
				
	    // Create a new Employee object and set its properties from the provided DTO
	    Employee employee = new Employee();
	    employee.setCountryId(dto.getCountryId());
	    employee.setCompanyId(dto.getCompanyId());
	    employee.setFirstName(dto.getFirstName());
	    employee.setLastName(dto.getLastName());

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
	    employeeRepo.save(employee);

	    // Create a new Password object and set its properties from the DTO
	    Password password = new Password();
	    password.setEmployeeId(employee.getEmployeeId());
	    password.setUsername(dto.getUsername());
	    password.setPassword(dto.getPassword());

	    // Save the password in the database
	    passwordRepository.save(password);
	    return dto;
	}

	/**
	 * Retrieve an Employee object and its associated Password information by employeeId.
	 *
	 * @param employeeId - The ID of the employee to retrieve.
	 *
	 * @return The EmployeeAndPasswordDTO object containing the employee and password information if found, otherwise null.
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
	 * @return List of EmployeeAndPasswordDTO objects containing employee and password information.
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
	            // Format dob as a String (assuming it's already in Date format in the entity)
	            // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            dto.setPasswordId(password.getPasswordId());
	            dto.setBirthDate(emp.getBirthDate());
	          //  dto.setBirthDate(emp.getBirthDate().toString());
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
	    int count =  employeeRepo.deleteEmployeeByEmployeeId(employeeId);
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
	public Password updatePasswordByEmployeeId(Password password ,int employeeId) {
		// TODO Auto-generated method stub
		Password toUpdatePassword = null;
		Password updatedPassword = null;
		
		 toUpdatePassword = passwordRepository.findByEmployeeId(employeeId);
		 
		 if(toUpdatePassword != null) {
			 
			 toUpdatePassword.setUsername(password.getUsername());
			 toUpdatePassword.setPassword(password.getPassword());
			 
			 updatedPassword = passwordRepository.save(toUpdatePassword);
			 logger.info("updated Employee :" + updatedPassword);
		 }
		 
		return updatedPassword;
	}

	@Override
	@Transactional
	public boolean  updateEmployeeAndPasswordByEmployeeId(EmployeeAndPasswordDTO dto, int employeeId) {
		// TODO Auto-generated method stub
		
		Employee toUpdatedEmployee = null;
		Employee updatedEmployee = null ;
		Password toUpdatePassword = null;
		Password updatedPassowrd = null;
				
		boolean isSuccess =false;
		toUpdatedEmployee = employeeRepo.findByEmployeeId(employeeId);
			System.out.println("here" + toUpdatedEmployee);
		if(toUpdatedEmployee != null) {
			
			toUpdatedEmployee.setCountryId(dto.getCountryId());
			toUpdatedEmployee.setCompanyId(dto.getCompanyId());;
			toUpdatedEmployee.setFirstName(dto.getFirstName());
			toUpdatedEmployee.setLastName(dto.getLastName());
		//	toUpdatedEmployee.setBirthDate(employee.getBirthDate());
			toUpdatedEmployee.setEmployeeEmail(dto.getEmployeeEmail());
		
			updatedEmployee= employeeRepo.save(toUpdatedEmployee);
			System.out.println(toUpdatedEmployee + " ////////////////////////");
			
			
			
		}
		toUpdatePassword = passwordRepository.findByEmployeeId(employeeId);
		
		if(toUpdatePassword != null) {
		toUpdatePassword.setUsername(dto.getUsername());;
		toUpdatePassword.setPassword(dto.getPassword());
		updatedPassowrd = passwordRepository.save(toUpdatePassword);
		System.out.println(toUpdatePassword + " ***************************");
		
		}
		
		
		if(updatedEmployee!=null && updatedPassowrd!=null) {
			isSuccess = true;
		}
		return isSuccess;
	}

	@Override
	public List<Object> getAllEmployees() {
		// TODO Auto-generated method stub
		logger.debug("Entering getAllCountries");

		List<Employee> employees = employeeRepo.findAll(); // Retrieve all active countries from the database.
		logger.info("Fetched all active countries: " + employees);

		// Create a new list to store objects
		List<Object> objects = new ArrayList<>(employees); // Convert the list of Country objects to a list of generic
															// Objects.

		logger.info("Fetched all active countries: " + employees);
		return objects; // Return the list of generic Objects.
	}


}
