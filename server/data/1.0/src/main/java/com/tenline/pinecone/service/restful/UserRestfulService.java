/**
 * 
 */
package com.tenline.pinecone.service.restful;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.service.UserService;

/**
 * @author Bill
 *
 */
@Service
public class UserRestfulService implements UserService {
	
	private UserDao userDao;

	/**
	 * 
	 */
	@Autowired
	public UserRestfulService(UserDao userDao) {
		// TODO Auto-generated constructor stub
		this.userDao = userDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.UserService#create(com.tenline.pinecone.model.User)
	 */
	@Override
	public User create(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.UserService#show(java.lang.String)
	 */
	@Override
	public Collection<User> show(String filter) {
		// TODO Auto-generated method stub
		return userDao.find(filter);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.UserService#update(com.tenline.pinecone.model.User)
	 */
	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.service.AbstractService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

}
