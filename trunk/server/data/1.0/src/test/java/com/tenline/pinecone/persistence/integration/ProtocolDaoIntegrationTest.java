/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.ProtocolDao;
import com.tenline.pinecone.persistence.UserDao;

/**
 * @author Bill
 *
 */
public class ProtocolDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private ProtocolDao protocolDao;
	
	@Test
	public void testCURD() {
		User newUser = new User();
		newUser.setSnsId("23");
		User user = userDao.save(newUser);		
		Device newDevice = new Device();
		newDevice.setName("ACU");
		newDevice.setUser(user);
		Device device = deviceDao.save(newDevice);
		Protocol newProtocol = new Protocol();
		newProtocol.setName("modbus");
		newProtocol.setVersion("1.0");
		newProtocol.setDevice(device);
		Protocol protocol = protocolDao.save(newProtocol);
		assertEquals("modbus", ((Protocol) protocolDao.find("id=='"+protocol.getId()+"'").toArray()[0]).getName());
		assertEquals("ACU", ((Protocol) protocolDao.find("id=='"+protocol.getId()+"'").toArray()[0]).getDevice().getName());
		protocol.setName("SNMP");
		protocol.setVersion("2.0");
		protocol = protocolDao.update(protocol);
		assertEquals("SNMP", ((Protocol) protocolDao.find("id=='"+protocol.getId()+"'").toArray()[0]).getName());
		assertEquals("2.0", ((Protocol) protocolDao.find("id=='"+protocol.getId()+"'").toArray()[0]).getVersion());
		protocolDao.delete(protocol.getId());
		assertEquals(0, protocolDao.find("id=='"+protocol.getId()+"'").size());
	}

}
