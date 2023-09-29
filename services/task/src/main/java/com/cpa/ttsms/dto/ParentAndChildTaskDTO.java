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
	public void setChildTasks(List<Task> childTasks) {
		this.allTasks = childTasks;
	}
	public ParentAndChildTaskDTO(List<Task> parentTasks, List<Task> childTasks) {
		super();
		this.parentTasks = parentTasks;
		this.allTasks = childTasks;
	}
	public ParentAndChildTaskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ParentAndChildTaskDTO [parentTasks=" + parentTasks + ", childTasks=" + allTasks + "]";
	}
}
