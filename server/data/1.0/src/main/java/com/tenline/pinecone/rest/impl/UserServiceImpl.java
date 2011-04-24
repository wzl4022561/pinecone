/**
 * 
 */
package com.tenline.pinecone.rest.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.rest.UserService;

/**
 * @author Bill
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	/**
	 * 
	 */
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		// TODO Auto-generated constructor stub
		this.userDao = userDao;
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.UserService#create(com.tenline.pinecone.model.User)
	 */
	@Override
	public Response create(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.UserService#show(java.lang.String)
	 */
	@Override
	public User show(String primaryKey) {
		// TODO Auto-generated method stub
		return userDao.find(primaryKey);
	}

}
