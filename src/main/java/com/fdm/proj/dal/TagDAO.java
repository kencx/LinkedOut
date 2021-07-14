package com.fdm.proj.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdm.proj.entities.Tag;


public class TagDAO extends ObjectDAO<Tag> {

	public TagDAO(EntityManagerFactory emf) {
		super(emf);
	}

	
	@Override
	public Tag findById(int id) {
		EntityManager em = emf.createEntityManager();
		Tag tag = em.find(Tag.class, id);	
		em.close();
		return tag;
	}

	
	@Override
	public List<Tag> findAll() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT t FROM Tag t", Tag.class);
		List<Tag> tags = query.getResultList();

		em.close();
		return tags;
	}

	@Override
	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Tag tag = em.find(Tag.class, id);
		em.remove(tag);
		et.commit();
		em.close();
	}

}
