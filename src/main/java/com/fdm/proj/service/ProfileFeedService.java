package com.fdm.proj.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.TagDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.Post;
import com.fdm.proj.model.User;

@Service
public class ProfileFeedService extends FeedService {
	
	private User user;
	
	@Autowired
	public ProfileFeedService(UserDAO userDAO, PostDAO postDAO, TagDAO tagDAO) {
		super(userDAO, postDAO, tagDAO);
	}

	/**
	 * This service method returns all posts made by the user only.
	 * All posts are sorted chronologically from most recent to least.
	 */
	@Override
	public List<Post> returnFeedPosts() {
		
		List<Post> userPosts = user.getCreatedPosts();
		
		Comparator<Post> sortByTimePassed = (p1, p2) -> Long.compare(p1.getTimePassedInMilli(), p2.getTimePassedInMilli());
		userPosts.sort(sortByTimePassed);
		return userPosts;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public void updateUserDetails(String firstname, String lastname, String location, String occupation, String password, String confirmPassword) {
		
		// TODO simplify this
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
		
		// TODO ensure password meets registration requirements
		if (password != null && confirmPassword != null && password != "") {
			if (password.equals(confirmPassword)) {
				user.setPassword(password);
			}
		}
		
		userDAO.updateUser(user.getUserId(), user);
	}
}
