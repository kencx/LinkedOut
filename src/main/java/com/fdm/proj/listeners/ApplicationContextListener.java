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
import com.fdm.proj.services.PostService;
import com.fdm.proj.services.RegisterService;


@WebListener
public class ApplicationContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SocialMediaProject");
		sce.getServletContext().setAttribute("emf", emf);
		
		// Inject DAO Layer
		UserDAO userDAO = new UserDAO(emf);		
		PostDAO postDAO = new PostDAO(emf);
		CommentDAO commentDAO = new CommentDAO(emf);
		sce.getServletContext().setAttribute("userDAO", userDAO);
		sce.getServletContext().setAttribute("postDAO", postDAO);
		sce.getServletContext().setAttribute("commentDAO", commentDAO);
		
		
		// Inject Service Layer
		LoginService loginService = new LoginService(userDAO);
		sce.getServletContext().setAttribute("loginService", loginService);
		
		RegisterService registerService = new RegisterService(userDAO);
		sce.getServletContext().setAttribute("registerService", registerService);
		
		PostService ps = new PostService(userDAO, postDAO);
		sce.getServletContext().setAttribute("ps", ps);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}

}
