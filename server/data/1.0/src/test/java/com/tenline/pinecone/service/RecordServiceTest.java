/**
 * 
 */
package com.tenline.pinecone.service;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tenline.pinecone.model.Record;
import com.tenline.pinecone.persistence.RecordDao;
import com.tenline.pinecone.service.restful.RecordRestfulService;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@RunWith(MockitoJUnitRunner.class) 
public class RecordServiceTest {
	
	private Record record;
	
	private List records;
	
	private RecordRestfulService recordService;
	
	@Mock
	private RecordDao recordDao;

	@Before
	public void testSetup() {
		recordService = new RecordRestfulService(recordDao);
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
		ArgumentCaptor<Record> argument = ArgumentCaptor.forClass(Record.class);  
		when(recordDao.save(record)).thenReturn(record);
		Record result = recordService.create(record);
		verify(recordDao).save(argument.capture()); 
		verify(recordDao).save(record);
		assertEquals("1", argument.getValue().getValue());
		assertEquals("1", result.getValue());
	}
	
	@Test
	public void testDelete() {
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);  
		recordService.delete(record.getId());
		verify(recordDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
	}
	
	@Test
	public void testUpdate() {
		ArgumentCaptor<Record> argument = ArgumentCaptor.forClass(Record.class); 
		when(recordDao.update(record)).thenReturn(record);
		Record result = recordService.update(record);
		verify(recordDao).update(argument.capture());
		verify(recordDao).update(record);
		assertEquals("1", argument.getValue().getValue());
		assertEquals("1", result.getValue());
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
