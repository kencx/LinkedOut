package com.fdm.proj.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


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
	protected EntityManager em;

	
	/**
	 * Constructor for the DAO object.
	 * @param emf EntityManagerFactory to be injected
	 */
	public ObjectDAO(EntityManagerFactory emf) {
		this.emf = emf;
		
	}
	
//	public ObjectDAO(EntityManager em) { // for testing
//		this.em = em;
//	}
	
	
	/**
	 * Persists and commits an entity to the database.
	 * @param t Entity to be added
	 */
	public void add(T t) {
		em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.merge(t);
		et.commit();
		em.close();
	};
	
	/**
	 * Searches the database for the entity with given id.
	 * @param id Id of entity 
	 * @return	 Entity with given id
	 */
	public T findById(int id) {
		em = emf.createEntityManager();
		T t = em.find(getEntityClass(), id);
		em.close();
		return t;
	}
	
	
	/**
	 * Finds all initialized entities in the database.
	 * @return All entities in database
	 */
	public List<T> findAll() {
		em = emf.createEntityManager();
		Class<T> entityClass = getEntityClass();
		TypedQuery<T> query = em.createQuery("SELECT e FROM " + entityClass.getName() + " e", getEntityClass());
		List<T> listOfT = query.getResultList();

		em.close();
		return listOfT;
	}
	
	/**
	 * Searches the database for the entity with given id and deletes it.
	 * @param id Id of entity
	 */
	public void delete(int id) {
		em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		T t = em.find(getEntityClass(), id);
		em.remove(t);
		et.commit();
		em.close();
	}
	
	/**
	 * Gets the actual class of given type argument T.
	 * @return Class of type argument T
	 */
	protected abstract Class<T> getEntityClass();
}
