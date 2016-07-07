package org.raunak.main.core.enums;

public enum MyErrorMessagesEnum {
	SUCCESS_CODE("200"),
	SUCCESS_MESSAGE("Success"),
	
	FAILURE_CODE("500"),
	FAILURE_MESSAGE("Failure"),
	
	DATA_NOT_FOUND_CODE("404"),
	DATA_NOT_FOUND_MESSAGE("Data Not Found."),
	
	INVALID_HEADER_CODE("505"),
	INVALID_HEADER_MESSAGE("Invalid Header.");
	
	private String value;

	private MyErrorMessagesEnum (String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
