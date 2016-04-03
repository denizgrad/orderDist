package com.du.order.dist.model.util;

public class ValidationError extends Exception {

	String[] nullFields;
	String[] invalidFields;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String[] getNullFields() {
		return nullFields;
	}
	public void setNullFields(String[] nullFields) {
		this.nullFields = nullFields;
	}
	public String[] getInvalidFields() {
		return invalidFields;
	}
	public void setInvalidFields(String[] invalidFields) {
		this.invalidFields = invalidFields;
	}
	
	public ValidationError(String[] nullFields, String[] invalidFields) {
		this.nullFields = nullFields;
		this.invalidFields = invalidFields;
	}
	
	
	

}
