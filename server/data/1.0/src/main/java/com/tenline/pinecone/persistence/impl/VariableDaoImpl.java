/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.model.Record;
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
		newInstance.setDevice((Device) getJdoTemplate().find(Device.class, newInstance.getDevice().getId()));
		return ((Variable) getJdoTemplate().persist(newInstance)).getId();
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
		return (Collection<Variable>) getJdoTemplate().get(queryString);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().delete(getJdoTemplate().find(Variable.class, id));
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.VariableDao#update(com.tenline.pinecone.model.Variable)
	 */
	@Override
	public String update(Variable instance) {
		// TODO Auto-generated method stub
		Variable detachedVariable = (Variable) getJdoTemplate().find(Variable.class, instance.getId());
		if (instance.getName() != null) detachedVariable.setName(instance.getName());
		if (instance.getType() != null) detachedVariable.setType(instance.getType());
		if (instance.getItems() != null) {
			for (Iterator<Item> i = instance.getItems().iterator(); i.hasNext();) {
				Item item = i.next();
				item.setVariable(detachedVariable);
				detachedVariable.getItems().add(item);
			}
		}
		if (instance.getRecords() != null) {
			for (Iterator<Record> i = instance.getRecords().iterator(); i.hasNext();) {
				Record record = i.next();
				record.setVariable(detachedVariable);
				detachedVariable.getRecords().add(record);
			}
		}
		return ((Variable) getJdoTemplate().persist(detachedVariable)).getId();
	}

}
