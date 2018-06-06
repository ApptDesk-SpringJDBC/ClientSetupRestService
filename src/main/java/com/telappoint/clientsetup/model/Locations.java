package com.telappoint.clientsetup.model;

import java.util.List;

public class Locations {
	private Integer clientId;
	private List<Location> locations;
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
}
