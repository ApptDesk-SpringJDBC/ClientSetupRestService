package com.telappoint.clientsetup.common.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 * @author Balaji N
 *
 */

@JsonAutoDetect
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Client {
	private long clientId;
	private String clientName;
	private String clientCode;
	private String clientLogoFilePath;
	private String contactPerson;
	private String contactPhone;
	
	private String contactEmail;
	private String contactTitle;
	private String clientWebsite;
	private String clientAddress;	
	private String clientCity;
	private String clientState;
	private String clientZip;
	private String clientTimeZone;
	
	private String billingName;
	private String billingEmail;
	private String billingCCEmail;
	
	private boolean hideSchedulerInput;
	private boolean hideRemindersInput;
	private boolean hideLocationInput;
	private boolean hideServiceInput;
	private boolean hideListOfDocsToBring;
	private boolean onlineScheduler;
	private boolean phoneScheduler;
	private String preferredAreaCode;
	private String preferredCity;
	private String preferredCountry;
	private String phoneReminder;
	private String textReminder;
	private String emailReminder;
	private Integer status;
	//'1-PendingClientInput, 2-Ready, 3-PendingDBSetup, 5-SetupComplete',
	
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientLogoFilePath() {
		return clientLogoFilePath;
	}
	public void setClientLogoFilePath(String clientLogoFilePath) {
		this.clientLogoFilePath = clientLogoFilePath;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getClientWebsite() {
		return clientWebsite;
	}
	public void setClientWebsite(String clientWebsite) {
		this.clientWebsite = clientWebsite;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getClientCity() {
		return clientCity;
	}
	public void setClientCity(String clientCity) {
		this.clientCity = clientCity;
	}
	public String getClientState() {
		return clientState;
	}
	public void setClientState(String clientState) {
		this.clientState = clientState;
	}
	public String getClientZip() {
		return clientZip;
	}
	public void setClientZip(String clientZip) {
		this.clientZip = clientZip;
	}
	public String getClientTimeZone() {
		return clientTimeZone;
	}
	public void setClientTimeZone(String clientTimeZone) {
		this.clientTimeZone = clientTimeZone;
	}
	
	public boolean isOnlineScheduler() {
		return onlineScheduler;
	}
	public void setOnlineScheduler(boolean onlineScheduler) {
		this.onlineScheduler = onlineScheduler;
	}
	public boolean isPhoneScheduler() {
		return phoneScheduler;
	}
	public void setPhoneScheduler(boolean phoneScheduler) {
		this.phoneScheduler = phoneScheduler;
	}
	public String getPreferredAreaCode() {
		return preferredAreaCode;
	}
	public void setPreferredAreaCode(String preferredAreaCode) {
		this.preferredAreaCode = preferredAreaCode;
	}
	public String getPreferredCity() {
		return preferredCity;
	}
	public void setPreferredCity(String preferredCity) {
		this.preferredCity = preferredCity;
	}
	public String getPreferredCountry() {
		return preferredCountry;
	}
	public void setPreferredCountry(String preferredCountry) {
		this.preferredCountry = preferredCountry;
	}
	public String getPhoneReminder() {
		return phoneReminder;
	}
	public void setPhoneReminder(String phoneReminder) {
		this.phoneReminder = phoneReminder;
	}
	public String getTextReminder() {
		return textReminder;
	}
	public void setTextReminder(String textReminder) {
		this.textReminder = textReminder;
	}
	public String getEmailReminder() {
		return emailReminder;
	}
	public void setEmailReminder(String emailReminder) {
		this.emailReminder = emailReminder;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getContactTitle() {
		return contactTitle;
	}
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
	
	public boolean isHideSchedulerInput() {
		return hideSchedulerInput;
	}
	public void setHideSchedulerInput(boolean hideSchedulerInput) {
		this.hideSchedulerInput = hideSchedulerInput;
	}
	public boolean isHideRemindersInput() {
		return hideRemindersInput;
	}
	public void setHideRemindersInput(boolean hideRemindersInput) {
		this.hideRemindersInput = hideRemindersInput;
	}
	
	public boolean isHideLocationInput() {
		return hideLocationInput;
	}
	public void setHideLocationInput(boolean hideLocationInput) {
		this.hideLocationInput = hideLocationInput;
	}
	public boolean isHideServiceInput() {
		return hideServiceInput;
	}
	public void setHideServiceInput(boolean hideServiceInput) {
		this.hideServiceInput = hideServiceInput;
	}
	public boolean isHideListOfDocsToBring() {
		return hideListOfDocsToBring;
	}
	public void setHideListOfDocsToBring(boolean hideListOfDocsToBring) {
		this.hideListOfDocsToBring = hideListOfDocsToBring;
	}
	public String getBillingName() {
		return billingName;
	}
	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}
	public String getBillingEmail() {
		return billingEmail;
	}
	public void setBillingEmail(String billingEmail) {
		this.billingEmail = billingEmail;
	}
	public String getBillingCCEmail() {
		return billingCCEmail;
	}
	public void setBillingCCEmail(String billingCCEmail) {
		this.billingCCEmail = billingCCEmail;
	}
}