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
	public Device show(String id) {
		// TODO Auto-generated method stub
		return deviceDao.find(id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.DeviceService#showAll()
	 */
	@Override
	public Collection<Device> showAll() {
		// TODO Auto-generated method stub
		return deviceDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.DeviceService#showAllByFilter(java.lang.String)
	 */
	@Override
	public Collection<Device> showAllByFilter(String filter) {
		// TODO Auto-generated method stub
		return deviceDao.findAllByFilter(filter);
	}

}
