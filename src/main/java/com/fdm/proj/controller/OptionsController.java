package com.fdm.proj.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.proj.model.User;
import com.fdm.proj.service.OptionsService;
import com.fdm.proj.service.UserService;

@Controller
public class OptionsController {

	private static final Logger INFO = LogManager.getLogger(LoginController.class);
	
	private UserService userService;
	private OptionsService optionsService;
	private HttpServletRequest req;
	
	@Autowired
	public OptionsController(OptionsService optionsService, UserService userService, HttpServletRequest req) {
		this.userService = userService;
		this.optionsService = optionsService;
		this.req = req;
	}
	
	
	@RequestMapping(value="/options", method=RequestMethod.GET)
	public String goToOptionsPage() {
		return "options";
	}
	
//	@RequestMapping(value="/options", method=RequestMethod.GET)
//	public String deleteAccount() {
//		
//		userService.deleteUser(optionsService.getUser().getUserId());
//		INFO.info("User deleted. Logging out.");
//		return "logout";
//	}
	
	@RequestMapping(value="/editparticulars", method=RequestMethod.GET)
	public String goToUpdateParticularsPage() {
		return "editparticulars";
	}
	
	@RequestMapping(value="/changepassword", method=RequestMethod.GET)
	public String goToChangePasswordPage() {
		return "changepassword";
	}
	
	/**
	 * This method handles requests to update the user's personal particulars.
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/editparticulars", method=RequestMethod.POST)
	public String updateParticulars(
			@RequestParam(required=false) String firstname, 
			@RequestParam(required=false) String lastname, 
			@RequestParam(required=false) String location,
			@RequestParam(required=false) String occupation, 
			@RequestParam(required=false) String bio ) {
		
		initializeUser();
		String message = optionsService.update(firstname, lastname, location, occupation, bio);
		req.setAttribute("message", message);
		return "editparticulars";
	}
	
	@RequestMapping(value="/changepassword", method=RequestMethod.POST)
	public String changePassword(
			@RequestParam String currentPassword, 
			@RequestParam String newPassword, 
			@RequestParam String confirmPassword) {
		
		initializeUser();
		String message = optionsService.updatePassword(currentPassword, newPassword, confirmPassword);
		req.setAttribute("message", message);
		
		return "changepassword";
	}
	
	
	public void initializeUser() {
		Integer userId = (Integer) req.getSession().getAttribute("currentUserId");
		User user = optionsService.returnUser(userId);
		optionsService.setUser(user);
	}
}