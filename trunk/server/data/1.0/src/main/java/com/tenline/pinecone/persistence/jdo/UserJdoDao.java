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

import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.UserDao;

/**
 * @author Bill
 *
 */
@Repository
@Transactional
public class UserJdoDao extends JdoDaoSupport implements UserDao {

	/**
	 * 
	 */
	@Autowired
	public UserJdoDao(PersistenceManagerFactory persistenceManagerFactory) {
		// TODO Auto-generated constructor stub
		setPersistenceManagerFactory(persistenceManagerFactory);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#save(com.tenline.pinecone.model.User)
	 */
	@Override
	public User save(User newInstance) {
		// TODO Auto-generated method stub
		return (User) getJdoTemplate().makePersistent(newInstance);
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
		return (Collection<User>) getJdoTemplate().find(queryString);
	}

	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#update(com.tenline.pinecone.model.User)
	 */
	@Override
	public User update(User instance) {
		// TODO Auto-generated method stub
		User detachedUser = (User) getJdoTemplate().getObjectById(User.class, instance.getId());
		if (instance.getSnsId() != null) detachedUser.setSnsId(instance.getSnsId());
		return (User) getJdoTemplate().makePersistent(detachedUser);
	}
	
	/* (non-Javadoc)
	 * @see com.tenline.pinecone.persistence.UserDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getJdoTemplate().deletePersistent(getJdoTemplate().getObjectById(User.class, id));
	}

}
