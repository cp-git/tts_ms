/**
 * @author  - Code Generator
 * @createdOn -  07-08-2023
 * @Description Entity class for Reason
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Reason;

@Repository
public interface ReasonRepo extends JpaRepository<Reason, Integer> {

	/**
	 * Retrieves a list of reasons associated with a specific task.
	 * 
	 * @param taskId The ID of the task for which reasons are to be retrieved.
	 * @return A list of Reason objects associated with the specified task.
	 */
	List<Reason> findByTaskId(int taskId);
}
