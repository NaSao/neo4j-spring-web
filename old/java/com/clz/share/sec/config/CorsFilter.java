package com.clz.share.sec.config;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	//@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		///
		String method = request.getMethod();
        response.setHeader("Access-Control-Allow-Methods",
                "POST,GET,OPTIONS,DELETE");
            response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader(
                "Access-Control-Allow-Headers",
                "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
            if ("OPTIONS".equals(method)) {
              response.setStatus(HttpStatus.OK.value());
            }
            else {
              chain.doFilter(req, res);
            }
          }
		
/*		
		response.setHeader("Access-Control-Allow-Methods",
				"POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "bearer, x-auth-token, x-requested-with");
		response.setHeader("Access-Control-Max-Age", "3600");
		if (request.getMethod() != "OPTIONS") {
			chain.doFilter(req, res);
		} else {
		}
	}
*/
	//@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	//@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}