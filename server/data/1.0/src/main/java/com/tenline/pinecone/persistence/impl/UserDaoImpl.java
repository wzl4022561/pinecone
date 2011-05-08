/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

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
		return ((User) getJdoTemplate().save(newInstance)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#find(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<User> find(String filter) {
		// TODO Auto-generated method stub
		return (Collection<User>) getJdoTemplate().find(User.class, filter);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#update(com.tenline.pinecone.model.User)
	 */
	@Override
	public String update(User instance) {
		// TODO Auto-generated method stub
		User detachedUser = (User) getJdoTemplate().getDetachedObject(User.class, instance.getId());
		if (instance.getName() != null) detachedUser.setName(instance.getName());
		return ((User) getJdoTemplate().save(detachedUser)).getId();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().delete(User.class, id);
	}

}
