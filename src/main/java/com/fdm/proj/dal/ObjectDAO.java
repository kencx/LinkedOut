package com.fdm.proj.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import javassist.bytecode.analysis.Analyzer;

/**
 * Abstract Database Access Object that can be extended by a child DAO.
 * Requires an {@code EntityManagerFactory} to be initialized.
 * Performs CRUD operations on objects corresponding to the child DAO.
 * @author Kenneth
 *
 * @param <T> Corresponding object of the child DAO
 */
public abstract class ObjectDAO<T> {
	
	protected EntityManagerFactory emf;
	
	/**
	 * Constructor for the DAO object.
	 * @param emf EntityManagerFactory to be injected
	 */
	public ObjectDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	/**
	 * Persists and commits an entity to the database.
	 * @param t Generic entity to be added
	 */
	public void add(T t) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(t);
		et.commit();
		em.close();
	};
	
	/**
	 * Searches the database for the entity with given id.
	 * @param id Id of entity 
	 * @return	 Entity with given id
	 */
	public abstract T findById(int id);
	
	
	/**
	 * Finds all initialized entities in the database.
	 * @return All entities in database
	 */
	public abstract List<T> findAll();
	
	/**
	 * Searches the database for the entity with given id and deletes it.
	 * @param id Id of entity
	 */
	public abstract void delete(int id);
	
}
