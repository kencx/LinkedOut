package com.fdm.proj.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.proj.controller.LoginController;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.User;

@Service
public class OptionsService {
	
	private static final Logger INFO = LogManager.getLogger(LoginController.class);
	
	private User user;
	private UserDAO userDAO;
	
	@Autowired
	public OptionsService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User returnUser(int userId) {
		User user = userDAO.findById(userId);
		return user;
	}

	public String update(String firstname, String lastname, String location, String occupation, String bio) {
		
		String message = "Update failed";
		
		if (firstname != null && firstname != "") {
			user.setFirstname(firstname);			
		}
		
		if (lastname != null && lastname != "") {
			user.setLastname(lastname);
		}

		if (location != null && location != "") {
			user.setLocation(location);
		}
		
		if (occupation != null && occupation != "") {
			user.setOccupation(occupation);
		}
		
		if (bio != null && bio != "") {
			user.setBio(bio);
		}
		
		userDAO.updateUser(user.getUserId(), user);
		INFO.info("User " + user.getUsername() + " particulars updated!");
		message = "Particulars updated!";
		
		return message;
	}

	
	public String updatePassword(String currentPassword, String newPassword, String confirmPassword) {
		
		String message = null;
		String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.]).{8,}$";
		
		if (currentPassword != user.getPassword()) {
			message = "Current password is wrong!";
			return message;
		}
		
		if (currentPassword != null && newPassword != null && confirmPassword != null 
				&& currentPassword != "" && newPassword != "" && confirmPassword != "" 
				&& newPassword.matches(passwordRegex)) {
			
			if (newPassword.equals(confirmPassword)) {
				
				user.setPassword(newPassword);
				userDAO.updateUser(user.getUserId(), user);
				INFO.info("User " + user.getUsername() + " password updated!");
				
				message = "Password updated!";
				
			} else message = "Passwords do not match!";

		} else message = "Password must be at least 8 characters with at least 1 capital letter, number and !@#$%^&*";
	
		return message;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
