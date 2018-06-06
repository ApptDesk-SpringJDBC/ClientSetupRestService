package com.telappoint.clientsetup.model;

import com.telappoint.clientsetup.common.model.BaseResponse;

public class ClientResponse extends BaseResponse {
	private long clientId;

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
}
