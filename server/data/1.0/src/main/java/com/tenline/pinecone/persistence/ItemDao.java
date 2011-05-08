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
	 * @param filter
	 * @return
	 */
	Collection<Item> find(String filter);
	
}
