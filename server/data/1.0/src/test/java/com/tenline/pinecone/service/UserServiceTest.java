/**
 * 
 */
package com.tenline.pinecone.service;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.service.restful.UserRestfulService;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@RunWith(MockitoJUnitRunner.class)  
public class UserServiceTest {

	private User user;
	
	private List users;
	
	private UserRestfulService userService; 	
	
	@Mock
	private UserDao userDao;
		
	@Before
	public void testSetup() {
		userService = new UserRestfulService(userDao);
		user = new User();
		user.setId("asa");
		user.setSnsId("23");		
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
		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);  
		when(userDao.save(user)).thenReturn(user);
		User result = userService.create(user);
		verify(userDao).save(argument.capture()); 
		verify(userDao).save(user);
		assertEquals("23", argument.getValue().getSnsId());
		assertEquals("23", result.getSnsId());
	}
	
	@Test
	public void testDelete() {
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);  
		userService.delete(user.getId());
		verify(userDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
	}
	
	@Test
	public void testUpdate() {
		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);  
		when(userDao.update(user)).thenReturn(user);
		User result = userService.update(user);
		verify(userDao).update(argument.capture());
		verify(userDao).update(user);
		assertEquals("23", argument.getValue().getSnsId());
		assertEquals("23", result.getSnsId());
	}
	
	@Test
	public void testShow() {
		String filter = "id=='asa'";
		when(userDao.find(filter)).thenReturn(users);
		Collection<User> result = userService.show(filter);
		verify(userDao).find(filter);
		assertEquals(1, result.size());
	}

}
