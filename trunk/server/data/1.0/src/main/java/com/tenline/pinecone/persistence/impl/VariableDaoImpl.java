/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.VariableDao;
import com.tenline.pinecone.utils.AbstractDaoSupport;

/**
 * @author Bill
 *
 */
@Repository
public class VariableDaoImpl extends AbstractDaoSupport implements VariableDao {

	/**
	 * 
	 */
	public VariableDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.VariableDao#save(com.tenline.pinecone.model.Variable)
	 */
	@Override
	public String save(Variable newInstance) {
		// TODO Auto-generated method stub
		return ((Variable) getJdoTemplate().save(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.VariableDao#find(java.lang.String)
	 */
	@Override
	public Variable find(String id) {
		// TODO Auto-generated method stub
		return (Variable) getJdoTemplate().find(Variable.class, id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.VariableDao#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Variable> findAll() {
		// TODO Auto-generated method stub
		return (Collection<Variable>) getJdoTemplate().findAll(Variable.class);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.VariableDao#findAllByFilter(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Variable> findAllByFilter(String filter) {
		// TODO Auto-generated method stub
		return (Collection<Variable>) getJdoTemplate().findAllByFilter(Variable.class, filter);
	}

}
