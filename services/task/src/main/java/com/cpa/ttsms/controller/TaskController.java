/**
 * @author - Code Generator
 * @developer - Akash
 * @createdOn 25-07-2023
 * @Description Controller class for task
 * 
 */

package com.cpa.ttsms.controller;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.dto.TaskDTO;
import com.cpa.ttsms.entity.Task;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.TaskService;

@CrossOrigin
@RestController
@RequestMapping("/ttsms")
public class TaskController {

	@Autowired
	private TaskService taskService;;

	// The ResourceBundle is used to retrieve localized messages.
	private ResourceBundle resourceBundle;

	// The logger is used for logging messages related to this class.
	private static Logger logger;

	TaskController() {
		resourceBundle = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(TaskController.class);
	}

	/**
	 * Creates a new task in the system based on the provided Task object.
	 *
	 * @param task The Task object representing the task to be created.
	 * @return ResponseEntity containing the created Task object and HTTP status
	 *         CREATED if successful. If the task with the provided task name
	 *         already exists, returns an error ResponseEntity with HTTP status
	 *         INTERNAL_SERVER_ERROR and an error message "err003".
	 * @throws CPException If an exception occurs during task creation, a custom
	 *                     CPException is thrown with the error code "err003" and
	 *                     the localized error message from the resource bundle.
	 */
	@PostMapping("/savetask")
	public ResponseEntity<Object> createTask(@RequestBody Task task) throws CPException {
		// Log that the method has been entered and print task details
		logger.debug("Entering createTask");
		logger.info("Data of creating Task: " + task.getTaskName());

		try {
			Task createdTask = taskService.createTask(task);
			// If the task does not exist (i.e., createdTask is not null), it has been
			// successfully created
			if (createdTask != null) {
				logger.info("Task created: " + createdTask.getTaskName());

				// Return a successful response with the created task and HTTP status CREATED
				return ResponseHandler.generateResponse(createdTask, HttpStatus.CREATED);

			} else {
				// If the task with the provided task name already exists, return an error
				// response
				// with HTTP status INTERNAL_SERVER_ERROR and an error message "err003"
				logger.error(resourceBundle.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			// If an exception occurs during task creation, log the error and throw a custom
			// CPException
			// with the error message "err003" and the localized error message from the
			// resource bundle.
			logger.error("Failed Task creation: " + ex.getMessage());
			throw new CPException("err003", resourceBundle.getString("err003"));
		}
	}

	/**
	 * Endpoint to get a task by its ID.
	 *
	 * @param id The ID of the task to retrieve, received as a path variable.
	 * @return ResponseEntity with the fetched Task object if it exists, or
	 *         ResponseEntity with HTTP status NOT_FOUND if the task does not exist.
	 * @throws CPException If there is an exception while fetching the task, it is
	 *                     caught and wrapped into a custom CPException, which will
	 *                     be handled by the global exception handler to return a
	 *                     meaningful error response to the client.
	 */
	@GetMapping("/task/{id}")
	public ResponseEntity<Object> getTaskById(@PathVariable("id") int id) throws CPException {
		// Log that the method has been entered and print the task ID received
		logger.debug("Entering getTaskById");
		logger.info("Entered task ID: " + id);

		// Initialize a variable to hold the fetched task
		Task task = null;

		try {
			// Fetch the task by its ID using the taskService
			task = taskService.getTaskById(id);
			logger.info("Fetched Task: " + task);

			// If the task is found (i.e., not null), return a successful response
			// with the fetched task and HTTP status OK
			if (task != null) {
				logger.debug("Task fetched, generating response");
				return ResponseHandler.generateResponse(task, HttpStatus.OK);
			} else {
				// If the task is not found, return an error response with HTTP status NOT_FOUND
				// and an error message "err001"
				logger.debug("Task not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {
			// If an exception occurs while fetching the task, log the error and throw a
			// custom CPException
			// with the error message "err001" and the localized error message from the
			// resource bundle.
			logger.error("Failed getting task: " + ex.getMessage());
			throw new CPException("err001", resourceBundle.getString("err001"));
		}
	}

	/**
	 * Endpoint to get all parent tasks based on their status.
	 *
	 * @param status The status of the parent tasks to retrieve, received as a
	 *               request parameter.
	 * @return List of parent tasks with the specified status.
	 */
	@GetMapping("/allparenttask")
	public ResponseEntity<Object> getAllParentTasksByStatus(@RequestParam("status") String status) {
		// Log that the method has been entered and print the status received
		logger.debug("Entering getAllParentTasksByStatus");
		logger.info("Entered status: " + status);

		try {
			// Fetch all parent tasks with the specified status using the taskService
			List<Task> parentTasks = taskService.getAllParentTasksByStatus(status);

			// Log the fetched parent tasks
			logger.info("Fetched parent tasks with status " + status + ": " + parentTasks);

			// If parentTasks list is not empty, return it as a successful response
			if (!parentTasks.isEmpty()) {
				return ResponseHandler.generateResponse(parentTasks, HttpStatus.OK);
			} else {
				// If no parent tasks are found with the specified status, return NOT_FOUND
				// status
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}
		} catch (Exception e) {
			// If any other exception occurs, log the error and return INTERNAL_SERVER_ERROR
			// status
			logger.error("Error while fetching parent tasks: " + e.getMessage());
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err002");
		}
	}

	/**
	 * Retrieves all child tasks with the specified parentId.
	 *
	 * @param parentId The ID of the parent task for which to fetch child tasks.
	 * @return ResponseEntity containing the list of child tasks if successful, or
	 *         ResponseEntity with HTTP status NOT_FOUND if no child tasks are found
	 *         with the specified parentId, or ResponseEntity with HTTP status
	 *         INTERNAL_SERVER_ERROR if an exception occurs during the retrieval.
	 */
	@GetMapping("/allchilds/{parentid}")
	public ResponseEntity<Object> getAllChildTasksByParentId(@PathVariable("parentid") int parentId) {
		// Log that the method has been entered and print the parentId received
		logger.debug("Entering getAllChildTasksByParentId");
		logger.info("Entered parentid: " + parentId);

		try {
			// Fetch all child tasks with the specified parentId using the taskService
			List<Task> childTasks = taskService.getAllChildTasksByParentId(parentId);

			// Log the fetched child tasks
			logger.info("Fetched child tasks with parentid " + parentId + ": " + childTasks);

			// If childTasks list is not empty, return it as a successful response
			if (!childTasks.isEmpty()) {
				return ResponseHandler.generateResponse(childTasks, HttpStatus.OK);
			} else {
				// If no child tasks are found with the specified parentId, return NOT_FOUND
				// status
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}
		} catch (Exception e) {
			// If any exception occurs, log the error and return INTERNAL_SERVER_ERROR
			// status
			logger.error("Error while fetching child tasks: " + e.getMessage());
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err002");
		}
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
	@PutMapping("/update/{taskid}")
	public ResponseEntity<Object> updateTask(@PathVariable int taskid, @RequestBody TaskDTO taskDTO) {
		try {
			// Check if the taskid in the path variable matches the taskid in the TaskDTO
			if (taskid != taskDTO.getTaskId()) {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "err006");
			}
			// Optional: You can fetch the empid from the UserService and set it in the
			// TaskDTO
			// int empid = userService.getEmpIdFromUsername(taskDTO.getUsername());
			// taskDTO.setEmpid(empid);

			// Update the task using the TaskService
			Task updatedTask = taskService.updateTask(taskDTO);
			if (updatedTask != null) {
				// Return the updated Task object with HTTP status OK
				return ResponseHandler.generateResponse(updatedTask, HttpStatus.OK);
			} else {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err004");
			}
		} catch (Exception e) {
			// If an exception occurs during the update, return HTTP status
			// INTERNAL_SERVER_ERROR
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
		}
	}

	/**
	 * Endpoint to get all parent tasks based on their status, created by, and
	 * assigned to.
	 *
	 * @param status     The status of the parent tasks to retrieve, received as a
	 *                   request parameter.
	 * @param createdBy  The user ID of the creator of the parent tasks, received as
	 *                   a request parameter.
	 * @param assignedTo The user ID of the assignee of the parent tasks, received
	 *                   as a request parameter.
	 * @param employeeId The employee ID associated with the tasks, received as a
	 *                   request parameter.
	 * @return ResponseEntity containing a list of parent tasks with the specified
	 *         status, created by, and assigned to.
	 */
	@GetMapping("/allparent")
	public ResponseEntity<Object> findTasksByStatusAndCreatorAndAssigneeOfCompanyByEmployeeId(
			@RequestParam("parentid") int parentId, @RequestParam("status") String status,
			@RequestParam("createdby") int createdBy, @RequestParam("assignedto") int assignedTo,
			@RequestParam("companyid") int companyId) {
		// Log that the method has been entered and print the status,createdby,
		// assignedto received
		logger.debug("Entering findTasksByStatusAndCreatorAndAssigneeOfCompanyByemployeeId");
		logger.info("Entered status/createdBy/assignedTo : " + status + "/" + createdBy + "/" + assignedTo + "/"
				+ companyId);

		List<Task> parentTasks = null;
		try {
			// Fetch all parent tasks with the specified status, createdby, assignedTo using
			// the taskService
			parentTasks = taskService.findTasksByParentByStatusAndCreatorAndAssigneeOfCompany(parentId,
					status, createdBy, assignedTo, companyId);

			// Log the fetched parent tasks
			logger.info("Fetched parent tasks with status/createdBy/assignedTo " + status + "/" + createdBy + "/"
					+ assignedTo + "/" + companyId + ": " + parentTasks);

			// If parentTasks list is not empty, return it as a successful response
			if (parentTasks != null) {
				return ResponseHandler.generateResponse(parentTasks, HttpStatus.OK);
			} else {
				// If no parent tasks are found with the specified status, return NOT_FOUND
				// status
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}
		} catch (Exception e) {
			// If any other exception occurs, log the error and return INTERNAL_SERVER_ERROR
			// status
			logger.error("Error while fetching parent tasks: " + e.getMessage());
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err002");
		}
	}

}
