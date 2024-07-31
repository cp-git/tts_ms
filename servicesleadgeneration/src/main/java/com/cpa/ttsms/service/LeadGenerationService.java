/**
 * @author  - Code Generator
 * @createdOn -  19/12/2023
 * @Description Entity class for LeadGeneration Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.LeadGeneration;

public interface LeadGenerationService {

	LeadGeneration createLeadGeneration(LeadGeneration leadgeneration);

	LeadGeneration getLeadGenerationBylocationId(String locationid);

	List<Object> getAllLeadGenerations();

	LeadGeneration updateLeadGenerationBylocationId(LeadGeneration leadgeneration, String locationid);

	int deleteLeadGenerationBylocationId(String locationid);

}