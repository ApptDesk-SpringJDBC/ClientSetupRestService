package com.telappoint.clientsetup.common.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.telappoint.clientsetup.common.constants.ClientSetupConstants;
import com.telappoint.clientsetup.common.constants.ErrorConstants;
import com.telappoint.clientsetup.common.dao.ClientSetupDAO;
import com.telappoint.clientsetup.common.model.Client;
import com.telappoint.clientsetup.handlers.exception.TelAppointException;
import com.telappoint.clientsetup.model.ListOfThingsBring;
import com.telappoint.clientsetup.model.Location;
import com.telappoint.clientsetup.model.Locations;
import com.telappoint.clientsetup.model.SchedulerConfig;
import com.telappoint.clientsetup.model.Service;
import com.telappoint.clientsetup.model.Services;

/**
 * 
 * @author Balaji N
 *
 */
@Repository
public class ClientSetupDAOImpl implements ClientSetupDAO {

	@Autowired
	private JdbcTemplate clientSetupJdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ClientSetupDAOImpl(JdbcTemplate jdbcTemplate) {
		this.clientSetupJdbcTemplate = jdbcTemplate;
	}

	public ClientSetupDAOImpl() {
	}

	@Override
	public void getClients(final Map<String, Client> clientCacheMap) throws TelAppointException {
		String query = "select * from client c";
		clientSetupJdbcTemplate.query(query.toString(), new ResultSetExtractor<Map<String, Client>>() {
			@Override
			public Map<String, Client> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Client client = null;
				String clientCode = null;
				while (rs.next()) {
					clientCode = rs.getString("client_code");
					
					client = new Client();
					client.setClientId(rs.getInt("id"));
					client.setClientCode(clientCode);
					client.setClientName(rs.getString("client_name"));
					client.setClientWebsite(rs.getString("client_website"));
					client.setContactPerson(rs.getString("contact_person"));
					client.setContactEmail(rs.getString("contact_email"));
					client.setContactPhone(rs.getString("contact_phone"));
					client.setClientAddress(rs.getString("client_address"));
					client.setClientState(rs.getString("client_state"));
					client.setClientZip(rs.getString("client_zip"));
					client.setBillingName(rs.getString("billing_name"));
					client.setBillingEmail(rs.getString("billing_email"));
					client.setBillingCCEmail(rs.getString("billing_ccemail"));
					client.setHideListOfDocsToBring("Y".equals(rs.getString("hide_list_of_doc_bring")) ? true:false);
					client.setHideLocationInput("Y".equals(rs.getString("hide_locations_input")) ? true:false);
					client.setHideServiceInput("Y".equals(rs.getString("hide_services_input")) ? true:false);
					client.setHideSchedulerInput("Y".equals(rs.getString("hide_scheduler_input")) ? true:false);
					clientCacheMap.put(""+rs.getInt("id"), client);
				}
				return clientCacheMap;
			}
		});
	}

	@Override
	public void addClient(Logger logger, Client client) throws TelAppointException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into client ");
		sql.append("(client_name,client_code, client_logo_filepath,contact_person,contact_phone,contact_email,client_website,");
		sql.append("client_address, client_city, client_state,client_zip, client_timezone, billing_name, billing_email, billing_ccemail,");
		sql.append("hide_scheduler_input, hide_reminders_input, hide_locations_input, hide_services_input,");
		sql.append("online_scheduler,phone_scheduler,preferred_area_code,preferred_city,preferred_county,");
		sql.append("phone_reminder,text_reminder,email_reminder,status) values ");
		sql.append("(:clientName,:clientCode,:clientLogoPath,:contactPerson,:contactPhone,:contactEmail,:clientWebsite,");
		sql.append(":clientAddress, :clientCity,:clientState,:clientZip,:clientTimeZone, :billingName, :billingEmail,:billingCCEmail,");
		sql.append(":hideScheduleInput,:hideReminderInput,:hideLocationsInput,:hideServicesInput,");
		sql.append(":onlineScheduler,:phoneScheduler,:preferredAreaCode,:preferredCity,:preferredCounty,");
		sql.append(":phoneReminder, :textReminder,:emailReminder,:status)");
		 KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			MapSqlParameterSource mapParam = new MapSqlParameterSource();
			mapParam.addValue("clientName", client.getClientName());
			mapParam.addValue("clientCode", client.getClientCode());
			mapParam.addValue("clientLogoPath", client.getClientLogoFilePath());
			mapParam.addValue("contactPerson", client.getContactPerson());
			mapParam.addValue("contactPhone", client.getContactPhone());
			mapParam.addValue("contactEmail", client.getContactEmail());
			mapParam.addValue("clientWebsite", client.getClientWebsite());
			mapParam.addValue("clientAddress",client.getClientAddress());
			mapParam.addValue("clientCity",client.getClientCity());
			mapParam.addValue("clientState", client.getClientState());
			mapParam.addValue("clientZip",client.getClientZip());
			mapParam.addValue("clientTimeZone",client.getClientTimeZone());
			mapParam.addValue("billingName", client.getBillingName());
			mapParam.addValue("billingEmail",client.getBillingEmail());
			mapParam.addValue("billingCCEmail",client.getBillingCCEmail());
			
			mapParam.addValue("hideScheduleInput", (client.isHideSchedulerInput()) ? "Y" : "N");
			mapParam.addValue("hideReminderInput", (client.isHideRemindersInput()) ? "Y" : "N");
			mapParam.addValue("hideLocationsInput", (client.isHideLocationInput()) ? "Y" : "N");
			mapParam.addValue("hideServicesInput", (client.isHideServiceInput()) ? "Y" : "N");
			mapParam.addValue("onlineScheduler", (client.isOnlineScheduler()) ? "Y" : "N");
			mapParam.addValue("phoneScheduler", (client.isPhoneScheduler()) ? "Y" : "N");
			mapParam.addValue("preferredAreaCode", client.getPreferredAreaCode());
			mapParam.addValue("preferredCity", client.getPreferredCity());
			mapParam.addValue("preferredCounty", client.getPreferredCountry());

			mapParam.addValue("phoneReminder", client.getPhoneReminder());
			mapParam.addValue("textReminder", client.getTextReminder());
			mapParam.addValue("emailReminder", client.getEmailReminder());
			mapParam.addValue("status", ClientSetupConstants.PENDING_CLIENT_INPUT.getValue());

			namedParameterJdbcTemplate.update(sql.toString(), mapParam, keyHolder);
			client.setClientId(keyHolder.getKey().longValue());
		} catch (DataAccessException dae) {
			logger.error("Error while adding the client to DB.");
			throw new TelAppointException(ErrorConstants.ERROR_1002.getCode(), ErrorConstants.ERROR_1002.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, dae.toString(), null);
		}
	}

	@Override
	public void addSchedulerConfig(Logger logger, SchedulerConfig schedulerConfig) throws TelAppointException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into scheduler_config ");
		sql.append("(client_id,online_scheduler_url,`database`,calendar_block_time,day_start_time,day_end_time,call_center_logic,user_name,`password`,db_template)");
		sql.append(" values (:clientId,:onlineSchedulerURL,:database,:calendarBlockTime,:dayStartTime,:dayEndTime,:callCenterLogic,:userName,:passowrd,:dbTemplate)");
		try {
			MapSqlParameterSource mapParam = new MapSqlParameterSource();
			mapParam.addValue("clientId", schedulerConfig.getClientId());
			mapParam.addValue("onlineSchedulerURL", schedulerConfig.getOnlineSchedulerURL());
			mapParam.addValue("database", schedulerConfig.getDatabase());
			mapParam.addValue("calendarBlockTime", schedulerConfig.getCalendarBlockTime());
			mapParam.addValue("dayStartTime", schedulerConfig.getDayStartTime());
			mapParam.addValue("dayEndTime", schedulerConfig.getDayEndTime());
			mapParam.addValue("callCenterLogic", schedulerConfig.isCallCenterLogic() ? "Y":"N");
			mapParam.addValue("userName", schedulerConfig.getUserName());
			mapParam.addValue("passowrd", schedulerConfig.getPassword());
			mapParam.addValue("dbTemplate", schedulerConfig.getDbTemplate());
			namedParameterJdbcTemplate.update(sql.toString(), mapParam);
		} catch (DataAccessException dae) {
			logger.error("Error while adding the schedulerConfig to DB.");
			throw new TelAppointException(ErrorConstants.ERROR_1005.getCode(), ErrorConstants.ERROR_1005.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, dae.toString(), null);
		} catch(ParseException pe) {
			logger.error("Error while parsing the dayStartTime/dayEndTime.");
			throw new TelAppointException(ErrorConstants.ERROR_1008.getCode(), ErrorConstants.ERROR_1008.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, pe.toString(), null);
		}
	}

	@Override
	public void addLocations(Logger logger, final Locations locations) throws TelAppointException {
		final String seatSQL = "insert into locations(client_id,name,address,city,state,zip,time_zone) values (?,?,?,?,?,?,?)";
		try {
			clientSetupJdbcTemplate.batchUpdate(seatSQL.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Location location = locations.getLocations().get(i);
					ps.setLong(1, locations.getClientId());
					ps.setString(2, location.getName());
					ps.setString(3, location.getAddress());
					ps.setString(4, location.getCity());
					ps.setString(5, location.getState());
					ps.setString(6, location.getZip());
					ps.setString(7, location.getTimeZone());
				}

				@Override
				public int getBatchSize() {
					return locations.getLocations().size();
				}
			});
		} catch (DataAccessException dae) {
			logger.error("Error while adding the locations to DB.");
			throw new TelAppointException(ErrorConstants.ERROR_1003.getCode(), ErrorConstants.ERROR_1003.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, dae.toString(), null);
		}
	}

	@Override
	public void addServices(Logger logger, Services services) throws TelAppointException {
		final String seatSQL = "insert into services(client_id,name,duration,allow_duplicate_appts) values (?,?,?,?)";
		try {
			clientSetupJdbcTemplate.batchUpdate(seatSQL.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Service service = services.getServices().get(i);
					ps.setLong(1, services.getClientId());
					ps.setString(2, service.getName());
					ps.setInt(3, Integer.valueOf(service.getDuration()));
					ps.setString(4, (service.isAllowDuplicateAppts())?"Y":"N");
				}

				@Override
				public int getBatchSize() {
					return services.getServices().size();
				}
			});
		} catch (DataAccessException dae) {
			logger.error("Error while adding the services to DB.");
			throw new TelAppointException(ErrorConstants.ERROR_1004.getCode(), ErrorConstants.ERROR_1004.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, dae.toString(), null);
		}
	}

	@Override
	public boolean isClientExist(Logger logger, Client client) throws TelAppointException {
		String sql = "select count(id) from client where LOWER(client_code)=?";
		int count = clientSetupJdbcTemplate.queryForInt(sql, new Object[]{client.getClientCode().toLowerCase()});
		if(count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void saveListOfThingBring(Logger logger, ListOfThingsBring listOfThingsBring) throws TelAppointException {
		try {
			String sql = "insert into list_of_things_bring (client_id, lang, display_text) values(?,?,?)";
			clientSetupJdbcTemplate.update(sql, new Object[]{listOfThingsBring.getClientId(), "us-en", listOfThingsBring.getListOfDocs()});
		} catch (DataAccessException dae) {
			logger.error("Error while adding the list of things bring to DB.");
			throw new TelAppointException(ErrorConstants.ERROR_1007.getCode(), ErrorConstants.ERROR_1007.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, dae.toString(), null);
		}
	}
}
