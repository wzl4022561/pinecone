/**
 * 
 */
package com.tenline.pinecone.rest.impl;

import java.util.Collection;

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
	public User show(String id) {
		// TODO Auto-generated method stub
		return userDao.find(id);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.UserService#showAll()
	 */
	@Override
	public Collection<User> showAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.UserService#showAllByFilter(java.lang.String)
	 */
	@Override
	public Collection<User> showAllByFilter(String filter) {
		// TODO Auto-generated method stub
		return userDao.findAllByFilter(filter);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.UserService#update(com.tenline.pinecone.model.User)
	 */
	@Override
	public Response update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.rest.AbstractService#delete(java.lang.String)
	 */
	@Override
	public Response delete(String id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
		return Response.status(Status.OK).build();
	}

}
