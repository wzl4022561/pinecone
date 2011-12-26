/**
 * 
 */
package com.tenline.pinecone.platform.web.service;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tenline.pinecone.platform.model.Device;
import com.tenline.pinecone.platform.model.Item;
import com.tenline.pinecone.platform.model.Record;
import com.tenline.pinecone.platform.model.Variable;
import com.tenline.pinecone.platform.web.service.restful.RecordRestfulService;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RecordServiceTest extends AbstractServiceTest {
	
	private Date date = new Date();
	
	private Device device;
	
	private Variable variable;
	
	private Item item;
	
	private Record record;
	
	private List records;
	
	private RecordRestfulService recordService;
	
	@Before
	public void testSetup() {
		recordService = new RecordRestfulService(persistenceManagerFactory);
		recordService.setJdoTemplate(jdoTemplate);
		record = new Record();
		record.setId("asa");
		record.setTimestamp(date);
		device = new Device();
		device.setId("aaa");
		record.setDevice(device);
		variable = new Variable();
		variable.setId("asa");
		record.setVariable(variable);
		item = new Item();
		item.setId("bbb");
		record.setItem(item);
		records = new ArrayList();
		records.add(record);
	}
	
	@After
	public void testShutdown() {	
		recordService = null;
		records.remove(record);
		device = null;
		variable = null;
		item = null;
		record = null;
		records = null;
	}

	@Test
	public void testCreate() {
		when(jdoTemplate.getObjectById(Device.class, device.getId())).thenReturn(device);
		when(jdoTemplate.getObjectById(Variable.class, variable.getId())).thenReturn(variable);
		when(jdoTemplate.getObjectById(Item.class, item.getId())).thenReturn(item);
		when(jdoTemplate.makePersistent(record)).thenReturn(record);
		Record result = recordService.create(record);
		verify(jdoTemplate).getObjectById(Device.class, device.getId());
		verify(jdoTemplate).getObjectById(Variable.class, variable.getId());
		verify(jdoTemplate).getObjectById(Item.class, item.getId());
		verify(jdoTemplate).makePersistent(record);
		assertEquals(date, result.getTimestamp());
	}
	
	@Test
	public void testDelete() {
		when(jdoTemplate.getObjectById(Record.class, record.getId())).thenReturn(record);
		Response result = recordService.delete(record.getId());
		verify(jdoTemplate).getObjectById(Record.class, record.getId());
		verify(jdoTemplate).deletePersistent(record);
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.getObjectById(Record.class, record.getId())).thenReturn(record);
		when(jdoTemplate.makePersistent(record)).thenReturn(record);
		Record result = recordService.update(record);
		verify(jdoTemplate).getObjectById(Record.class, record.getId());
		verify(jdoTemplate).makePersistent(record);
		assertEquals(date, result.getTimestamp());
	}
	
	@Test
	public void testShow() {
		String filter = "value=='1'";
		String queryString = "select from " + Record.class.getName() + " where " + filter;
		when(jdoTemplate.find(queryString)).thenReturn(records);
		Collection<Record> result = recordService.show(filter);
		verify(jdoTemplate).find(queryString);
		assertEquals(1, result.size());
	}

}
