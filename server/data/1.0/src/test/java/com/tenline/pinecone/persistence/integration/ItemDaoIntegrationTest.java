/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.ItemDao;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.persistence.VariableDao;
import com.tenline.pinecone.persistence.impl.DeviceDaoImpl;
import com.tenline.pinecone.persistence.impl.ItemDaoImpl;
import com.tenline.pinecone.persistence.impl.UserDaoImpl;
import com.tenline.pinecone.persistence.impl.VariableDaoImpl;

/**
 * @author Bill
 *
 */
public class ItemDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	private UserDao userDao;
	
	private DeviceDao deviceDao;
	
	private VariableDao variableDao;
	
	private ItemDao itemDao;

	@Before
	public void testSetup() {
		super.testSetup();
		userDao = new UserDaoImpl();
		deviceDao = new DeviceDaoImpl();
		variableDao = new VariableDaoImpl();
		itemDao = new ItemDaoImpl();
	}
	
	@After
	public void testShutdown() {
		super.testShutdown();
		userDao = null;
		deviceDao = null;
		variableDao = null;
		itemDao = null;
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
		Item newItem = new Item();
		newItem.setValue("1");
		Variable variable = new Variable();
		variable.setId(variableId);
		newItem.setVariable(variable);
		String itemId = itemDao.save(newItem);
		assertEquals("1", ((Item) itemDao.find("id=='"+itemId+"'").toArray()[0]).getValue());
		assertEquals("STA", ((Item) itemDao.find("id=='"+itemId+"'").toArray()[0]).getVariable().getName());
		assertEquals(1, ((Item) itemDao.find("id=='"+itemId+"'").toArray()[0]).getVariable().getItems().size());
		Item item = new Item();
		item.setId(itemId);
		item.setValue("2");
		itemId = itemDao.update(item);
//		assertEquals("2", ((Item) itemDao.find("id=='"+itemId+"'").toArray()[0]).getValue());
		itemDao.delete(itemId);
		assertEquals(0, itemDao.find("id=='"+itemId+"'").size());
	}

}
