/**
 * @author  - Code Generator
 * @createdOn -  12/12/2023
 * @Description Entity class for Visa
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Visa;

@Repository
public interface VisaRepo extends JpaRepository<Visa, Integer> {

	public Visa findByVisaId(int visaId);

	public List<Visa> findAll();
	
	public List<Visa> findByCompanyId(int companyId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM visa WHERE visaid = ?", nativeQuery = true)
	public int deleteVisaById(int visaId);

}
