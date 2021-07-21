package com.fdm.proj.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class SessionContextListener implements HttpSessionListener {

	private static int activeSessions;
	// TODO figure out stuff to add here
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		activeSessions++;
		System.out.println("Session created. Total active sessions: " + activeSessions);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		activeSessions--;
		System.out.println("Session destroyed. Total active sessions: " + activeSessions);
	}

	


}
