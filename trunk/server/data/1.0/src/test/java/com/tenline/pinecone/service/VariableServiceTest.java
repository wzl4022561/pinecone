/**
 * 
 */
package com.tenline.pinecone.service;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.VariableDao;
import com.tenline.pinecone.service.restful.VariableRestfulService;

/**
 * @author Bill
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@RunWith(MockitoJUnitRunner.class)
public class VariableServiceTest {

	private Variable variable;
	
	private List variables;
	
	private VariableRestfulService variableService;
	
	@Mock
	private VariableDao variableDao;
	
	@Before
	public void testSetup() {
		variableService = new VariableRestfulService(variableDao);
		variable = new Variable();
		variable.setId("asa");
		variable.setName("IF Output");
		variables = new ArrayList();
		variables.add(variable);
	}
	
	@After
	public void testShutdown() {
		variableService = null;
		variableDao = null;
		variables.remove(variable);
		variable = null;
		variables = null;
	}
	
	@Test
	public void testCreate() {
		ArgumentCaptor<Variable> argument = ArgumentCaptor.forClass(Variable.class); 
		when(variableDao.save(variable)).thenReturn(variable);
		Variable result = variableService.create(variable);
		verify(variableDao).save(argument.capture()); 
		verify(variableDao).save(variable);
		assertEquals("IF Output", argument.getValue().getName());
		assertEquals("IF Output", result.getName());
	}
	
	@Test
	public void testDelete() {
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class); 
		variableService.delete(variable.getId());
		verify(variableDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
	}
	
	@Test
	public void testUpdate() {
		ArgumentCaptor<Variable> argument = ArgumentCaptor.forClass(Variable.class);  
		when(variableDao.update(variable)).thenReturn(variable);
		Variable result = variableService.update(variable);
		verify(variableDao).update(argument.capture());
		verify(variableDao).update(variable);
		assertEquals("IF Output", argument.getValue().getName());
		assertEquals("IF Output", result.getName());
	}
	
	@Test
	public void testShow() {
		String filter = "name=='IF Output'";
		when(variableDao.find(filter)).thenReturn(variables);
		Collection<Variable> result = variableService.show(filter);
		verify(variableDao).find(filter);
		assertEquals(1, result.size());
	}

}
