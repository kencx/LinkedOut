package com.fdm.proj.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class WebInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext sc) throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(Config.class);
		context.setServletContext(sc);
		sc.addListener(new ContextLoaderListener(context));
		ServletRegistration.Dynamic appServlet = sc.addServlet("dispatcher", new DispatcherServlet(context));
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");
	}

}
