/**
 * 
 */
package com.tenline.pinecone.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.jdo.Extent;
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
	 * @param filter
	 * @return
	 */
	public Collection<?> find(Class<?> objClass, String filter) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Collection<Object> objs = new ArrayList<Object>();
		try {    
			tx.begin();
			if (filter.equals("all")) {
				Extent<?> extent = pm.getExtent(objClass);			
				for (Iterator<?> i = extent.iterator(); i.hasNext();) {
					objs.add(i.next());
			    }
			    extent.closeAll();
			} else {
				String queryString = "select from " + objClass.getName();
				if (filter != null) queryString += " where " + filter;
	            Query query = pm.newQuery(queryString);
	            Collection<?> result = (Collection<?>) query.execute();
	            for (Iterator<?> i = result.iterator(); i.hasNext();) {
	            	objs.add(i.next());
	            }
	        	query.closeAll();
			}			
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
