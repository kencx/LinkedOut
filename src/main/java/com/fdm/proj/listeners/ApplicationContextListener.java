package com.fdm.proj.listeners;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fdm.proj.commands.CommandFactory;
import com.fdm.proj.commands.HomeFeedCommandFactory;
import com.fdm.proj.commands.LoginCommandFactory;
import com.fdm.proj.commands.LogoutCommandFactory;
import com.fdm.proj.commands.ProfileFeedCommandFactory;
import com.fdm.proj.commands.RegisterCommandFactory;
import com.fdm.proj.dal.CommentDAO;
import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.TagDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.services.HomeFeedService;
import com.fdm.proj.services.LoginService;
import com.fdm.proj.services.ProfileFeedService;
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
		TagDAO tagDAO = new TagDAO(emf);
		sce.getServletContext().setAttribute("userDAO", userDAO);
		sce.getServletContext().setAttribute("postDAO", postDAO);
		sce.getServletContext().setAttribute("tagDAO", tagDAO);

		
		// Inject Service Layer
		// TODO simplify factory init process
		CommandFactory lcf = new LoginCommandFactory();
		CommandFactory lgcf = new LogoutCommandFactory();
		CommandFactory rcf = new RegisterCommandFactory();
		CommandFactory fcf = new HomeFeedCommandFactory();
		CommandFactory pcf = new ProfileFeedCommandFactory();
		sce.getServletContext().setAttribute("lcf", lcf);		
		sce.getServletContext().setAttribute("lgcf", lgcf);		
		sce.getServletContext().setAttribute("rcf", rcf);		
		sce.getServletContext().setAttribute("fcf", fcf);		
		sce.getServletContext().setAttribute("pcf", pcf);
		
		// TODO simplify service init process
		LoginService ls = new LoginService(userDAO);
		sce.getServletContext().setAttribute("loginService", ls);		

		RegisterService rs = new RegisterService(userDAO);
		sce.getServletContext().setAttribute("registerService", rs);
		
		HomeFeedService hfService = new HomeFeedService(userDAO, postDAO);
		sce.getServletContext().setAttribute("hfService", hfService);
		
		ProfileFeedService pfService = new ProfileFeedService(userDAO, postDAO);
		sce.getServletContext().setAttribute("pfService", pfService);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
