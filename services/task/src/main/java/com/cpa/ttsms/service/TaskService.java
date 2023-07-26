/**
 * @author  - Code Generator
 * @createdOn -  25-07-2023
 * @Description Entity class for Task Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.Task;

public interface TaskService {

	Task createTask(Task task);

	Task getTaskById(int id);

	List<Object> getAllTasks();

	Task updateTaskById(Task task, int id);

	int deleteTaskById(String id);

	List<Task> getAllParentTasksByStatus(String status);

	List<Task> getAllChildTasksByParentId(int parentId);

}