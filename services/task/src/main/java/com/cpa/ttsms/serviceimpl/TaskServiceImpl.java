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

//import com.cpa.ttsms.controller.TaskController;
import com.cpa.ttsms.entity.Task;
import com.cpa.ttsms.repository.TaskRepo;
import com.cpa.ttsms.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepo taskRepo;
	private static Logger logger;

	public TaskServiceImpl() {
		logger = Logger.getLogger(TaskServiceImpl.class);
	}

	/**
	 * @param : Task task
	 * @return : Task createdTask
	 * @description : For creating/inserting entry in task table
	 */
	@Override
	public Task createTask(Task task) {
		logger.debug("Entering createTask");
		Task createdTask = null;

		// task.setTaskCreatedBy("admin");
		// task.setTaskModifiedBy("admin");

		createdTask = taskRepo.save(task);
		logger.info("created Task :" + createdTask);
		return createdTask;
	}

	/**
	 * @param : String id
	 * @return : Task task
	 * @description : For get entry in task table
	 */
	@Override
	public Task getTaskById(int id) {
		logger.debug("Entering getTaskById");

		Task task = taskRepo.findByTaskId(id);
		logger.info("Founded task :" + task);

		return task;
	}

	/**
	 * @return : List<Object> task
	 * @description : For fetching all task which are active state from task table
	 */
	@Override
	public List<Object> getAllTasks() {
		logger.debug("Entering getAllTasks");

		List<Object> tasks = taskRepo.findByTaskIsActiveTrue();
		logger.info("Fetched all active task :" + tasks);
		return tasks;
	}

	/**
	 * @param : Task to update
	 * @return : task
	 * @description : For updating task of task table
	 */
	@Override
	public Task updateTaskById(Task task, int id) {
		logger.debug("Entering updateTask");

		Task toUpdatedTask = null;
		Task updatedTask = null;

		toUpdatedTask = taskRepo.findByTaskId(id);
		logger.info("exisitng Task :: " + toUpdatedTask);

		if (toUpdatedTask != null) {
			logger.debug("setting new data of Task to exisitng Task");

//			task.setModifiedBy("admin");

			updatedTask = taskRepo.save(task);

			logger.info("updated Task :" + updatedTask);
		}

		return updatedTask;
	}

	/**
	 * @param : String id
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of Task
	 * 
	 */
	@Override
	public int deleteTaskById(String id) {
		logger.debug("Entering deleteTaskById");

		int count = taskRepo.deleteTaskById(id);
		logger.info("deleted Task count : " + count);
		return count;
	}

	@Override
	public List<Task> getAllParentTasksByStatus(String status) {
		switch (status.toLowerCase()) {
		case "created":
			return taskRepo.findByTaskParentIsNullAndTaskStatus("Created");
		case "done":
			return taskRepo.findByTaskParentIsNullAndTaskStatus("Done");
		case "inprogress":
			return taskRepo.findByTaskParentIsNullAndTaskStatusNotIn("Created", "Done");
		default:
			throw new IllegalArgumentException("Invalid status provided.");
		}
	}

	@Override
	public List<Task> getAllChildTasksByParentId(int parentId) {
		return taskRepo.findByTaskParent(parentId);
	}
}
