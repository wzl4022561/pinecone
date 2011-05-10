/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.tenline.pinecone.model.Record;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.RecordDao;
import com.tenline.pinecone.utils.AbstractDaoSupport;

/**
 * @author Bill
 *
 */
@Repository
public class RecordDaoImpl extends AbstractDaoSupport implements RecordDao {

	/**
	 * 
	 */
	public RecordDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().delete(getJdoTemplate().find(Record.class, id));
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.RecordDao#save(com.tenline.pinecone.model.Record)
	 */
	@Override
	public String save(Record newInstance) {
		// TODO Auto-generated method stub
		newInstance.setTimestamp(new Date());
		newInstance.setVariable((Variable) getJdoTemplate().find(Variable.class, newInstance.getVariable().getId()));
		return ((Record) getJdoTemplate().persist(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.RecordDao#update(com.tenline.pinecone.model.Record)
	 */
	@Override
	public String update(Record instance) {
		// TODO Auto-generated method stub
		Record detachedRecord = (Record) getJdoTemplate().find(Record.class, instance.getId());
		detachedRecord.setTimestamp(new Date());
		if (instance.getValue() != null) detachedRecord.setValue(instance.getValue());
		return ((Record) getJdoTemplate().persist(detachedRecord)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.RecordDao#find(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Record> find(String filter) {
		// TODO Auto-generated method stub
		String queryString = "select from " + Record.class.getName();
		if (!filter.equals("all")) queryString += " where " + filter;
		return (Collection<Record>) getJdoTemplate().get(queryString);
	}

}
