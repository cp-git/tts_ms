/**
 * @author  - Code Generator
 * @createdOn -  12/12/2023
 * @Description Entity class for Visa Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.Visa;

public interface VisaService {

	Visa createVisa(Visa visa);

	Visa getVisaByvisaId(int visaId);

	List<Object> getAllVisas();

	Visa updateVisaByvisaId(Visa visa, int visaId);

	int deleteVisaByvisaId(int visaId);

	List<Object> getAllVisasByCompanyId(int companyId);

}