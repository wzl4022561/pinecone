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
	Item save(Item newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	Item update(Item instance);
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<Item> find(String filter);
	
}
