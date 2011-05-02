/**
 * 
 */
package com.tenline.pinecone.persistence;

import java.util.Collection;

import com.tenline.pinecone.model.User;

/**
 * @author Bill
 *
 */
public interface UserDao extends AbstractDao {

	/**
	 * 
	 * @param newInstance
	 * @return
	 */
	String save(User newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	String update(User instance);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	User find(String id);
	
	/**
	 * 
	 * @return
	 */
	Collection<User> findAll();
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<User> findAllByFilter(String filter);
	
}
