/**
 * 
 */
package com.tenline.pinecone.persistence;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.impl.UserDaoImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"}) 
public class UserDaoTest extends AbstractDaoTest {
		
	private User user;
	
	private List users;
	
	private UserDaoImpl userDao; 	
		
	@Before
	public void testSetup() {
		userDao = new UserDaoImpl();
		userDao.setJdoTemplate(jdoTemplate);
		user = new User();
		user.setId("asa");
		user.setName("bill");		
		users = new ArrayList();
		users.add(user);
	}
	
	@After
	public void testShutdown() {	
		userDao = null;
		users.remove(user);
		user = null;
		users = null;
	}

	@Test
	public void testSave() {
		when(jdoTemplate.save(user)).thenReturn(user);
		String result = userDao.save(user);
		verify(jdoTemplate).save(user);
		assertEquals("asa", result);
	}
	
	@Test
	public void testFind() {
		when(jdoTemplate.find(User.class, user.getId())).thenReturn(user);
		User result = userDao.find(user.getId());
		verify(jdoTemplate).find(User.class, user.getId());
		assertEquals("bill", result.getName());
	}
	
	@Test
	public void testFindAll() {
		when(jdoTemplate.findAll(User.class)).thenReturn(users);
		Collection<User> result = userDao.findAll();
		verify(jdoTemplate).findAll(User.class);
		assertEquals(1, result.size());
	}
	
	@Test
	public void testFindAllByFilter() {
		String filter = "name=='bill'";
		when(jdoTemplate.findAllByFilter(User.class, filter)).thenReturn(users);
		Collection<User> result = userDao.findAllByFilter(filter);
		verify(jdoTemplate).findAllByFilter(User.class, filter);
		assertEquals(1, result.size());
	}

}
