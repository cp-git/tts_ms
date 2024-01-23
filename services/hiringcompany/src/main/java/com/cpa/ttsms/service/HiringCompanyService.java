/**
 * @author  - Code Generator
 * @createdOn -  17/01/2024
 * @Description Entity class for HiringCompany Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.HiringCompany;

public interface HiringCompanyService {

	HiringCompany createHiringCompany(HiringCompany hiringCompany);

	HiringCompany getHiringCompanyByHiringCompanyId(int hiringcompanyid);

	List<Object> getAllHiringCompanysByCompanyId(int companyId);

	HiringCompany updateHiringCompanyByHiringCompanyId(HiringCompany hiringCompany, int hiringcompanyid);

	int deleteHiringCompanyByHiringCompanyId(int hiringcompanyid);

}