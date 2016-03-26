package com.du.order.dist.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.du.order.dist")
@PropertySource("classpath:META-INF/${env:dev}/app.properties")
public class AppConfig  {

}
