package com.fdm.proj.services;

import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.User;


public class LoginService {
	
private UserDAO userDAO;
	
	public LoginService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	public User verifyUser(String username, String password) {
		User user = userDAO.findByUsername(username);
		
		if (user != null && user.getPassword().equals(password)) {
			return user;
			
		} else {
			return null;
		}
	}
}
