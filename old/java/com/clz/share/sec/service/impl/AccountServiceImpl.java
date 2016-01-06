package com.clz.share.sec.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.clz.share.sec.converter.AccountConverter;
import com.clz.share.sec.entity.Account;
import com.clz.share.sec.entity.Authorities;
import com.clz.share.sec.service.AccountService;
import com.clz.share.sec.util.UsernameAlreadyInUseException;
import com.clz.share.sec.vo.AccountVO;


public class AccountServiceImpl implements AccountService{

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private AccountConverter accountConverter; 
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional
	public void createAccount(AccountVO accountVO) throws UsernameAlreadyInUseException{
		try {
			Session sess= sessionFactory.getCurrentSession();
					
			Account acc =accountConverter.convertVOToEntity(accountVO);
			
			acc.setPassword(passwordEncoder.encode(acc.getPassword()));
			
			Long accId=(Long)sess.save(acc);
			Account account= (Account) sess.load(Account.class, accId);
			
			Authorities auth =new Authorities(account, "USER");
			sess.save(auth);

		} catch (Exception e) {
			e.printStackTrace();
			//return null;
			throw new UsernameAlreadyInUseException(accountVO.getEmail());
		}
	}

	
	@Transactional
	public AccountVO findAccount(String username){
		AccountVO accountVO = null;
		Account account = null;
		// TODO Auto-generated method stub	
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Account.class);
		if(username != null && !username.isEmpty()){
			c.add(Restrictions.eq("email",username));
			if(c.list().size()>0){
				account = (Account) c.list().get(0);
			}
		}
		accountVO = accountConverter.convertEntityToVO(account);
		return accountVO;	
	}

	public void deleteAccount(String username){

	}
		
	public void changePassword(String username, String oldPassword,
			String newPassword) {
		// TODO Auto-generated method stub
		
	}

	public void updateAccount(AccountVO account) {
		// TODO Auto-generated method stub
		
	}

}
