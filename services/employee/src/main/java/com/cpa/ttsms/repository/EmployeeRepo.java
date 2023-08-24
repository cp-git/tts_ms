/**
 * @author  - Code Generator
 * @createdOn -  25/07/2023
 * @Description Entity class for Employee
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	// Retrieves an employee by their employeeId.
	public Employee findByEmployeeId(int employeeId); 

	// Retrieves a list of all employees.
	public List<Employee> findAll(); 
	
	public Employee findByEmployeeEmail(String EmployeeEmail);
	

	// Deletes an employee by their employeeId.
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM employee WHERE id = ?1", nativeQuery = true)
	public int deleteEmployeeByEmployeeId(int employeeId); 
	
	List<Employee> findByCompanyId(int companyId);



}
