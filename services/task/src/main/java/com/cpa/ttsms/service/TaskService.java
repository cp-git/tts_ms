/**
 * @author  - Code Generator
 * @developer - Akash
 * @createdOn -  25-07-2023
 * @Description Entity class for Task Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.dto.TaskDTO;
import com.cpa.ttsms.entity.Task;

public interface TaskService {

	/**
	 * Creates a new task in the system.
	 * 
	 * @param task The Task object to be created.
	 * @return The created Task object.
	 */
	Task createTask(Task task);

	/**
	 * Retrieves a task by its unique ID.
	 * 
	 * @param id The ID of the task to retrieve.
	 * @return The Task object with the specified ID, or null if not found.
	 */
	Task getTaskById(int id);

	/**
	 * Retrieves all parent tasks in the system with the specified status.
	 * 
	 * @param status The status of the parent tasks to retrieve (e.g., "created",
	 *               "done").
	 * @return A list of parent Task objects with the specified status.
	 * @throws IllegalArgumentException If an invalid status is provided.
	 */
	List<Task> getAllParentTasksByStatus(String status);

	/**
	 * Retrieves all child tasks in the system associated with the specified
	 * parentId.
	 * 
	 * @param parentId The ID of the parent task for which to retrieve child tasks.
	 * @return A list of child Task objects associated with the specified parentId.
	 */
	List<Task> getAllChildTasksByParentId(int parentId);

	/**
	 * Updates a task in the system based on the provided TaskDTO.
	 * 
	 * @param taskDTO The TaskDTO containing the updated task information.
	 * @return The updated Task object.
	 * @throws Exception If an error occurs during the update process.
	 */
	Task updateTask(TaskDTO taskDTO) throws Exception;

	/**
	 * Retrieves all parent tasks in the system with the specified status,
	 * createdby, assignedTo.
	 * 
	 * @param status     The status of the parent tasks to retrieve (e.g.,
	 *                   "created", "done").
	 * @param createdBy  The createdBy of the parent tasks to retrieve
	 * @param assignedTo The assignedTo of the parent tasks to retrieve
	 * @return A list of parent Task objects with the specified status, createdby,
	 *         assignedTo..
	 * @throws IllegalArgumentException If an invalid status is provided.
	 */
	List<Task> findTasksByParentByStatusAndCreatorAndAssigneeOfCompany(int parentId, String status, int createdBy, int assignedTo, int companyId);

}