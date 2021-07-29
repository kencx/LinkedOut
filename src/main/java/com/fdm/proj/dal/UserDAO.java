package com.fdm.proj.dal;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdm.proj.model.User;

@Repository
public class UserDAO extends ObjectDAO<User> {

	@Autowired
	public UserDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
	
	public void updateUserUsername(int id, String newUsername) {
		em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		User user = em.find(User.class, id);
		user.setUsername(newUsername);
		et.commit();
		em.close();
	}
	
	public void updateUser(int id, User newUser) {
		em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(newUser);
		et.commit();
		em.close();
	}

	public User findByUsername(String username) {

		em = emf.createEntityManager();
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username LIKE :username", User.class);
		query.setParameter("username", username);
		List<User> users = query.getResultList();
		
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	
	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
}
