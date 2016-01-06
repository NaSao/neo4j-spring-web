package com.clz.share.event.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.clz.share.event.entity.EventInfo;
import com.clz.share.sec.entity.Account;


public class EventParticipantVO implements Serializable {


	private static final long serialVersionUID = 1L;
	//@NotEmpty
	private Long accountId;
	//@NotEmpty
	private String eventId;
	private String contribution;
	private String username;
	
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContribution() {
		return contribution;
	}
	public void setContribution(String contribution) {
		this.contribution = contribution;
	}
	
	
}
