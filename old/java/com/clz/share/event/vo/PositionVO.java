package com.clz.share.event.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty;

import com.clz.share.event.entity.EventInfo;

public class PositionVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private double eventLatitude;
	@NotEmpty
	private double eventLongitude;
	@NotEmpty
	private int distance;
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
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	

}
