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

import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.impl.VariableDaoImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class VariableDaoTest extends AbstractDaoTest {

	private Variable variable;
	
	private List variables;
	
	private VariableDaoImpl variableDao;
	
	@Before
	public void testSetup() {
		variableDao = new VariableDaoImpl();
		variableDao.setJdoTemplate(jdoTemplate);
		variable = new Variable();
		variable.setId("asa");
		variable.setName("IF Output");
		variables = new ArrayList();
		variables.add(variable);
	}
	
	@After
	public void testShutdown() {
		variableDao = null;
		variables.remove(variable);
		variable = null;
		variables = null;
	}
	
	@Test
	public void testSave() {
		when(jdoTemplate.save(variable)).thenReturn(variable);
		String result = variableDao.save(variable);
		verify(jdoTemplate).save(variable);
		assertEquals("asa", result);
	}
	
	@Test
	public void testDelete() {
		doAnswer(new Answer<Object>() {
	        public Object answer(InvocationOnMock invocation) {
	            Object[] args = invocation.getArguments();
	            assertEquals(args[0], Variable.class);
	            assertEquals(args[1], variable.getId());
	            return args;
	        }
	    }).when(jdoTemplate).delete(Variable.class, variable.getId());
		variableDao.delete(variable.getId());
		verify(jdoTemplate).delete(Variable.class, variable.getId());
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.getDetachedObject(Variable.class, variable.getId())).thenReturn(variable);
		when(jdoTemplate.save(variable)).thenReturn(variable);
		String variableId = variableDao.update(variable);
		verify(jdoTemplate).save(variable);
		verify(jdoTemplate).getDetachedObject(Variable.class, variable.getId());
		assertNotNull(variableId);
	}
	
	@Test
	public void testFind() {
		when(jdoTemplate.find(Variable.class, variable.getId())).thenReturn(variable);
		Variable result = variableDao.find(variable.getId());
		verify(jdoTemplate).find(Variable.class, variable.getId());
		assertEquals("IF Output", result.getName());
	}
	
	@Test
	public void testFindAll() {
		when(jdoTemplate.findAll(Variable.class, null)).thenReturn(variables);
		Collection<Variable> result = variableDao.findAll();
		verify(jdoTemplate).findAll(Variable.class, null);
		assertEquals(1, result.size());
	}
	
	@Test
	public void testFindAllByFilter() {
		String filter = "name=='IF Output'";
		when(jdoTemplate.findAll(Variable.class, filter)).thenReturn(variables);
		Collection<Variable> result = variableDao.findAll(filter);
		verify(jdoTemplate).findAll(Variable.class, filter);
		assertEquals(1, result.size());
	}
	
}
