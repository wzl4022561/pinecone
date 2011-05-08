/**
 * 
 */
package com.tenline.pinecone.rest.impl;

import java.util.Collection;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.persistence.ItemDao;
import com.tenline.pinecone.rest.ItemService;

/**
 * @author Bill
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	private ItemDao itemDao;
	
	/**
	 * 
	 */
	@Autowired
	public ItemServiceImpl(ItemDao itemDao) {
		// TODO Auto-generated constructor stub
		this.itemDao = itemDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.AbstractService#delete(java.lang.String)
	 */
	@Override
	public Response delete(String id) {
		// TODO Auto-generated method stub
		itemDao.delete(id);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.ItemService#create(com.tenline.pinecone.model.Item)
	 */
	@Override
	public Response create(Item item) {
		// TODO Auto-generated method stub
		itemDao.save(item);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.ItemService#update(com.tenline.pinecone.model.Item)
	 */
	@Override
	public Response update(Item item) {
		// TODO Auto-generated method stub
		itemDao.update(item);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.ItemService#show(java.lang.String)
	 */
	@Override
	public Item show(String id) {
		// TODO Auto-generated method stub
		return itemDao.find(id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.ItemService#showAll()
	 */
	@Override
	public Collection<Item> showAll() {
		// TODO Auto-generated method stub
		return itemDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.ItemService#showAll(java.lang.String)
	 */
	@Override
	public Collection<Item> showAll(String filter) {
		// TODO Auto-generated method stub
		return itemDao.findAll(filter);
	}

}
