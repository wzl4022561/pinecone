/**
 * 
 */
package com.tenline.pinecone.persistence;

import java.util.Collection;

import com.tenline.pinecone.model.Protocol;

/**
 * @author Bill
 *
 */
public interface ProtocolDao extends AbstractDao {

	/**
	 * 
	 * @param newInstance
	 * @return
	 */
	String save(Protocol newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	String update(Protocol instance);
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<Protocol> find(String filter);
	
}
