package com.du.order.dist.service;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.du.order.dist.interfaces.IMessageSource;

//@Bean
public class ResourceMessage implements IMessageSource{

	@Override
	public String getMessage(String key) {
        try {
            MessageSource bean = ServiceProvider.getContext().getBean(MessageSource.class);
            return bean.getMessage(key, null, Locale.getDefault());
        }
        catch (Exception e) {
            return key;
        }
	}

}
