package com.fdm.proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.User;

@Service
public class RegisterService {
	
	private UserDAO userDAO;
	private String errorMessage = "Create account failed. Please try again!";
	
	@Autowired
	public RegisterService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public boolean registerUser(String username, String password, String confirmPassword, String firstname, String lastname) {
		
		String usernameRegex = "^[A-Za-z0-9]{5,}$";
		String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.]).{8,}$";
		boolean successful = false;
		
		if (username == null || password == null || confirmPassword == null) {
			errorMessage = "Required fields are empty!";
			return successful;
			
		} else if (!password.equals(confirmPassword)) {
			errorMessage = "Passwords do not match!";
			return successful;
			
		} else if (duplicateUsernameExists(username)) {
			errorMessage = "Username already exists!";
			return successful;
			
		} else if (!username.matches(usernameRegex)) {
			errorMessage = "Username must be more than 5 characters!";
			return successful;
			
		} else if (!password.matches(passwordRegex)) {
			errorMessage = "Password must be at least 8 characters with at least 1 capital letter, number and !@#$%^&*";
			return successful;
			
		} else {
			errorMessage = null;
			successful = true;
		}
		
		if (successful) {
			User user = new User(username, password, firstname, lastname);
			userDAO.add(user);
			return successful;
			
		} else {
			return successful;
		}
	}
	
	public boolean duplicateUsernameExists(String username) {
		User user = userDAO.findByUsername(username);

		if (user != null && user.getUsername().equals(username)) {
			return true;
		} else return false;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
