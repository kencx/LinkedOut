package com.fdm.proj.commands;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface CommandFactory {
		
	public abstract Command createCommand(ServletContext sc, HttpServletRequest req, HttpServletResponse resp);
	
}
