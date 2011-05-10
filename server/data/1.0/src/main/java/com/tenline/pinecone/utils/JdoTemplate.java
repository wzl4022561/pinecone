/**
 * 
 */
package com.tenline.pinecone.utils;

import java.util.Collection;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.KeyFactory;

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
	 * @param attachedObject
	 */
	public void delete(Object attachedObject) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
            tx.begin();     
            pm.deletePersistent(attachedObject);
            tx.commit();
        } finally {
            if (tx.isActive()) {
            	tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * 
	 * @param dirtyObject
	 * @return
	 */
	public Object persist(Object dirtyObject) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Object detachedObject = null;
		try {
            tx.begin();      
            detachedObject = pm.makePersistent(dirtyObject);
            tx.commit();
        } finally {
            if (tx.isActive()) {
            	tx.rollback();
            }
            pm.close();
        }
        return detachedObject;
	}
	
	/**
	 * 
	 * @param objectClass
	 * @param id
	 * @return
	 */
	public Object find(Class<?> objectClass, String id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Object detachedObject = null;
		try {    
			tx.begin();
			detachedObject = pm.getObjectById(objectClass, KeyFactory.stringToKey(id));
            tx.commit();
        } finally { 
        	if (tx.isActive()) {
            	tx.rollback();
            }
            pm.close();
        }
		return detachedObject;
	}
	
	/**
	 * 
	 * @param queryString
	 * @return
	 */
	public Collection<?> get(String queryString) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Collection<?> detachedObjects = null;
		try {    
			tx.begin();
            detachedObjects = (Collection<?>) pm.newQuery(queryString).execute();	
            tx.commit();
        } finally { 
        	if (tx.isActive()) {
            	tx.rollback();
            }
            pm.close();
        }
		return detachedObjects;
	}

}
