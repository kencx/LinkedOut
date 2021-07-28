package com.fdm.proj.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This controller handles all requests to the logout page.
 * @author Kenneth
 *
 */
@Controller
public class LogoutController {
	
	private static final Logger INFO = LogManager.getLogger(LogoutController.class);

	/**
	 * Handles GET requests to logout. All session information is cleared and the user is redirected to the login page.
	 * @param session HttpSession Current session
	 * @param resp HttpServletResponse
	 * @return
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutUser(HttpSession session, HttpServletResponse resp) {

		int userId = (int) session.getAttribute("currentUserId");
		session.invalidate();

		resp.setHeader("Cache-Control", "no-store");
		INFO.info("Session info cleared successfully! User " + userId + " logged out successfully.");
		return "redirect:/login";
	}

}
