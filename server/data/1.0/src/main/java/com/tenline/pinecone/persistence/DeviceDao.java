/**
 * 
 */
package com.tenline.pinecone.persistence;

import java.util.Collection;

import com.tenline.pinecone.model.Device;

/**
 * @author Bill
 *
 */
public interface DeviceDao extends AbstractDao {
	
	/**
	 * 
	 * @param newInstance
	 * @return
	 */
	String save(Device newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	String update(Device instance);
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<Device> find(String filter);

}
