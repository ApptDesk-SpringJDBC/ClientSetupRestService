package com.telappoint.clientsetup.model;

public class Service {
	private String name;
	private String duration;
	private boolean allowDuplicateAppts = true;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public boolean isAllowDuplicateAppts() {
		return allowDuplicateAppts;
	}

	public void setAllowDuplicateAppts(boolean allowDuplicateAppts) {
		this.allowDuplicateAppts = allowDuplicateAppts;
	}
}
