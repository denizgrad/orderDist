package com.du.order.dist.service;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

//idle
public class DuJsonMapper extends ObjectMapper{
	
    public DuJsonMapper() {
        super();
        configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
