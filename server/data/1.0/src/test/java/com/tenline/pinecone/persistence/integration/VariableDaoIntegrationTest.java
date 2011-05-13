/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

/**
 * @author Bill
 *
 */
public class VariableDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private VariableDao variableDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private RecordDao recordDao;
	
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
		variable.setType("WRITE_ONLY");
		variableId = variableDao.update(variable);
		assertEquals("MUT", ((Variable) variableDao.find("id=='"+variableId+"'").toArray()[0]).getName());
		assertEquals("WRITE_ONLY", ((Variable) variableDao.find("id=='"+variableId+"'").toArray()[0]).getType());
		variableDao.delete(variableId);
		assertEquals(0, variableDao.find("id=='"+variableId+"'").size());
		assertEquals(0, itemDao.find("all").size());
		assertEquals(0, recordDao.find("all").size());
	}

}
