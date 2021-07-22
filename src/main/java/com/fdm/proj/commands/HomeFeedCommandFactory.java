package com.fdm.proj.commands;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeFeedCommandFactory implements CommandFactory {

	@Override
	public HomeFeedCommand createCommand(ServletContext sc, HttpServletRequest req, HttpServletResponse resp) {
		return new HomeFeedCommand(sc, req, resp);
	}
	
}
