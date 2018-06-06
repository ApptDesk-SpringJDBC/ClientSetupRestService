package com.telappoint.clientsetup.model;

import java.util.List;

public class Services {
	private Integer clientId;
	private List<Service> services;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
}
