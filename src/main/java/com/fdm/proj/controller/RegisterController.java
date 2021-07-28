package com.fdm.proj.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.proj.service.RegisterService;

/**
 * This controller manages all incoming requests to the Register page where users can create an account for the site.
 * @author Kenneth
 *
 */

@Controller
public class RegisterController {

	private static final Logger INFO = LogManager.getLogger(RegisterController.class);
	private static final Logger ERROR = LogManager.getLogger(RegisterController.class);
	
	private RegisterService registerService;
	
	@Autowired
	public RegisterController(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	/**
	 * Handles the GET request to the Register page
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String goToRegistrationPage() {
		return "register";
	}
	
	
	/**
	 * Handles the POST request to the Register page. This method is called when the user registration form is submitted in register.jsp
	 * @param req HttpServletRequest
	 * @param username Username of registered user
	 * @param password Password of registered user
	 * @param confirmPassword Confirm password
	 * @param firstname First name of registered user
	 * @param lastname Last name of registered user
	 * @return String "redirect:/login" if successful, "register" if registration failed
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registeringUser(HttpServletRequest req, @RequestParam String username, 
			@RequestParam String password, @RequestParam String confirmPassword,
			@RequestParam String firstname, @RequestParam String lastname) {
		
		boolean status = registerService.registerUser(username, password, confirmPassword, firstname, lastname);
		
		if (status) {
			INFO.info(username + " accounted created.");
			return "redirect:/login";
			
		} else {
			
			String errorMessage = registerService.getErrorMessage();
			ERROR.error("Account creation failed! Error: " + errorMessage);			
			req.setAttribute("registerFailedMessage", errorMessage);
			return "register";
		}
	}
	
}
