package com.fdm.proj.listeners;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fdm.proj.dal.CommentDAO;
import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.services.LoginService;
import com.fdm.proj.services.RegisterService;


@WebListener
public class ApplicationContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SocialMediaProject");
		
		UserDAO userDAO = new UserDAO(emf);
		
		// change to sessioncontext
//		PostDAO postDAO = new PostDAO(emf);
//		CommentDAO commentDAO = new CommentDAO(emf);
		
		LoginService loginService = new LoginService(userDAO);
		sce.getServletContext().setAttribute("loginService", loginService);
		
		RegisterService registerService = new RegisterService(userDAO);
		sce.getServletContext().setAttribute("registerService", registerService);
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}

}
