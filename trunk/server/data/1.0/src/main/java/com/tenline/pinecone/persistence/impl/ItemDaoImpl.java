/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.model.Variable;
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
		getJdoTemplate().delete(getJdoTemplate().find(Item.class, id));
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ItemDao#save(com.tenline.pinecone.model.Item)
	 */
	@Override
	public String save(Item newInstance) {
		// TODO Auto-generated method stub
		newInstance.setVariable((Variable) getJdoTemplate().find(Variable.class, newInstance.getVariable().getId()));
		return ((Item) getJdoTemplate().persist(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ItemDao#update(com.tenline.pinecone.model.Item)
	 */
	@Override
	public String update(Item instance) {
		// TODO Auto-generated method stub
		Item detachedItem = (Item) getJdoTemplate().find(Item.class, instance.getId());
		if (instance.getValue() != null) detachedItem.setValue(instance.getValue());
		if (instance.getText() != null) detachedItem.setText(instance.getText());
		return ((Item) getJdoTemplate().persist(detachedItem)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ItemDao#find(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Item> find(String filter) {
		// TODO Auto-generated method stub
		String queryString = "select from " + Item.class.getName();
		if (!filter.equals("all")) queryString += " where " + filter;
		return (Collection<Item>) getJdoTemplate().get(queryString);
	}

}
