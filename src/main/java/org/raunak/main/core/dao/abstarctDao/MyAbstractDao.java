package org.raunak.main.core.dao.abstarctDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

@Transactional

public class MyAbstractDao <T> {

	private Class<T> classType;

	@PersistenceContext
	private EntityManager entityManager;
	
	public MyAbstractDao(Class<T> classType){
		this.classType = classType;
	}
	
	protected Session getSession() {
		Session session = null;
		 session =  entityManager.unwrap(Session.class);
		session.setFlushMode(FlushMode.ALWAYS);
	    return session;
	}
	
	protected Class<T> getClassType() {
		return classType;
	}
	
}
