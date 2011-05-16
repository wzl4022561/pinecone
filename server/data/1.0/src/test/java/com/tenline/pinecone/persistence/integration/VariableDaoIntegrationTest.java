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
		User user = userDao.save(newUser);
		Device newDevice = new Device();
		newDevice.setName("LNB");
		newDevice.setType("serial");
		newDevice.setUser(user);
		Device device = deviceDao.save(newDevice);
		Variable newVariable = new Variable();
		newVariable.setName("STA");
		newVariable.setType("READ_ONLY");
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
		Variable variable = variableDao.save(newVariable);
		assertEquals("STA", ((Variable) variableDao.find("id=='"+variable.getId()+"'").toArray()[0]).getName());
		assertEquals(1, itemDao.find("all").size());
		assertEquals(1, recordDao.find("all").size());
		variable.setName("MUT");
		variable.setType("WRITE_ONLY");
		variable = variableDao.update(variable);
		assertEquals("MUT", ((Variable) variableDao.find("id=='"+variable.getId()+"'").toArray()[0]).getName());
		assertEquals("WRITE_ONLY", ((Variable) variableDao.find("id=='"+variable.getId()+"'").toArray()[0]).getType());
		variableDao.delete(variable.getId());
		assertEquals(0, variableDao.find("id=='"+variable.getId()+"'").size());
		assertEquals(0, itemDao.find("all").size());
		assertEquals(0, recordDao.find("all").size());
	}

}
