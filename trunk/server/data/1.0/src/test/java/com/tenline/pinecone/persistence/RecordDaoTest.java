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
import com.tenline.pinecone.persistence.impl.RecordDaoImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RecordDaoTest extends AbstractDaoTest {
	
	private Record record;
	
	private Variable variable;
	
	private List records;
	
	private RecordDaoImpl recordDao;

	@Before
	public void testSetup() {
		recordDao = new RecordDaoImpl();
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
		when(jdoTemplate.persist(record)).thenReturn(record);
		String result = recordDao.save(record);
		verify(jdoTemplate).persist(record);
		assertEquals("asa", result);
	}
	
	@Test
	public void testDelete() {
		doAnswer(new Answer<Object>() {
	        public Object answer(InvocationOnMock invocation) {
	            Object[] args = invocation.getArguments();
	            assertNotNull(args[0]);
	            return args;
	        }
	    }).when(jdoTemplate).delete(record);
		when(jdoTemplate.find(Record.class, record.getId())).thenReturn(record);
		recordDao.delete(record.getId());
		verify(jdoTemplate).find(Record.class, record.getId());
		verify(jdoTemplate).delete(record);
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.find(Record.class, record.getId())).thenReturn(record);
		when(jdoTemplate.persist(record)).thenReturn(record);
		String userId = recordDao.update(record);
		verify(jdoTemplate).persist(record);
		verify(jdoTemplate).find(Record.class, record.getId());
		assertNotNull(userId);
	}
	
	@Test
	public void testFind() {
		String filter = "value=='1'";
		String queryString = "select from " + Record.class.getName() + " where " + filter;
		when(jdoTemplate.get(queryString)).thenReturn(records);
		Collection<Record> result = recordDao.find(filter);
		verify(jdoTemplate).get(queryString);
		assertEquals(1, result.size());
	}

}
