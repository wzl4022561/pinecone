/**
 * 
 */
package com.tenline.pinecone.rest.impl;

import java.util.Collection;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.rest.DeviceService;

/**
 * @author Bill
 *
 */
@Service
public class DeviceServiceImpl implements DeviceService {
	
	private DeviceDao deviceDao;

	/**
	 * 
	 */
	@Autowired
	public DeviceServiceImpl(DeviceDao deviceDao) {
		// TODO Auto-generated constructor stub
		this.deviceDao = deviceDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.DeviceService#create(com.tenline.pinecone.model.Device)
	 */
	@Override
	public Response create(Device device) {
		// TODO Auto-generated method stub
		deviceDao.save(device);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.DeviceService#show(java.lang.String)
	 */
	@Override
	public Collection<Device> show(String filter) {
		// TODO Auto-generated method stub
		return deviceDao.find(filter);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.AbstractService#delete(java.lang.String)
	 */
	@Override
	public Response delete(String id) {
		// TODO Auto-generated method stub
		deviceDao.delete(id);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.DeviceService#update(com.tenline.pinecone.model.Device)
	 */
	@Override
	public Response update(Device device) {
		// TODO Auto-generated method stub
		deviceDao.update(device);
		return Response.status(Status.OK).build();
	}

}
