/**
 * 
 */
package com.tenline.pinecone.persistence.jdo;

import java.util.Collection;

import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.support.JdoDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.ItemDao;

/**
 * @author Bill
 *
 */
@Repository
@Transactional
public class ItemJdoDao extends JdoDaoSupport implements ItemDao {

	/**
	 * 
	 */
	@Autowired
	public ItemJdoDao(PersistenceManagerFactory persistenceManagerFactory) {
		// TODO Auto-generated constructor stub
		setPersistenceManagerFactory(persistenceManagerFactory);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.AbstractDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().deletePersistent(getJdoTemplate().getObjectById(Item.class, id));
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ItemDao#save(com.tenline.pinecone.model.Item)
	 */
	@Override
	public Item save(Item newInstance) {
		// TODO Auto-generated method stub
		newInstance.setVariable((Variable) getJdoTemplate().getObjectById(Variable.class, 
				newInstance.getVariable().getId()));
		return (Item) getJdoTemplate().makePersistent(newInstance);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.ItemDao#update(com.tenline.pinecone.model.Item)
	 */
	@Override
	public Item update(Item instance) {
		// TODO Auto-generated method stub
		Item detachedItem = (Item) getJdoTemplate().getObjectById(Item.class, instance.getId());
		if (instance.getValue() != null) detachedItem.setValue(instance.getValue());
		if (instance.getText() != null) detachedItem.setText(instance.getText());
		return (Item) getJdoTemplate().makePersistent(detachedItem);
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
		return (Collection<Item>) getJdoTemplate().find(queryString);
	}

}
