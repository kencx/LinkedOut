package com.fdm.proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.User;

@Service
public class UserService {

	private UserDAO userDAO;

	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}
	
}
