package com.du.order.dist.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.du.order.dist.model.util.LoginForm;
import com.du.order.dist.service.ServiceProvider;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
 
		logger.debug("Interceptor: Pre-handle");
 
		// Avoid a redirect loop for some urls
		if( !request.getRequestURI().equals("/") &&
		    !request.getRequestURI().equals("/login") &&
		    !request.getRequestURI().equals("/login.failed"))
		  {
			  LoginForm userData = (LoginForm) request.getSession().getAttribute("LOGGEDIN_USER");
			 
		   if(userData == null)		   {
		    response.sendRedirect("/");
		    return false;
		   } else {
			   ServiceProvider.setCurrentUserName(userData.getUsername());
		   }
	  }
	  return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("Post-handle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("After-completion");
	}
}
