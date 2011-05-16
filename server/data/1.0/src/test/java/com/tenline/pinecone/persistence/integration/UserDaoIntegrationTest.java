/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.UserDao;

/**
 * @author Bill
 *
 */
public class UserDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DeviceDao deviceDao;
		
	@Test
	public void testCURD() {
		User newUser = new User();
		newUser.setSnsId("23");
		newUser.setDevices(new ArrayList<Device>());
		Device newDevice = new Device();
		newDevice.setName("ACU");
		newDevice.setType("serial");
		newUser.getDevices().add(newDevice);
		User user = userDao.save(newUser);
		assertEquals("23", ((User) userDao.find("id=='"+user.getId()+"'").toArray()[0]).getSnsId());
		assertEquals(1, ((User) userDao.find("id=='"+user.getId()+"'").toArray()[0]).getDevices().size());
		assertEquals(1, deviceDao.find("all").size());
		assertEquals("23", ((Device) deviceDao.find("all").toArray()[0]).getUser().getSnsId());
		user.setSnsId("24");
		user = userDao.update(user);
		assertEquals("24", ((User) userDao.find("id=='"+user.getId()+"'").toArray()[0]).getSnsId());
		assertEquals("24", ((Device) deviceDao.find("all").toArray()[0]).getUser().getSnsId());
		userDao.delete(user.getId());
		assertEquals(0, userDao.find("id=='"+user.getId()+"'").size());
		assertEquals(0, deviceDao.find("all").size());
	}
	
}