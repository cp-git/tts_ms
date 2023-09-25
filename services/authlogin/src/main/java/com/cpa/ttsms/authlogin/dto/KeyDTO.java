package com.cpa.ttsms.authlogin.dto;

public class KeyDTO {

	private String key;
	private Object data;

	/**
	 * 
	 */
	public KeyDTO() {
		super();
	}

	/**
	 * @param key
	 */
	public KeyDTO(String key) {
		super();
		this.key = key;
	}

	/**
	 * @param key
	 * @param data
	 */
	public KeyDTO(String key, Object data) {
		super();
		this.key = key;
		this.data = data;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

}
