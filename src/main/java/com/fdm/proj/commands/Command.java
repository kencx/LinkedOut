package com.fdm.proj.commands;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class Command {
	
	protected ServletContext sc;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	
	static final Logger ERROR = LogManager.getLogger("com.fdm.proj.actions.Error");
	static final Logger INFO = LogManager.getLogger("com.fdm.proj.actions.Info");
	
	public Command (ServletContext sc, HttpServletRequest req, HttpServletResponse resp) {
		this.sc = sc;
		this.req = req;
		this.resp = resp;
	}
	
	public abstract String execute() throws ServletException, IOException;
}
