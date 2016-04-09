package com.du.order.dist.init;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.HttpConstraintElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletSecurityElement;
import javax.servlet.annotation.ServletSecurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import com.du.order.dist.config.AppConfig;
import com.du.order.dist.config.WebMvcConfig;

public class Initializer implements WebApplicationInitializer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("onStartup");
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        // Manage the lifecycle of the root appcontext
        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.setInitParameter("defaultHtmlEscape", "true");
        servletContext.setInitParameter("encoding", "UTF-8");
        
        servletContext.setInitParameter("log4j-config-location", "/WEB-INF/log4j.properties");

        servletContext.addListener(Log4jConfigListener.class);
        
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter",new CharacterEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration encodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8"));
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");
        encodingFilter.setInitParameter("encoding", "UTF-8");

        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();

        mvcContext.register(WebMvcConfig.class);

        // The main Spring MVC servlet.
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", new DispatcherServlet(mvcContext));

        HttpConstraintElement httpConstraintElement = new HttpConstraintElement(ServletSecurity.TransportGuarantee.NONE);
        ServletSecurityElement servletSecurityElement = new ServletSecurityElement(httpConstraintElement);
        appServlet.setServletSecurity(servletSecurityElement);

        appServlet.setLoadOnStartup(1);
        Set<String> mappingConflicts = appServlet.addMapping("/") ;

        if (!mappingConflicts.isEmpty()) {
            for (String s : mappingConflicts) {
                logger.info("Mapping conflict: " + s);
            }
            throw new IllegalStateException(
                    "'appServlet' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
        }
    }

}
