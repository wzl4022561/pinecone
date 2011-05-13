/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.ItemDao;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.persistence.VariableDao;

/**
 * @author Bill
 *
 */
public class ItemDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private VariableDao variableDao;
	
	@Autowired
	private ItemDao itemDao;
	
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
		newItem.setText("A");
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
		item.setText("B");
		itemId = itemDao.update(item);
		assertEquals("B", ((Item) itemDao.find("id=='"+itemId+"'").toArray()[0]).getText());
		itemDao.delete(itemId);
		assertEquals(0, itemDao.find("id=='"+itemId+"'").size());
	}

}
