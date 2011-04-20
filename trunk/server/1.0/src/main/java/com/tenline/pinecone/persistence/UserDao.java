/**
 * 
 */
package com.tenline.pinecone.persistence;

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
	Long save(User newInstance);
	
	/**
	 * 
	 * @param primaryKey
	 * @return
	 */
	User find(Long primaryKey);
	
}
