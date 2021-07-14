package com.fdm.proj.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdm.proj.entities.User;


public class UserDAO extends ObjectDAO<User> {

	public UserDAO(EntityManagerFactory emf) {
		super(emf);
	}


	@Override
	public User findById(int id) {
		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, id);	
		em.close();
		return user;
	}


	@Override
	public List<User> findAll() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT u FROM User u", User.class);
		List<User> users = query.getResultList();

		em.close();
		return users;
	}


	@Override
	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		User user = em.find(User.class, id);
		em.remove(user);
		et.commit();
		em.close();
	}


	public void updateElementUsername(int id, String newUsername) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		User user = em.find(User.class, id);
		user.setUsername(newUsername);
		et.commit();
		em.close();
	}

}
