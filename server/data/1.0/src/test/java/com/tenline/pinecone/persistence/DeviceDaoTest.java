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
import com.tenline.pinecone.persistence.impl.DeviceDaoImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class DeviceDaoTest extends AbstractDaoTest {
	
	private Device device;
	
	private List devices;
	
	private DeviceDaoImpl deviceDao;

	@Before
	public void testSetup() {
		deviceDao = new DeviceDaoImpl();
		deviceDao.setJdoTemplate(jdoTemplate);
		device = new Device();
		device.setId("asa");
		device.setName("ACU");
		devices = new ArrayList();
		devices.add(device);
	}
	
	@After
	public void testShutdown() {
		deviceDao = null;
		devices.remove(device);
		device = null;
		devices = null;
	}
	
	@Test
	public void testSave() {
		when(jdoTemplate.save(device)).thenReturn(device);
		String result = deviceDao.save(device);
		verify(jdoTemplate).save(device);
		assertEquals("asa", result);
	}
	
	@Test
	public void testDelete() {
		doAnswer(new Answer<Object>() {
	        public Object answer(InvocationOnMock invocation) {
	            Object[] args = invocation.getArguments();
	            assertEquals(args[0], Device.class);
	            assertEquals(args[1], device.getId());
	            return args;
	        }
	    }).when(jdoTemplate).delete(Device.class, device.getId());
		deviceDao.delete(device.getId());
		verify(jdoTemplate).delete(Device.class, device.getId());
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.getDetachedObject(Device.class, device.getId())).thenReturn(device);
		when(jdoTemplate.save(device)).thenReturn(device);
		String deviceId = deviceDao.update(device);
		verify(jdoTemplate).save(device);
		verify(jdoTemplate).getDetachedObject(Device.class, device.getId());
		assertNotNull(deviceId);
	}
	
	@Test
	public void testFind() {
		when(jdoTemplate.find(Device.class, device.getId())).thenReturn(device);
		Device result = deviceDao.find(device.getId());
		verify(jdoTemplate).find(Device.class, device.getId());
		assertEquals("ACU", result.getName());
	}
	
	@Test
	public void testFindAll() {
		when(jdoTemplate.findAll(Device.class, null)).thenReturn(devices);
		Collection<Device> result = deviceDao.findAll();
		verify(jdoTemplate).findAll(Device.class, null);
		assertEquals(1, result.size());
	}
	
	@Test
	public void testFindAllByFilter() {
		String filter = "name=='ACU'";
		when(jdoTemplate.findAll(Device.class, filter)).thenReturn(devices);
		Collection<Device> result = deviceDao.findAll(filter);
		verify(jdoTemplate).findAll(Device.class, filter);
		assertEquals(1, result.size());
	}

}
