/**
 * 
 */
package com.tenline.pinecone.utils;

import java.lang.reflect.Field;
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
	 * @param instance
	 * @return
	 */
	public Object update(Object instance) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Object obj = null;
		try {
            tx.begin();      
            Class<?> cls = instance.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (int i=0; i<fields.length; i++) {
            	Field field = fields[i];
            	field.setAccessible(true);
            	if (field.getName().equals("id")) {
            		System.out.println(field.get(instance));
            		obj = pm.getObjectById(cls, field.get(instance));
            	} else {
            		obj.getClass().getDeclaredField(field.getName()).set(obj, field.get(instance));
            	}
            }
            pm.makePersistent(obj);
            tx.commit();
        } catch (Exception e) {
        	e.printStackTrace();
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
	 * @return
	 */
	public Collection<?> findAll(Class<?> objClass) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Collection<?> objs = null;
		try {    
			tx.begin();
            Query query = pm.newQuery(objClass);
            objs = (Collection<?>) query.execute();
            tx.commit();
        } finally {
        	if (tx.isActive()) {
            	tx.rollback();
            }
            pm.close();
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
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Collection<?> objs = null;
		try {    
			tx.begin();
            Query query = pm.newQuery(objClass);
            query.setFilter(filter);
            objs = (Collection<?>) query.execute();
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
