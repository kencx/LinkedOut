package com.fdm.proj.dal;

import javax.persistence.EntityManagerFactory;

import com.fdm.proj.entities.Tag;


public class TagDAO extends ObjectDAO<Tag> {

	public TagDAO(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	protected Class<Tag> getEntityClass() {
		return Tag.class;
	}


}
