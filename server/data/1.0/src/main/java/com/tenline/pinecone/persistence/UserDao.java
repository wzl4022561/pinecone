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
public interface UserDao {

	/**
	 * 
	 * @param newInstance
	 * @return
	 */
	String save(User newInstance);
	
	/**
	 * 
	 * @param primaryKey
	 * @return
	 */
	User find(String primaryKey);
	
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
