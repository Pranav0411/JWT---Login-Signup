package com.usecase.exception;

public class Exceptionss extends RuntimeException{

	private static final long serialVersionUID = 1L;
	String resourceName;
	String fieldName;
	long fieldValue;
	public Exceptionss(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %l", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public long getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
}
