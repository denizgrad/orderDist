package com.du.order.dist.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ServiceProvider implements ApplicationContextAware {
	
    private static ApplicationContext context;

    public static ApplicationContext getContext() {

        if (context != null) {
            return context;
        }
        else {
            return null;
       //     throw new IllegalStateException("The Spring application context is not yet available.");
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if (context == null) {
        	ServiceProvider.context = applicationContext;
        }

    }

    private static String currentUserName;

	public static String getCurrentUserName() {
		return currentUserName;
	}

	public static void setCurrentUserName(String currentUserName) {
		ServiceProvider.currentUserName = currentUserName;
	}
}
