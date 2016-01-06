package com.clz.share.event.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clz.share.event.service.EventService;
import com.clz.share.event.vo.EventParticipantVO;
import com.clz.share.event.vo.EventVO;
import com.clz.share.event.vo.PositionVO;
import com.clz.share.sec.vo.AccountVO;

@Controller
public class EventController {
	
	private Logger logger = Logger.getLogger(EventController.class);
	
	@Autowired
	EventService eventService;
	
	/**
	 * Create an Event
	 * @param event
	 * @return success 200
	 */
	@RequestMapping(value = "/event/createEvent", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody String createEvent(@Valid @RequestBody EventVO event){
		//Hand over Event Name, StartDate, EndStart, location(double, double), type 
		try{
			System.out.println(""+event);
			eventService.addEvent(event);
		}catch(Exception e){
			e.printStackTrace();
			throw new AccountIsNullException();
		}
		return "200";
	}
	
	/**
	 * get the near Events by setting a distance
	 * @param currentPosition
	 * @param distance
	 * @return list of eventVO
	 */
	@RequestMapping(value = "/event/getEventsNearBy", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<EventVO> getEventsNearBy(@RequestBody PositionVO pvo){
		//TODO: implement event create; This would need to return a JSON Array event objects
		List<EventVO> eventList = eventService.getEventsByDistance(pvo);
		return eventList;
	}
	
	/**
	 * update an Event
	 * @param event
	 * @return success 200
	 */
	@RequestMapping(value = "/event/updateEvent", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody String updateEvent(@Valid @RequestBody EventVO event){
		//TODO: implement event update
		//be able to update name, startdate, enddate, location, type
		try{
			eventService.updateEvent(event);
		}catch(Exception e){
			e.printStackTrace();
			throw new AccountIsNullException();
		}
		return "200";
	}
	
	/**
	 * get all the events belongs to one account
	 * @param accountId
	 * @param eventState  the state of the event
	 * @return list of eventVO
	 */
	@RequestMapping(value = "/event/getEventsByAccountId_old", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<EventVO> getEventByAccountId_old(@RequestBody long accountId, @RequestBody String eventState){
		return eventService.getEventByAccountId(accountId, eventState);
	}
	
	@RequestMapping(value = "/event/getEventsByAccountId", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<EventVO> getEventByAccountId(@RequestBody long accountId){
		Long id = new Long(accountId);
		//long accountId=7; 
		return eventService.getEventByAccountId(id);
	}
	/**
	 * get an Event by its ID
	 * @param eventId
	 * @return a Object eventVO
	 */
	@RequestMapping(value = "/event/getEventsByEventId", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody EventVO getEventByEventId(@RequestBody String eventId){
		return eventService.getEventByEventId(eventId);
	}
	
	/**
	 * delete an Event by its ID
	 * @param eventId
	 * @return success 200
	 */
	@RequestMapping(value = "/event/deleteEventsByEventId", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody String deleteEventByEventId(@RequestBody String eventId){
		eventService.deleteEventByEventId(eventId);
		return "200";
	}
	
	/**
	 * get all Events
	 * @return list of eventVO
	 */
	@RequestMapping(value = "/event/getAllEvents", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<EventVO> getAllEvents(){
		//System.out.println("aaaaaaaaa");
		return eventService.getAllEventS();
	}
	
	
	/**
	 * Add a participant to a event
	 * @param event
	 * @return success 200
	 */
	@RequestMapping(value = "/event/addParticipant", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody String addParticipant(@Valid @RequestBody EventParticipantVO epVO){
		//Hand over Event Name, StartDate, EndStart, location(double, double), type 
		try{
			eventService.addParticipant(epVO);;
		}catch(Exception e){
			e.printStackTrace();
			throw new OnlyOneTimeParticipantException();
		}
		return "200";
	}
	
	/**
	 * update a participant to a event
	 * @param event
	 * @return success 200
	 */
	@RequestMapping(value = "/event/updateParticipant", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody String updateParticipant(@Valid @RequestBody EventParticipantVO epVO){
		//Hand over Event Name, StartDate, EndStart, location(double, double), type 
		try{
			eventService.updateParticipant(epVO);
		}catch(Exception e){
			e.printStackTrace();
			throw new OnlyOneTimeParticipantException();
		}
		return "200";
	}
	
	/**
	 * cancel a participant to a event
	 * @param event
	 * @return success 200
	 */
	@RequestMapping(value = "/event/cancelParticipant", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody String cancelParticipant(@Valid @RequestBody EventParticipantVO epVO){
		//Hand over Event Name, StartDate, EndStart, location(double, double), type 
		try{
			eventService.cancelParticipant(epVO);
		}catch(Exception e){
			e.printStackTrace();
			throw new OnlyOneTimeParticipantException();
		}
		return "200";
	}
	
	/**
	 * get all participant who joining the event
	 * @param eventId
	 * @return list of accountVO
	 */
	@RequestMapping(value = "/event/getParticipantByEventId", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<EventParticipantVO> getParticipantByEventId(@RequestBody String eventId){
		return eventService.getParticipantByEventId(eventId);
	}
	
	/**
	 * get all the events which accountId has participant
	 * @param eventId
	 * @return list of eventVO
	 */
	@RequestMapping(value = "/event/getEventByParticipantAccountId", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<EventVO> getEventByParticipantAccountId(@RequestBody Long accountId){
		return eventService.getEventByParticipantAccountId(accountId);
	}
	
	/**
	 * see if an account has participanted the event
	 * @param eventId
	 * @return 0 is not has participanted, 1 is  participant 
	 */
	@RequestMapping(value = "/event/isAccountParticipant", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody int isAccountParticipant(@RequestBody EventParticipantVO epVO){
		//0 is false not yet
		int isP = 0;
		if(eventService.isAccountParticipant(epVO)){
			isP=1;
		}
		return isP;
	}
	
	@RequestMapping(value = "/event/getPartByEventId", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody EventVO getPartByEventId(@RequestBody String eventId){
		return eventService.getPartByEventId(eventId);
	}
	
	
	/**
	 * Exception:Account Id cannot be null
	 * @author Triger
	 *
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	class AccountIsNullException extends RuntimeException {

		public AccountIsNullException() {
			super("Account Id cannot be null");
		}
	}
	
	/**
	 * Exception:can only participant the same event one time
	 * @author Triger
	 *
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	class OnlyOneTimeParticipantException extends RuntimeException {

		public OnlyOneTimeParticipantException() {
			super("eventId and accountId cannot be null, "
					+ "and you can only participant the same event one time");
		}
	}
}
