package com.fdm.proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.User;

@Service
public class LoginService {

	private UserDAO userDAO;
	
	@Autowired
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
