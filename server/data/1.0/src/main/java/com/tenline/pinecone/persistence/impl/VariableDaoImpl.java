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
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.VariableDao;

/**
 * @author Bill
 *
 */
@Repository
@Transactional
public class VariableDaoImpl extends JdoDaoSupport implements VariableDao {

	/**
	 * 
	 */
	@Autowired
	public VariableDaoImpl(PersistenceManagerFactory persistenceManagerFactory) {
		// TODO Auto-generated constructor stub
		setPersistenceManagerFactory(persistenceManagerFactory);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.VariableDao#save(com.tenline.pinecone.model.Variable)
	 */
	@Override
	public String save(Variable newInstance) {
		// TODO Auto-generated method stub
		newInstance.setDevice((Device) getJdoTemplate().getObjectById(Device.class, 
				newInstance.getDevice().getId()));
		return ((Variable) getJdoTemplate().makePersistent(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.VariableDao#find(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Variable> find(String filter) {
		// TODO Auto-generated method stub
		String queryString = "select from " + Variable.class.getName();
		if (!filter.equals("all")) queryString += " where " + filter;
		return (Collection<Variable>) getJdoTemplate().find(queryString);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().deletePersistent(getJdoTemplate().getObjectById(Variable.class, id));
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.VariableDao#update(com.tenline.pinecone.model.Variable)
	 */
	@Override
	public String update(Variable instance) {
		// TODO Auto-generated method stub
		Variable detachedVariable = (Variable) getJdoTemplate().getObjectById(Variable.class, instance.getId());
		if (instance.getName() != null) detachedVariable.setName(instance.getName());
		if (instance.getType() != null) detachedVariable.setType(instance.getType());
		return ((Variable) getJdoTemplate().makePersistent(detachedVariable)).getId();
	}

}
