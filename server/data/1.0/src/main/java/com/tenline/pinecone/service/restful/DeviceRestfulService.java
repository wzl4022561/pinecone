/**
 * 
 */
package com.tenline.pinecone.service.restful;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.service.DeviceService;

/**
 * @author Bill
 *
 */
@Service
public class DeviceRestfulService implements DeviceService {
	
	private DeviceDao deviceDao;

	/**
	 * 
	 */
	@Autowired
	public DeviceRestfulService(DeviceDao deviceDao) {
		// TODO Auto-generated constructor stub
		this.deviceDao = deviceDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.DeviceService#create(com.tenline.pinecone.model.Device)
	 */
	@Override
	public Device create(Device device) {
		// TODO Auto-generated method stub
		return deviceDao.save(device);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.DeviceService#show(java.lang.String)
	 */
	@Override
	public Collection<Device> show(String filter) {
		// TODO Auto-generated method stub
		return deviceDao.find(filter);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.AbstractService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		deviceDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.DeviceService#update(com.tenline.pinecone.model.Device)
	 */
	@Override
	public Device update(Device device) {
		// TODO Auto-generated method stub
		return deviceDao.update(device);
	}

}
