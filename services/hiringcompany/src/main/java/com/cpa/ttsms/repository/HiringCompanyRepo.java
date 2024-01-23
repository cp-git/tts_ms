/**
 * @author  - Code Generator
 * @createdOn -  17/01/2024
 * @Description Entity class for HiringCompany
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.HiringCompany;

@Repository
public interface HiringCompanyRepo extends JpaRepository<HiringCompany, Integer> {

	public HiringCompany findByHiringCompanyId(int hiringCompanyId);

	public List<Object> findAllByCompanyId(int companyId);

	@Transactional
	@Modifying
	public int deleteByHiringCompanyId(int hiringCompanyId);

}
