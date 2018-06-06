package com.telappoint.clientsetup.controller;


import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import com.telappoint.clientsetup.common.model.Client;
import com.telappoint.clientsetup.common.model.ResponseModel;
import com.telappoint.clientsetup.handlers.exception.TelAppointException;
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
@Controller
@RequestMapping("/clientsetup")
public class ClientSetupController {

	@Autowired
	private ClientSetupService clientSetupService;
	
	private static final String logName = "clientSetup";
	@RequestMapping(method = RequestMethod.GET, value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseModel> authenticate(HttpServletRequest request, @RequestParam("userName") String userName, 
			@RequestParam("password") String password) {
		Logger logger = null;
		try {
			logger = clientSetupService.getLogger(logName, "INFO");
			return clientSetupService.authenticate(logger, userName, password);
		} catch (TelAppointException e) {
			return clientSetupService.handleException(logger, e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addClient", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseModel> addClient(HttpServletRequest request, @RequestBody Client client, UriComponentsBuilder ucBuilder) {
		Logger logger = null;
		try {
			logger = clientSetupService.getLogger(logName, "INFO");
			return clientSetupService.addClient(logger, client, ucBuilder);
		} catch (TelAppointException e) {
			return clientSetupService.handleException(logger, e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseModel> clients(HttpServletRequest request) {
		Logger logger = null;
		try {
			logger = clientSetupService.getLogger(logName, "INFO");
			return clientSetupService.getClients(logger);
		} catch (TelAppointException e) {
			return clientSetupService.handleException(logger, e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getClientsLite", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseModel> getClientsLite(HttpServletRequest request) {
		Logger logger = null;
		try {
			logger = clientSetupService.getLogger(logName, "INFO");
			return clientSetupService.getClientsWithRequiredInfo(logger);
		} catch (TelAppointException e) {
			return clientSetupService.handleException(logger, e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> getClient(@PathVariable("id") long id) {
		Logger logger = null;
		try {
			logger = clientSetupService.getLogger(logName, "INFO");
			return clientSetupService.getClientById(logger,id);
		} catch (TelAppointException e) {
			return clientSetupService.handleException(logger, e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addSchedulerConfig", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseModel> addSchedulerConfig(HttpServletRequest request, @RequestBody SchedulerConfig schedulerConfig, UriComponentsBuilder ucBuilder) {
		Logger logger = null;
		try {
			logger = clientSetupService.getLogger(logName, "INFO");
			return clientSetupService.addSchedulerConfig(logger, schedulerConfig, ucBuilder);
		} catch (TelAppointException e) {
			return clientSetupService.handleException(logger, e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addListThingsBring", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseModel> addListThingsBring(HttpServletRequest request, @RequestBody ListOfThingsBring listOfThingsBring, UriComponentsBuilder ucBuilder) {
		Logger logger = null;
		try {
			logger = clientSetupService.getLogger(logName, "INFO");
			return clientSetupService.addListThingsBring(logger, listOfThingsBring, ucBuilder);
		} catch (TelAppointException e) {
			return clientSetupService.handleException(logger, e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addLocations", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseModel> addLocations(HttpServletRequest request, @RequestBody Locations locations, UriComponentsBuilder ucBuilder) {
		Logger logger = null;
		try {
			logger = clientSetupService.getLogger(logName, "INFO");
			return clientSetupService.addLocations(logger, locations, ucBuilder);
		} catch (TelAppointException e) {
			return clientSetupService.handleException(logger, e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addServices", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseModel> addServices(HttpServletRequest request, @RequestBody Services services, UriComponentsBuilder ucBuilder) {
		Logger logger = null;
		try {
			logger = clientSetupService.getLogger(logName, "INFO");
			return clientSetupService.addServices(logger, services, ucBuilder);
		} catch (TelAppointException e) {
			return clientSetupService.handleException(logger, e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/uploadClientLogo", consumes = "multipart/form-data", produces = "application/json")
	public @ResponseBody ResponseEntity<ResponseModel> uploadClientLogo(@RequestParam("clientCode") String clientCode,@RequestParam("file") MultipartFile file) {
		Logger logger = null;
		try {
			logger = clientSetupService.getLogger(logName, "INFO");
			return clientSetupService.writeFileData(clientCode, logger, file);
		} catch (TelAppointException tae) {
			return clientSetupService.handleException(logger, tae);
		}
	}
}
