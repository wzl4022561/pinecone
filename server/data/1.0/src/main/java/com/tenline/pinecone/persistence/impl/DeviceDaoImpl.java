/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;

import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.support.JdoDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.DeviceDao;

/**
 * @author Bill
 *
 */
@Repository
@Transactional
public class DeviceDaoImpl extends JdoDaoSupport implements DeviceDao {

	/**
	 * 
	 */
	@Autowired
	public DeviceDaoImpl(PersistenceManagerFactory persistenceManagerFactory) {
		// TODO Auto-generated constructor stub
		setPersistenceManagerFactory(persistenceManagerFactory);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.DeviceDao#save(com.tenline.pinecone.model.Device)
	 */
	@Override
	public String save(Device newInstance) {
		// TODO Auto-generated method stub
		newInstance.setUser((User) getJdoTemplate().getObjectById(User.class, newInstance.getUser().getId()));
		return ((Device) getJdoTemplate().makePersistent(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.DeviceDao#find(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Device> find(String filter) {
		// TODO Auto-generated method stub
		String queryString = "select from " + Device.class.getName();
		if (!filter.equals("all")) queryString += " where " + filter;
		return (Collection<Device>) getJdoTemplate().find(queryString);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().deletePersistent(getJdoTemplate().getObjectById(Device.class, id));
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.DeviceDao#update(com.tenline.pinecone.model.Device)
	 */
	@Override
	public String update(Device instance) {
		// TODO Auto-generated method stub
		Device detachedDevice = (Device) getJdoTemplate().getObjectById(Device.class, instance.getId());
		if (instance.getName() != null) detachedDevice.setName(instance.getName());
		if (instance.getType() != null) detachedDevice.setType(instance.getType());
		return ((Device) getJdoTemplate().makePersistent(detachedDevice)).getId();
	}

}
