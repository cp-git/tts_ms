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
	@Transactional
	@Modifying
	@Query(value = "SELECT task.* FROM task JOIN status ON task.status = status.id WHERE task.parent = 0 AND status.code NOT IN (?1,?2)", nativeQuery = true)
	List<Task> findByTaskParentIsNullAndTaskStatusNotIn(String status1, String status2);

	/**
	 * Fetch all child tasks associated with a given parent task ID.
	 *
	 * @param parentId The ID of the parent task for which to fetch child tasks.
	 * @return A list of child tasks associated with the given parent task ID.
	 */
	List<Task> findByTaskParent(int parentId);

	// fetch all task using company and parent id
	List<Task> findByCompanyIdAndTaskParentOrderByTaskStartDate(int companyId, int parentId);

	// fetch all task using company and parent id
	List<Task> findByCompanyIdAndTaskParentAndTaskAssignedToOrderByTaskStartDate(int companyId, int parentId,
			int assignedTo);

	// fetch all task using company and parent id
	List<Task> findByCompanyIdAndTaskParentAndTaskStatusOrderByTaskStartDate(int companyId, int parentId, int status);

	// fetch all task using company and parent id
	List<Task> findByCompanyIdAndTaskParentAndTaskStatusAndTaskAssignedToOrderByTaskStartDate(int companyId,
			int parentId, int status, int assignedTo);

	// fetch all task using company and parent id
	List<Task> findByCompanyIdAndTaskParentAndTaskCreatedByOrderByTaskStartDate(int companyId, int parentId,
			int createdBy);

	// fetch all task using company and parent id
	List<Task> findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskAssignedToOrderByTaskStartDate(int companyId,
			int parentId, int createdBy, int assignedTo);

	// fetch all task using company and parent id
	List<Task> findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskStatusOrderByTaskStartDate(int companyId,
			int parentId, int createdBy, int status);

	// fetch all task using company and parent id
	List<Task> findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskStatusAndTaskAssignedToOrderByTaskStartDate(
			int companyId, int parentId, int createdBy, int status, int assignedTo);

	@Modifying
	@Query(value = "UPDATE public.task SET havingchild = true WHERE id = ?1", nativeQuery = true)
	int updateHavingChildToTrueByParentId(int parentId);

	public List<Task> findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskStatusInOrderByTaskStartDate(int companyId,
			int parentId, int createdBy, List<Integer> statusIds);

	public List<Task> findByCompanyIdAndTaskParentAndTaskCreatedByAndTaskStatusInAndTaskAssignedToOrderByTaskStartDate(
			int companyId, int parentId, int createdBy, List<Integer> statusIds, int assignedTo);

	public List<Task> findByCompanyIdAndTaskParentAndTaskStatusInOrderByTaskStartDate(int companyId, int parentId,
			List<Integer> statusIds);

	public List<Task> findByCompanyIdAndTaskParentAndTaskStatusInAndTaskAssignedToOrderByTaskStartDate(int companyId,
			int parentId, List<Integer> statusIds, int assignedTo);

	public List<Task> findByTaskAssignedToOrTaskCreatedByOrderByTaskEndDateDesc(int assingedTo, int createdBy);

	public List<Task> findByCompanyIdAndTaskParent(int companyId, int parentTask);

	/*
	 * Fetch all parent task using status, createdBy and assignedTo, copmany id
	 * using employee id
	 */
//	@Query(value = "SELECT task.* FROM public.task task JOIN public.status st ON task.status = st.id WHERE task.parent = 0 AND task.companyid = ?4 AND (?1 = 'ALL' OR (st.code = ?1)) AND ((?2 > 0 AND task.createdby = ?2) OR ?2 <= 0) AND ((?3 > 0 AND task.assignedto = ?3) OR ?3 <= 0)", nativeQuery = true)
//	List<Task> findTasksByStatusAndCreatorAndAssigneeOfCompanyByEmployeeId(String status, int createdBy, int assignedTo,
//			int employeeId);
	
	public List<Task> findByCompanyId(int companyId);
	
	@Query(value="Select concat(ts) from task ts,externaltask et where assignedto=?1 OR createdby=?2 AND ts.id=et.taskid",nativeQuery=true)
	public List<Task> findByTaskByCreatedByAndAssignedTo(int taskAssignedTo,int taskCreatedBy);
	
	@Query(value="SELECT * FROM task t1 INNER JOIN task t2 on t1.parent = t2.id AND t2.id=?1",nativeQuery=true)
	public List<Task> findChildTaskByParentId(int taskId);
}
