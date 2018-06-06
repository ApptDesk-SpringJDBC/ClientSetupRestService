package com.telappoint.clientsetup.common.constants;

/**
 * 
 * @author Balaji N
 *
 */
public enum ErrorConstants {
	// DB layer error codes
	ERROR_1000("1000", "Error in .."), 
	ERROR_1001("1001", "Error in getLogger instance."), 
	ERROR_1002("1002", "Error while adding client to DB"),
	ERROR_1003("1003", "Error while adding locations to DB"),
	ERROR_1004("1004", "Error while adding services to DB"),
	ERROR_1005("1005", "Error while adding scheduler config to DB"),
	ERROR_1006("1006", "Error while fetching client information from DB"),
	ERROR_1007("1007", "Error while adding list of things bring to DB"),
	ERROR_1008("1008","Error while parsing dayStart/dayEnd Time "),
	
	// Service layer error codes
	ERROR_2000("2000", "Sorry, Authentication error"),
	ERROR_2001("2001", "Unauthorized"),
	ERROR_2002("2002", "Client already exist!");

	private String code;
	private String message;

	private ErrorConstants(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
