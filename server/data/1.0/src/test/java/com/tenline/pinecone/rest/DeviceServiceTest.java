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

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.rest.impl.DeviceServiceImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@RunWith(MockitoJUnitRunner.class) 
public class DeviceServiceTest {

	private Device device;
	
	private List devices;
	
	private DeviceServiceImpl deviceService;
	
	@Mock
	private DeviceDao deviceDao;
	
	@Before
	public void testSetup() {
		deviceService = new DeviceServiceImpl(deviceDao);
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
		Response result = deviceService.create(device);
		ArgumentCaptor<Device> argument = ArgumentCaptor.forClass(Device.class);  
		verify(deviceDao).save(argument.capture()); 
		assertEquals("ACU", argument.getValue().getName());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testDelete() {
		Response result = deviceService.delete(device.getId());
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);  
		verify(deviceDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testUpdate() {
		Response result = deviceService.update(device);
		ArgumentCaptor<Device> argument = ArgumentCaptor.forClass(Device.class);  
		verify(deviceDao).update(argument.capture());
		assertEquals("ACU", argument.getValue().getName());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testShow() {
		when(deviceDao.find(device.getId())).thenReturn(device);
		Device result = deviceService.show(device.getId());
		verify(deviceDao).find(device.getId());
		assertEquals("ACU", result.getName());
	}
	
	@Test 
	public void testShowAll() {
		when(deviceDao.findAll()).thenReturn(devices);
		Collection<Device> result = deviceService.showAll();
		verify(deviceDao).findAll();
		assertEquals(1, result.size());
	}
	
	@Test
	public void testShowAllByFilter() {
		String filter = "name=='ACU'";
		when(deviceDao.findAll(filter)).thenReturn(devices);
		Collection<Device> result = deviceService.showAll(filter);
		verify(deviceDao).findAll(filter);
		assertEquals(1, result.size());
	}

}
