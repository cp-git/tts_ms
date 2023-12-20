/**
 * @author  - Code Generator
 * @createdOn -  18/12/2023
 * @Description Entity class for Taxtype
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Taxtype;

@Repository
public interface TaxtypeRepo extends JpaRepository<Taxtype, Integer> {

	public Taxtype findByTaxTypeId(int taxtypeid);

	public List<Taxtype> findAll();

	public List<Taxtype> findByCompanyId(int companyId);

//	@Transactional
//	@Modifying
//	@Query(value = "UPDATE taxtype SET is_active=false WHERE taxtypeid = ?1", nativeQuery = true)
//	public int deleteTaxtypeBytaxtypeid(int taxtypeid);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM taxtype WHERE taxtypeid = ?", nativeQuery = true)
	public int deleteTaxTypeById(int taxtypeid);

}
