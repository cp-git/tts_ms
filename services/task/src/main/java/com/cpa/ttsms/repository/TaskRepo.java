/**
 * @author  - Code Generator
 * @createdOn -  25-07-2023
 * @Description Entity class for Task
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {

	/**
	 * Find a task in the database by its unique identifier (task ID).
	 *
	 * @param id The task ID to search for.
	 * @return The Task entity if found, otherwise null.
	 */
	public Task findByTaskId(int id);

	/**
	 * Fetch all parent tasks with the specified status code.
	 *
	 * @param status The status code of the tasks to fetch.
	 * @return A list of parent tasks with the specified status code.
	 */
	@Transactional
	@Modifying
	@Query(value = "SELECT task.* FROM task JOIN status ON task.status = status.id WHERE task.parent = 0 AND status.code = ?1", nativeQuery = true)
	List<Task> findByTaskParentIsNullAndTaskStatus(String status);

	/**
	 * Fetch all parent tasks with status not equal to the provided status codes.
	 *
	 * @param statuses Status codes to be excluded from the search.
	 * @return A list of parent tasks with status not in the provided status codes.
	 */
	List<Task> findByTaskParentIsNullAndTaskStatusNotIn(String... statuses);

	/**
	 * Fetch all child tasks associated with a given parent task ID.
	 *
	 * @param parentId The ID of the parent task for which to fetch child tasks.
	 * @return A list of child tasks associated with the given parent task ID.
	 */
	List<Task> findByTaskParent(int parentId);

}
