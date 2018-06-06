package com.telappoint.clientsetup.common.model;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.http.ResponseEntity;

import com.telappoint.clientsetup.handlers.exception.TelAppointException;

/**
 * 
 * @author Balaji N
 *
 */
@JsonAutoDetect
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseModel {
	private Object data;
	private Object errors;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResponseEntity<ResponseModel> exceptionResponse(Logger logger, TelAppointException be) {
		ResponseModel jsonData = new ResponseModel();
		Errors errors = new Errors();
		errors.setMessage(be.getMessage());
		errors.setCode(be.getCode());
		jsonData.setErrors(errors);
		logger.error("Error Response: "+be.toString());
		logger.error("Error: "+be, be);
		return new ResponseEntity<ResponseModel>(jsonData, be.getHttpStatus());
	}
	
	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}
}
