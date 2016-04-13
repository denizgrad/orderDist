package com.du.order.dist.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ASLI BOREK ORDER DISTRIBUTION ContextListener.contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ASLI BOREK ORDER DISTRIBUTION ContextListener.contextDestroyed");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("ASLI BOREK ORDER DISTRIBUTION ContextListener.sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("ASLI BOREK ORDER DISTRIBUTION ContextListener.sessionDestroyed");
    }
   }
