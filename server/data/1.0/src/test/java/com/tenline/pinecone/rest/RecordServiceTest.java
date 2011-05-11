/**
 * 
 */
package com.tenline.pinecone.rest;

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
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tenline.pinecone.model.Record;
import com.tenline.pinecone.persistence.RecordDao;
import com.tenline.pinecone.rest.impl.RecordServiceImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@RunWith(MockitoJUnitRunner.class) 
public class RecordServiceTest {
	
	private Record record;
	
	private List records;
	
	private RecordServiceImpl recordService;
	
	@Mock
	private RecordDao recordDao;

	@Before
	public void testSetup() {
		recordService = new RecordServiceImpl(recordDao);
		record = new Record();
		record.setId("asa");
		record.setValue("1");
		record.setTimestamp(new Date());
		records = new ArrayList();
		records.add(record);
	}
	
	@After
	public void testShutdown() {	
		recordService = null;
		recordDao = null;
		records.remove(record);
		record = null;
		records = null;
	}

	@Test
	public void testCreate() {
		Response result = recordService.create(record);
		ArgumentCaptor<Record> argument = ArgumentCaptor.forClass(Record.class);  
		verify(recordDao).save(argument.capture()); 
		assertEquals("1", argument.getValue().getValue());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testDelete() {
		Response result = recordService.delete(record.getId());
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);  
		verify(recordDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testUpdate() {
		Response result = recordService.update(record);
		ArgumentCaptor<Record> argument = ArgumentCaptor.forClass(Record.class);  
		verify(recordDao).update(argument.capture());
		assertEquals("1", argument.getValue().getValue());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testShow() {
		String filter = "value=='1'";
		when(recordDao.find(filter)).thenReturn(records);
		Collection<Record> result = recordService.show(filter);
		verify(recordDao).find(filter);
		assertEquals(1, result.size());
	}

}
