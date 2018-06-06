package com.telappoint.clientsetup.common.constants;

/**
 * 
 * @author Balaji N
 *
 */
public enum PropertiesConstants {

	CLIENT_SETUP_REST_WS_PROP("clientSetupRestService.properties");
	
	private String propertyFileName;
	
	private PropertiesConstants(String propertyFileName) {
		this.setPropertyFileName(propertyFileName);
	}

	public String getPropertyFileName() {
		return propertyFileName;
	}

	public void setPropertyFileName(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}
}
