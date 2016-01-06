/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.clz.share.sec.util;

import java.security.Principal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;

public class SignInUtils {
	
	/**
	 * Programmatically signs in the user with the given the user ID.
	 */
	public static void signin(String userId) {
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));
	}


	private static Principal signinPrivate(String userId) {
		Authentication authentication = new UsernamePasswordAuthenticationToken("user", "pass",
	            AuthorityUtils.createAuthorityList("ROLE_USER"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return (Principal) authentication.getPrincipal();
	}

	

	
	public static ResponseEntity<?>  signinOauth2(String userId) {
		
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("client_id", "appid");
        parameters.put("client_secret", "myOAuthSecret");
        parameters.put("grant_type", "password");
        parameters.put("username", "user");
        parameters.put("password", "pass");
        parameters.put("scope", "read write");

		Principal principal = signinPrivate(userId);
		System.out.println("         pricipal  "+principal.getName());
		
		
		ResponseEntity<?> res = null;
		try {
			return getToken(principal);
		} catch (HttpRequestMethodNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	
	
	public static ResponseEntity<?>  signinOauth2A(String userId) {
		
		Principal principal = signinPrivate(userId);
		System.out.println("         pricipal  "+principal.getName());
		
		
		ResponseEntity<?> res = null;
		try {
			return getToken(principal);
		} catch (HttpRequestMethodNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	
	//@Inject
	@Autowired
	private static TokenEndpoint tokenEndpoint;

	public static ResponseEntity<?> getToken(Principal principal) throws HttpRequestMethodNotSupportedException {

	        HashMap<String, String> parameters = new HashMap<String, String>();
	        parameters.put("client_id", "appid");
	        parameters.put("client_secret", "myOAuthSecret");
	        parameters.put("grant_type", "password");
	        parameters.put("username", "user");
	        parameters.put("password", "pass");
	        parameters.put("scope", "read write");
	        return tokenEndpoint.getAccessToken(principal, parameters);
	}
	
}
