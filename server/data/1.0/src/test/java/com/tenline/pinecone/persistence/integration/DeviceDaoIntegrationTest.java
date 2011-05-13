/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.ProtocolDao;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.persistence.VariableDao;

/**
 * @author Bill
 *
 */
public class DeviceDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private ProtocolDao protocolDao;
	
	@Autowired
	private VariableDao variableDao;
	
	@Test
	public void testCURD() {
		User newUser = new User();
		newUser.setSnsId("23");
		String userId = userDao.save(newUser);
		Device newDevice = new Device();
		newDevice.setName("ACU");
		newDevice.setType("serial");
		User user = new User();
		user.setId(userId);
		newDevice.setUser(user);
		Protocol newProtocol = new Protocol();
		newProtocol.setName("modbus");
		newProtocol.setVersion("1.0");
		newDevice.setProtocols(new ArrayList<Protocol>());
		newDevice.getProtocols().add(newProtocol);
		Variable newVariable = new Variable();
		newVariable.setName("STA");
		newVariable.setType("READ_ONLY");	
		newDevice.setVariables(new ArrayList<Variable>());
		newDevice.getVariables().add(newVariable);
		String deviceId = deviceDao.save(newDevice);
		assertEquals("ACU", ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getName());
		assertEquals(1, ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getProtocols().size());
		assertEquals(1, ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getVariables().size());
		assertEquals(1, variableDao.find("all").size());
		assertEquals("ACU", ((Variable) variableDao.find("all").toArray()[0]).getDevice().getName());
		assertEquals(1, protocolDao.find("all").size());
		Device device = new Device();
		device.setId(deviceId);
		device.setName("LNB");
		device.setType("tcp");
		deviceId = deviceDao.update(device);
		assertEquals("LNB", ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getName());
		assertEquals("tcp", ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getType());
		assertEquals("LNB", ((Variable) variableDao.find("all").toArray()[0]).getDevice().getName());
		deviceDao.delete(deviceId);
		assertEquals(0, deviceDao.find("id=='"+deviceId+"'").size());
		assertEquals(0, variableDao.find("all").size());
		assertEquals(0, protocolDao.find("all").size());
	}

}
