package com.cpa.ttsms.dto;

import java.util.List;

import com.cpa.ttsms.entity.Task;

public class ParentAndChildTaskDTO {
	List<Task> parentTasks;
	List<Task> allTasks;

	public List<Task> getParentTasks() {
		return parentTasks;
	}

	public void setParentTasks(List<Task> parentTasks) {
		this.parentTasks = parentTasks;
	}

	public List<Task> getChildTasks() {
		return allTasks;
	}

	public void setChildTasks(List<Task> allTasks) {
		this.allTasks = allTasks;
	}

	public ParentAndChildTaskDTO(List<Task> parentTaskList, List<Task> allTasks) {
		super();
		this.parentTasks = parentTaskList;
		this.allTasks = allTasks;
	}

	public ParentAndChildTaskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ParentAndChildTaskDTO [parentTasks=" + parentTasks + ", allTasks=" + allTasks + "]";
	}
}
