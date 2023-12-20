/**
 * @author  - Code Generator
 * @createdOn -  18/12/2023
 * @Description Entity class for Taxtype Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.Taxtype;

public interface TaxtypeService {

	Taxtype createTaxtype(Taxtype taxtype);

	Taxtype getTaxtypeBytaxtypeid(int taxtypeid);

	List<Object> getAllTaxtypes();

	Taxtype updateTaxtypeBytaxtypeid(Taxtype taxtype, int taxtypeid);

	int deleteTaxtypeBytaxtypeid(int taxtypeid);

	List<Object> getAllTaxtypeByCompanyId(int companyId);

}