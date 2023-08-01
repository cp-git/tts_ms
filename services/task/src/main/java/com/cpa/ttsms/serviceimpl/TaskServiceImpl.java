/**
 * @author - Code Generator
 * @createdOn 25-07-2023
 * @Description Controller class for task
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpa.ttsms.dto.TaskDTO;
import com.cpa.ttsms.entity.Password;
import com.cpa.ttsms.entity.Task;
import com.cpa.ttsms.repository.PasswordRepo;
import com.cpa.ttsms.repository.TaskRepo;
import com.cpa.ttsms.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepo taskRepo;
	@Autowired
	private PasswordRepo passwordRepo;
	private static Logger logger;

	public TaskServiceImpl() {
		logger = Logger.getLogger(TaskServiceImpl.class);
	}

	/**
	 * Create a new task.
	 *
	 * @param task The Task object to be created.
	 * @return The created Task object.
	 */
	@Override
	public Task createTask(Task task) {
		logger.debug("Entering createTask");
		Task createdTask = taskRepo.save(task);
		logger.info("created Task: " + createdTask);
		return createdTask;
	}

	/**
	 * Get a task by its ID.
	 *
	 * @param id The ID of the task to retrieve.
	 * @return The retrieved Task object, or null if not found.
	 */
	@Override
	public Task getTaskById(int id) {
		logger.debug("Entering getTaskById");
		Task task = taskRepo.findByTaskId(id);
		logger.info("Founded task: " + task);
		return task;
	}

	/**
	 * Get all parent tasks with a specific status.
	 *
	 * @param status The status of the parent tasks to fetch (e.g., "created",
	 *               "done", "inprogress").
	 * @return The list of parent tasks with the specified status.
	 * @throws IllegalArgumentException If an invalid status is provided.
	 */
	@Override
	public List<Task> getAllParentTasksByStatus(String status) {
		// Switch statement to handle different status values and fetch corresponding
		// tasks
		switch (status.toLowerCase()) {
		// If status is "created", fetch all parent tasks with status "Created"
		case "created":
			return taskRepo.findByTaskParentIsNullAndTaskStatus("Created");
		// If status is "done", fetch all parent tasks with status "Done"
		case "done":
			return taskRepo.findByTaskParentIsNullAndTaskStatus("Done");
		// If status is "inprogress", fetch all parent tasks with status not in
		// "Created" or "Done"
		case "inprogress":
			return taskRepo.findByTaskParentIsNullAndTaskStatusNotIn("Created", "Done");
		// If an invalid status is provided, throw an exception
		default:
			throw new IllegalArgumentException("Invalid status provided.");
		}
	}

	/**
	 * Get all child tasks of a parent task with a specific ID.
	 *
	 * @param parentId The ID of the parent task.
	 * @return The list of child tasks of the specified parent task.
	 */
	@Override
	public List<Task> getAllChildTasksByParentId(int parentId) {
		return taskRepo.findByTaskParent(parentId);
	}

	/**
	 * Update a task based on the provided taskid and TaskDTO.
	 *
	 * @param taskid  The ID of the task to be updated.
	 * @param taskDTO The TaskDTO containing the updated task information.
	 * @return ResponseEntity with the updated Task object if successful, or
	 *         ResponseEntity with HTTP status BAD_REQUEST if taskid does not match
	 *         the TaskDTO taskid, or ResponseEntity with HTTP status
	 *         INTERNAL_SERVER_ERROR if an exception occurs during the update.
	 */
	public Task updateTask(TaskDTO taskDTO) throws Exception {
		// Fetch the empid from the username
		int empid = getEmpIdFromUsername(taskDTO.getUsername());
		if (empid <= 0) {
			return null;
		} else {
			// Fetch the task from the database based on the task ID
			Task task = taskRepo.findByTaskId(taskDTO.getTaskId());
			// Check if the logged-in user (username) is the same as the owner
			// (taskCreatedBy) and Assignedto
			if (empid != task.getTaskCreatedBy() && empid != task.getTaskAssignedTo()) {
				logger.info("Logged-in user is not the owner and assignedto of the task");
				return null;
			} else {
				// Update the status and assignedto fields
				task.setTaskStatus(taskDTO.getStatus());
				task.setTaskAssignedTo(taskDTO.getAssignedTo());
				// Save the updated task to the database
				Task updatedTask = taskRepo.save(task);
				return updatedTask;
			}
		}
	}

	/**
	 * Get the employee ID (empid) based on the provided username.
	 *
	 * @param username The username for which to fetch the empid.
	 * @return The empid of the user with the provided username.
	 * @throws Exception If the user is not found.
	 */
	public int getEmpIdFromUsername(String username) throws Exception {
		// Fetch the Password object from the database based on the provided username
		Password password = passwordRepo.findByUsername(username);
		// Check if the user with the provided username exists
		if (password == null) {
			// If the user is not found, throw an exception with a descriptive message
			return 0;
		}
		// Return the employee ID (empid) of the user
		return password.getEmployeeId();
	}
}
