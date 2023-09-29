/**
 * @author - Code Generator
 * @createdOn 25-07-2023
 * @Description Controller class for task
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.cpa.ttsms.dto.EmailDTO;
import com.cpa.ttsms.dto.EmployeeDTO;
import com.cpa.ttsms.dto.ParentAndChildTaskDTO;
import com.cpa.ttsms.dto.StatusDTO;
import com.cpa.ttsms.dto.TaskAndReasonDTO;
import com.cpa.ttsms.dto.TaskDTO;
import com.cpa.ttsms.entity.Password;
import com.cpa.ttsms.entity.Reason;
import com.cpa.ttsms.entity.Status;
import com.cpa.ttsms.entity.Task;
import com.cpa.ttsms.entity.TaskAttachment;
import com.cpa.ttsms.repository.PasswordRepo;
import com.cpa.ttsms.repository.ReasonRepo;
import com.cpa.ttsms.repository.StatusRepo;
import com.cpa.ttsms.repository.TaskAttachmentRepo;
import com.cpa.ttsms.repository.TaskRepo;
import com.cpa.ttsms.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	// Inject the value of 'file.base-path' from application.yml file
	@Value("${file.base-path}")
	private String basePath;

	private final String REASON_API_URL = "http://localhost:8090/reason/ttsms/reason";
	private final String UPLOAD_FILE_URL = "http://localhost:8090/uploadfile/ttsms/upload";
	private final String email_URL = "http://localhost:8090/email/taskMail";
	private final String employee_URL = "http://localhost:8090/employee/ttsms/employee/";
	private final String status_URL = "http://localhost:8090/status/ttsms/status/";
	private final String task_URL = "http://localhost:8090/task/ttsms/task/";

	@Autowired
	private TaskRepo taskRepo;

	@Autowired
	private ReasonRepo reasonRepo;

	@Autowired
	private PasswordRepo passwordRepo;

	@Autowired
	private StatusRepo statusRepo;

	@Autowired
	private TaskAttachmentRepo taskAttachmentRepo;

	private static Logger logger;

	private final RestTemplate restTemplate;

	public TaskServiceImpl(RestTemplate restTemplate) {
		logger = Logger.getLogger(TaskServiceImpl.class);
		this.restTemplate = restTemplate;
	}

	/**
	 * Create a new task.
	 *
	 * @param task The Task object to be created.
	 * @return The created Task object.
	 */
	@Transactional
	@Override
	public TaskAndReasonDTO createOrUpdateTaskAndAddReason(TaskAndReasonDTO taskAndReasonDTO, MultipartFile file) {

		logger.debug("Entering createTask");
		try {

			Task task = new Task();

			// for updating need taskId, if there is no foreign key then new entry will
			// create
			int taskId = taskAndReasonDTO.getTaskId();
			int parentId = taskAndReasonDTO.getTaskParent();
			if (parentId > 0) {
				updateParentHavingChildIfChildIsExist(parentId);
			}

			// Set values in the Task object
			if (taskId > 0) {
				task.setTaskId(taskId);

				// Check if the parent task's status can be updated
				if (isTaskStatusDoneOrCancel(taskAndReasonDTO.getTaskStatus())) {
					if (!canUpdateParentTaskStatus(task)) {
						return null; // Return null if it cannot be updated
					}
				}
				checkAssignedToAndStatusIsUpdated(taskAndReasonDTO);

			}
			task.setTaskName(taskAndReasonDTO.getTaskName());
			task.setTaskDescription(taskAndReasonDTO.getTaskDescription());
			task.setTaskCreatedBy(taskAndReasonDTO.getTaskCreatedBy());
			task.setTaskAssignedTo(taskAndReasonDTO.getTaskAssignedTo());
			task.setTaskStartDate(taskAndReasonDTO.getTaskStartDate());
			task.setTaskEndDate(taskAndReasonDTO.getTaskEndDate());
			task.setTaskActualStartDate(taskAndReasonDTO.getTaskActualStartDate());
			task.setTaskActualEndDate(taskAndReasonDTO.getTaskActualEndDate());
			task.setTaskStatus(taskAndReasonDTO.getTaskStatus());
			task.setTaskParent(taskAndReasonDTO.getTaskParent());
			task.setHavingChild(taskAndReasonDTO.isHavingChild());

			task.setCompanyId(taskAndReasonDTO.getCompanyId());

			// adding or updating row
			Task createdTask = taskRepo.save(task);

			logger.info("created Task " + createdTask.getTaskName());
			if (createdTask != null) {

				if (file != null) {
					// adding attachement file
					TaskAttachment taskAttachment = new TaskAttachment();
					taskAttachment.setTaskID(createdTask.getTaskId());
					taskAttachment.setFileName(file.getOriginalFilename());
					taskAttachment.setAttachedBy(taskAndReasonDTO.getEmployeeId());
					taskAttachmentRepo.save(taskAttachment);

					File tempFile = null;

					// converting multi part file into file
					tempFile = File.createTempFile("temp", file.getOriginalFilename());
					file.transferTo(tempFile);

					// building form-data to pass in request for uploading file
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("filename", file.getOriginalFilename());
					map.add("file", new FileSystemResource(tempFile));
					map.add("folder", "task_attachement/" + createdTask.getTaskName() + "_" + createdTask.getTaskId());

					// setting content type for header
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.MULTIPART_FORM_DATA);

					// building request entity using values and header
					HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

					// calling api for uploading file
					ResponseEntity<String> response = restTemplate.postForEntity(UPLOAD_FILE_URL, requestEntity,
							String.class);

					if (response.getStatusCode() == HttpStatus.OK) {
						logger.info("file uploaded");
					} else {
						logger.error("Error uploading data to remote microservice: " + response.getStatusCodeValue());
					}
				}

				// adding reason
				Reason reason = new Reason();
				// setting values in reasonDTO object
				reason.setReasonText(taskAndReasonDTO.getReason());
				reason.setTaskId(createdTask.getTaskId());
				reason.setEmployeeId(taskAndReasonDTO.getEmployeeId());
				reason.setStatusId(taskAndReasonDTO.getTaskStatus());
				reason.setAssignedTo(taskAndReasonDTO.getTaskAssignedTo());
				Reason createdReason = this.reasonRepo.save(reason);

				logger.info("created Reason " + createdReason.getReasonText());
				if (createdReason != null) {
					taskAndReasonDTO.setTaskId(createdTask.getTaskId());
				}

				return taskAndReasonDTO;
			}
		} catch (Exception e) {
			logger.error("Error while processing data: " + e.getMessage(), e);
		}

		return null;

	}

	void updateParentHavingChildIfChildIsExist(int parentId) {
		// TODO Auto-generated method stub
		System.out.println("*******************************8888888888888888888888888888888" + parentId);
		taskRepo.updateHavingChildToTrueByParentId(parentId);
	}

	/**
	 * Checks whether the "Assigned To" or "Task Status" fields of a task have been
	 * updated. If updated, it sends a task update notification.
	 *
	 * @param taskDTO The TaskAndReasonDTO representing the current state of the
	 *                task.
	 * @return True if "Assigned To" or "Task Status" is updated and notification is
	 *         sent, false otherwise.
	 */
	private boolean checkAssignedToAndStatusIsUpdated(TaskAndReasonDTO taskDTO) {
		// Create a TaskAndReasonDTO object to store the current state of the task
		TaskAndReasonDTO task = new TaskAndReasonDTO();

		// Retrieve the current state of the task from the API
		ResponseEntity<TaskAndReasonDTO> response = restTemplate.getForEntity(task_URL + taskDTO.getTaskId(),
				TaskAndReasonDTO.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			task = response.getBody();
		} else {
			logger.error("Failed to retrieve taskDTO data for task ID: " + taskDTO.getTaskId());
		}

		// Compare "Assigned To" and "Task Status" fields for updates
		if (taskDTO.getTaskAssignedTo() != task.getTaskAssignedTo()
				|| taskDTO.getTaskStatus() != task.getTaskStatus()) {
			// If either "Assigned To" or "Task Status" is updated, send a task update
			// notification
			sendTaskUpdateNotification(taskDTO);
			return true;
		}

		// No updates were detected
		return false;
	}

	/**
	 * Sends a task update notification email to relevant employees when a task's
	 * status changes.
	 *
	 * @param taskAndReasonDTO The TaskAndReasonDTO containing information about the
	 *                         task and reason for the update.
	 * @return True if the notification was successfully sent, false otherwise.
	 */
	private boolean sendTaskUpdateNotification(TaskAndReasonDTO taskAndReasonDTO) {
		// Collect employee IDs involved in the task update
		List<Integer> employeeIds = Arrays.asList(taskAndReasonDTO.getTaskCreatedBy(),
				taskAndReasonDTO.getTaskAssignedTo(), taskAndReasonDTO.getEmployeeId());

		// Create a list to store the response objects
		List<EmployeeDTO> responseBodies = new ArrayList<>();

		// Loop through each employee ID and make a request to retrieve employee data
		for (int employeeId : employeeIds) {
			ResponseEntity<EmployeeDTO> response = restTemplate.getForEntity(employee_URL + employeeId,
					EmployeeDTO.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				EmployeeDTO responseBody = response.getBody();
				responseBodies.add(responseBody);
			} else {
				logger.error("Failed to retrieve EmployeeDTO data for employee ID: " + employeeId);
			}
		}

		// Create a StatusDTO object to store task status information
		StatusDTO statusResponse = new StatusDTO();
		int statusId = taskAndReasonDTO.getTaskStatus();
		ResponseEntity<StatusDTO> statusResponseEntity = restTemplate.getForEntity(status_URL + statusId,
				StatusDTO.class);
		if (statusResponseEntity.getStatusCode() == HttpStatus.OK) {
			statusResponse = statusResponseEntity.getBody();
		} else {
			logger.error("Failed to retrieve StatusDTO data for status ID: " + statusId);
		}

		// Get the reason text from the taskAndReasonDTO
		String reasonText = taskAndReasonDTO.getReason();

		// Check if there are valid response bodies
		if (!responseBodies.isEmpty()) {
			// Extract employee information
			EmployeeDTO createdBy = responseBodies.get(0);
			EmployeeDTO assignedTo = responseBodies.get(1);
			EmployeeDTO assignedBy = responseBodies.get(2);

			// Extract relevant data for the email notification
			String createdByEmail = createdBy.getEmployeeEmail();
			String assignedToEmail = assignedTo.getEmployeeEmail();
			String assignedByEmail = assignedBy.getEmployeeEmail();
			String taskName = taskAndReasonDTO.getTaskName();

			// Construct the email message body with reason
			String msgBody = "Dear User," + "\n\n" + "Task name   : " + taskName + "\n" + "Task status  : "
					+ statusResponse.getStatusCode() + "\n" + "Reason        : " + reasonText + "\n\n"
					+ "Assigned to : " + assignedTo.getFirstName() + " " + assignedTo.getLastName() + "\n"
					+ "Changed by : " + assignedBy.getFirstName() + " " + assignedBy.getLastName() + "\n\n"
					+ "Best regards," + "\n" + "TTS Admin";

			// Create a list of recipient email addresses
			List<String> emails = Arrays.asList(createdByEmail, assignedToEmail, assignedByEmail);

			// Create an EmailDTO object to send the email
			EmailDTO emailDTO = new EmailDTO();
			emailDTO.setRecipient(emails); // Set the recipient's email address
			emailDTO.setMsgBody(msgBody); // Set the email's message body
			emailDTO.setSubject("Task Update Notification Email"); // Set the email's subject

			// Send the email using a REST call
			ResponseEntity<String> emailResponse = restTemplate.postForEntity(email_URL, emailDTO, String.class);
			if (emailResponse.getStatusCode() == HttpStatus.OK) {
				String responseBody = emailResponse.getBody();
				return true;
			} else {
				// Email sending failed
				return false;
			}
		}

		// No valid response bodies were obtained, so the notification cannot be sent
		return false;
	}

	private boolean isTaskStatusDoneOrCancel(int taskStatus) {
		return taskStatus == 3 || taskStatus == 4;
	}

	/**
	 * Checks whether the parent task's status can be updated based on the status of
	 * its child tasks.
	 *
	 * @param parentTask The parent task for which to check status update
	 *                   feasibility.
	 * @return True if the parent task's status can be updated, false otherwise.
	 */
	private boolean canUpdateParentTaskStatus(Task parentTask) {
		// Retrieve a list of child tasks for the given parent task
		List<Task> childTasks = getAllChildTasksByParentId(parentTask.getTaskId());

		// Iterate through the child tasks and their nested child tasks
		for (Task childTask : childTasks) {
			// Check if the child task's status is not "Done" /"Cancel"(status 3 or 4)
			if (!isTaskStatusDone(childTask)) {
				// If any child task's status is not done/cancel, the parent task cannot be
				// updated
				return false;
			}
		}

		// If all child tasks are done/cancel, the parent task can be updated
		return true;
	}

	/**
	 * Checks whether a task's status indicates that it is done/cancel.
	 *
	 * @param task The task to check.
	 * @return True if the task's status is "Done/cancel," false otherwise.
	 */
	private boolean isTaskStatusDone(Task task) {
		// Check if the task's status is either 3 (Done) or 4 (Cancelled)
		return task.getTaskStatus() == 3 || task.getTaskStatus() == 4;
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
//	@Override
//	public List<Task> getAllParentTasksByStatus(String status) {
//		// Switch statement to handle different status values and fetch corresponding
//		// tasks
//		switch (status.toLowerCase()) {
//		// If status is "created", fetch all parent tasks with status "Created"
//		case "created":
//			return taskRepo.findByTaskParentIsNullAndTaskStatus("CREATED");
//		// If status is "done", fetch all parent tasks with status "Done"
//		case "done":
//			return taskRepo.findByTaskParentIsNullAndTaskStatus("DONE");
//		// If status is "inprogress", fetch all parent tasks with status not in
//		// "Created" or "Done"
//		case "inprogress":
//			return taskRepo.findByTaskParentIsNullAndTaskStatusNotIn("CREATED", "DONE");
//		// If an invalid status is provided, throw an exception
//		default:
//			throw new IllegalArgumentException("Invalid status provided.");
//		}
//	}

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

	
	public List<Task> findTasksByParentByStatusAndCreatorAndAssigneeOfCompany(int parentId, List<String> statuses, int createdBy, int assignedTo, int companyId) {

	    List<Task> taskList = null;

	    try {
	        // Created by all
	        if (createdBy == 0) {
	            // Check if statuses contain "ALL"
	            if (statuses.contains("ALL")) {
	                // Checks assigned to 0 means all
	                if (assignedTo == 0) {
	                    // Get all tasks by company id and parent id
	                    taskList = taskRepo.findByCompanyIdAndTaskParentOrderByTaskStartDate(companyId, parentId);
	                } else {
	                    // Get all tasks by company id, parent id, and assigned to
	                    taskList = taskRepo.findByCompanyIdAndTaskParentAndTaskAssignedToOrderByTaskStartDate(companyId, parentId, assignedTo);
	                }
	            } else {
	                // Statuses contain specific values
	                List<Integer> statusIds = statuses.stream()
	                        .map(status -> statusRepo.findByStatusCodeIgnoreCase(status).getStatusId())
	                        .collect(Collectors.toList());

	                // Checks assigned to 0 means all
	                if (assignedTo == 0) {
	                    // Task by company id, parent id, and status
	                    taskList = taskRepo.findByCompanyIdAndTaskParentAndTaskStatusInOrderByTaskStartDate(companyId, parentId, statusIds);
	                } else {
	                    // Task by company id, parent id, status, and assigned to
	                    taskList = taskRepo.findByCompanyIdAndTaskParentAndTaskStatusInAndTaskAssignedToOrderByTaskStartDate(companyId, parentId, statusIds, assignedTo);
	                }
	            }
	        }
	        // For created by me
	        else {
	            // Check if statuses contain "ALL"
	            if (statuses.contains("ALL")) {
	                // Checks assigned to is 0 means all
	                if (assignedTo == 0) {
	                    // Task by company id, parent id, and created by
	                    taskList = taskRepo.findByCompanyIdAndTaskParentAndTaskCreatedByOrderByTaskStartDate(companyId, parentId, createdBy);
	                } else {
	                    // Task by company id, parent id, created by, and assigned to
	                    taskList = taskRepo.findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskAssignedToOrderByTaskStartDate(companyId, parentId, createdBy, assignedTo);
	                }
	            } else {
	                // Statuses contain specific values
	                List<Integer> statusIds = statuses.stream()
	                        .map(status -> statusRepo.findByStatusCodeIgnoreCase(status).getStatusId())
	                        .collect(Collectors.toList());

	                // Checks assigned to is 0 means all
	                if (assignedTo == 0) {
	                    // Task by company id, parent id, created by, and status
	                    taskList = taskRepo.findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskStatusInOrderByTaskStartDate(companyId, parentId, createdBy, statusIds);
	                } else {
	                    // Task by company id, parent id, created by, status, and assigned to
	                    taskList = taskRepo.findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskStatusInAndTaskAssignedToOrderByTaskStartDate(companyId, parentId, createdBy, statusIds, assignedTo);
	                }
	            }
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return taskList;
	}

	@Transactional
	@Override
	public List<Object> getFilesUsingTaskId(int taskId) {
		// TODO Auto-generated method stub

		Task task = taskRepo.findByTaskId(taskId);
		List<Object> fileNames = new ArrayList<>();

		String folderName = null;

		if (task != null) {

			folderName = "task_attachement/" + task.getTaskName() + "_" + task.getTaskId();
			// Create a File object for the specified subdirectory
			File directory = new File(basePath, folderName);

			// Check if the subdirectory exists and is a directory
			if (directory.exists() && directory.isDirectory()) {
				// List all files in the subdirectory
				File[] files = directory.listFiles();

				// Iterate through the files and add file names to the list
				if (files != null) {
					for (File file : files) {
						// Check if the current item is a regular file
						if (file.isFile()) {
							// Add the name of the file to the list
							fileNames.add(file.getName());
						}
					}
				}

			}

		}

		return fileNames;
	}

	@Override
	public Resource downloadFileByTaskIdAndFileName(int taskId, String fileName) {
		Task task = taskRepo.findByTaskId(taskId);

		String folderName = basePath + "/task_attachement/" + task.getTaskName() + "_" + task.getTaskId();

		Path filePath = Paths.get(folderName).resolve(fileName);
		Resource resource;
		try {
			resource = new UrlResource(filePath.toUri());
			return resource;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retrieves a list of ParentAndChildTaskDTO objects in the system that are both created and assigned by the specified employee.
	 * 
	 * @param employeeId The ID of the employee for whom tasks are to be retrieved.
	 * @return A list containing a ParentAndChildTaskDTO object representing parent tasks and their child tasks created and assigned by the specified employee.
	 * @throws IllegalArgumentException If the provided employee ID is invalid or if no tasks are found for the specified employee.
	 */
	@Override
	public List<ParentAndChildTaskDTO> getAllTaskCreatedByMeAndAssignToMe(int employeeId) {
	   
	    
	    // TODO: Implementation to retrieve tasks created and assigned by the specified employee
	    int createdBy = employeeId;
	    int assignedBy = employeeId;

	    // Fetch all tasks assigned or created by the specified employee
	    List<Task> allTasks = taskRepo.findByTaskAssignedToOrTaskCreatedBy(createdBy, assignedBy);
	    
	    // Extract parent task IDs from the list of all tasks
	    List<Integer> parentTaskIds = allTasks.stream().filter(task -> task.getTaskParent() == 0).map(Task::getTaskId)
	            .collect(Collectors.toList());
	    
	    // Fetch parent tasks using parent task IDs
	    List<Task> parentTaskList = new ArrayList<>();
	    List<ParentAndChildTaskDTO> parentAndChildDTOs = new ArrayList<>();
	    for (Integer parentId : parentTaskIds) {
	        Task parentTask = taskRepo.findByTaskId(parentId);
	        parentTaskList.add(parentTask);
	    }
	    
	    // Create a ParentAndChildTaskDTO object and add it to the list
	    ParentAndChildTaskDTO dto = new ParentAndChildTaskDTO(parentTaskList, allTasks);
	    parentAndChildDTOs.add(dto);
	    

	    return parentAndChildDTOs;
	}


	
}
