package com.fdm.proj.listeners;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fdm.proj.commands.CommandFactory;
import com.fdm.proj.commands.FeedCommandFactory;
import com.fdm.proj.commands.LoginCommandFactory;
import com.fdm.proj.commands.LogoutCommandFactory;
import com.fdm.proj.commands.RegisterCommandFactory;
import com.fdm.proj.dal.CommentDAO;
import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.TagDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.services.FeedService;
import com.fdm.proj.services.LoginService;
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
		TagDAO tagDAO = new TagDAO(emf);
		sce.getServletContext().setAttribute("userDAO", userDAO);
		sce.getServletContext().setAttribute("postDAO", postDAO);
		sce.getServletContext().setAttribute("commentDAO", commentDAO);
		sce.getServletContext().setAttribute("tagDAO", tagDAO);

		
		// Inject Service Layer
		// TODO simplify this factory initialization process
		CommandFactory lcf = new LoginCommandFactory();
		CommandFactory lgcf = new LogoutCommandFactory();
		CommandFactory rcf = new RegisterCommandFactory();
		CommandFactory fcf = new FeedCommandFactory();
		sce.getServletContext().setAttribute("lcf", lcf);		
		sce.getServletContext().setAttribute("lgcf", lgcf);		
		sce.getServletContext().setAttribute("rcf", rcf);		
		sce.getServletContext().setAttribute("fcf", fcf);		
		
		LoginService ls = new LoginService(userDAO);
		sce.getServletContext().setAttribute("loginService", ls);		

		RegisterService rs = new RegisterService(userDAO);
		sce.getServletContext().setAttribute("registerService", rs);
		
		FeedService feedService = new FeedService(userDAO, postDAO, commentDAO);
		sce.getServletContext().setAttribute("feedService", feedService);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
