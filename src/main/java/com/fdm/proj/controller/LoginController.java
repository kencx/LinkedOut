package com.fdm.proj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.proj.model.User;
import com.fdm.proj.service.LoginService;

/**
 * This controller handles all incoming requests to the Login page where users can login into the site with an existing account.
 * @author Kenneth
 *
 */
@Controller
public class LoginController {
	
	private static final Logger INFO = LogManager.getLogger(LoginController.class);
	private static final Logger ERROR = LogManager.getLogger(LoginController.class);
	
	private LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String goToLoginPage() {
		return "login";
	}
	
	/**
	 * Handles the POST request to log in. This method is called when the login form is submitted in login.jsp
	 * @param req HttpServletRequest
	 * @param username Username of user
	 * @param password Password of user
	 * @return String "redirect:/homefeed" if successful, "login" if login failed
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginUser(HttpServletRequest req, 
			@RequestParam String username, 
			@RequestParam String password) {
		
		User user = loginService.verifyUser(username, password);
		
		if (user != null) {
			INFO.info(user.getUsername() + " has logged in.");
			HttpSession session = req.getSession();
			session.setAttribute("currentUserId", user.getUserId());
			return "redirect:/homefeed";
			
		} else {
			ERROR.error("login failed");
			String errorMessage = "Login failed. Please try again.";
			req.setAttribute("loginFailedMessage", errorMessage);
			return "login";
		}
	}
}
