/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.utils.AbstractDaoSupport;

/**
 * @author Bill
 *
 */
@Repository
public class UserDaoImpl extends AbstractDaoSupport implements UserDao {

	/**
	 * 
	 */
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#save(com.tenline.pinecone.model.User)
	 */
	@Override
	public String save(User newInstance) {
		// TODO Auto-generated method stub
		return ((User) getJdoTemplate().persist(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#find(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<User> find(String filter) {
		// TODO Auto-generated method stub
		String queryString = "select from " + User.class.getName();
		if (!filter.equals("all")) queryString += " where " + filter;
		return (Collection<User>) getJdoTemplate().get(queryString);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#update(com.tenline.pinecone.model.User)
	 */
	@Override
	public String update(User instance) {
		// TODO Auto-generated method stub
		User detachedUser = (User) getJdoTemplate().find(User.class, instance.getId());
		if (instance.getSnsId() != null) detachedUser.setSnsId(instance.getSnsId());
		if (instance.getDevices() != null) {
			for (Iterator<Device> i = instance.getDevices().iterator(); i.hasNext();) {
				Device device = i.next();
				device.setUser(detachedUser);
				detachedUser.getDevices().add(device);
			}
		}
		return ((User) getJdoTemplate().persist(detachedUser)).getId();
	}
	
	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().delete(getJdoTemplate().find(User.class, id));
	}

}
