package com.telappoint.clientsetup.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.telappoint.clientsetup.common.components.CommonComponent;
import com.telappoint.clientsetup.common.constants.ErrorConstants;
import com.telappoint.clientsetup.common.constants.PropertiesConstants;
import com.telappoint.clientsetup.common.dao.ClientSetupDAO;
import com.telappoint.clientsetup.common.model.BaseResponse;
import com.telappoint.clientsetup.common.model.Client;
import com.telappoint.clientsetup.common.model.Errors;
import com.telappoint.clientsetup.common.model.ResponseModel;
import com.telappoint.clientsetup.common.utils.PropertyUtils;
import com.telappoint.clientsetup.handlers.exception.TelAppointException;
import com.telappoint.clientsetup.model.ClientResponse;
import com.telappoint.clientsetup.model.ListOfThingsBring;
import com.telappoint.clientsetup.model.Locations;
import com.telappoint.clientsetup.model.SchedulerConfig;
import com.telappoint.clientsetup.model.Services;
import com.telappoint.clientsetup.service.ClientSetupService;

/**
 * 
 * @author Balaji N
 *
 */
@Service
public class ClientSetupServiceImpl implements ClientSetupService {

	@Autowired
	private ClientSetupDAO clientSetupDAO;

	@Autowired
	private CommonComponent commonComponent;

	public ResponseModel populateRMDSuccessData(Logger logger, Object data) {
		return commonComponent.populateRMDData(logger, data);
	}

	public ResponseEntity<ResponseModel> handleException(Logger logger, TelAppointException tae) {
		return commonComponent.handleException(logger, tae);
	}

	@Override
	public Logger getLogger(String clientCode, String logLevel) throws TelAppointException {
		return commonComponent.getLogger(clientCode, logLevel);
	}

	@Override
	public ResponseEntity<ResponseModel> authenticate(Logger logger, String userName, String password) throws TelAppointException {
		try {
			String userNameFromProp = PropertyUtils.getValueFromProperties("userName", PropertiesConstants.CLIENT_SETUP_REST_WS_PROP.getPropertyFileName());
			String passowrdFromProp = PropertyUtils.getValueFromProperties("password", PropertiesConstants.CLIENT_SETUP_REST_WS_PROP.getPropertyFileName());
			if (userName.equals(userNameFromProp) && password.equals(passowrdFromProp)) {
				return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, new BaseResponse()), HttpStatus.OK);
			} else {
				Errors error = getError(ErrorConstants.ERROR_2001.getCode(), ErrorConstants.ERROR_2001.getMessage());
				return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, error), HttpStatus.UNAUTHORIZED);
			}
		} catch (IOException ioe) {
			throw new TelAppointException(ErrorConstants.ERROR_2000.getCode(), ErrorConstants.ERROR_2000.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ioe.toString(), null);
		}
	}

	private Errors getError(String code, String message) {
		Errors error = new Errors();
		error.setCode(code);
		error.setMessage(message);
		return error;
	}

	@Override
	public ResponseEntity<ResponseModel> addClient(Logger logger, Client client, UriComponentsBuilder ucBuilder) throws TelAppointException {
		boolean isClientExist = isClientExist(logger, client);
		if (isClientExist) {
			Errors error = getError(ErrorConstants.ERROR_2002.getCode(), ErrorConstants.ERROR_2002.getMessage());
			return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, error), HttpStatus.CONFLICT);
		} else {
			clientSetupDAO.addClient(logger, client);
			ClientResponse clientResponse = new ClientResponse();
			clientResponse.setClientId(client.getClientId());
			clientResponse.setResourceUri(ucBuilder.path("/clientsetup/client/{id}").buildAndExpand(client.getClientId()).toUri());
			return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, clientResponse), HttpStatus.CREATED);
		}
	}

	private boolean isClientExist(Logger logger, Client client) throws TelAppointException {
		return clientSetupDAO.isClientExist(logger, client);
	}

	@Override
	public ResponseEntity<ResponseModel> addSchedulerConfig(Logger logger, SchedulerConfig schedulerConfig, UriComponentsBuilder ucBuilder) throws TelAppointException {
		clientSetupDAO.addSchedulerConfig(logger, schedulerConfig);
		return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, new BaseResponse()), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseModel> addLocations(Logger logger, Locations locations, UriComponentsBuilder ucBuilder) throws TelAppointException {
		clientSetupDAO.addLocations(logger, locations);
		return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, new BaseResponse()), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseModel> addServices(Logger logger, Services services, UriComponentsBuilder ucBuilder) throws TelAppointException {
		clientSetupDAO.addServices(logger, services);
		return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, new BaseResponse()), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseModel> getClients(Logger logger) throws TelAppointException {
		Map<String, Client> clientMap = new LinkedHashMap<String, Client>();
		clientSetupDAO.getClients(clientMap);
		// TODO: get it from cache.
		if (clientMap.size() > 0) {
			List<Client> clientList = new ArrayList<Client>(clientMap.values());
			return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, clientList), HttpStatus.OK);
		} else {
			return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, null), HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getClientsWithRequiredInfo(Logger logger) throws TelAppointException {
		Map<String, Client> clientMap = new LinkedHashMap<String, Client>();
		clientSetupDAO.getClients(clientMap);
		// TODO: get it from cache.
		if (clientMap.size() > 0) {
			List<Client> clientList = new ArrayList<Client>(clientMap.values());
			clientList = getRequiredClientInfo(clientList);
			return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, clientList), HttpStatus.OK);
		} else {
			return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, null), HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getClientById(Logger logger, long clientId) throws TelAppointException {
		Map<String, Client> clientMap = new LinkedHashMap<String, Client>();
		clientSetupDAO.getClients(clientMap);
		// TODO: get it from cache.
		if (clientMap.size() > 0) {
			Client client = clientMap.get("" + clientId);
			return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, client), HttpStatus.OK);
		} else {
			return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, null), HttpStatus.NOT_FOUND);
		}
	}

	public List<Client> getRequiredClientInfo(List<Client> clientList) {
		List<Client> clientListInfo = new ArrayList<Client>();
		Client clientIn = null;
		for (Client client : clientList) {
			clientIn = new Client();
			clientIn.setClientId(client.getClientId());
			clientIn.setClientName(client.getClientName());
			clientListInfo.add(clientIn);
		}
		return clientListInfo;
	}

	@Override
	public ResponseEntity<ResponseModel> addListThingsBring(Logger logger, ListOfThingsBring listOfThingsBring, UriComponentsBuilder ucBuilder) throws TelAppointException {
		clientSetupDAO.saveListOfThingBring(logger, listOfThingsBring);
		return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, new BaseResponse()), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseModel> writeFileData(String clientCode, Logger logger, MultipartFile file) throws TelAppointException {
		BaseResponse baseResponse = new BaseResponse();
		String originalFileName = file.getOriginalFilename();
		OutputStream out = null;
		try {
			String uploadedFileLocation = PropertyUtils.getValueFromProperties("CLIENT_LOGOS_BASE_LOCATION", PropertiesConstants.CLIENT_SETUP_REST_WS_PROP.getPropertyFileName());
			if(uploadedFileLocation == null) {
				logger.error("CLIENT_LOGOS_BASE_LOCATION not configured.");
				baseResponse.setStatus(false);
				baseResponse.setMessage("Configuration is missed!");
				return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, baseResponse), HttpStatus.OK);
			}
			
			uploadedFileLocation = uploadedFileLocation +"/"+file.getOriginalFilename();
			String fileExtension = originalFileName.substring(originalFileName.indexOf(".") + 1);
			if ("jpg".equalsIgnoreCase(fileExtension) || "png".equalsIgnoreCase(fileExtension) || "gif".equalsIgnoreCase(fileExtension)) {
				String output = "File uploaded to : " + uploadedFileLocation;
				int read = 0;
				byte[] bytes = new byte[1024];
				out = new FileOutputStream(new File(uploadedFileLocation));
				InputStream inputStream = file.getInputStream();
				while ((read = inputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				logger.info("Client logo uploaded to :" + output);
			} else {
				logger.error("Not allowed file extension: "+fileExtension);
				baseResponse.setStatus(false);
				baseResponse.setMessage("File format is not allowed. {"+fileExtension+"}");
			}
		} catch (IOException ioe) {
			logger.error("Error:"+ioe,ioe);
			baseResponse.setStatus(false);
			baseResponse.setMessage("Failed while uploading client logo file.");
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				logger.error("Error:" + e, e);
			}
		}
		return new ResponseEntity<ResponseModel>(commonComponent.populateRMDData(logger, baseResponse), HttpStatus.OK);
	}
}
