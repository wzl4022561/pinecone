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
	Protocol save(Protocol newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	Protocol update(Protocol instance);
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<Protocol> find(String filter);
	
}
