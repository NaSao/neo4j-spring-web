package com.clz.share.sec.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.clz.share.sec.service.AccountService;
import com.clz.share.sec.vo.AccountVO;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	
	/**
	 * Return the user account informations
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/me", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody AccountVO myAccount(Principal principal){		
		AccountVO avo = accountService.findAccount(principal.getName());
		//System.out.println(" account "+avo.getAccountId()+" name "+avo.getUsername());
		return avo;
	}
	
	@RequestMapping(value = "/me2", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody AccountVO myAccount2(String name){		
		AccountVO avo = accountService.findAccount("user");
		return avo;
	}
	
}
