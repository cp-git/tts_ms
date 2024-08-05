/**
 * @author  - Code Generator
 * @createdOn -  16/07/2024
 * @Description Entity class for LeadDetails
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.LeadDetails;

@Repository
public interface LeadDetailsRepo extends JpaRepository<LeadDetails, Integer> {

	
	/*
	 * Find Lead Details By User Id
	 */
	public LeadDetails findById(int id);
	
	
	  List<LeadDetails> findByIdIn(List<Integer> ids);
	

	
	/*
	 * Find Details By User id
	 */

	public List<LeadDetails> findByUserId(int userId);
	

	/*
	 * Delete the Details by Id
	 */
	void deleteById(int id);
	
	

	 /*
	  * Search Details by Company Name
	  */
	 
	 @Query("SELECT p FROM LeadDetails p WHERE (:companyName IS NULL OR p.companyName LIKE %:companyName%)")
	  List<LeadDetails> searchByCompanyName(@Param("companyName") String companyName);
	 
	 
	 //Search Details By Recruiter Name
	 @Query("SELECT p FROM LeadDetails p WHERE (:recruiterName IS NULL OR p.recruiterName LIKE %:recruiterName%) ")
	  List<LeadDetails> searchByRecuiterName(@Param("recruiterName") String recruiterName);
	 
	 
	//Search Details By Recruiter Email
	 @Query("SELECT p FROM LeadDetails p WHERE (:recruiterMail IS NULL OR p.recruiterMail LIKE %:recruiterMail%) ")
	  List<LeadDetails> searchByRecruiterMail(@Param("recruiterMail") String recruiterMail);
	 
	 
	//Search Details By Position Name
	 @Query("SELECT p FROM LeadDetails p WHERE (:positionName IS NULL OR p.positionName LIKE %:positionName%) ")
	  List<LeadDetails> searchBypositionName(@Param("positionName") String positionName);
	 
	//Search Details By Job Location 
	 @Query("SELECT p FROM LeadDetails p WHERE (:jobLocation IS NULL OR p.jobLocation LIKE %:jobLocation%) ")
	  List<LeadDetails> searchByjobLocation(@Param("jobLocation") String jobLocation);
	 
	 

	 //Select All Data in Searching...
	   @Query("SELECT p FROM LeadDetails p WHERE (:companyName IS NULL OR p.companyName LIKE %:companyName%) OR (:recruiterName IS NULL OR p.recruiterName LIKE %:recruiterName%) OR (:recruiterMail IS NULL OR p.recruiterMail LIKE %:recruiterMail%) OR (:positionName IS NULL OR p.positionName LIKE %:positionName%) OR (:jobLocation IS NULL OR p.jobLocation LIKE %:jobLocation%)")
	    List<LeadDetails> search(@Param("companyName") String companyName, @Param("recruiterName") String recruiterName, @Param("recruiterMail") String recruiterMail,@Param("positionName") String positionName,@Param("jobLocation") String jobLocation);

	   
	   
	   /*
	    * Searching by specific User id...
	    */
	   
	   @Query("SELECT p FROM LeadDetails p WHERE (:companyName IS NULL OR p.companyName LIKE %:companyName%) AND p.userId = :userId")
		  List<LeadDetails> searchByCompanyNameAndId(@Param("companyName") String companyName, @Param("userId") int userId);
	   
	   
	   /*
	    * Searching Details by specific Recruiter name and User Id
	    */
	   @Query("SELECT p FROM LeadDetails p WHERE (:recruiterName IS NULL OR p.recruiterName LIKE %:recruiterName%) AND p.userId = :userId")
		List<LeadDetails> searchByRecuiterNameAndID(@Param("recruiterName") String recruiterName, @Param("userId") int userId);
		
	   
	    //Search Details By Recruiter Email with user id
		 @Query("SELECT p FROM LeadDetails p WHERE (:recruiterMail IS NULL OR p.recruiterMail LIKE %:recruiterMail%) AND p.userId = :userId")
		  List<LeadDetails> searchByRecruiterMailAndId(@Param("recruiterMail") String recruiterMail,@Param("userId") int userId);
		 
		 
		//Search Details By Position Name
		 @Query("SELECT p FROM LeadDetails p WHERE (:positionName IS NULL OR p.positionName LIKE %:positionName%) AND p.userId = :userId")
		  List<LeadDetails> searchBypositionNameAndId(@Param("positionName") String positionName,@Param("userId") int userId);
		 
		//Search Details By Job Location 
		 @Query("SELECT p FROM LeadDetails p WHERE (:jobLocation IS NULL OR p.jobLocation LIKE %:jobLocation%) AND p.userId = :userId ")
		  List<LeadDetails> searchByjobLocationAndId(@Param("jobLocation") String jobLocation,@Param("userId") int userId);
		 
		 
//		 //GetAll
		 @Query("SELECT p FROM LeadDetails p WHERE (:companyName IS NULL OR p.companyName LIKE %:companyName%) OR (:recruiterName IS NULL OR p.recruiterName LIKE %:recruiterName%) OR (:recruiterMail IS NULL OR p.recruiterMail LIKE %:recruiterMail%) OR (:positionName IS NULL OR p.positionName LIKE %:positionName%) OR (:jobLocation IS NULL OR p.jobLocation LIKE %:jobLocation%) AND p.userId = :userId")
		 List<LeadDetails> searchById(@Param("companyName") String companyName, @Param("recruiterName") String recruiterName, @Param("recruiterMail") String recruiterMail,@Param("positionName") String positionName,@Param("jobLocation") String jobLocation ,@Param("userId") int userId);
	   
	   
}
