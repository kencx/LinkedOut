package com.fdm.proj.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LogoutController {
	
	private static final Logger INFO = LogManager.getLogger(LogoutController.class);


	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutUser(HttpSession session, HttpServletResponse resp) {

		int userId = (int) session.getAttribute("currentUserId");
		session.invalidate();

		resp.setHeader("Cache-Control", "no-store");
		INFO.info("Session info cleared successfully! User " + userId + " logged out successfully.");
		return "redirect:/login";
	}

}
