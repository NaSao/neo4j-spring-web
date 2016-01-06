package com.clz.share.common.webservice;

import javax.jws.WebService;

@WebService
public interface HelloWorldWSService {
	public void sayHello(); 
	public String returnHello();
}
