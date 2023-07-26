/**
 * @author - Code Generator
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.Task;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.TaskService;

@RestController
@RequestMapping("/ttsms")
public class TaskController {

	@Autowired
	private TaskService taskService;;

	private ResourceBundle resourceBunde;
	private static Logger logger;

	TaskController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(TaskController.class);
	}

	@PostMapping("/task")
	public ResponseEntity<Object> createTask(@RequestBody Task task) throws CPException {
		logger.debug("Entering createTask");
		logger.info("data of creating Task  :" + task.toString());

		Task createdTask = null;
		try {

			Task toCheckTask = taskService.getTaskById(task.getTaskId());
			logger.debug("existing task :" + toCheckTask);

			if (toCheckTask == null) {

				// TODO: Uncomment below 2 lines and change the method name as per your Entity
				// class
				// task.setCreatedby("admin");
				// task.setUpdatedby("admin");

				createdTask = taskService.createTask(task);
				logger.info("Task created :" + createdTask);

				return ResponseHandler.generateResponse(createdTask, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed Task creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/task/{id}")
	public ResponseEntity<Object> getTaskById(@PathVariable("id") int id) throws CPException {
		logger.debug("Entering getTaskByid");
		logger.info("entered user name :" + id);

		Task task = null;

		try {

			task = taskService.getTaskById(id);
			logger.info("fetched Task :" + task);

			if (task != null) {
				logger.debug("Task fetched generating response");
				return ResponseHandler.generateResponse(task, HttpStatus.OK);
			} else {
				logger.debug("Task not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {

			logger.error("Failed getting task : " + ex.getMessage());
			throw new CPException("err001", resourceBunde.getString("err001"));
		}

	}

	@GetMapping("/task")
	public ResponseEntity<List<Object>> getAllTasks(@RequestParam(name = "id") String id) throws CPException {
		logger.debug("Entering getAllTask");
		logger.info("Parameter  :" + id);

		List<Object> tasks = null;

		try {

			if (id.equalsIgnoreCase("all")) {

				tasks = taskService.getAllTasks();
				logger.info("Fetched all Task :" + tasks);

				return ResponseHandler.generateListResponse(tasks, HttpStatus.OK);
			} else {

				logger.info(resourceBunde.getString("err002"));
				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
			}

		} catch (Exception ex) {

			logger.error("Failed getting all tasks : " + ex.getMessage());
			throw new CPException("err002", resourceBunde.getString("err002"));

		}
	}

	@DeleteMapping("/task/{id}")
	public ResponseEntity<Object> deleteTaskById(@PathVariable("id") String id) throws CPException {
		logger.debug("Entering deleteAuthUser");
		logger.info("entered deleteTask  :" + id);
		// TODO - implement the business logic

		int count = 0;

		try {
			count = taskService.deleteTaskById(id);
			if (count >= 1) {
				logger.info("deleted Task : Id = " + id);
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
			} else {
				logger.info(resourceBunde.getString("err005"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
			}

		} catch (Exception ex) {
			logger.error("Failed to delete Task :" + ex.getMessage());
			throw new CPException("err005", resourceBunde.getString("err005"));
		}

	}

	@PutMapping("/task/{id}")
	public ResponseEntity<Object> updateTaskById(@RequestBody Task task, @PathVariable("id") int id)
			throws CPException {
		logger.debug("Entering updateTask");
		logger.info("entered  updateTask :" + task);

		Task updatedTask = null;

		try {
			updatedTask = taskService.updateTaskById(task, id);

			if (updatedTask == null) {
				logger.info(resourceBunde.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				logger.info("updated task : " + updatedTask);
				return ResponseHandler.generateResponse(updatedTask, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			logger.error("Failed update Task : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}

	@GetMapping("/allparenttask")
	public List<Task> getAllParentTasksByStatus(@RequestParam("status") String status) {
		return taskService.getAllParentTasksByStatus(status);
	}

	@GetMapping("/allchilds/{parentid}")
	public List<Task> getAllChildTasksByParentId(@PathVariable("parentid") int parentId) {
		return taskService.getAllChildTasksByParentId(parentId);
	}
}
