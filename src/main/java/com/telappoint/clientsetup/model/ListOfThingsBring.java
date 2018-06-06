package com.telappoint.clientsetup.model;


public class ListOfThingsBring {
	private Integer clientId;
	private String langCode;
	private String listOfDocs;
	
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
	public String getListOfDocs() {
		return listOfDocs;
	}
	public void setListOfDocs(String listOfDocs) {
		this.listOfDocs = listOfDocs;
	}
}
