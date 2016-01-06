package com.clz.share.event.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.clz.share.event.entity.EventInfo;
import com.clz.share.event.entity.EventParticipant;
import com.clz.share.event.entity.EventParticipantId;
import com.clz.share.event.vo.EventParticipantVO;
import com.clz.share.event.vo.EventVO;
import com.clz.share.sec.entity.Account;
import com.clz.share.sec.vo.AccountVO;

@Service
public class EventConverter {
	//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");  
	
	public EventInfo convertVOToEntity(EventVO evo){
		EventInfo entity = new EventInfo();
		entity.setEventId(evo.getEventId());
		entity.setEventName(evo.getEventName());
		entity.setStartDate(evo.getStartDate());
		entity.setEndDate(evo.getEndDate());
		entity.setEventLatitude(evo.getEventLatitude());
		entity.setEventLongitude(evo.getEventLongitude());
		entity.setEventLocation(evo.getEventLocation());
		entity.setAccountId(evo.getAccountId());
		entity.setEventType(evo.getEventType());
		entity.setEventDescription(evo.getEventDescription());
		entity.setEventState(evo.getEventState());
		entity.setCity(evo.getCity());
		entity.setDetail(evo.getDetail());
		entity.setPostcode(evo.getPostcode());
		entity.setStreet(evo.getStreet());
		return entity;
	}
	
	public EventVO convertEntityToVO(EventInfo entity){
		EventVO evo = new EventVO();
		evo.setEventId(entity.getEventId());
		evo.setEventName(entity.getEventName());
		evo.setStartDate(entity.getStartDate());
		evo.setEndDate(entity.getEndDate());
		evo.setEventLatitude(entity.getEventLatitude());
		evo.setEventLongitude(entity.getEventLongitude());
		evo.setEventLocation(entity.getEventLocation());
		evo.setAccountId(entity.getAccountId());
		evo.setEventType(entity.getEventType());
		evo.setEventDescription(entity.getEventDescription());
		evo.setEventState(entity.getEventState());
		evo.setCity(entity.getCity());
		evo.setDetail(entity.getDetail());
		evo.setPostcode(entity.getPostcode());
		evo.setStreet(entity.getStreet());
		return evo;
	}
	
	public EventVO convertParticipantEntityToEventVO(EventParticipant EPentity){
		EventVO evo = new EventVO();
		EventInfo entity = EPentity.getEventInfo();
		Account acc = EPentity.getAccount();
		evo.setEventId(entity.getEventId());
		evo.setEventName(entity.getEventName());
		evo.setStartDate(entity.getStartDate());
		evo.setEndDate(entity.getEndDate());
		evo.setEventLatitude(entity.getEventLatitude());
		evo.setEventLongitude(entity.getEventLongitude());
		evo.setEventLocation(entity.getEventLocation());
		evo.setAccountId(entity.getAccountId());
		evo.setEventType(entity.getEventType());
		evo.setEventDescription(entity.getEventDescription());
		evo.setEventState(entity.getEventState());
		evo.setCity(entity.getCity());
		evo.setDetail(entity.getDetail());
		evo.setPostcode(entity.getPostcode());
		evo.setStreet(entity.getStreet());
		evo.setUsername(acc.getFirstname()+" "+acc.getLastname());
		return evo;
	}
	
	public List<EventVO> convertEntityListTOVOList(List<EventInfo> evenList){
		List<EventVO> evoList = new ArrayList<EventVO>();
		for(int i=0;i<evenList.size();i++){
			evoList.add(this.convertEntityToVO(evenList.get(i)));
		}
		return evoList;
	}
	
	public EventParticipantId convertEventPVOToEpIdEntity(EventParticipantVO epVO){
		EventParticipantId epId = new EventParticipantId();
		epId.setAccountId(epVO.getAccountId());
		epId.setEventId(epVO.getEventId());
		return epId;
	}
	
	public EventParticipant convertEpVOToEpEntity(EventParticipantVO epVO){
		EventParticipantId epId = this.convertEventPVOToEpIdEntity(epVO);
		Account account = new Account();
		account.setAccountId(epVO.getAccountId());
		EventInfo event = new EventInfo();
		event.setEventId(epVO.getEventId());
		EventParticipant ep = new EventParticipant(epId,account,event,epVO.getContribution());
		return ep;
	}
	
	public List<EventParticipantVO> convertEpEntityListToEpVOList(List<EventParticipant> epList){
		List<EventParticipantVO> epVOList = new ArrayList<EventParticipantVO>();
		for(int i =0;i<epList.size();i++){
			EventParticipantVO epVO = new EventParticipantVO();
			EventParticipant ep = epList.get(i);
			epVO.setContribution(ep.getContribution());
			epVO.setUsername(ep.getAccount().getFirstname()+" `"+ep.getAccount().getLastname());
			epVOList.add(epVO);
		}
		return epVOList;
	}
	
	public List<AccountVO> convertEpEntityListToAccountVOList(List<EventParticipant> epList){
		List<AccountVO> accountList = new ArrayList<AccountVO>();
		for(int i =0;i<epList.size();i++){
			Account aentity = epList.get(i).getAccount();
			AccountVO avo = new AccountVO();
			avo.setAccountId(aentity.getAccountId());
			avo.setFirstname(aentity.getFirstname());
			avo.setLastname(aentity.getLastname());
			avo.setEmail(aentity.getEmail());
			accountList.add(avo);
		}
		return accountList;
	}
	

	
	public List<EventVO> convertEpEntityListToEventVOList(List<EventParticipant> epList){
		List<EventVO> evoList = new ArrayList<EventVO>();
		for(int i=0; i<epList.size();i++){
			EventInfo einfo = epList.get(i).getEventInfo();
			EventVO evo = this.convertEntityToVO(einfo);
			Account acc = epList.get(i).getAccount();
			evo.setUsername(acc.getFirstname()+" "+acc.getLastname());
			evoList.add(evo);
		}
		return evoList;
	}
}
