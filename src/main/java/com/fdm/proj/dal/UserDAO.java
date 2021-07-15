package com.fdm.proj.dal;


import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdm.proj.entities.User;


public class UserDAO extends ObjectDAO<User> {

	public UserDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
//	public UserDAO(EntityManager em) {
//		super(em);
//	}

	
	
	public void updateElementUsername(int id, String newUsername) {
		em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		User user = em.find(User.class, id);
		user.setUsername(newUsername);
		et.commit();
		em.close();
	}


	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
