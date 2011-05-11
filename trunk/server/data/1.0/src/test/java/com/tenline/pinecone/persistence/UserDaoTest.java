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
		when(jdoTemplate.persist(user)).thenReturn(user);
		String result = userDao.save(user);
		verify(jdoTemplate).persist(user);
		assertEquals("asa", result);
	}
	
	@Test
	public void testDelete() {
		doAnswer(new Answer<Object>() {
	        public Object answer(InvocationOnMock invocation) {
	            Object[] args = invocation.getArguments();
	            assertNotNull(args[0]);
	            return args;
	        }
	    }).when(jdoTemplate).delete(user);
		when(jdoTemplate.find(User.class, user.getId())).thenReturn(user);
		userDao.delete(user.getId());
		verify(jdoTemplate).find(User.class, user.getId());
		verify(jdoTemplate).delete(user);
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.find(User.class, user.getId())).thenReturn(user);
		when(jdoTemplate.persist(user)).thenReturn(user);
		String userId = userDao.update(user);
		verify(jdoTemplate).persist(user);
		verify(jdoTemplate).find(User.class, user.getId());
		assertNotNull(userId);
	}
	
	@Test
	public void testFind() {
		String filter = "id=='asa'";
		String queryString = "select from " + User.class.getName() + " where " + filter;
		when(jdoTemplate.get(queryString)).thenReturn(users);
		Collection<User> result = userDao.find(filter);
		verify(jdoTemplate).get(queryString);
		assertEquals(1, result.size());
	}

}
