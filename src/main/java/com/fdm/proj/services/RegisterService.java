package com.fdm.proj.services;

import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.User;


public class RegisterService {
	
	private UserDAO userDAO;
	private String errorMessage = "Create account failed. Please try again!";
	
	public RegisterService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public boolean registerUser(String username, String password, String confirmPassword) {
		
//		String usernameRegex = "^[A-Za-z]\\w{5,29}$";
//		String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,20}$";
		boolean status = false;
		
		if (username == null || password == null || confirmPassword == null) {
			errorMessage = "Required fields are empty!";
			return status;
			
		} else if (!password.equals(confirmPassword)) {
			errorMessage = "Passwords do not match!";
			return status;
			
		} else if (duplicateUsernameExists(username)) {
			errorMessage = "Username already exists!";
			return status;
			
//		} else if (!username.matches(usernameRegex)) {
//			errorMessage = "Username must have 5 to 30 characters with letters and numbers!";
//			return status;
//			
//		} else if (!password.matches(passwordRegex)) {
//			errorMessage = "Password must be between 8-20 characters with letters, numbers and symbols";
//			return status;
			
		} else {
			errorMessage = null;
			status = true;
		}
		
		if (status) {
			User newUser = new User(username, password);
			userDAO.add(newUser);
			return status;
			
		} else {
			return status;
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
