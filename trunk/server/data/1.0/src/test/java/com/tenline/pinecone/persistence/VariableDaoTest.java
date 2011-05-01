/**
 * 
 */
package com.tenline.pinecone.persistence;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void testFind() {
		when(jdoTemplate.find(Variable.class, variable.getId())).thenReturn(variable);
		Variable result = variableDao.find(variable.getId());
		verify(jdoTemplate).find(Variable.class, variable.getId());
		assertEquals("IF Output", result.getName());
	}
	
	@Test
	public void testFindAll() {
		when(jdoTemplate.findAll(Variable.class)).thenReturn(variables);
		Collection<Variable> result = variableDao.findAll();
		verify(jdoTemplate).findAll(Variable.class);
		assertEquals(1, result.size());
	}
	
	@Test
	public void testFindAllByFilter() {
		String filter = "name=='IF Output'";
		when(jdoTemplate.findAllByFilter(Variable.class, filter)).thenReturn(variables);
		Collection<Variable> result = variableDao.findAllByFilter(filter);
		verify(jdoTemplate).findAllByFilter(Variable.class, filter);
		assertEquals(1, result.size());
	}
	
}
