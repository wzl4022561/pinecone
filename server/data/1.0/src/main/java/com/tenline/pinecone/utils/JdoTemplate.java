/**
 * 
 */
package com.tenline.pinecone.utils;

import java.util.Collection;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

/**
 * @author Bill
 *
 */
public class JdoTemplate {

	private static final PersistenceManagerFactory pmf = 
		JDOHelper.getPersistenceManagerFactory("transactions-optional");
		
	/**
	 * 
	 */
	public JdoTemplate() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param objClass
	 * @param id
	 */
	public void delete(Class<?> objClass, String id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
            tx.begin();     
            pm.deletePersistent(pm.getObjectById(objClass, id));
            tx.commit();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            if (tx.isActive()) {
            	tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * 
	 * @param newInstance
	 * @return
	 */
	public Object save(Object newInstance) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Object obj = null;
		try {
            tx.begin();      
            obj = pm.makePersistent(newInstance);
            tx.commit();
        } finally {
            if (tx.isActive()) {
            	tx.rollback();
            }
            pm.close();
        }
        return obj;
	}
	
	/**
	 * 
	 * @param objClass
	 * @param id
	 * @return
	 */
	public Object getDetachedObject(Class<?> objClass, String id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Object obj = null;
		try {
			tx.begin();
            obj = pm.detachCopy(pm.getObjectById(objClass, id));
            tx.commit();
        } finally {
        	if (tx.isActive()) {
            	tx.rollback();
            }
            pm.close();
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
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Object obj = null;
		try {
			tx.begin();
            obj = pm.getObjectById(objClass, id);
            tx.commit();
        } finally {  
        	if (tx.isActive()) {
            	tx.rollback();
            }
            pm.close();
        }
		return obj;
	}
	
	/**
	 * 
	 * @param objClass
	 * @param filter
	 * @return
	 */
	public Collection<?> findAll(Class<?> objClass, String filter) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Collection<?> objs = null;
		try {    
			tx.begin();
			String queryString = "select from " + objClass.getName();
			if (filter != null) queryString += " where " + filter;
            objs = (Collection<?>) pm.newQuery(queryString).execute();
            tx.commit();
        } finally {  
        	if (tx.isActive()) {
            	tx.rollback();
            }
            pm.close();
        }
		return objs;
	}

}
