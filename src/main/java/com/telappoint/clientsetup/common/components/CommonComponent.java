package com.telappoint.clientsetup.common.components;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.springframework.beans.MethodInvocationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telappoint.clientsetup.common.constants.ErrorConstants;
import com.telappoint.clientsetup.common.constants.PropertiesConstants;
import com.telappoint.clientsetup.common.logger.AsynchLogger;
import com.telappoint.clientsetup.common.logger.LoggerPool;
import com.telappoint.clientsetup.common.model.ResponseModel;
import com.telappoint.clientsetup.common.utils.PropertyUtils;
import com.telappoint.clientsetup.handlers.exception.TelAppointException;

/**
 * 
 * @author Balaji N
 *
 */
@Component
public class CommonComponent {

	private VelocityEngine ve = null;

	public CommonComponent() {
		ve = new VelocityEngine();
		Properties properties = new Properties();
		String logFileLocation = null;
		try {
			logFileLocation = PropertyUtils.getValueFromProperties("LOG_LOCATION_PATH", PropertiesConstants.CLIENT_SETUP_REST_WS_PROP.getPropertyFileName());
		} catch (Exception e) {
			if (logFileLocation == null) {
				logFileLocation = "/home/jenkin/logs";
			}
		}
		properties.setProperty("runtime.log", logFileLocation + "/velocity.log");
		properties.setProperty("resource.loader", "string");
		properties.setProperty("string.resource.loader.class", "org.apache.velocity.runtime.resource.loader.StringResourceLoader");
		ve.init(properties);
	}

	

	public Logger getLogger(String clientCode, String logLevel) throws TelAppointException {
		Logger asynchLogger = null;
		Level level = getLogLevel(logLevel);
		String logFileLocation = null;
		try {
			logFileLocation = PropertyUtils.getValueFromProperties("LOG_LOCATION_PATH", PropertiesConstants.CLIENT_SETUP_REST_WS_PROP.getPropertyFileName());
			if (LoggerPool.getHandleToLogger(clientCode) == null) {
				asynchLogger = (new AsynchLogger(logFileLocation + "/" + clientCode, level)).getLogger(clientCode);
				LoggerPool.addLogger(clientCode, asynchLogger);
			}
			return LoggerPool.getHandleToLogger(clientCode);
		} catch (IOException ioe) {
			throw new TelAppointException(ErrorConstants.ERROR_1001.getCode(), ErrorConstants.ERROR_1001.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ioe.toString(),
					null);
		} catch (Exception e) {
			throw new TelAppointException(ErrorConstants.ERROR_1001.getCode(), ErrorConstants.ERROR_1001.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e.toString(),
					null);
		}
	}

	public Logger changeLogLevel(String siloName, String logLevel) throws Exception {
		Logger asynchLogger = null;
		Level level = getLogLevel(logLevel);
		String logFileLocation = PropertyUtils.getValueFromProperties("LOG_LOCATION_PATH", PropertiesConstants.CLIENT_SETUP_REST_WS_PROP.getPropertyFileName());
		if (LoggerPool.getHandleToLogger(siloName) != null) {
			asynchLogger = (new AsynchLogger(logFileLocation + "/" + siloName, level)).setLoggerProperties(LoggerPool.getHandleToLogger(siloName), level);
			LoggerPool.addLogger(siloName, asynchLogger);
		}
		return LoggerPool.getHandleToLogger(siloName);
	}

	public StringWriter processTemplate(String templateName, String templateContent, Object data) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		StringWriter writer = new StringWriter();
		VelocityContext context = new VelocityContext();
		StringResourceRepository repo = StringResourceLoader.getRepository();
		repo.putStringResource(templateName, templateContent);
		context.put("dynamicPlaceHolderData", data);
		Template t = ve.getTemplate(templateName);
		t.merge(context, writer);

		return writer;
	}

	public ResponseModel populateRMDSuccessData(Logger logger, Object data) {
		return populateRMDSuccessData(logger, data, true);
	}

	public ResponseModel populateRMDData(Logger logger, Object data) {
		return populateRMDSuccessData(logger, data, true);
	}

	public ResponseModel populateRMDSuccessData(Logger logger, Object data, boolean logging) {
		if (logging == false) {
			// TODO: log it
		}

		ResponseModel responseModel = new ResponseModel();
		responseModel.setData(data);
		return responseModel;
	}

	private Level getLogLevel(String logLevel) {
		Level level = null;
		if (logLevel == null) {
			level = Level.INFO;
		} else {
			if (logLevel.equalsIgnoreCase("TRACE")) {
				level = Level.TRACE;
			} else if (logLevel.equalsIgnoreCase("DEBUG")) {
				level = Level.DEBUG;
			} else {
				level = Level.INFO;
			}
		}
		return level;
	}
	
	public @ResponseBody ResponseEntity<ResponseModel> handleException(Logger logger, TelAppointException be) {
		return ResponseModel.exceptionResponse(logger, be);
	}
}
