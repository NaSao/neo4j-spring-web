package com.clz.share.event.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clz.share.event.converter.EventConverter;
import com.clz.share.event.entity.EventInfo;
import com.clz.share.event.entity.EventParticipant;
import com.clz.share.event.entity.EventParticipantId;
import com.clz.share.event.service.EventService;
import com.clz.share.event.vo.EventParticipantVO;
import com.clz.share.event.vo.EventVO;
import com.clz.share.event.vo.PositionVO;
import com.clz.share.sec.entity.Account;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private EventConverter econverter;

	public int calculateDis(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		int dist = (int) (earthRadius * c / 1000);
		return dist;
	}
	

	public double[] getCoordinateFromString(String coordS) {
		double[] coord = new double[2];
		String[] coordSA = coordS.split(",");
		coord[0] = Float.parseFloat(coordSA[0].replace("(", ""));
		coord[1] = Float.parseFloat(coordSA[0].replace("(", ""));
		return coord;
	}

	public List<EventVO> getEventsByDistance(PositionVO pvo) {
		double lat = pvo.getEventLatitude();
		double lng = pvo.getEventLongitude();
		int distance = pvo.getDistance();
		Criteria c=sessionFactory.getCurrentSession().createCriteria(EventInfo.class);
		List<EventInfo> eList = c.list();
		List<EventVO> reList = new ArrayList<EventVO>();
		for(int i=0;i<eList.size();i++){
			EventInfo event = eList.get(i);
			int distBetw = calculateDis(event.getEventLatitude(),event.getEventLongitude(),lat,lng);
			if(distBetw<=distance){
				Account ac = getAccountByAccountId(event.getAccountId());
				EventVO en = econverter.convertEntityToVO(event);
				en.setUsername(ac.getFirstname()+" "+ac.getLastname());
				reList.add(en);
			}
		}
		return reList;
	}	

	@Transactional
	public void addEvent(EventVO evo) {
		evo.setEventLocation("POINT("+evo.getEventLatitude()+" "+evo.getEventLongitude()+")");
		Session session = sessionFactory.getCurrentSession();
		EventInfo entity = econverter.convertVOToEntity(evo);
		session.save(entity);
		session.flush();
	}

	@Transactional
	public void updateEvent(EventVO evo) {
		evo.setEventLocation("POINT("+evo.getEventLatitude()+" "+evo.getEventLongitude()+")");
		Session session = sessionFactory.getCurrentSession();
		EventInfo entity = econverter.convertVOToEntity(evo);
		session.merge(entity);
		session.flush();
	}


	@Transactional
	public List<EventVO> getEventByAccountId(long accountId, String eventState) {
		// TODO Auto-generated method stub
		if(accountId==0){
			return null;
		}
		Criteria c=sessionFactory.getCurrentSession().createCriteria(EventInfo.class);
		c.add(Restrictions.eq("accountId", accountId));
		if(!eventState.isEmpty())
			c.add(Restrictions.eq("eventState",eventState));
		return econverter.convertEntityListTOVOList(c.list());
	}

	@Override
	public List<EventVO> getEventByAccountId(long accountId) {
		// TODO Auto-generated method stub
		if(accountId==0){
			return null;
		}
		Criteria c=sessionFactory.getCurrentSession().createCriteria(EventInfo.class);
		c.add(Restrictions.eq("accountId", accountId));
		return econverter.convertEntityListTOVOList(c.list());
	}

	@Transactional
	public EventVO getEventByEventId(String eventId) {
		if("".equals(eventId)){
			return null;
		}
		Criteria c=sessionFactory.getCurrentSession().createCriteria(EventInfo.class);
		c.add(Restrictions.eq("eventId", eventId));
		List<EventInfo> epList = c.list();
		if(c.list().isEmpty()){
			return null;
		}
		else{
			EventInfo en = epList.get(0);
			Account ac = getAccountByAccountId(en.getAccountId());
			EventVO ev = econverter.convertEntityToVO(en);
			ev.setUsername(ac.getFirstname()+" "+ac.getLastname());
			return ev;
		}
	}

	@Transactional
	public List<EventVO> getAllEventS() {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(EventInfo.class);
		//Query query = sessionFactory.getCurrentSession().createQuery(hql); 
		return econverter.convertEntityListTOVOList(c.list());
	}
	
	@Transactional
	public List<EventVO> getEvents(String eventState) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(EventInfo.class);
		//Query query = sessionFactory.getCurrentSession().createQuery(hql); 
		if(eventState!=null)
			c.add(Restrictions.eq("eventState",eventState));
		
		return econverter.convertEntityListTOVOList(c.list());
	}

	@Transactional
	public void deleteEventByEventId(String eventId) {
		Session session = sessionFactory.getCurrentSession();
		EventInfo event = (EventInfo)session.load(EventInfo.class,eventId);
	    session.delete(event);
	    session.flush();
	}


	@Transactional
	public void addParticipant(EventParticipantVO epVO) {
		// TODO Auto-generated method stub
		EventParticipant epEntity = econverter.convertEpVOToEpEntity(epVO);
		Session session = sessionFactory.getCurrentSession();
		session.save(epEntity);
		session.flush();
	}
	
	@Transactional
	public void updateParticipant(EventParticipantVO epVO) {
		// TODO Auto-generated method stub
		EventParticipant epEntity = econverter.convertEpVOToEpEntity(epVO);
		Session session = sessionFactory.getCurrentSession();
		session.merge(epEntity);
		session.flush();
	}


	@Transactional
	public void cancelParticipant(EventParticipantVO epVO) {
		EventParticipant epEntity = econverter.convertEpVOToEpEntity(epVO);
		Session session = sessionFactory.getCurrentSession();
		session.delete(epEntity);
		session.flush();
	}


	public List<EventParticipantVO> getParticipantByEventId(String eventId) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(EventParticipant.class);
		c.createAlias("eventInfo","eventInfo");
		c.add(Restrictions.eq("eventInfo.eventId", eventId));
		List<EventParticipant> epList = c.list();
		List<EventParticipantVO> aList = econverter.convertEpEntityListToEpVOList(epList);
		return aList;
	}


	public List<EventVO> getEventByParticipantAccountId(Long accountId) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(EventParticipant.class);
		c.createAlias("account","account");
		c.add(Restrictions.eq("account.accountId", accountId));

		List<EventParticipant> epList = c.list();
		List<EventVO> eList = econverter.convertEpEntityListToEventVOList(epList);
		return eList;
	}


	public boolean isAccountParticipant(EventParticipantVO epVO) {
		EventParticipantId epId = new EventParticipantId();
		epId.setAccountId(epVO.getAccountId());
		epId.setEventId(epVO.getEventId());
		EventParticipant ep = (EventParticipant)sessionFactory.getCurrentSession().get(EventParticipant.class, epId);
		if(ep==null)
			return false;
		else
			return true;
	}

	public Account getAccountByAccountId(Long accountId){
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Account.class);
		c.add(Restrictions.eq("accountId", accountId));
		if(c.list().isEmpty())
			return null;
		else
			return (Account) c.list().get(0);
	}

	@Transactional
	public EventVO getPartByEventId(String eventId) {
		if("".equals(eventId)){
			return null;
		}
		Criteria c=sessionFactory.getCurrentSession().createCriteria(EventInfo.class);
		c.add(Restrictions.eq("eventId", eventId));
		List<EventInfo> epList = c.list();
		if(c.list().isEmpty()){
			return null;
		}
		else{
			EventInfo en = epList.get(0);
			EventVO ev = econverter.convertEntityToVO(en);
			return ev;
		}
	}

}
