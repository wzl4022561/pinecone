/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.VariableDao;
import com.tenline.pinecone.persistence.impl.VariableDaoImpl;

/**
 * @author Bill
 *
 */
public class VariableDaoIntegrationTest extends AbstractDaoIntegrationTest {
	
	private VariableDao variableDao;

	@Before
	public void testSetup() {
		super.testSetup();
		variableDao = new VariableDaoImpl();
	}
	
	@After
	public void testShutdown() {
		super.testShutdown();
		variableDao = null;
	}
	
	@Test
	public void testSaveAndFind() {
		Variable newVariable = new Variable();
		newVariable.setName("Output");
		String variableId = variableDao.save(newVariable);
		assertNotNull(variableId);	
		Variable variable = variableDao.find(variableId);
		assertEquals("Output", variable.getName());
	}
	
	@Test
	public void testFindAll() {
		assertNotNull(variableDao.findAll());
	}
	
	@Test
	public void testFindAllByFilter() {
		assertNotNull(variableDao.findAll("name=='Output'"));
	}

}
