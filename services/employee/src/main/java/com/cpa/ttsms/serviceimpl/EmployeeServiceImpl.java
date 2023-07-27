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

//import com.cpa.ttsms.controller.EmployeeController;
import com.cpa.ttsms.entity.Employee;
import com.cpa.ttsms.entity.EmployeeAndPassword;
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
	 * @param : Employee employee
	 * @return : Employee createdEmployee
	 * @description : For creating/inserting entry in employee table
	 */
	@Override
	public Employee createEmployee(Employee employee) {
		logger.debug("Entering createEmployee");
		Employee createdEmployee = null;

		// employee.setEmployeeCreatedBy("admin");
		// employee.setEmployeeModifiedBy("admin");

		createdEmployee = employeeRepo.save(employee);
		logger.info("created Employee :" + createdEmployee);
		return createdEmployee;
	}

	/**
	 * @param : String empid
	 * @return : Employee employee
	 * @description : For get entry in employee table
	 */
	@Override
	public Employee getEmployeeByempId(int employeeId) {
		logger.debug("Entering getEmployeeByempId");

		Employee employee = employeeRepo.findByEmployeeId(employeeId);
		logger.info("Founded employee :" + employee);

		return employee;
	}

	/**
	 * @return : List<Object> employee
	 * @description : For fetching all employee which are active state from employee
	 *              table
	 */
//	@Override
//	public List<Object> getAllEmployees() {
//		logger.debug("Entering getAllEmployees");
//
//		List<Object> employees = employeeRepo.findByEmployeeIsActiveTrue();
//		logger.info("Fetched all active employee :" + employees);
//		return employees;
//	}

	/**
	 * @param : Employee to update
	 * @return : employee
	 * @description : For updating employee of employee table
	 */
	@Override
	public Employee updateEmployeeByEmployeeId(Employee employee, int employeeId) {
		logger.debug("Entering updateEmployee");

		Employee toUpdatedEmployee = null;
		Employee updatedEmployee = null;

		toUpdatedEmployee = employeeRepo.findByEmployeeId(employeeId);
		logger.info("exisitng Employee :: " + toUpdatedEmployee);

		if (toUpdatedEmployee != null) {
			logger.debug("setting new data of Employee to exisitng Employee");

			toUpdatedEmployee.setCountryId(employee.getCompanyId());
			toUpdatedEmployee.setCompanyId(employee.getCompanyId());
			toUpdatedEmployee.setFirstName(employee.getFirstName());
			toUpdatedEmployee.setLastName(employee.getLastName());
			toUpdatedEmployee.setBirthDate(employee.getBirthDate());
			toUpdatedEmployee.setEmployeeEmail(employee.getEmployeeEmail());
//			employee.setModifiedBy("admin");

			updatedEmployee = employeeRepo.save(toUpdatedEmployee);

			logger.info("updated Employee :" + updatedEmployee);
		}

		return updatedEmployee;
	}

	/**
	 * @param : String empid
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of Employee
	 * 
	 */

//	@Override
//	public int deleteEmployeeByempId(int employeeId) {
//		logger.debug("Entering deleteEmployeeByempId");
//
//		int count =  employeeRepo.deleteEmployeeByempId(employeeId);
//		logger.info("deleted Employee count : " + count);
//		return count;
//	}

	@Transactional
	@Override
	public EmployeeAndPassword saveEmployeeAndPassword(EmployeeAndPassword dto) {
		// TODO Auto-generated method stub

		Employee saveEmployee = null;
		Employee employee = new Employee();
		employee.setCountryId(dto.getCountryId());
		employee.setCompanyId(dto.getCompanyId());
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());

		Date dob;
		try {
			dob = new SimpleDateFormat("yyyy-dd-MM").parse(dto.getBirthDate());
			employee.setBirthDate(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		employee.setEmployeeEmail(dto.getEmployeeEmail());
		Employee savedEmployee = employeeRepo.save(employee);

		Password password = new Password();
		password.setEmployeeId(employee.getEmployeeId());
		password.setUsername(dto.getUsername());
		password.setPassword(dto.getPassword());
		passwordRepository.save(password);
		return dto;

	}

	public EmployeeAndPassword getEmployeeAndPasswordByEmployeeId(int employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		System.out.println(employee + "==============================================");
		if (employee.isPresent()) {
			EmployeeAndPassword dto = new EmployeeAndPassword();
			Employee emp = employee.get();
			Password password = passwordRepository.findByEmployeeId(employeeId);
			System.out.println(password + "************************************************");
			if (password != null) {
				dto.setEmployeeId(emp.getEmployeeId());
				dto.setCountryId(emp.getCountryId());
				dto.setCompanyId(emp.getCompanyId());
				dto.setFirstName(emp.getFirstName());
				dto.setLastName(emp.getLastName());
				// Format dob as a String (assuming it's already in Date format in the entity)
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dto.setBirthDate(dateFormat.format(emp.getBirthDate()));
				dto.setPasswordId(password.getPasswordId());
				dto.setEmployeeEmail(emp.getEmployeeEmail());
				dto.setUsername(password.getUsername());
				dto.setPassword(password.getPassword());

				System.out.println(dto + "99999999999999999999999999999");

				return dto;
			}
		}
		return null;
	}

	@Override
	public List<Object> getAllEmployeesAndPasswords() {
		List<Object> employeePasswordDTOList = new ArrayList<>();
		List<Employee> employees = employeeRepo.findAll();

		for (Employee emp : employees) {
			Password password = passwordRepository.findByEmployeeId(emp.getEmployeeId());
			if (password != null) {
				EmployeeAndPassword dto = new EmployeeAndPassword();
				dto.setEmployeeId(emp.getEmployeeId());
				dto.setCountryId(emp.getCountryId());
				dto.setCompanyId(emp.getCompanyId());
				dto.setFirstName(emp.getFirstName());
				dto.setLastName(emp.getLastName());
				// Format dob as a String (assuming it's already in Date format in the entity)
				// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dto.setPasswordId(password.getPasswordId());
				dto.setBirthDate(emp.getBirthDate().toString());
				dto.setEmployeeEmail(emp.getEmployeeEmail());
				dto.setUsername(password.getUsername());
				dto.setPassword(password.getPassword());

				employeePasswordDTOList.add(dto);
			}
		}

		return employeePasswordDTOList;

	}

}
