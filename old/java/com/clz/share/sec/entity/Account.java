package com.clz.share.sec.entity;
// Generated 14.09.2015 14:50:51 by Hibernate Tools 4.3.1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.clz.share.event.entity.EventParticipant;

/**
 * Account generated by hbm2java
 */
@Entity
@Table(name = "account", catalog = "share", uniqueConstraints = @UniqueConstraint(columnNames = "email") )
public class Account implements java.io.Serializable {

	private Long accountId;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private Set<Authorities> authoritieses = new HashSet<Authorities>(0);
	private Set<EventParticipant> eventParticipants = new HashSet<EventParticipant>(0);

	public Account() {
	}

	public Account(String password, String email, String firstname, String lastname, Set<Authorities> authoritieses,
			Set<EventParticipant> eventParticipants) {
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.authoritieses = authoritieses;
		this.eventParticipants = eventParticipants;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "account_id", unique = true, nullable = false)
	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "password", length = 150)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", unique = true, length = 150)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "firstname", length = 150)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname", length = 150)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Authorities> getAuthoritieses() {
		return this.authoritieses;
	}

	public void setAuthoritieses(Set<Authorities> authoritieses) {
		this.authoritieses = authoritieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<EventParticipant> getEventParticipants() {
		return this.eventParticipants;
	}

	public void setEventParticipants(Set<EventParticipant> eventParticipants) {
		this.eventParticipants = eventParticipants;
	}

}
