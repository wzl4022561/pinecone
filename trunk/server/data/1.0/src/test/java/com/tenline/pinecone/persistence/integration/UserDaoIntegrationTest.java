/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.persistence.impl.UserDaoImpl;

/**
 * @author Bill
 *
 */
public class UserDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	private UserDao userDao;
	
	@Before
	public void testSetup() {
		super.testSetup();
		userDao = new UserDaoImpl();
	}
	
	@After
	public void testShutdown() {
		super.testShutdown();
		userDao = null;
	}
		
	@Test
	public void testSaveAndFind() {
		User newUser = new User();
		newUser.setName("bill");	
		String userId = userDao.save(newUser);
		assertNotNull(userId);	
		User user = userDao.find(userId);
		assertEquals("bill", user.getName());
	}
	
	@Test
	public void testFindAll() {
		assertNotNull(userDao.findAll());
	}
	
	@Test
	public void testFindAllByFilter() {
		assertNotNull(userDao.findAll("name=='bill'"));
	}
	
}

