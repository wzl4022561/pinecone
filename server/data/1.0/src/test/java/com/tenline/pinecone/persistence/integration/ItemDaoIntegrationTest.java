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
		User user = userDao.save(newUser);		
		Device newDevice = new Device();
		newDevice.setName("ACU");
		newDevice.setUser(user);
		Device device = deviceDao.save(newDevice);
		Variable newVariable = new Variable();
		newVariable.setName("STA");
		newVariable.setType("READ_ONLY");
		newVariable.setDevice(device);
		Variable variable = variableDao.save(newVariable);
		Item newItem = new Item();
		newItem.setValue("1");
		newItem.setText("A");
		newItem.setVariable(variable);
		Item item = itemDao.save(newItem);
		assertEquals("1", ((Item) itemDao.find("id=='"+item.getId()+"'").toArray()[0]).getValue());
		assertEquals("STA", ((Item) itemDao.find("id=='"+item.getId()+"'").toArray()[0]).getVariable().getName());
		assertEquals(1, ((Item) itemDao.find("id=='"+item.getId()+"'").toArray()[0]).getVariable().getItems().size());
		item.setValue("2");
		item.setText("B");
		item = itemDao.update(item);
		assertEquals("B", ((Item) itemDao.find("id=='"+item.getId()+"'").toArray()[0]).getText());
		itemDao.delete(item.getId());
		assertEquals(0, itemDao.find("id=='"+item.getId()+"'").size());
	}

}
