/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.Record;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.RecordDao;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.persistence.VariableDao;
import com.tenline.pinecone.persistence.impl.DeviceDaoImpl;
import com.tenline.pinecone.persistence.impl.RecordDaoImpl;
import com.tenline.pinecone.persistence.impl.UserDaoImpl;
import com.tenline.pinecone.persistence.impl.VariableDaoImpl;

/**
 * @author Bill
 *
 */
public class RecordDaoIntegrationTest extends AbstractDaoIntegrationTest {

	private UserDao userDao;
	
	private DeviceDao deviceDao;
	
	private VariableDao variableDao;
	
	private RecordDao recordDao;

	@Before
	public void testSetup() {
		super.testSetup();
		userDao = new UserDaoImpl();
		deviceDao = new DeviceDaoImpl();
		variableDao = new VariableDaoImpl();
		recordDao = new RecordDaoImpl();
	}
	
	@After
	public void testShutdown() {
		super.testShutdown();
		userDao = null;
		deviceDao = null;
		variableDao = null;
		recordDao = null;
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
		Variable newVariable = new Variable();
		newVariable.setName("STA");
		newVariable.setType("READ_ONLY");
		Device device = new Device();
		device.setId(deviceId);
		newVariable.setDevice(device);
		String variableId = variableDao.save(newVariable);
		Record newRecord = new Record();
		newRecord.setValue("1");
		Variable variable = new Variable();
		variable.setId(variableId);
		newRecord.setVariable(variable);
		String recordId = recordDao.save(newRecord);
		assertEquals("1", ((Record) recordDao.find("id=='"+recordId+"'").toArray()[0]).getValue());
		assertEquals("STA", ((Record) recordDao.find("id=='"+recordId+"'").toArray()[0]).getVariable().getName());
		assertEquals(1, ((Record) recordDao.find("id=='"+recordId+"'").toArray()[0]).getVariable().getRecords().size());
		Record record = new Record();
		record.setId(recordId);
		record.setValue("2");
		recordId = recordDao.update(record);
//		assertEquals("2", ((Record) recordDao.find("id=='"+recordId+"'").toArray()[0]).getValue());
		recordDao.delete(recordId);
		assertEquals(0, recordDao.find("id=='"+recordId+"'").size());
	}

}
