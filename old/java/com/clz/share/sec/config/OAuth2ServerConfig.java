package com.clz.share.sec.config;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
public class OAuth2ServerConfig {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@Configuration
	@EnableResourceServer
	protected static class ResourceServer extends
			ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http.requestMatchers().antMatchers("/resources/**","/me","/me2","/account","/oauth/token/revoke/**","/logout",
					"/event/**","/signup").and()
					.authorizeRequests()//.anyRequest().access("#oauth2.hasScope('openid')")
					.antMatchers("/signup").anonymous()//access("#oauth2.hasScope('trust')")
					.antMatchers("/event/**").access("#oauth2.hasScope('openid')")
					.antMatchers("/me").access("#oauth2.hasScope('openid')")
					.antMatchers("/me2").anonymous()
					.antMatchers("/account").access("#oauth2.hasScope('openid')")
					.antMatchers("/logout").access("#oauth2.hasScope('openid')")
					.antMatchers("/oauth/token/revoke/**").access("#oauth2.hasScope('openid')");
			// @formatter:on
		}

	}

	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2Config extends
			AuthorizationServerConfigurerAdapter {

		@Autowired
		private TokenStore tokenStore;
		
		@Autowired
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer)
				throws Exception {
			oauthServer.allowFormAuthenticationForClients();
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients)
				throws Exception {
			clients.inMemory()
					.withClient("acme")
					.secret("acmesecret")
					.authorizedGrantTypes("authorization_code",
							"refresh_token", "password")
					.scopes("openid")
					;
			/*		.and()
		            .withClient("acme-signup")
	                .authorizedGrantTypes("implicit")
	                .authorities("ROLE_CLIENT","USER")
	                .scopes("read", "write", "trust","openid")
	                .autoApprove(true);
			*/
		}
		
		
		
		@Bean
		public TokenStore tokenStore() {
			return new InMemoryTokenStore();
		}
		
		@Bean
		public DefaultTokenServices defaultTokenServices() {
			DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
			defaultTokenServices.setTokenStore(tokenStore());
		    return defaultTokenServices;
		}
		
		@RequestMapping(value = "/oauth/token/revoke", method = RequestMethod.POST)
		public @ResponseBody void create(@RequestParam("token") String value) throws Exception {
			
			System.out.println("***************** Token :"+value);
			
			defaultTokenServices().revokeToken(value);	
		}
		
	}
}