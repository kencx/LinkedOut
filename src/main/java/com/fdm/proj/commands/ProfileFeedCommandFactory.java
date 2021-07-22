package com.fdm.proj.commands;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileFeedCommandFactory implements CommandFactory {

	@Override
	public ProfileFeedCommand createCommand(ServletContext sc, HttpServletRequest req, HttpServletResponse resp) {
		return new ProfileFeedCommand(sc, req, resp);
	}

}
