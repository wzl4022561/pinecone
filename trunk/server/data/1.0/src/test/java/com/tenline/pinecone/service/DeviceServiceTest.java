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

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.service.restful.DeviceRestfulService;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@RunWith(MockitoJUnitRunner.class) 
public class DeviceServiceTest {

	private Device device;
	
	private List devices;
	
	private DeviceRestfulService deviceService;
	
	@Mock
	private DeviceDao deviceDao;
	
	@Before
	public void testSetup() {
		deviceService = new DeviceRestfulService(deviceDao);
		device = new Device();
		device.setId("asa");
		device.setName("ACU");
		devices = new ArrayList();
		devices.add(device);
	}
	
	@After
	public void testShutdown() {	
		deviceService = null;
		deviceDao = null;
		devices.remove(device);
		device = null;
		devices = null;
	}

	@Test
	public void testCreate() {
		ArgumentCaptor<Device> argument = ArgumentCaptor.forClass(Device.class);  
		when(deviceDao.save(device)).thenReturn(device);
		Device result = deviceService.create(device);
		verify(deviceDao).save(argument.capture()); 
		verify(deviceDao).save(device);
		assertEquals("ACU", argument.getValue().getName());
		assertEquals("ACU", result.getName());
	}
	
	@Test
	public void testDelete() {
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class); 
		deviceService.delete(device.getId());
		verify(deviceDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
	}
	
	@Test
	public void testUpdate() {
		ArgumentCaptor<Device> argument = ArgumentCaptor.forClass(Device.class); 
		when(deviceDao.update(device)).thenReturn(device);
		Device result = deviceService.update(device);
		verify(deviceDao).update(argument.capture());
		verify(deviceDao).update(device);
		assertEquals("ACU", argument.getValue().getName());
		assertEquals("ACU", result.getName());
	}
	
	@Test
	public void testShow() {
		String filter = "name=='ACU'";
		when(deviceDao.find(filter)).thenReturn(devices);
		Collection<Device> result = deviceService.show(filter);
		verify(deviceDao).find(filter);
		assertEquals(1, result.size());
	}

}
