/**
 * 
 */
package com.tenline.pinecone.utils;

import java.util.Collection;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

/**
 * @author Bill
 *
 */
public class JdoTemplate {

	private static final PersistenceManagerFactory persistenceManagerFactory = 
		JDOHelper.getPersistenceManagerFactory("transactions-optional");
		
	/**
	 * 
	 */
	public JdoTemplate() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param newInstance
	 * @return
	 */
	public Object save(Object newInstance) {
		PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
		Transaction transaction = persistenceManager.currentTransaction();
		Object obj = null;
		try {
            transaction.begin();      
            obj = persistenceManager.makePersistent(newInstance);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
            	transaction.rollback();
            }
            persistenceManager.close();
        }
		return obj;
	}

	/**
	 * 
	 * @param objClass
	 * @param id
	 * @return
	 */
	public Object find(Class<?> objClass, String id) {
		PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
		Transaction transaction = persistenceManager.currentTransaction();
		Object obj = null;
		try {
            transaction.begin();      
            obj = persistenceManager.getObjectById(objClass, id);
            transaction.commit();
        } finally {  
        	if (transaction.isActive()) {
            	transaction.rollback();
            }
            persistenceManager.close();
        }
		return obj;
	}
	
	/**
	 * 
	 * @param objClass
	 * @return
	 */
	public Collection<?> findAll(Class<?> objClass) {
		PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
		Transaction transaction = persistenceManager.currentTransaction();
		Collection<?> objs = null;
		try {
            transaction.begin();      
            Query query = persistenceManager.newQuery(objClass);
            objs = (Collection<?>) query.execute();
            transaction.commit();
        } finally {  
        	if (transaction.isActive()) {
            	transaction.rollback();
            }
            persistenceManager.close();
        }
		return objs;
	}
	
	/**
	 * 
	 * @param objClass
	 * @param filter
	 * @return
	 */
	public Collection<?> findAllByFilter(Class<?> objClass, String filter) {
		PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
		Transaction transaction = persistenceManager.currentTransaction();
		Collection<?> objs = null;
		try {
            transaction.begin();      
            Query query = persistenceManager.newQuery(objClass);
            query.setFilter(filter);
            objs = (Collection<?>) query.execute();
            transaction.commit();
        } finally {  
        	if (transaction.isActive()) {
            	transaction.rollback();
            }
            persistenceManager.close();
        }
		return objs;
	}

}
