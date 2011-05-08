/**
 * 
 */
package com.tenline.pinecone.persistence;

import java.util.Collection;

import com.tenline.pinecone.model.Item;

/**
 * @author Bill
 *
 */
public interface ItemDao extends AbstractDao {

	/**
	 * 
	 * @param newInstance
	 * @return
	 */
	String save(Item newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	String update(Item instance);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Item find(String id);
	
	/**
	 * 
	 * @return
	 */
	Collection<Item> findAll();
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<Item> findAll(String filter);
	
}
