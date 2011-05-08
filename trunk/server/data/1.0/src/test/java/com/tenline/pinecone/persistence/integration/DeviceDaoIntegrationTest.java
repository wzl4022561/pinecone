/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;

import java.util.Collection;

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
	public void testCURD() {
		Device newDevice = new Device();
		newDevice.setName("ACU");
		String deviceId = deviceDao.save(newDevice);
		assertNotNull(deviceId);	
		Collection<Device> devices = deviceDao.find("id=='"+deviceId+"'");
		assertEquals(1, devices.size());
		Device device = (Device) devices.toArray()[0];
		device.setName("LNB");
		deviceId = deviceDao.update(device);
		devices = deviceDao.find("id=='"+deviceId+"'");
		assertEquals("LNB", ((Device) devices.toArray()[0]).getName());
//		deviceDao.delete(deviceId);
//		assertNull(deviceDao.find("id=='"+deviceId+"'"));
	}

}
