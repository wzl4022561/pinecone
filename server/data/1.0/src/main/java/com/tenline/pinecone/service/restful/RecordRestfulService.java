/**
 * 
 */
package com.tenline.pinecone.service.restful;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.Record;
import com.tenline.pinecone.persistence.RecordDao;
import com.tenline.pinecone.service.RecordService;

/**
 * @author Bill
 *
 */
@Service
public class RecordRestfulService implements RecordService {
	
	private RecordDao recordDao;

	/**
	 * 
	 */
	@Autowired
	public RecordRestfulService(RecordDao recordDao) {
		// TODO Auto-generated constructor stub
		this.recordDao = recordDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.AbstractService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		recordDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.RecordService#create(com.tenline.pinecone.model.Record)
	 */
	@Override
	public Record create(Record record) {
		// TODO Auto-generated method stub
		return recordDao.save(record);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.RecordService#update(com.tenline.pinecone.model.Record)
	 */
	@Override
	public Record update(Record record) {
		// TODO Auto-generated method stub
		return recordDao.update(record);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.RecordService#show(java.lang.String)
	 */
	@Override
	public Collection<Record> show(String filter) {
		// TODO Auto-generated method stub
		return recordDao.find(filter);
	}

}
