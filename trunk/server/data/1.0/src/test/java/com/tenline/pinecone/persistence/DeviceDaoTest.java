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

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.impl.DeviceDaoImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class DeviceDaoTest extends AbstractDaoTest {
	
	private Device device;
	
	private User user;
	
	private List devices;
	
	private DeviceDaoImpl deviceDao;

	@Before
	public void testSetup() {
		deviceDao = new DeviceDaoImpl(persistenceManagerFactory);
		deviceDao.setJdoTemplate(jdoTemplate);
		device = new Device();
		device.setId("asa");
		device.setName("ACU");
		user = new User();
		user.setId("ddd");
		device.setUser(user);
		devices = new ArrayList();
		devices.add(device);
	}
	
	@After
	public void testShutdown() {
		deviceDao = null;
		devices.remove(device);
		device = null;
		devices = null;
		user = null;
	}
	
	@Test
	public void testSave() {
		when(jdoTemplate.makePersistent(device)).thenReturn(device);
		when(jdoTemplate.getObjectById(User.class, user.getId())).thenReturn(user);
		String result = deviceDao.save(device);
		verify(jdoTemplate).getObjectById(User.class, user.getId());
		verify(jdoTemplate).makePersistent(device);
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
	    }).when(jdoTemplate).deletePersistent(device);
		when(jdoTemplate.getObjectById(Device.class, device.getId())).thenReturn(device);
		deviceDao.delete(device.getId());
		verify(jdoTemplate).getObjectById(Device.class, device.getId());
		verify(jdoTemplate).deletePersistent(device);
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.getObjectById(Device.class, device.getId())).thenReturn(device);
		when(jdoTemplate.makePersistent(device)).thenReturn(device);
		String deviceId = deviceDao.update(device);
		verify(jdoTemplate).makePersistent(device);
		verify(jdoTemplate).getObjectById(Device.class, device.getId());
		assertNotNull(deviceId);
	}
	
	@Test
	public void testFind() {
		String filter = "name=='ACU'";
		String queryString = "select from " + Device.class.getName() + " where " + filter;
		when(jdoTemplate.find(queryString)).thenReturn(devices);
		Collection<Device> result = deviceDao.find(filter);
		verify(jdoTemplate).find(queryString);
		assertEquals(1, result.size());
	}

}
