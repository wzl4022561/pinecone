/**
 * 
 */
package com.tenline.pinecone.service.restful;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.persistence.ItemDao;
import com.tenline.pinecone.service.ItemService;

/**
 * @author Bill
 *
 */
@Service
public class ItemRestfulService implements ItemService {

	private ItemDao itemDao;
	
	/**
	 * 
	 */
	@Autowired
	public ItemRestfulService(ItemDao itemDao) {
		// TODO Auto-generated constructor stub
		this.itemDao = itemDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.AbstractService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		itemDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.ItemService#create(com.tenline.pinecone.model.Item)
	 */
	@Override
	public Item create(Item item) {
		// TODO Auto-generated method stub
		return itemDao.save(item);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.ItemService#update(com.tenline.pinecone.model.Item)
	 */
	@Override
	public Item update(Item item) {
		// TODO Auto-generated method stub
		return itemDao.update(item);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.ItemService#show(java.lang.String)
	 */
	@Override
	public Collection<Item> show(String filter) {
		// TODO Auto-generated method stub
		return itemDao.find(filter);
	}

}
