package com.clz.share.event.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clz.share.event.vo.EventParticipantVO;
import com.clz.share.event.vo.EventVO;
import com.clz.share.event.vo.PositionVO;
import com.clz.share.sec.entity.Account;
import com.clz.share.sec.vo.AccountVO;

@Service
public interface EventService {
	
	/**
	 * Calculate the distance of two coordinate 
	 * @param lat1 Latitude
	 * @param lng1 Longitude
	 * @param lat2
	 * @param lng2
	 * @return distance km
	 */
	public int calculateDis(double lat1,double lng1,double lat2,double lng2);
	
	/**
	 * get the coordinate from a String
	 * @param coordS
	 * @return  coordinate float array
	 */
	public double[] getCoordinateFromString(String coordS);
	
	/**
	 * get the near by events by distance
	 * @param pvo
	 * @return
	 */
	public List<EventVO> getEventsByDistance(PositionVO pvo);
	
	/**
	 * add event
	 * @param event
	 * @return success or unsuccess
	 */
	public void addEvent(EventVO event);
	
	/**
	 * update event
	 * @param event
	 * @return success or unsuccess
	 */
	public void updateEvent(EventVO event);
	
	/**
	 * get all the events belongs to one account
	 * @param userId
	 * @param eventState
	 * @return Events
	 */
	public List<EventVO> getEventByAccountId(long accountId, String eventState);
	
	public List<EventVO> getEventByAccountId(long accountId);
	
	/**
	 * get an Event by its ID
	 * @param eventId
	 * @return Event
	 */
	public EventVO getEventByEventId(String eventId);
	
	/**
	 * delete an Event by its ID
	 * @param eventId
	 * @return Event
	 */
	public void deleteEventByEventId(String eventId);
	
	/**
	 * add Participant, join the event
	 * @param epVO
	 */
	public void addParticipant(EventParticipantVO epVO);
	
	/**
	 * modify contribution
	 * @param epVO
	 */
	public void updateParticipant(EventParticipantVO epVO);
	
	/**
	 * cancel Participant, delete the join data
	 * @param epVO
	 */
	public void cancelParticipant(EventParticipantVO epVO);
	
	/**
	 * get all participant who joining the event
	 * @param eventId
	 * @return list of participant
	 */
	public List<EventParticipantVO> getParticipantByEventId(String eventId);
	
	/**
	 * get all the events which accountId has participant
	 * @param accountId
	 * @return list of event
	 */
	public List<EventVO> getEventByParticipantAccountId(Long accountId);
	
	/**
	 * see if this account has participant a event
	 * @param epVO
	 * @return true is has participanted, false is not participant yet
	 */
	public boolean isAccountParticipant(EventParticipantVO epVO);
	
	/**
	 * 
	 * @return
	 */
	public List<EventVO> getAllEventS();
	
	public Account getAccountByAccountId(Long accountId);
	public EventVO getPartByEventId(String eventId);
}
