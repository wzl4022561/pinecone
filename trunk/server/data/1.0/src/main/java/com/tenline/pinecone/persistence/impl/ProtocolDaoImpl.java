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
import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.persistence.ProtocolDao;

/**
 * @author Bill
 *
 */
@Repository
@Transactional
public class ProtocolDaoImpl extends JdoDaoSupport implements ProtocolDao {

	/**
	 * 
	 */
	@Autowired
	public ProtocolDaoImpl(PersistenceManagerFactory persistenceManagerFactory) {
		// TODO Auto-generated constructor stub
		setPersistenceManagerFactory(persistenceManagerFactory);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().deletePersistent(getJdoTemplate().getObjectById(Protocol.class, id));
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ProtocolDao#save(com.tenline.pinecone.model.Protocol)
	 */
	@Override
	public String save(Protocol newInstance) {
		// TODO Auto-generated method stub
		newInstance.setDevice((Device) getJdoTemplate().getObjectById(Device.class, 
				newInstance.getDevice().getId()));
		return ((Protocol) getJdoTemplate().makePersistent(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ProtocolDao#update(com.tenline.pinecone.model.Protocol)
	 */
	@Override
	public String update(Protocol instance) {
		// TODO Auto-generated method stub
		Protocol detachedProtocol = (Protocol) getJdoTemplate().getObjectById(Protocol.class, instance.getId());
		if (instance.getName() != null) detachedProtocol.setName(instance.getName());
		if (instance.getVersion() != null) detachedProtocol.setVersion(instance.getVersion());
		return ((Protocol) getJdoTemplate().makePersistent(detachedProtocol)).getId();
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
		return (Collection<Protocol>) getJdoTemplate().find(queryString);
	}

}
