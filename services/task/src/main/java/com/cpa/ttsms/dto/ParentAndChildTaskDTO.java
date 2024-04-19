package com.cpa.ttsms.dto;

import java.util.List;

public class ParentAndChildTaskDTO {
	List<TaskDTO> parentTasks;
	List<TaskDTO> allTasks;

	public List<TaskDTO> getParentTasks() {
		return parentTasks;
	}

	public void setParentTasks(List<TaskDTO> parentTasks) {
		this.parentTasks = parentTasks;
	}

	public List<TaskDTO> getChildTasks() {
		return allTasks;
	}

	public void setChildTasks(List<TaskDTO> allTasks) {
		this.allTasks = allTasks;
	}

	public ParentAndChildTaskDTO(List<TaskDTO> parentTaskList, List<TaskDTO> allTasks) {
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
