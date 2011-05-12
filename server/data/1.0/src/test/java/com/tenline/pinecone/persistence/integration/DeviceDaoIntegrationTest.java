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
import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.ProtocolDao;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.persistence.VariableDao;
import com.tenline.pinecone.persistence.impl.DeviceDaoImpl;
import com.tenline.pinecone.persistence.impl.ProtocolDaoImpl;
import com.tenline.pinecone.persistence.impl.UserDaoImpl;
import com.tenline.pinecone.persistence.impl.VariableDaoImpl;

/**
 * @author Bill
 *
 */
public class DeviceDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	private UserDao userDao;

	private DeviceDao deviceDao;
	
	private ProtocolDao protocolDao;
	
	private VariableDao variableDao;
	
	@Before
	public void testSetup() {
		super.testSetup();
		userDao = new UserDaoImpl();
		deviceDao = new DeviceDaoImpl();
		protocolDao = new ProtocolDaoImpl();
		variableDao = new VariableDaoImpl();
	}
	
	@After
	public void testShutdown() {
		super.testShutdown();
		userDao = null;
		deviceDao = null;
		protocolDao = null;
		variableDao = null;
	}
	
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
//		Protocol newProtocol = new Protocol();
//		newProtocol.setName("modbus");
//		newProtocol.setVersion("1.0");
//		newDevice.setProtocol(newProtocol);
		Variable newVariable = new Variable();
		newVariable.setName("STA");
		newVariable.setType("READ_ONLY");	
		newDevice.setVariables(new ArrayList<Variable>());
		newDevice.getVariables().add(newVariable);
		String deviceId = deviceDao.save(newDevice);
		assertEquals("ACU", ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getName());
//		assertEquals("modbus", ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getProtocol().getName());
		assertEquals(1, ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getVariables().size());
		assertEquals(1, variableDao.find("all").size());
		assertEquals("ACU", ((Variable) variableDao.find("all").toArray()[0]).getDevice().getName());
//		assertEquals(1, protocolDao.find("all").size());
		Device device = new Device();
		device.setId(deviceId);
		device.setName("LNB");
		Protocol protocol = new Protocol();
		protocol.setName("SNMP");
		protocol.setVersion("2.0");
		device.setProtocol(protocol);
		Variable variable = new Variable();
		variable.setName("MUT");
		variable.setType("WRITE_ONLY");
		device.setVariables(new ArrayList<Variable>());
		device.getVariables().add(variable);
		deviceId = deviceDao.update(device);
		assertEquals("LNB", ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getName());
//		assertEquals("SNMP", ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getProtocol().getName());
//		assertEquals(2, ((Device) deviceDao.find("id=='"+deviceId+"'").toArray()[0]).getVariables().size());
//		assertEquals(2, variableDao.find("all").size());
		assertEquals("LNB", ((Variable) variableDao.find("all").toArray()[0]).getDevice().getName());
		deviceDao.delete(deviceId);
		assertEquals(0, deviceDao.find("id=='"+deviceId+"'").size());
		assertEquals(0, variableDao.find("all").size());
		assertEquals(0, protocolDao.find("all").size());
	}

}
