package com.cpa.ttsms.dto;

import java.util.List;

public class InternalExternalListDTO {

	List<InternalTaskDTO> internalTask;
	List<ExternalTaskDTO> externalTask;

	public InternalExternalListDTO(List<InternalTaskDTO> internalTask, List<ExternalTaskDTO> externalTask) {
		super();
		this.internalTask = internalTask;
		this.externalTask = externalTask;
	}

	public InternalExternalListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<InternalTaskDTO> getInternalTask() {
		return internalTask;
	}

	public void setInternalTask(List<InternalTaskDTO> internalTask) {
		this.internalTask = internalTask;
	}

	public List<ExternalTaskDTO> getExternalTask() {
		return externalTask;
	}

	public void setExternalTask(List<ExternalTaskDTO> externalTask) {
		this.externalTask = externalTask;
	}

	@Override
	public String toString() {
		return "InternalExternalListDTO [internalTask=" + internalTask + ", externalTask=" + externalTask + "]";
	}

}
