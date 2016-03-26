package com.du.order.dist.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.du.order.dist.model.LoginForm;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
 
		log.info("Interceptor: Pre-handle");
 
		// Avoid a redirect loop for some urls
		if( !request.getRequestURI().equals("/") &&
		    !request.getRequestURI().equals("/login") &&
		    !request.getRequestURI().equals("/login.failed"))
		  {
			  LoginForm userData = (LoginForm) request.getSession().getAttribute("LOGGEDIN_USER");
		   if(userData == null)
		   {
		    response.sendRedirect("/");
		    return false;
		   }   
		  }
		  return true;
	}
}
