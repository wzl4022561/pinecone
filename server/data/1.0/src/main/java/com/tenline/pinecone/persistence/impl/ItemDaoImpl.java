/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.persistence.ItemDao;
import com.tenline.pinecone.utils.AbstractDaoSupport;

/**
 * @author Bill
 *
 */
@Repository
public class ItemDaoImpl extends AbstractDaoSupport implements ItemDao {

	/**
	 * 
	 */
	public ItemDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().delete(Item.class, id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ItemDao#save(com.tenline.pinecone.model.Item)
	 */
	@Override
	public String save(Item newInstance) {
		// TODO Auto-generated method stub
		return ((Item) getJdoTemplate().save(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ItemDao#update(com.tenline.pinecone.model.Item)
	 */
	@Override
	public String update(Item instance) {
		// TODO Auto-generated method stub
		Item detachedItem = (Item) getJdoTemplate().getDetachedObject(Item.class, instance.getId());
		if (instance.getValue() != null) detachedItem.setValue(instance.getValue());
		return ((Item) getJdoTemplate().save(detachedItem)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ItemDao#find(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Item> find(String filter) {
		// TODO Auto-generated method stub
		return (Collection<Item>) getJdoTemplate().find(Item.class, filter);
	}

}
