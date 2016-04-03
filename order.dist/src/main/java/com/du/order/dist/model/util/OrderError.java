package com.du.order.dist.model.util;

public class OrderError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String description;
	

	public OrderError(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
