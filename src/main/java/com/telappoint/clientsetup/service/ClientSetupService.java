package com.telappoint.clientsetup.service;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.telappoint.clientsetup.common.model.BaseResponse;
import com.telappoint.clientsetup.common.model.Client;
import com.telappoint.clientsetup.common.model.ResponseModel;
import com.telappoint.clientsetup.handlers.exception.TelAppointException;
import com.telappoint.clientsetup.model.ListOfThingsBring;
import com.telappoint.clientsetup.model.Locations;
import com.telappoint.clientsetup.model.SchedulerConfig;
import com.telappoint.clientsetup.model.Services;

/**
 * 
 * @author Balaji N
 *
 */
public interface ClientSetupService {
	public Logger getLogger(String clientCode, String device) throws TelAppointException;
	public ResponseModel populateRMDSuccessData(Logger logger, Object data) ;
	public ResponseEntity<ResponseModel> handleException(Logger logger, TelAppointException tae);
	public ResponseEntity<ResponseModel> authenticate(Logger logger, String userName, String password) throws TelAppointException;
	public ResponseEntity<ResponseModel> addClient(Logger logger, Client client, UriComponentsBuilder ucBuilder) throws TelAppointException;
	public ResponseEntity<ResponseModel> addSchedulerConfig(Logger logger, SchedulerConfig schedulerConfig, UriComponentsBuilder ucBuilder) throws TelAppointException;
	public ResponseEntity<ResponseModel> addLocations(Logger logger, Locations locations, UriComponentsBuilder ucBuilder) throws TelAppointException;
	public ResponseEntity<ResponseModel> addServices(Logger logger, Services services, UriComponentsBuilder ucBuilder) throws TelAppointException;
	public ResponseEntity<ResponseModel> getClients(Logger logger) throws TelAppointException;
	public ResponseEntity<ResponseModel> getClientById(Logger logger, long clientId) throws TelAppointException;
	public ResponseEntity<ResponseModel> getClientsWithRequiredInfo(Logger logger) throws TelAppointException;
	public ResponseEntity<ResponseModel> addListThingsBring(Logger logger, ListOfThingsBring listOfThingsBring, UriComponentsBuilder ucBuilder) throws TelAppointException;
	public ResponseEntity<ResponseModel> writeFileData(String clientCode, Logger logger, MultipartFile file) throws TelAppointException;
}
