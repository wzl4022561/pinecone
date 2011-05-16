/**
 * 
 */
package com.tenline.pinecone.service.restful;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.persistence.ProtocolDao;
import com.tenline.pinecone.service.ProtocolService;

/**
 * @author Bill
 *
 */
@Service
public class ProtocolRestfulService implements ProtocolService {

	private ProtocolDao protocolDao;
	
	/**
	 * 
	 */
	@Autowired
	public ProtocolRestfulService(ProtocolDao protocolDao) {
		// TODO Auto-generated constructor stub
		this.protocolDao = protocolDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.AbstractService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		protocolDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.ProtocolService#create(com.tenline.pinecone.model.Protocol)
	 */
	@Override
	public Protocol create(Protocol protocol) {
		// TODO Auto-generated method stub
		return protocolDao.save(protocol);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.ProtocolService#update(com.tenline.pinecone.model.Protocol)
	 */
	@Override
	public Protocol update(Protocol protocol) {
		// TODO Auto-generated method stub
		return protocolDao.update(protocol);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.ProtocolService#show(java.lang.String)
	 */
	@Override
	public Collection<Protocol> show(String filter) {
		// TODO Auto-generated method stub
		return protocolDao.find(filter);
	}

}
