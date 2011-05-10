/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.persistence.ProtocolDao;
import com.tenline.pinecone.utils.AbstractDaoSupport;

/**
 * @author Bill
 *
 */
@Repository
public class ProtocolDaoImpl extends AbstractDaoSupport implements ProtocolDao {

	/**
	 * 
	 */
	public ProtocolDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().delete(getJdoTemplate().find(Protocol.class, id));
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ProtocolDao#save(com.tenline.pinecone.model.Protocol)
	 */
	@Override
	public String save(Protocol newInstance) {
		// TODO Auto-generated method stub
		newInstance.setDevice((Device) getJdoTemplate().find(Device.class, newInstance.getDevice().getId()));
		return ((Protocol) getJdoTemplate().persist(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ProtocolDao#update(com.tenline.pinecone.model.Protocol)
	 */
	@Override
	public String update(Protocol instance) {
		// TODO Auto-generated method stub
		Protocol detachedProtocol = (Protocol) getJdoTemplate().find(Protocol.class, instance.getId());
		if (instance.getName() != null) detachedProtocol.setName(instance.getName());
		if (instance.getVersion() != null) detachedProtocol.setVersion(instance.getVersion());
		return ((Protocol) getJdoTemplate().persist(detachedProtocol)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ProtocolDao#find(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Protocol> find(String filter) {
		// TODO Auto-generated method stub
		String queryString = "select from " + Protocol.class.getName();
		if (!filter.equals("all")) queryString += " where " + filter;
		return (Collection<Protocol>) getJdoTemplate().get(queryString);
	}

}
