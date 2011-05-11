/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.persistence.impl.DeviceDaoImpl;
import com.tenline.pinecone.persistence.impl.UserDaoImpl;

/**
 * @author Bill
 *
 */
public class UserDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	private UserDao userDao;
	
	private DeviceDao deviceDao;
	
	@Before
	public void testSetup() {
		super.testSetup();
		userDao = new UserDaoImpl();
		deviceDao = new DeviceDaoImpl();
	}
	
	@After
	public void testShutdown() {
		super.testShutdown();
		userDao = null;
		deviceDao = null;
	}
		
	@Test
	public void testCURD() {
		User newUser = new User();
		newUser.setSnsId("23");
		newUser.setDevices(new ArrayList<Device>());
		Device newDevice = new Device();
		newDevice.setName("ACU");
		newDevice.setType("serial");
		newUser.getDevices().add(newDevice);
		String userId = userDao.save(newUser);
		assertEquals("23", ((User) userDao.find("id=='"+userId+"'").toArray()[0]).getSnsId());
		assertEquals(1, ((User) userDao.find("id=='"+userId+"'").toArray()[0]).getDevices().size());
		assertEquals(1, deviceDao.find("all").size());
		assertEquals("23", ((Device) deviceDao.find("all").toArray()[0]).getUser().getSnsId());
		User user = new User();
		user.setId(userId);
		user.setSnsId("24");
		Device device = new Device();
		device.setName("LNB");
		device.setType("tcp");
		user.setDevices(new ArrayList<Device>());
		user.getDevices().add(device);
		userId = userDao.update(user);
		assertEquals("24", ((User) userDao.find("id=='"+userId+"'").toArray()[0]).getSnsId());
		assertEquals(2, ((User) userDao.find("id=='"+userId+"'").toArray()[0]).getDevices().size());
		assertEquals(2, deviceDao.find("all").size());
		assertEquals("24", ((Device) deviceDao.find("all").toArray()[0]).getUser().getSnsId());
		userDao.delete(userId);
		assertEquals(0, userDao.find("id=='"+userId+"'").size());
		assertEquals(0, deviceDao.find("all").size());
	}
	
}