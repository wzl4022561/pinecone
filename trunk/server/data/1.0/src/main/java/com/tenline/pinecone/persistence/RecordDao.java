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
	Record save(Record newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	Record update(Record instance);
	
	/** 
	 * @param filter
	 * @return
	 */
	Collection<Record> find(String filter);

}
