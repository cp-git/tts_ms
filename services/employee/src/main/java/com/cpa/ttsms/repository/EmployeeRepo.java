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

	public Employee findByEmployeeId(int employeeId);

	public List<Employee> findAll();

	@Transactional
	@Modifying
	@Query(value = "UPDATE employee SET is_active=false WHERE empid = ?1", nativeQuery = true)
	public int deleteEmployeeByEmployeeId(int employeeId);

}
