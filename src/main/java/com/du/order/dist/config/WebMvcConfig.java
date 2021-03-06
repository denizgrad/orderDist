package com.du.order.dist.config;

import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.du.order.dist.interceptors.AuthenticationInterceptor;
import com.du.order.dist.interceptors.LoggerInterceptor;
import com.du.order.dist.interfaces.IMessageSource;
import com.du.order.dist.service.ResourceMessage;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.du.order.dist.service" , "com.du.order.dist.controller" , "com.du.order.dist.client"})
@EnableTransactionManagement
@EnableJpaRepositories("com.du.order.dist.repository")
@PropertySource("classpath:META-INF/${env:dev}/app.properties")
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	@Autowired
	Environment env;
	
	
	@Bean
	public Filter loggingFilter(){
	    AbstractRequestLoggingFilter f = new AbstractRequestLoggingFilter() {

	        @Override
	        protected void beforeRequest(HttpServletRequest request, String message) {
	            logger.info(message);
	        }

	        @Override
	        protected void afterRequest(HttpServletRequest request, String message) {
	            logger.info(message);
	        }
	    };
	    f.setIncludeClientInfo(true);
	    f.setIncludePayload(true);
	    f.setIncludeQueryString(true);

	    f.setBeforeMessagePrefix("BEFORE REQUEST  [");
	    f.setAfterMessagePrefix("AFTER REQUEST    [");
	    f.setAfterMessageSuffix("]\n");
	    return f;
	}
	
    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        String env = "dev";
        if(StringUtils.isNotBlank(System.getProperty("env"))){
        	env = System.getProperty("env");
        }
        messageSource.setBasename( env + "/app_messages.properties");//TODO deniz
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
    
    @Bean
    public IMessageSource resourceMessage(){
    	return new ResourceMessage();
    }
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));

        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan("com.du.order.dist.model");
        entityManagerFactoryBean.setJpaProperties(hibProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/jquery-ui-1.9.2/**").addResourceLocations("/jquery-ui-1.9.2/");
    }

//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        Properties mailProperties = new Properties();
//        mailProperties.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
//        mailProperties.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls"));
//        mailProperties.put("mail.smtp.starttls.required", env.getProperty("mail.smtp.starttls"));
//        //mailProperties.put("mail.smtps.ssl.checkserveridentity", "true");
//        //mailProperties.put("mail.smtps.ssl.trust", "*");
//        //mailProperties.setProperty("mail.debug", "true");
//        mailSender.setJavaMailProperties(mailProperties);
//        mailSender.setHost(env.getProperty("mail.host"));
//        mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
//        mailSender.setProtocol(env.getProperty("mail.protocol"));
//        mailSender.setUsername(env.getProperty("mail.username"));
//        mailSender.setPassword(env.getProperty("mail.password"));
//        return mailSender;
//    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect"  , env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql" , env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto" , env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.connection.CharSet" , env.getRequiredProperty("hibernate.connection.CharSet"));
        properties.put("hibernate.connection.characterEncoding" , env.getRequiredProperty("hibernate.connection.characterEncoding"));
        properties.put("hibernate.connection.useUnicode" , env.getRequiredProperty("hibernate.connection.useUnicode"));
        //c3p0
        properties.put("hibernate.c3p0.acquire_increment" , env.getRequiredProperty("hibernate.c3p0.acquire_increment"));
        properties.put("hibernate.c3p0.idle_test_period" , env.getRequiredProperty("hibernate.c3p0.idle_test_period"));
        properties.put("hibernate.c3p0.max_size" , env.getRequiredProperty("hibernate.c3p0.max_size"));
        properties.put("hibernate.c3p0.max_statements" , env.getRequiredProperty("hibernate.c3p0.max_statements"));
        properties.put("hibernate.c3p0.min_size" , env.getRequiredProperty("hibernate.c3p0.min_size"));
        properties.put("hibernate.c3p0.timeout" , env.getRequiredProperty("hibernate.c3p0.timeout"));
        properties.put("hibernate.c3p0.validate" , env.getRequiredProperty("hibernate.c3p0.validate"));
        properties.put("hibernate.c3p0.preferredTestQuery" , env.getRequiredProperty("hibernate.c3p0.preferredTestQuery"));
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");
    }
}
