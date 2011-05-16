/**
 * 
 */
package com.tenline.pinecone.persistence;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.tenline.pinecone.model.Record;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.jdo.RecordJdoDao;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RecordDaoTest extends AbstractDaoTest {
	
	private Record record;
	
	private Variable variable;
	
	private List records;
	
	private RecordJdoDao recordDao;

	@Before
	public void testSetup() {
		recordDao = new RecordJdoDao(persistenceManagerFactory);
		recordDao.setJdoTemplate(jdoTemplate);
		record = new Record();
		record.setId("asa");
		record.setValue("1");
		record.setTimestamp(new Date());
		variable = new Variable();
		variable.setId("ddd");
		record.setVariable(variable);
		records = new ArrayList();
		records.add(record);
	}
	
	@After
	public void testShutdown() {	
		recordDao = null;
		records.remove(record);
		record = null;
		records = null;
		variable = null;
	}
	
	@Test
	public void testSave() {
		when(jdoTemplate.makePersistent(record)).thenReturn(record);
		Record result = recordDao.save(record);
		verify(jdoTemplate).makePersistent(record);
		assertEquals("asa", result.getId());
	}
	
	@Test
	public void testDelete() {
		doAnswer(new Answer<Object>() {
	        public Object answer(InvocationOnMock invocation) {
	            Object[] args = invocation.getArguments();
	            assertNotNull(args[0]);
	            return args;
	        }
	    }).when(jdoTemplate).deletePersistent(record);
		when(jdoTemplate.getObjectById(Record.class, record.getId())).thenReturn(record);
		recordDao.delete(record.getId());
		verify(jdoTemplate).getObjectById(Record.class, record.getId());
		verify(jdoTemplate).deletePersistent(record);
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.getObjectById(Record.class, record.getId())).thenReturn(record);
		when(jdoTemplate.makePersistent(record)).thenReturn(record);
		Record result = recordDao.update(record);
		verify(jdoTemplate).makePersistent(record);
		verify(jdoTemplate).getObjectById(Record.class, record.getId());
		assertEquals("asa", result.getId());
	}
	
	@Test
	public void testFind() {
		String filter = "value=='1'";
		String queryString = "select from " + Record.class.getName() + " where " + filter;
		when(jdoTemplate.find(queryString)).thenReturn(records);
		Collection<Record> result = recordDao.find(filter);
		verify(jdoTemplate).find(queryString);
		assertEquals(1, result.size());
	}

}
