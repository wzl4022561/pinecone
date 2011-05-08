/**
 * 
 */
package com.tenline.pinecone.persistence;

import java.util.Collection;

import com.tenline.pinecone.model.Record;

/**
 * @author Bill
 *
 */
public interface RecordDao extends AbstractDao {
	
	/**
	 * 
	 * @param newInstance
	 * @return
	 */
	String save(Record newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	String update(Record instance);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Record find(String id);
	
	/**
	 * 
	 * @return
	 */
	Collection<Record> findAll();
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<Record> findAll(String filter);

}
