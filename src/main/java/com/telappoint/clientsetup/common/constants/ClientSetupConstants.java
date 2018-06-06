package com.telappoint.clientsetup.common.constants;

/**
 * @author Balaji
 */
public enum ClientSetupConstants {
	PENDING_CLIENT_INPUT(1, "PendingClientInput"),
	READY(2,"Ready"),
	PENDING_DBSETUP(3,"PendingDBSetup"),
	SETUPCOMPLETE(4, "SetupComplete");
	
	private Integer value;
	private String description;
	private ClientSetupConstants(Integer value, String desc) {
		this.setValue(value);
		this.setDescription(desc);
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
