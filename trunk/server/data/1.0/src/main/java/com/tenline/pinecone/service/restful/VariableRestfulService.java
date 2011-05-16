/**
 * 
 */
package com.tenline.pinecone.service.restful;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.VariableDao;
import com.tenline.pinecone.service.VariableService;

/**
 * @author Bill
 *
 */
@Service
public class VariableRestfulService implements VariableService {

	private VariableDao variableDao;
	
	/**
	 * 
	 */
	@Autowired
	public VariableRestfulService(VariableDao variableDao) {
		// TODO Auto-generated constructor stub
		this.variableDao = variableDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.VariableService#create(com.tenline.pinecone.model.Variable)
	 */
	@Override
	public Variable create(Variable variable) {
		// TODO Auto-generated method stub
		return variableDao.save(variable);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.VariableService#show(java.lang.String)
	 */
	@Override
	public Collection<Variable> show(String filter) {
		// TODO Auto-generated method stub
		return variableDao.find(filter);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.AbstractService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		variableDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.VariableService#update(com.tenline.pinecone.model.Variable)
	 */
	@Override
	public Variable update(Variable variable) {
		// TODO Auto-generated method stub
		return variableDao.update(variable);
	}

}
