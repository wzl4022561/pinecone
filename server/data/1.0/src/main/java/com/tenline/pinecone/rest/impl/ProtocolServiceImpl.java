/**
 * 
 */
package com.tenline.pinecone.rest.impl;

import java.util.Collection;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.persistence.ProtocolDao;
import com.tenline.pinecone.rest.ProtocolService;

/**
 * @author Bill
 *
 */
@Service
public class ProtocolServiceImpl implements ProtocolService {

	private ProtocolDao protocolDao;
	
	/**
	 * 
	 */
	@Autowired
	public ProtocolServiceImpl(ProtocolDao protocolDao) {
		// TODO Auto-generated constructor stub
		this.protocolDao = protocolDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.AbstractService#delete(java.lang.String)
	 */
	@Override
	public Response delete(String id) {
		// TODO Auto-generated method stub
		protocolDao.delete(id);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.ProtocolService#create(com.tenline.pinecone.model.Protocol)
	 */
	@Override
	public Response create(Protocol protocol) {
		// TODO Auto-generated method stub
		protocolDao.save(protocol);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.ProtocolService#update(com.tenline.pinecone.model.Protocol)
	 */
	@Override
	public Response update(Protocol protocol) {
		// TODO Auto-generated method stub
		protocolDao.update(protocol);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.ProtocolService#show(java.lang.String)
	 */
	@Override
	public Collection<Protocol> show(String filter) {
		// TODO Auto-generated method stub
		return protocolDao.find(filter);
	}

}
