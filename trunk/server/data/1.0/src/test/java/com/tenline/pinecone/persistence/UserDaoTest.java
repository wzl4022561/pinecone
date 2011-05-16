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
import com.tenline.pinecone.persistence.jdo.UserJdoDao;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"}) 
public class UserDaoTest extends AbstractDaoTest {
		
	private User user;
	
	private List users;
	
	private UserJdoDao userDao; 	
		
	@Before
	public void testSetup() {
		userDao = new UserJdoDao(persistenceManagerFactory);
		userDao.setJdoTemplate(jdoTemplate);
		user = new User();
		user.setId("asa");
		user.setSnsId("12");		
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
		when(jdoTemplate.makePersistent(user)).thenReturn(user);
		User result = userDao.save(user);
		verify(jdoTemplate).makePersistent(user);
		assertEquals("asa", result.getId());
	}
	
	@Test
	public void testDelete() {
		doAnswer(new Answer<Object>() {
	        public Object answer(InvocationOnMock invocation) {
	            Object[] args = invocation.getArguments();
	            assertNotNull(args[0]);
	            return args;
	        }
	    }).when(jdoTemplate).deletePersistent(user);
		when(jdoTemplate.getObjectById(User.class, user.getId())).thenReturn(user);
		userDao.delete(user.getId());
		verify(jdoTemplate).getObjectById(User.class, user.getId());
		verify(jdoTemplate).deletePersistent(user);
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.getObjectById(User.class, user.getId())).thenReturn(user);
		when(jdoTemplate.makePersistent(user)).thenReturn(user);
		User result = userDao.update(user);
		verify(jdoTemplate).makePersistent(user);
		verify(jdoTemplate).getObjectById(User.class, user.getId());
		assertEquals("asa", result.getId());
	}
	
	@Test
	public void testFind() {
		String filter = "id=='asa'";
		String queryString = "select from " + User.class.getName() + " where " + filter;
		when(jdoTemplate.find(queryString)).thenReturn(users);
		Collection<User> result = userDao.find(filter);
		verify(jdoTemplate).find(queryString);
		assertEquals(1, result.size());
	}

}
