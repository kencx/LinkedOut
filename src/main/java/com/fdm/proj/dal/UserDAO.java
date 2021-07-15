package com.fdm.proj.dal;


import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdm.proj.entities.User;


public class UserDAO extends ObjectDAO<User> {

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


	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
