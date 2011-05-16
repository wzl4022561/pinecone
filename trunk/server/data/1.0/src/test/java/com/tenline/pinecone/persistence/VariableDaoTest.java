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
import com.tenline.pinecone.persistence.jdo.VariableJdoDao;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class VariableDaoTest extends AbstractDaoTest {

	private Variable variable;
	
	private Device device;
	
	private List variables;
	
	private VariableJdoDao variableDao;
	
	@Before
	public void testSetup() {
		variableDao = new VariableJdoDao(persistenceManagerFactory);
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
		when(jdoTemplate.makePersistent(variable)).thenReturn(variable);
		when(jdoTemplate.getObjectById(Device.class, device.getId())).thenReturn(device);
		Variable result = variableDao.save(variable);
		verify(jdoTemplate).getObjectById(Device.class, device.getId());
		verify(jdoTemplate).makePersistent(variable);
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
	    }).when(jdoTemplate).deletePersistent(variable);
		when(jdoTemplate.getObjectById(Variable.class, variable.getId())).thenReturn(variable);
		variableDao.delete(variable.getId());
		verify(jdoTemplate).getObjectById(Variable.class, variable.getId());
		verify(jdoTemplate).deletePersistent(variable);
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.getObjectById(Variable.class, variable.getId())).thenReturn(variable);
		when(jdoTemplate.makePersistent(variable)).thenReturn(variable);
		Variable result = variableDao.update(variable);
		verify(jdoTemplate).makePersistent(variable);
		verify(jdoTemplate).getObjectById(Variable.class, variable.getId());
		assertEquals("asa", result.getId());
	}
	
	@Test
	public void testFind() {
		String filter = "name=='IF Output'";
		String queryString = "select from " + Variable.class.getName()+ " where " + filter;
		when(jdoTemplate.find(queryString)).thenReturn(variables);
		Collection<Variable> result = variableDao.find(filter);
		verify(jdoTemplate).find(queryString);
		assertEquals(1, result.size());
	}
	
}
