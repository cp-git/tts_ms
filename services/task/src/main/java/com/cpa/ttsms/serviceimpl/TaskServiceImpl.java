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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.cpa.ttsms.dto.InternalExternalTaskDTO;
import com.cpa.ttsms.dto.ParentAndChildTaskDTO;
import com.cpa.ttsms.dto.StatusDTO;
import com.cpa.ttsms.dto.TaskAndReasonDTO;
import com.cpa.ttsms.dto.TaskDTO;
import com.cpa.ttsms.entity.ExternalTask;
import com.cpa.ttsms.entity.InternalTask;
import com.cpa.ttsms.entity.Password;
import com.cpa.ttsms.entity.Reason;
import com.cpa.ttsms.entity.Status;
import com.cpa.ttsms.entity.Task;
import com.cpa.ttsms.entity.TaskAttachment;
import com.cpa.ttsms.repository.ExternalTaskRepository;
import com.cpa.ttsms.repository.InternalTaskRepository;
import com.cpa.ttsms.repository.PasswordRepo;
import com.cpa.ttsms.repository.ReasonRepo;
import com.cpa.ttsms.repository.StatusRepo;
import com.cpa.ttsms.repository.TaskAttachmentRepo;
import com.cpa.ttsms.repository.TaskRepo;
import com.cpa.ttsms.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private static final int INTERNAL_PLACEMENT_ID = 1;
	private static final int EXTERNAL_PLACEMENT_ID = 2;

	// Inject the value of 'file.base-path' from application.yml file
	@Value("${file.base-path}")
	private String basePath;

//	private final String REASON_API_URL = "http://localhost:8080/reason/ttsms/reason";
//	private final String UPLOAD_FILE_URL = "http://localhost:8080/uploadfile/ttsms/upload";
//	private final String email_URL = "http://localhost:8080/email/taskMail";
//	private final String employee_URL = "http://localhost:8080/employee/ttsms/employee/";
//	private final String status_URL = "http://localhost:8080/status/ttsms/status/";
//	private final String task_URL = "http://localhost:8080/task/ttsms/task/";

	@Value("${external-services.reason-api-url}")
	private String REASON_API_URL;

	@Value("${external-services.upload-file-url}")
	private String UPLOAD_FILE_URL;

	@Value("${external-services.email-url}")
	private String email_URL;

	@Value("${external-services.employee-url}")
	private String employee_URL;

	@Value("${external-services.status-url}")
	private String status_URL;

	@Value("${external-services.task-url}")
	private String task_URL;

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

	@Autowired
	private InternalTaskRepository internalTaskRepo;

	@Autowired
	private ExternalTaskRepository externalTaskRepo;

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

		logger.info("Entering createTask");
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
				logger.info("Task id greator than 0");
				task.setTaskId(taskId);
				Status taskStatus = statusRepo.findById(taskAndReasonDTO.getTaskStatus());
				logger.info("status " + taskStatus);
				// Check if the parent task's status can be updated
				if (isTaskStatusDoneOrCancel(taskStatus)) {
					logger.info("task status is done/cancelled");
					if (!canUpdateParentTaskStatus(task)) {
						logger.info("can update parenttaskstatus");
						return null; // Return null if it cannot be updated
					}
				}
				logger.info("before");
				checkAssignedToAndStatusIsUpdated(taskAndReasonDTO);
				logger.info("after");

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
	 * Checks whether the "Assigned To" or "Task Status" fields of a task have been
	 * updated. If updated, it sends a task update notification.
	 *
	 * @param taskDTO The TaskAndReasonDTO representing the current state of the
	 *                task.
	 * @return True if "Assigned To" or "Task Status" is updated and notification is
	 *         sent, false otherwise.
	 */
	private boolean checkAssignedToAndStatusIsUpdated(InternalExternalTaskDTO taskDTO) {
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

	/**
	 * Sends a task update notification email to relevant employees when a task's
	 * status changes.
	 *
	 * @param taskAndReasonDTO The TaskAndReasonDTO containing information about the
	 *                         task and reason for the update.
	 * @return True if the notification was successfully sent, false otherwise.
	 */
	private boolean sendTaskUpdateNotification(InternalExternalTaskDTO taskAndReasonDTO) {
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

	private boolean isTaskStatusDoneOrCancel(Status taskStatus) {
		return (taskStatus.isActualStartDate() == true && taskStatus.isActualEndDate() == true
				&& taskStatus.isFinalStatus() == true);
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
		logger.info("all child tasks " + childTasks); // Iterate through the child tasks and their nested child tasks
		for (Task childTask : childTasks) {
			// Check if the child task's status is not "Done" /"Cancel"
			Status taskStatus = statusRepo.findById(childTask.getTaskStatus());
			if (!isTaskStatusDone(taskStatus)) {
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
	private boolean isTaskStatusDone(Status taskStatus) {
		// Check if the task's status is either (Done) or (Cancelled)
		return (taskStatus.isActualStartDate() == true && taskStatus.isActualEndDate() == true
				&& taskStatus.isFinalStatus() == true);
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

	public List<Task> findTasksByParentByStatusAndCreatorAndAssigneeOfCompany(int parentId, List<String> statuses,
			int createdBy, int assignedTo, int companyId) {

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
						taskList = taskRepo.findByCompanyIdAndTaskParentAndTaskAssignedToOrderByTaskStartDate(companyId,
								parentId, assignedTo);
					}
				} else {
					// Statuses contain specific values
					List<Integer> statusIds = statuses.stream()
							.map(status -> statusRepo.findByStatusCodeIgnoreCase(status).getStatusId())
							.collect(Collectors.toList());

					// Checks assigned to 0 means all
					if (assignedTo == 0) {
						// Task by company id, parent id, and status
						taskList = taskRepo.findByCompanyIdAndTaskParentAndTaskStatusInOrderByTaskStartDate(companyId,
								parentId, statusIds);
					} else {
						// Task by company id, parent id, status, and assigned to
						taskList = taskRepo
								.findByCompanyIdAndTaskParentAndTaskStatusInAndTaskAssignedToOrderByTaskStartDate(
										companyId, parentId, statusIds, assignedTo);
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
						taskList = taskRepo.findByCompanyIdAndTaskParentAndTaskCreatedByOrderByTaskStartDate(companyId,
								parentId, createdBy);
					} else {
						// Task by company id, parent id, created by, and assigned to
						taskList = taskRepo
								.findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskAssignedToOrderByTaskStartDate(
										companyId, parentId, createdBy, assignedTo);
					}
				} else {
					// Statuses contain specific values
					List<Integer> statusIds = statuses.stream()
							.map(status -> statusRepo.findByStatusCodeIgnoreCase(status).getStatusId())
							.collect(Collectors.toList());

					// Checks assigned to is 0 means all
					if (assignedTo == 0) {
						// Task by company id, parent id, created by, and status
						taskList = taskRepo
								.findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskStatusInOrderByTaskStartDate(
										companyId, parentId, createdBy, statusIds);
					} else {
						// Task by company id, parent id, created by, status, and assigned to
						taskList = taskRepo
								.findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskStatusInAndTaskAssignedToOrderByTaskStartDate(
										companyId, parentId, createdBy, statusIds, assignedTo);
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

	@Override
	public ParentAndChildTaskDTO getAllTaskCreatedByMeAndAssignToMe(int employeeId) {
		// TODO Auto-generated method stub
		int createdBy = employeeId;
		int assignedBy = employeeId;
		List<Task> allTasks = taskRepo.findByTaskAssignedToOrTaskCreatedByOrderByTaskEndDateDesc(createdBy, assignedBy);

//		List<Integer> parentTaskIds = allTasks.stream().filter(task -> task.getTaskParent() == 0).map(Task::getTaskId)
//				.collect(Collectors.toList());

		Set<Integer> parentTaskIdsSet1 = allTasks.stream().map(Task::getTaskParent) // Get all parent task IDs without
																					// filtering
				.filter(parentId -> parentId != null && parentId != 0) // Filter out null parent task IDs, if any
				.collect(Collectors.toSet());

		Set<Integer> parentTaskIdsSet2 = allTasks.stream().filter(task -> task.getTaskParent() == 0)
				.map(Task::getTaskId).collect(Collectors.toSet());

		List<Task> parentTaskList = new ArrayList<Task>();

		List<ParentAndChildTaskDTO> parentAndChildDTOs = new ArrayList<>();

		Set<Integer> parentTaskIds = mergeSet(parentTaskIdsSet1, parentTaskIdsSet2);

		for (Integer parentId : parentTaskIds) {

			Task parentTask = taskRepo.findByTaskId(parentId);
			parentTaskList.add(parentTask);
		}

		ParentAndChildTaskDTO dto = new ParentAndChildTaskDTO(parentTaskList, allTasks); // Set childTasks as null or an
																							// empty list
		parentAndChildDTOs.add(dto);
		System.out.println(dto);
		return dto;
	}

	public static <T> Set<T> mergeSet(Set<T> a, Set<T> b) {

		// Creating an empty HashSet
		Set<T> mergedSet = new HashSet<T>();

		// Adding the two sets to be merged
		// into the new Set using addAll() method
		mergedSet.addAll(a);
		mergedSet.addAll(b);

		// Returning the merged set
		return mergedSet;
	}

	@Override
	public ParentAndChildTaskDTO getAllParentTasksByCompanyId(int companyId) {
		// TODO Auto-generated method stub
		List<Task> allTasks = taskRepo.findByCompanyId(companyId);

//		List<Integer> parentTaskIds = allTasks.stream().filter(task -> task.getTaskParent() == 0).map(Task::getTaskId)
//				.collect(Collectors.toList());

		Set<Integer> parentTaskIdsSet1 = allTasks.stream().map(Task::getTaskParent) // Get all parent task IDs without
																					// filtering
				.filter(parentId -> parentId != null && parentId != 0) // Filter out null parent task IDs, if any
				.collect(Collectors.toSet());

		Set<Integer> parentTaskIdsSet2 = allTasks.stream().filter(task -> task.getTaskParent() == 0)
				.map(Task::getTaskId).collect(Collectors.toSet());

		List<Task> parentTaskList = new ArrayList<Task>();

		List<ParentAndChildTaskDTO> parentAndChildDTOs = new ArrayList<>();

		Set<Integer> parentTaskIds = mergeSet(parentTaskIdsSet1, parentTaskIdsSet2);

		for (Integer parentId : parentTaskIds) {

			Task parentTask = taskRepo.findByTaskId(parentId);
			parentTaskList.add(parentTask);
		}

		ParentAndChildTaskDTO dto = new ParentAndChildTaskDTO(parentTaskList, allTasks); // Set childTasks as null or an
																							// empty list
		parentAndChildDTOs.add(dto);
		System.out.println(dto);
		return dto;
	}

	/**
	 * Create a new task.
	 *
	 * @param task The Task object to be created.
	 * @return The created Task object.
	 */
	@Transactional
	@Override
	public InternalExternalTaskDTO createOrUpdateTask(InternalExternalTaskDTO internalExternalTaskDTO,
			MultipartFile file) {

		logger.info("Entering createOrUpdateTask " + internalExternalTaskDTO);
		try {

			Task task = new Task();
			InternalTask internalTask = null;
			ExternalTask externalTask = null;
			ExternalTask externalCreatedTask = null;
			InternalTask internalCreatedTask = null;

			// for updating need taskId, if there is no foreign key then new entry will
			// create
			int taskId = internalExternalTaskDTO.getTaskId();
			int parentId = internalExternalTaskDTO.getTaskParent();

			// setting parent task's having child field to true
			if (parentId > 0) {
				updateParentHavingChildIfChildIsExist(parentId);
			}

			// Set values in the Task object (if taskId is greater than 0 then we are
			// updating the data
			if (taskId > 0) {
				logger.info("Task id greator than 0");
				task.setTaskId(taskId);
				Status taskStatus = statusRepo.findById(internalExternalTaskDTO.getTaskStatus());
				logger.info("status " + taskStatus);
				// Check if the parent task's status can be updated
				if (isTaskStatusDoneOrCancel(taskStatus)) {
					logger.info("task status is done/cancelled");
					if (!canUpdateParentTaskStatus(task)) {
						logger.info("can update parenttaskstatus");
						return null; // Return null if it cannot be updated
					}
				}
				logger.info("before");
				checkAssignedToAndStatusIsUpdated(internalExternalTaskDTO);
				logger.info("after");

			}
			task.setTaskName(internalExternalTaskDTO.getTaskName());
			task.setTaskDescription(internalExternalTaskDTO.getTaskDescription());
			task.setTaskCreatedBy(internalExternalTaskDTO.getTaskCreatedBy());
			task.setTaskAssignedTo(internalExternalTaskDTO.getTaskAssignedTo());
			task.setTaskStartDate(internalExternalTaskDTO.getTaskStartDate());
			task.setTaskEndDate(internalExternalTaskDTO.getTaskEndDate());
			task.setTaskActualStartDate(internalExternalTaskDTO.getTaskActualStartDate());
			task.setTaskActualEndDate(internalExternalTaskDTO.getTaskActualEndDate());
			task.setTaskStatus(internalExternalTaskDTO.getTaskStatus());
			task.setTaskParent(internalExternalTaskDTO.getTaskParent());
			task.setHavingChild(internalExternalTaskDTO.isHavingChild());
			task.setCompanyId(internalExternalTaskDTO.getCompanyId());
			task.setPlacementId(internalExternalTaskDTO.getPlacementId());

			if (internalExternalTaskDTO.getPlacementId() <= 0) {
				if (internalExternalTaskDTO.getInternalId() > 0) {
					task.setPlacementId(INTERNAL_PLACEMENT_ID);
				} else if (internalExternalTaskDTO.getExternalId() > 0) {
					task.setPlacementId(EXTERNAL_PLACEMENT_ID);
				}
			}

			// adding or updating row
			Task createdTask = taskRepo.save(task);
			internalExternalTaskDTO.setPlacementId(createdTask.getPlacementId());
			logger.info("created Task " + createdTask.getTaskName());
			if (createdTask != null) {

				if (task.getPlacementId() == INTERNAL_PLACEMENT_ID) {
					internalTask = new InternalTask();
					// insert data in internal task table

					if (internalExternalTaskDTO.getInternalId() > 0) {
						internalTask.setInternalId(internalExternalTaskDTO.getInternalId());
					}
					internalTask.setCandidateId(internalExternalTaskDTO.getCandidateId());
					internalTask.setHiringCompanyName(internalExternalTaskDTO.getHiringCompanyName());
					internalTask.setJobPortalId(internalExternalTaskDTO.getJobPortalId());
					internalTask.setJobTitle(internalExternalTaskDTO.getJobTitle());
					internalTask.setExperienceRequired(internalExternalTaskDTO.getExperienceRequired());
					internalTask.setJobLocationId(internalExternalTaskDTO.getJobLocationId());
					internalTask.setTaxTypeId(internalExternalTaskDTO.getTaxTypeId());
					internalTask.setRate(internalExternalTaskDTO.getRate());
					internalTask.setRecruiterName(internalExternalTaskDTO.getRecruiterName());
					internalTask.setRecruiterEmail(internalExternalTaskDTO.getRecruiterEmail());
					internalTask.setRecruiterPhone(internalExternalTaskDTO.getRecruiterPhone());
					internalTask.setJobSubmissionPortalId(internalExternalTaskDTO.getJobSubmissionPortalId());
					internalTask.setPortalName(internalExternalTaskDTO.getPortalName());
					internalTask.setDatePosted(internalExternalTaskDTO.getDatePosted());
					internalTask.setJobLink(internalExternalTaskDTO.getJobLink());
					internalTask.setVisaId(internalExternalTaskDTO.getVisaId());
					internalTask.setJobReferenceNumber((internalExternalTaskDTO.getJobReferenceNumber()));

					internalTask.setJobAddress(internalExternalTaskDTO.getJobAddress());
					internalTask.setJobCity(internalExternalTaskDTO.getJobCity());
					internalTask.setJobState(internalExternalTaskDTO.getJobState());
					internalTask.setJobDescription(internalExternalTaskDTO.getJobDescription());

					internalTask.setTaskId((createdTask.getTaskId()));

					internalCreatedTask = internalTaskRepo.save(internalTask);
					internalExternalTaskDTO.setInternalId(internalCreatedTask.getInternalId());

				} else if (task.getPlacementId() == EXTERNAL_PLACEMENT_ID) {
					externalTask = new ExternalTask();
					System.out.println(internalExternalTaskDTO.toString());
					if (internalExternalTaskDTO.getExternalId() > 0) {
						externalTask.setExternalId(internalExternalTaskDTO.getExternalId());
					}
					externalTask.setCandidateName(internalExternalTaskDTO.getCandidateName());
					externalTask.setCandidateCompany(internalExternalTaskDTO.getCandidateCompany());
					externalTask.setCompanyAddress(internalExternalTaskDTO.getCompanyAddress());
					externalTask.setHrName(internalExternalTaskDTO.getHrName());
					externalTask.setHrEmail(internalExternalTaskDTO.getHrEmail());
					externalTask.setHrPhone(internalExternalTaskDTO.getHrPhone());
					externalTask.setJobPortalId(internalExternalTaskDTO.getJobPortalId());
					externalTask.setHiringCompanyName(internalExternalTaskDTO.getHiringCompanyName());
					externalTask.setJobTitle(internalExternalTaskDTO.getJobTitle());
					externalTask.setExperienceRequired(internalExternalTaskDTO.getExperienceRequired());
					externalTask.setJobLocationId(internalExternalTaskDTO.getJobLocationId());
					externalTask.setTaxTypeId(internalExternalTaskDTO.getTaxTypeId());
					externalTask.setRate(internalExternalTaskDTO.getRate());
					externalTask.setRecruiterName(internalExternalTaskDTO.getRecruiterName());
					externalTask.setRecruiterEmail(internalExternalTaskDTO.getRecruiterEmail());
					externalTask.setRecruiterPhone(internalExternalTaskDTO.getRecruiterPhone());
					externalTask.setJobSubmissionPortalId(internalExternalTaskDTO.getJobSubmissionPortalId());
					externalTask.setPortalName(internalExternalTaskDTO.getPortalName());
					externalTask.setDatePosted(internalExternalTaskDTO.getDatePosted());
					externalTask.setJobLink(internalExternalTaskDTO.getJobLink());
					externalTask.setVisaId(internalExternalTaskDTO.getVisaId());
					externalTask.setJobReferenceNumber((internalExternalTaskDTO.getJobReferenceNumber()));
					externalTask.setTaskId((createdTask.getTaskId()));

					externalTask.setJobAddress(internalExternalTaskDTO.getJobAddress());
					externalTask.setJobCity(internalExternalTaskDTO.getJobCity());
					externalTask.setJobState(internalExternalTaskDTO.getJobState());
					externalTask.setJobDescription(internalExternalTaskDTO.getJobDescription());

					externalTask.setCandidateExperience(internalExternalTaskDTO.getCandidateExperience());
					externalTask.setExpectedMinSalary(internalExternalTaskDTO.getExpectedMinSalary());
					externalTask.setExpectedMaxSalary(internalExternalTaskDTO.getExpectedMaxSalary());
					externalTask.setWillingToRelocate(internalExternalTaskDTO.isWillingToRelocate());
					externalTask.setWillingToNegotiateSalary(internalExternalTaskDTO.isWillingToNegotiateSalary());

					externalTask.setReasonToFitForJob(internalExternalTaskDTO.getReasonToFitForJob());
					externalTask.setHiringCompanyId(internalExternalTaskDTO.getHiringCompanyId());

					externalCreatedTask = externalTaskRepo.save(externalTask);
					internalExternalTaskDTO.setExternalId(externalCreatedTask.getExternalId());

				}

				if (file != null && (externalCreatedTask != null || internalCreatedTask != null)) {
					// adding attachement file
					TaskAttachment taskAttachment = new TaskAttachment();
					taskAttachment.setTaskID(createdTask.getTaskId());
					taskAttachment.setFileName(file.getOriginalFilename());
					taskAttachment.setAttachedBy(internalExternalTaskDTO.getEmployeeId());
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
				reason.setReasonText(internalExternalTaskDTO.getReason());
				reason.setTaskId(createdTask.getTaskId());
				reason.setEmployeeId(internalExternalTaskDTO.getEmployeeId());
				reason.setStatusId(internalExternalTaskDTO.getTaskStatus());
				reason.setAssignedTo(internalExternalTaskDTO.getTaskAssignedTo());
				Reason createdReason = this.reasonRepo.save(reason);

				logger.info("created Reason " + createdReason.getReasonText());
				if (createdReason != null) {
					internalExternalTaskDTO.setTaskId(createdTask.getTaskId());
				}

				return internalExternalTaskDTO;
			}
		} catch (Exception e) {
			logger.error("Error while processing data: " + e.getMessage(), e);
		}

		return null;

	}

	/**
	 * Get a task by its ID.
	 *
	 * @param id The ID of the task to retrieve.
	 * @return The retrieved Task object, or null if not found.
	 */
	@Override
	public InternalExternalTaskDTO getInternalOrExternalTaskByTaskId(int id) {
		logger.debug("Entering getTaskById");

		Task task = taskRepo.findByTaskId(id);
		InternalExternalTaskDTO internalExternalTaskDTO = null;
		InternalTask internalTask = null;
		ExternalTask externalTask = null;

		try {
			if (task != null) {

				internalTask = internalTaskRepo.findByTaskId(task.getTaskId());
				if (internalTask != null) {
					internalExternalTaskDTO = new InternalExternalTaskDTO(task, internalTask);
				} else {
					externalTask = externalTaskRepo.findByTaskId(task.getTaskId());
					if (externalTask != null) {
						internalExternalTaskDTO = new InternalExternalTaskDTO(task, externalTask);
					}
				}

			}
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

//		if (task.getPlacementId() == INTERNAL_PLACEMENT_ID) {
//			InternalTask internalTask = internalTaskRepo.findByTaskId(id);
//			internalExternalTaskDTO = new InternalExternalTaskDTO(task, internalTask);
//
//		} else if (task.getPlacementId() == EXTERNAL_PLACEMENT_ID) {
//			ExternalTask externalTask = externalTaskRepo.findByTaskId(id);
//			internalExternalTaskDTO = new InternalExternalTaskDTO(task, externalTask);
//		}

		logger.info("Founded internalExternalTaskDTO: " + internalExternalTaskDTO);
		return internalExternalTaskDTO;
	}
}
