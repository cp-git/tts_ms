/**
 * @author  - Code Generator
 * @createdOn -  24/07/2023
 * @Description Entity class for Company
 * 
 */

package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpa.ttsms.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {

	// Retrieves a company by their id
	public Company findByCompanyId(int id);

	// delete company by their company code
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Company WHERE code = ?1", nativeQuery = true)
	public int deleteByCompanyCode(String companyCode);

	// Retrieves a company by their code
	public Company findByCompanyCode(String companyCode);
}
