/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.ProtocolDao;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.persistence.impl.DeviceDaoImpl;
import com.tenline.pinecone.persistence.impl.ProtocolDaoImpl;
import com.tenline.pinecone.persistence.impl.UserDaoImpl;

/**
 * @author Bill
 *
 */
public class ProtocolDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	private UserDao userDao;
	
	private DeviceDao deviceDao;
	
	private ProtocolDao protocolDao;

	@Before
	public void testSetup() {
		super.testSetup();
		userDao = new UserDaoImpl();
		deviceDao = new DeviceDaoImpl();
		protocolDao = new ProtocolDaoImpl();
	}
	
	@After
	public void testShutdown() {
		super.testShutdown();
		userDao = null;
		deviceDao = null;
		protocolDao = null;
	}
	
	@Test
	public void testCURD() {
		User newUser = new User();
		newUser.setSnsId("23");
		String userId = userDao.save(newUser);		
		Device newDevice = new Device();
		newDevice.setName("ACU");
		User user = new User();
		user.setId(userId);
		newDevice.setUser(user);
		String deviceId = deviceDao.save(newDevice);
		Protocol newProtocol = new Protocol();
		newProtocol.setName("modbus");
		newProtocol.setVersion("1.0");
		Device device = new Device();
		device.setId(deviceId);
		newProtocol.setDevice(device);
		String protocolId = protocolDao.save(newProtocol);
		assertEquals("modbus", ((Protocol) protocolDao.find("id=='"+protocolId+"'").toArray()[0]).getName());
		assertEquals("ACU", ((Protocol) protocolDao.find("id=='"+protocolId+"'").toArray()[0]).getDevice().getName());
		Protocol protocol = new Protocol();
		protocol.setId(protocolId);
		protocol.setName("SNMP");
		protocol.setVersion("2.0");
		protocolId = protocolDao.update(protocol);
		assertEquals("SNMP", ((Protocol) protocolDao.find("id=='"+protocolId+"'").toArray()[0]).getName());
		protocolDao.delete(protocolId);
		assertEquals(0, protocolDao.find("id=='"+protocolId+"'").size());
	}

}
