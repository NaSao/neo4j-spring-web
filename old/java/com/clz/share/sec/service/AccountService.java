package com.clz.share.sec.service;


import org.springframework.stereotype.Service;

import com.clz.share.sec.vo.AccountVO;
import com.clz.share.sec.util.UsernameAlreadyInUseException;

@Service 
public interface AccountService {
	
	public void createAccount(AccountVO accountVO) throws UsernameAlreadyInUseException;
	public AccountVO findAccount(String username);
	public void updateAccount(AccountVO account);
	public void changePassword(String username,String oldPassword, String newPassword);
	public void deleteAccount(String username);

}
