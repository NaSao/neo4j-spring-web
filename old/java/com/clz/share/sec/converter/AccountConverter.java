package com.clz.share.sec.converter;


import org.springframework.stereotype.Service;

import com.clz.share.sec.entity.Account;
import com.clz.share.sec.vo.AccountVO;

@Service 
public class AccountConverter {
	
	public AccountVO convertEntityToVO(Account a){
		AccountVO avo = new AccountVO();		
		avo.setAccountId(a.getAccountId());
		avo.setFirstname(a.getFirstname());
		avo.setLastname(a.getLastname());
		avo.setEmail(a.getEmail());
		return avo;
	}

	public Account convertVOToEntity(AccountVO av){
		Account a = new Account();
		a.setAccountId(av.getAccountId());
		//
		a.setPassword(av.getPassword());
		a.setFirstname(av.getFirstname());
		a.setLastname(av.getLastname());
		a.setEmail(av.getEmail());
		
		return a;
	}
}
