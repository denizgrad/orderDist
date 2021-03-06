package com.du.order.dist.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    //TODO timestampi normal saat yap
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        
        StringBuffer jb = new StringBuffer();
        String line = null;
//        try {
//          BufferedReader reader = request.getReader();
//          while ((line = reader.readLine()) != null)
//            jb.append(line);
//        } catch (Exception e) { /*report an error*/ }
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: Start Time=" + System.currentTimeMillis()
                + " ::Request.Ip::" + request.getRemoteAddr()
        );
//        logger.info("JSON REQUEST: " + jb.toString() );
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    	if(request.getRequestURI().indexOf("getOrderList") > -1){
    		logger.info("Request Url: getOrderList");
    	} else {
        logger.info("Request URL::" + request.getRequestURL().toString()
                + " Sent to Handler :: Current Time=" + System.currentTimeMillis()
                + " ::Request.Ip::" + request.getRemoteAddr());
    	}
        //we can add attributes in the modelAndView and use that in the view page
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: End Time=" + System.currentTimeMillis());
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: Time Taken=" + (System.currentTimeMillis() - startTime)
                + " ::Request.Ip::" + request.getRemoteAddr());
    }

}
