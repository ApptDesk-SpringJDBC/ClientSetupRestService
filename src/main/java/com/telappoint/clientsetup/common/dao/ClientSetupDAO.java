package com.telappoint.clientsetup.common.dao;

import java.util.Map;

import org.apache.log4j.Logger;

import com.telappoint.clientsetup.common.model.Client;
import com.telappoint.clientsetup.handlers.exception.TelAppointException;
import com.telappoint.clientsetup.model.ListOfThingsBring;
import com.telappoint.clientsetup.model.Locations;
import com.telappoint.clientsetup.model.SchedulerConfig;
import com.telappoint.clientsetup.model.Services;

public interface ClientSetupDAO {
	public  void getClients(final Map<String, Client> clientCacheMap) throws TelAppointException;
	public void addClient(Logger logger, Client client) throws TelAppointException;
	public void addSchedulerConfig(Logger logger, SchedulerConfig schedulerConfig) throws TelAppointException;
	public void addLocations(Logger logger, Locations locations) throws TelAppointException;
	public void addServices(Logger logger, Services services) throws TelAppointException;
	public boolean isClientExist(Logger logger, Client client) throws TelAppointException;
	public void saveListOfThingBring(Logger logger, ListOfThingsBring listOfThingsBring) throws TelAppointException;
}
