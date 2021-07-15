package com.fdm.proj.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdm.proj.entities.Comment;


public class CommentDAO extends ObjectDAO<Comment> {

	public CommentDAO(EntityManagerFactory emf) {
		super(emf);
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


	@Override
	protected Class<Comment> getEntityClass() {
		return Comment.class;
	}

}
