package com.telappoint.clientsetup.handlers.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Balaji N
 *
 */
public class TelAppointException extends Exception {
	public String code;
	public String message; // userMessage
	public String internalErrorMessage; // internal message - more about exception including printstackTrace
	public HttpStatus httpStatus;
	private Object inputData; // request bean data. it will used to send an email about error. {including inputData too}
	
	public TelAppointException(String code, String message,  HttpStatus httpStatus,String internalErrorMessage, Object inputData) {
		setCode(code);
		setMessage(message);
		setInternalErrorMessage(internalErrorMessage);
		setHttpStatus(httpStatus);
		setInputData(inputData);
	}

	public TelAppointException() {

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

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getInternalErrorMessage() {
		return internalErrorMessage;
	}

	public void setInternalErrorMessage(String internalErrorMessage) {
		this.internalErrorMessage = internalErrorMessage;
	}

	public Object getInputData() {
		return inputData;
	}

	public void setInputData(Object inputData) {
		this.inputData = inputData;
	}	
	
}
