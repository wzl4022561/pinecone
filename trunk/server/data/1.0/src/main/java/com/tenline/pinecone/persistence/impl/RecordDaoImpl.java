/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;
import java.util.Date;

import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.support.JdoDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tenline.pinecone.model.Record;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.RecordDao;

/**
 * @author Bill
 *
 */
@Repository
@Transactional
public class RecordDaoImpl extends JdoDaoSupport implements RecordDao {

	/**
	 * 
	 */
	@Autowired
	public RecordDaoImpl(PersistenceManagerFactory persistenceManagerFactory) {
		// TODO Auto-generated constructor stub
		setPersistenceManagerFactory(persistenceManagerFactory);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().deletePersistent(getJdoTemplate().getObjectById(Record.class, id));
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.RecordDao#save(com.tenline.pinecone.model.Record)
	 */
	@Override
	public String save(Record newInstance) {
		// TODO Auto-generated method stub
		newInstance.setTimestamp(new Date());
		newInstance.setVariable((Variable) getJdoTemplate().getObjectById(Variable.class, 
				newInstance.getVariable().getId()));
		return ((Record) getJdoTemplate().makePersistent(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.RecordDao#update(com.tenline.pinecone.model.Record)
	 */
	@Override
	public String update(Record instance) {
		// TODO Auto-generated method stub
		Record detachedRecord = (Record) getJdoTemplate().getObjectById(Record.class, instance.getId());
		detachedRecord.setTimestamp(new Date());
		if (instance.getValue() != null) detachedRecord.setValue(instance.getValue());
		return ((Record) getJdoTemplate().makePersistent(detachedRecord)).getId();
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
		return (Collection<Record>) getJdoTemplate().find(queryString);
	}

}
