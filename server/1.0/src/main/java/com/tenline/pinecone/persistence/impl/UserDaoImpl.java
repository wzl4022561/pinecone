/**
 * 
 */
package com.tenline.pinecone.persistence.impl;

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
	
	public Long save(User newInstance) {
		// TODO Auto-generated method stub
		User user = (User) getJdoTemplate().save(newInstance);
		return user.getId();
	}
	
	public User find(Long primaryKey) {
		// TODO Auto-generated method stub
		return (User) getJdoTemplate().find(User.class, primaryKey);
	}

}
