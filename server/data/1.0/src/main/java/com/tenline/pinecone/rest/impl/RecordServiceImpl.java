/**
 * 
 */
package com.tenline.pinecone.rest.impl;

import java.util.Collection;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.Record;
import com.tenline.pinecone.persistence.RecordDao;
import com.tenline.pinecone.rest.RecordService;

/**
 * @author Bill
 *
 */
@Service
public class RecordServiceImpl implements RecordService {
	
	private RecordDao recordDao;

	/**
	 * 
	 */
	@Autowired
	public RecordServiceImpl(RecordDao recordDao) {
		// TODO Auto-generated constructor stub
		this.recordDao = recordDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.AbstractService#delete(java.lang.String)
	 */
	@Override
	public Response delete(String id) {
		// TODO Auto-generated method stub
		recordDao.delete(id);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.RecordService#create(com.tenline.pinecone.model.Record)
	 */
	@Override
	public Response create(Record record) {
		// TODO Auto-generated method stub
		recordDao.save(record);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.RecordService#update(com.tenline.pinecone.model.Record)
	 */
	@Override
	public Response update(Record record) {
		// TODO Auto-generated method stub
		recordDao.update(record);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.RecordService#show(java.lang.String)
	 */
	@Override
	public Collection<Record> show(String filter) {
		// TODO Auto-generated method stub
		return recordDao.find(filter);
	}

}
