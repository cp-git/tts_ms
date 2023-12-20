/**
 * @author  - Code Generator
 * @createdOn -  18/12/2023
 * @Description Entity class for JobPortal Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.JobPortal;

public interface JobPortalService {

	JobPortal createJobPortal(JobPortal jobportal);

	JobPortal getJobPortalByjobPortalId(int jobportalid);

	List<Object> getAllJobPortals();

	JobPortal updateJobPortalByjobPortalId(JobPortal jobportal, int jobportalid);

	int deleteJobPortalByjobPortalId(int jobportalid);

	List<Object> getAllPortalsByCompanyId(int companyId);

}