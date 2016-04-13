package com.du.order.dist.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.du.order.dist.model.util.LoginForm;
import com.du.order.dist.service.IInfoProvider;
import com.du.order.dist.service.ServiceProvider;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
@Autowired
IInfoProvider info;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		logger.debug("Interceptor: Pre-handle");
 
		// Avoid a redirect loop for some urls
		if( !request.getRequestURI().equals("/order.dist/") &&
		    !request.getRequestURI().equals("/order.dist/login") &&
		    !request.getRequestURI().equals("/order.dist/login.failed") &&
			!request.getRequestURI().equals("/order.dist/logout") &&
			!request.getRequestURI().equals("/order.dist/v1/siparis/islem/createSiparis") &&
			!request.getRequestURI().equals("/order.dist/v1/siparis/islem/updateSiparis") 
			)
		  {
			  LoginForm userData = (LoginForm) request.getSession().getAttribute("LOGGEDIN_USER");
			 
		   if(userData == null)		   {
		    response.sendRedirect("/order.dist/");
		    return false;
		   } else {
			   ServiceProvider.setCurrentUserName(userData.getUserId());
			   info.setAccId(userData.getUserId());
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
