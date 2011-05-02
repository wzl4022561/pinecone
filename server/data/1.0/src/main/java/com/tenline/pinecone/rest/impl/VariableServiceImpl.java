/**
 * 
 */
package com.tenline.pinecone.rest.impl;

import java.util.Collection;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.VariableDao;
import com.tenline.pinecone.rest.VariableService;

/**
 * @author Bill
 *
 */
@Service
public class VariableServiceImpl implements VariableService {

	private VariableDao variableDao;
	
	/**
	 * 
	 */
	@Autowired
	public VariableServiceImpl(VariableDao variableDao) {
		// TODO Auto-generated constructor stub
		this.variableDao = variableDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.VariableService#create(com.tenline.pinecone.model.Variable)
	 */
	@Override
	public Response create(Variable variable) {
		// TODO Auto-generated method stub
		variableDao.save(variable);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.VariableService#show(java.lang.String)
	 */
	@Override
	public Variable show(String id) {
		// TODO Auto-generated method stub
		return variableDao.find(id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.VariableService#showAll()
	 */
	@Override
	public Collection<Variable> showAll() {
		// TODO Auto-generated method stub
		return variableDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.VariableService#showAllByFilter(java.lang.String)
	 */
	@Override
	public Collection<Variable> showAllByFilter(String filter) {
		// TODO Auto-generated method stub
		return variableDao.findAllByFilter(filter);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.AbstractService#delete(java.lang.String)
	 */
	@Override
	public Response delete(String id) {
		// TODO Auto-generated method stub
		variableDao.delete(id);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.VariableService#update(com.tenline.pinecone.model.Variable)
	 */
	@Override
	public Response update(Variable variable) {
		// TODO Auto-generated method stub
		variableDao.update(variable);
		return Response.status(Status.OK).build();
	}

}
