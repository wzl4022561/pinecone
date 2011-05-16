/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.Record;
import com.tenline.pinecone.model.User;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.DeviceDao;
import com.tenline.pinecone.persistence.RecordDao;
import com.tenline.pinecone.persistence.UserDao;
import com.tenline.pinecone.persistence.VariableDao;

/**
 * @author Bill
 *
 */
public class RecordDaoIntegrationTest extends AbstractDaoIntegrationTest {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private VariableDao variableDao;
	
	@Autowired
	private RecordDao recordDao;
	
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
		Record newRecord = new Record();
		newRecord.setValue("1");
		newRecord.setVariable(variable);
		Record record = recordDao.save(newRecord);
		assertEquals("1", ((Record) recordDao.find("id=='"+record.getId()+"'").toArray()[0]).getValue());
		assertEquals("STA", ((Record) recordDao.find("id=='"+record.getId()+"'").toArray()[0]).getVariable().getName());
		assertEquals(1, ((Record) recordDao.find("id=='"+record.getId()+"'").toArray()[0]).getVariable().getRecords().size());
		record.setValue("2");
		record = recordDao.update(record);
		assertEquals("2", ((Record) recordDao.find("id=='"+record.getId()+"'").toArray()[0]).getValue());
		recordDao.delete(record.getId());
		assertEquals(0, recordDao.find("id=='"+record.getId()+"'").size());
	}

}
