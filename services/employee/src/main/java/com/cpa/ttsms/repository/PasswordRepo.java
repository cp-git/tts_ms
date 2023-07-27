package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpa.ttsms.entity.Password;

public interface PasswordRepo extends JpaRepository<Password, Integer> {

	public Password findByEmployeeId(int employeeId);

	// public List<Emp> findAll();

//	@Transactional
//	@Modifying
//	@Query(value = "UPDATE  SET is_active=false WHERE empid = ?1", nativeQuery = true)
//	public int deleteEmployeeByEmployeeId(int employeeId);

}
