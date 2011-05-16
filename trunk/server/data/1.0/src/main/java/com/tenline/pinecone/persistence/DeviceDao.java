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
	Device save(Device newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	Device update(Device instance);
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<Device> find(String filter);

}
