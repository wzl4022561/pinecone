/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.utils.AbstractDaoSupport;

/**
 * @author Bill
 *
 */
@Repository
public class DeviceDaoImpl extends AbstractDaoSupport implements DeviceDao {

	/**
	 * 
	 */
	public DeviceDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.DeviceDao#save(com.tenline.pinecone.model.Device)
	 */
	@Override
	public String save(Device newInstance) {
		// TODO Auto-generated method stub
		newInstance.setUser((User) getJdoTemplate().find(User.class, newInstance.getUser().getId()));
		return ((Device) getJdoTemplate().persist(newInstance)).getId();
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
		return (Collection<Device>) getJdoTemplate().get(queryString);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().delete(getJdoTemplate().find(Device.class, id));
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.DeviceDao#update(com.tenline.pinecone.model.Device)
	 */
	@Override
	public String update(Device instance) {
		// TODO Auto-generated method stub
		Device detachedDevice = (Device) getJdoTemplate().find(Device.class, instance.getId());
		if (instance.getName() != null) detachedDevice.setName(instance.getName());
		if (instance.getType() != null) detachedDevice.setType(instance.getType());
		if (instance.getProtocol() != null) {
			instance.getProtocol().setDevice(detachedDevice);
			detachedDevice.setProtocol(instance.getProtocol());
		}
		if (instance.getVariables() != null) {
			for (Iterator<Variable> i = instance.getVariables().iterator(); i.hasNext();) {
				Variable variable = i.next();
				variable.setDevice(detachedDevice);
				detachedDevice.getVariables().add(variable);
			}
		}
		return ((Device) getJdoTemplate().persist(detachedDevice)).getId();
	}

}
