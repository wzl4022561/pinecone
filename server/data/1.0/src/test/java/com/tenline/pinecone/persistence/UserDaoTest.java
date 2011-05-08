/**
 * 
 */
package com.tenline.pinecone.persistence;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
	public void testDelete() {
		doAnswer(new Answer<Object>() {
	        public Object answer(InvocationOnMock invocation) {
	            Object[] args = invocation.getArguments();
	            assertEquals(args[0], User.class);
	            assertEquals(args[1], user.getId());
	            return args;
	        }
	    }).when(jdoTemplate).delete(User.class, user.getId());
		userDao.delete(user.getId());
		verify(jdoTemplate).delete(User.class, user.getId());
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.getDetachedObject(User.class, user.getId())).thenReturn(user);
		when(jdoTemplate.save(user)).thenReturn(user);
		String userId = userDao.update(user);
		verify(jdoTemplate).save(user);
		verify(jdoTemplate).getDetachedObject(User.class, user.getId());
		assertNotNull(userId);
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
		when(jdoTemplate.findAll(User.class, null)).thenReturn(users);
		Collection<User> result = userDao.findAll();
		verify(jdoTemplate).findAll(User.class, null);
		assertEquals(1, result.size());
	}
	
	@Test
	public void testFindAllByFilter() {
		String filter = "name=='bill'";
		when(jdoTemplate.findAll(User.class, filter)).thenReturn(users);
		Collection<User> result = userDao.findAll(filter);
		verify(jdoTemplate).findAll(User.class, filter);
		assertEquals(1, result.size());
	}

}
