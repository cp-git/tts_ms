package com.cpa.ttsms.service;

import java.io.InputStream;
import java.util.List;

import com.cpa.ttsms.entity.LeadDetails;

public interface LeadDetailsService {
	
	//Manual Additional for Lead Details..
	LeadDetails createLeadDetails(LeadDetails leadDetails);
	
	//Check the LeadDetailsByid
	LeadDetails getLeadDetailsById(int id);
	
	//Upload Functionality
	List<LeadDetails> saveUploadFile(InputStream inputStream , int userId);
	
	
	//Get Lead Details By User Id
	List<LeadDetails> getLeadDetailsByUserId(int userId);
	
	//Update the Details By User Id..
	LeadDetails updateLeadDetailsById(LeadDetails leadDetails, int id);
	
	//Delete the Lead details by User Id
	void deleteLeadDetailsById(int id);

}
