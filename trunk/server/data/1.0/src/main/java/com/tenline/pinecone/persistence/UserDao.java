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
	User save(User newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	User update(User instance);
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<User> find(String filter);
	
}
