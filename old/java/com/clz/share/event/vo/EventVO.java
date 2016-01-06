package com.clz.share.event.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty;

import com.clz.share.event.entity.EventInfo;

public class EventVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String eventId;

	@NotEmpty
	private String eventName;
	// @NotEmpty
	private Date startDate;
	// @NotEmpty
	private Date endDate;

	// POINT(30.620076 104.067221)
	private String eventLocation;
	
	private String eventType;
	
	private double eventLatitude;

	private double eventLongitude;

	//@NotEmpty
	private Long accountId;

	private String eventDescription;

	private String eventState;

	private String city;
	
	private String street;
	
	private String postcode;
	
	private String detail;
	
	private String username;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEventState() {
		return eventState;
	}

	public void setEventState(String eventState) {
		this.eventState = eventState;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}




	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public double getEventLatitude() {
		return eventLatitude;
	}

	public void setEventLatitude(double eventLatitude) {
		this.eventLatitude = eventLatitude;
	}

	public double getEventLongitude() {
		return eventLongitude;
	}

	public void setEventLongitude(double eventLongitude) {
		this.eventLongitude = eventLongitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

}
