/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.tenline.pinecone.model.Record;
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
		getJdoTemplate().delete(Record.class, id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.RecordDao#save(com.tenline.pinecone.model.Record)
	 */
	@Override
	public String save(Record newInstance) {
		// TODO Auto-generated method stub
		newInstance.setTimestamp(new Date());
		return ((Record) getJdoTemplate().save(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.RecordDao#update(com.tenline.pinecone.model.Record)
	 */
	@Override
	public String update(Record instance) {
		// TODO Auto-generated method stub
		Record detachedRecord = (Record) getJdoTemplate().getDetachedObject(Record.class, instance.getId());
		detachedRecord.setTimestamp(new Date());
		if (instance.getValue() != null) detachedRecord.setValue(instance.getValue());
		return ((Record) getJdoTemplate().save(detachedRecord)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.RecordDao#find(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Record> find(String filter) {
		// TODO Auto-generated method stub
		return (Collection<Record>) getJdoTemplate().find(Record.class, filter);
	}

}
