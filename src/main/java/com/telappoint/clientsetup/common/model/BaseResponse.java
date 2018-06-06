package com.telappoint.clientsetup.common.model;
import java.net.URI;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BaseResponse {
	private boolean status = true;
	private String message;
	private URI resourceUri;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public URI getResourceUri() {
		return resourceUri;
	}
	public void setResourceUri(URI resourceUri) {
		this.resourceUri = resourceUri;
	}
	
}
