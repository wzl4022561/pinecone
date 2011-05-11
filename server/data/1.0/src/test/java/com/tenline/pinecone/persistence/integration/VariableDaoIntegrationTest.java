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
import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.model.Record;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.ItemDao;
import com.tenline.pinecone.persistence.RecordDao;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.persistence.VariableDao;
import com.tenline.pinecone.persistence.impl.DeviceDaoImpl;
import com.tenline.pinecone.persistence.impl.ItemDaoImpl;
import com.tenline.pinecone.persistence.impl.RecordDaoImpl;
import com.tenline.pinecone.persistence.impl.UserDaoImpl;
import com.tenline.pinecone.persistence.impl.VariableDaoImpl;

/**
 * @author Bill
 *
 */
public class VariableDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	private UserDao userDao;
	
	private DeviceDao deviceDao;
	
	private VariableDao variableDao;
	
	private ItemDao itemDao;
	
	private RecordDao recordDao;

	@Before
	public void testSetup() {
		super.testSetup();
		userDao = new UserDaoImpl();
		deviceDao = new DeviceDaoImpl();
		variableDao = new VariableDaoImpl();
		itemDao = new ItemDaoImpl();
		recordDao = new RecordDaoImpl();
	}
	
	@After
	public void testShutdown() {
		super.testShutdown();
		userDao = null;
		deviceDao = null;
		variableDao = null;
		itemDao = null;
		recordDao = null;
	}
	
	@Test
	public void testCURD() {
		User newUser = new User();
		newUser.setSnsId("23");
		String userId = userDao.save(newUser);
		Device newDevice = new Device();
		newDevice.setName("LNB");
		newDevice.setType("serial");
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
		newVariable.setItems(new ArrayList<Item>());
		Item newItem = new Item();
		newItem.setText("A");
		newItem.setValue("1");
		newVariable.getItems().add(newItem);
		newVariable.setRecords(new ArrayList<Record>());
		Record newRecord = new Record();
		newRecord.setValue("1");
		newVariable.getRecords().add(newRecord);
		String variableId = variableDao.save(newVariable);
		assertEquals("STA", ((Variable) variableDao.find("id=='"+variableId+"'").toArray()[0]).getName());
		assertEquals(1, itemDao.find("all").size());
		assertEquals(1, recordDao.find("all").size());
		Variable variable = new Variable();
		variable.setId(variableId);
		variable.setName("MUT");
		variable.setItems(new ArrayList<Item>());
		Item item = new Item();
		item.setText("A");
		item.setValue("1");
		variable.getItems().add(item);
		Record record = new Record();
		record.setValue("1");
		variable.setRecords(new ArrayList<Record>());
		variable.getRecords().add(record);
		variableId = variableDao.update(variable);
		assertEquals("MUT", ((Variable) variableDao.find("id=='"+variableId+"'").toArray()[0]).getName());
//		assertEquals(2, itemDao.find("all").size());
//		assertEquals(2, recordDao.find("all").size());
		variableDao.delete(variableId);
		assertEquals(0, variableDao.find("id=='"+variableId+"'").size());
		assertEquals(0, itemDao.find("all").size());
		assertEquals(0, recordDao.find("all").size());
	}

}
