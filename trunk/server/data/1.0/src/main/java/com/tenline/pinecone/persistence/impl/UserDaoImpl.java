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
		return ((User) getJdoTemplate().save(newInstance)).getPrimaryKey();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#find(java.lang.String)
	 */
	@Override
	public User find(String primaryKey) {
		// TODO Auto-generated method stub
		return (User) getJdoTemplate().find(User.class, primaryKey);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<User> findAll() {
		// TODO Auto-generated method stub
		return (Collection<User>) getJdoTemplate().findAll(User.class);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#findAllByFilter(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<User> findAllByFilter(String filter) {
		// TODO Auto-generated method stub
		return (Collection<User>) getJdoTemplate().findAllByFilter(User.class, filter);
	}

}
