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
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.tenline.pinecone.model.Device;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.impl.VariableDaoImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class VariableDaoTest extends AbstractDaoTest {

	private Variable variable;
	
	private Device device;
	
	private List variables;
	
	private VariableDaoImpl variableDao;
	
	@Before
	public void testSetup() {
		variableDao = new VariableDaoImpl();
		variableDao.setJdoTemplate(jdoTemplate);
		variable = new Variable();
		variable.setId("asa");
		variable.setName("IF Output");
		device = new Device();
		device.setId("ddd");
		variable.setDevice(device);
		variables = new ArrayList();
		variables.add(variable);
	}
	
	@After
	public void testShutdown() {
		variableDao = null;
		variables.remove(variable);
		variable = null;
		variables = null;
		device = null;
	}
	
	@Test
	public void testSave() {
		when(jdoTemplate.persist(variable)).thenReturn(variable);
		when(jdoTemplate.find(Device.class, device.getId())).thenReturn(device);
		String result = variableDao.save(variable);
		verify(jdoTemplate).find(Device.class, device.getId());
		verify(jdoTemplate).persist(variable);
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
	    }).when(jdoTemplate).delete(variable);
		when(jdoTemplate.find(Variable.class, variable.getId())).thenReturn(variable);
		variableDao.delete(variable.getId());
		verify(jdoTemplate).find(Variable.class, variable.getId());
		verify(jdoTemplate).delete(variable);
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.find(Variable.class, variable.getId())).thenReturn(variable);
		when(jdoTemplate.persist(variable)).thenReturn(variable);
		String variableId = variableDao.update(variable);
		verify(jdoTemplate).persist(variable);
		verify(jdoTemplate).find(Variable.class, variable.getId());
		assertNotNull(variableId);
	}
	
	@Test
	public void testFind() {
		String filter = "name=='IF Output'";
		String queryString = "select from " + Variable.class.getName()+ " where " + filter;
		when(jdoTemplate.get(queryString)).thenReturn(variables);
		Collection<Variable> result = variableDao.find(filter);
		verify(jdoTemplate).get(queryString);
		assertEquals(1, result.size());
	}
	
}
