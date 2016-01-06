package com.clz.share.sec.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * The Spring Security Java Configuration.
 * The configuration creates a Servlet Filter known as the springSecurityFilterChain 
 * which is responsible for all the security (protecting the application URLs, 
 * validating submitted username and passwords, redirecting to the log in form, etc)
 * within our application. 
 * 
 * @author Bertin Moyou
 *
 */
@Configuration
@EnableWebSecurity
//
//@ImportResource("classpath:/share-app-context.xml")
//@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/images/**", "/oauth/uncache_approvals", "/oauth/cache_approvals");
    }
    
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

    @Bean
	@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  	  auth.jdbcAuthentication()
  	  	.dataSource(dataSource)
  	  	.passwordEncoder(passwordEncoder())
  		.usersByUsernameQuery(
  			"select email as username,password, 1 as enabled from account where email=?")
  		.authoritiesByUsernameQuery(
  			"select email as username, authority from authorities a inner join account u on u.account_id=a.account_id where u.email=?");
    }


    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
                 http
            .authorizeRequests()
                .antMatchers("/login.jsp").permitAll()
                .anyRequest().authenticated()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/login.jsp?authorization_error=true")
                .and()
            // TODO: put CSRF protection back into this endpoint
            .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
            .logout()
            	.logoutUrl("/logout")
                .logoutSuccessUrl("/login.jsp")
                .and()
            .formLogin()
            	.loginProcessingUrl("/login")
                .failureUrl("/login.jsp?authentication_error=true")
                .loginPage("/login.jsp");
        // @formatter:on
              
    }
    */
}
