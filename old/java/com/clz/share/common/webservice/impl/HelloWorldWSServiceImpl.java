package com.clz.share.common.webservice.impl;

import javax.jws.WebService;

import com.clz.share.common.webservice.HelloWorldWSService;

@WebService(endpointInterface = "com.clz.share.common.webservice.HelloWorldWSService")
public class HelloWorldWSServiceImpl implements HelloWorldWSService {

	public void sayHello() {
		
		System.out.println("Hello world.++++++++++++++++++++");  
	}
	
	public String returnHello() {
		
		System.out.println("Hello world.");  
		return "Hello world.";
	}

}
