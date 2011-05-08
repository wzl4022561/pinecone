/**
 * 
 */
package com.tenline.pinecone.rest;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.rest.impl.UserServiceImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@RunWith(MockitoJUnitRunner.class)  
public class UserServiceTest {

	private User user;
	
	private List users;
	
	private UserServiceImpl userService; 	
	
	@Mock
	private UserDao userDao;
		
	@Before
	public void testSetup() {
		userService = new UserServiceImpl(userDao);
		user = new User();
		user.setId("asa");
		user.setName("bill");		
		users = new ArrayList();
		users.add(user);
	}
	
	@After
	public void testShutdown() {	
		userService = null;
		userDao = null;
		users.remove(user);
		user = null;
		users = null;
	}

	@Test
	public void testCreate() {
		Response result = userService.create(user);
		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);  
		verify(userDao).save(argument.capture()); 
		assertEquals("bill", argument.getValue().getName());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testDelete() {
		Response result = userService.delete(user.getId());
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);  
		verify(userDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testUpdate() {
		Response result = userService.update(user);
		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);  
		verify(userDao).update(argument.capture());
		assertEquals("bill", argument.getValue().getName());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testShow() {
		when(userDao.find(user.getId())).thenReturn(user);
		User result = userService.show(user.getId());
		verify(userDao).find(user.getId());
		assertEquals("bill", result.getName());
	}
	
	@Test 
	public void testShowAll() {
		when(userDao.findAll()).thenReturn(users);
		Collection<User> result = userService.showAll();
		verify(userDao).findAll();
		assertEquals(1, result.size());
	}
	
	@Test
	public void testShowAllByFilter() {
		String filter = "name=='bill'";
		when(userDao.findAll(filter)).thenReturn(users);
		Collection<User> result = userService.showAll(filter);
		verify(userDao).findAll(filter);
		assertEquals(1, result.size());
	}

}
