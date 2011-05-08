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
	@SuppressWarnings("unchecked")
	public Collection<Variable> find(String filter) {
		// TODO Auto-generated method stub
		return (Collection<Variable>) getJdoTemplate().find(Variable.class, filter);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().delete(Variable.class, id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.VariableDao#update(com.tenline.pinecone.model.Variable)
	 */
	@Override
	public String update(Variable instance) {
		// TODO Auto-generated method stub
		Variable detachedVariable = (Variable) getJdoTemplate().getDetachedObject(Variable.class, instance.getId());
		if (instance.getName() != null) detachedVariable.setName(instance.getName());
		if (instance.getType() != null) detachedVariable.setType(instance.getType());
		return ((Variable) getJdoTemplate().save(detachedVariable)).getId();
	}

}
