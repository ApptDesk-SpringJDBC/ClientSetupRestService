package com.telappoint.clientsetup.model;

import java.text.ParseException;

import com.telappoint.clientsetup.common.utils.DateUtils;

public class SchedulerConfig {
	private Integer clientId;
	private String onlineSchedulerURL;
	private String database;
	private Integer calendarBlockTime;
	private String dayStartTime;
	private String dayEndTime;
	private boolean callCenterLogic = false;
	private String userName;
	private String password;
	private String dbTemplate;
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getOnlineSchedulerURL() {
		return onlineSchedulerURL;
	}
	public void setOnlineSchedulerURL(String onlineSchedulerURL) {
		this.onlineSchedulerURL = onlineSchedulerURL;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public Integer getCalendarBlockTime() {
		return calendarBlockTime;
	}
	public void setCalendarBlockTime(Integer calendarBlockTime) {
		this.calendarBlockTime = calendarBlockTime;
	}
	public String getDayStartTime() throws ParseException {
		if(dayStartTime.contains("AM") || dayStartTime.contains("PM")) {
			dayStartTime = DateUtils.convert12To24HoursFormat(dayStartTime)+":00";
		}
		return dayStartTime;
	}
	public void setDayStartTime(String dayStartTime) {
		this.dayStartTime = dayStartTime;
	}
	public String getDayEndTime() throws ParseException {
		if(dayEndTime.contains("AM") || dayEndTime.contains("PM")) {
			dayEndTime = DateUtils.convert12To24HoursFormat(dayEndTime)+":00";
		}
		return dayEndTime;
	}
	public void setDayEndTime(String dayEndTime) {
		this.dayEndTime = dayEndTime;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDbTemplate() {
		return dbTemplate;
	}
	public void setDbTemplate(String dbTemplate) {
		this.dbTemplate = dbTemplate;
	}
	public boolean isCallCenterLogic() {
		return callCenterLogic;
	}
	public void setCallCenterLogic(boolean callCenterLogic) {
		this.callCenterLogic = callCenterLogic;
	}
}
