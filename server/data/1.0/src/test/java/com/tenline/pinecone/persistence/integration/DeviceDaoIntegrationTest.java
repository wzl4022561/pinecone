/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.impl.DeviceDaoImpl;

/**
 * @author Bill
 *
 */
public class DeviceDaoIntegrationTest extends AbstractDaoIntegrationTest {

	private DeviceDao deviceDao;
	
	@Before
	public void testSetup() {
		super.testSetup();
		deviceDao = new DeviceDaoImpl();
	}
	
	@After
	public void testShutdown() {
		super.testShutdown();
		deviceDao = null;
	}
	
	@Test
	public void testSaveAndFind() {
		Device newDevice = new Device();
		newDevice.setName("ACU");
		String deviceId = deviceDao.save(newDevice);
		assertNotNull(deviceId);	
		Device device = deviceDao.find(deviceId);
		assertEquals("ACU", device.getName());
	}
	
	@Test
	public void testFindAll() {
		assertNotNull(deviceDao.findAll());
	}
	
	@Test
	public void testFindAllByFilter() {
		assertNotNull(deviceDao.findAll("name=='ACU'"));
	}

}
