package com.fdm.proj.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdm.proj.entities.Comment;


public class CommentDAO extends ObjectDAO<Comment> {

	public CommentDAO(EntityManagerFactory emf) {
		super(emf);
	}

	
	@Override
	public Comment findById(int id) {
		EntityManager em = emf.createEntityManager();
		Comment comment = em.find(Comment.class, id);	
		em.close();
		return comment;
	}

	
	@Override
	public List<Comment> findAll() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT c FROM Comment c", Comment.class);
		List<Comment> comments = query.getResultList();

		em.close();
		return comments;
	}

	
	@Override
	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Comment comment = em.find(Comment.class, id);
		em.remove(comment);
		et.commit();
		em.close();		
	}
	
	
	public void updateCommentBody(int id, String commentBody) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Comment comment = em.find(Comment.class, id);
		comment.setCommentBody(commentBody);
		et.commit();
		em.close();		
	}

}
