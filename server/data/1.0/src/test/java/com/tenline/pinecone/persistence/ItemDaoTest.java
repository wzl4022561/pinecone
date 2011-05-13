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

import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.model.Variable;
import com.tenline.pinecone.persistence.impl.ItemDaoImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ItemDaoTest extends AbstractDaoTest {
	
	private Item item;
	
	private Variable variable;
	
	private List items;
	
	private ItemDaoImpl itemDao;

	@Before
	public void testSetup() {
		itemDao = new ItemDaoImpl(persistenceManagerFactory);
		itemDao.setJdoTemplate(jdoTemplate);
		item = new Item();
		item.setId("asa");
		item.setValue("1");
		variable = new Variable();
		variable.setId("ddd");
		item.setVariable(variable);
		items = new ArrayList();
		items.add(item);
	}
	
	@After
	public void testShutdown() {	
		itemDao = null;
		items.remove(item);
		item = null;
		items = null;
		variable = null;
	}
	
	@Test
	public void testSave() {
		when(jdoTemplate.makePersistent(item)).thenReturn(item);
		String result = itemDao.save(item);
		verify(jdoTemplate).makePersistent(item);
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
	    }).when(jdoTemplate).deletePersistent(item);
		when(jdoTemplate.getObjectById(Item.class, item.getId())).thenReturn(item);
		itemDao.delete(item.getId());
		verify(jdoTemplate).getObjectById(Item.class, item.getId());
		verify(jdoTemplate).deletePersistent(item);
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.getObjectById(Item.class, item.getId())).thenReturn(item);
		when(jdoTemplate.makePersistent(item)).thenReturn(item);
		String userId = itemDao.update(item);
		verify(jdoTemplate).makePersistent(item);
		verify(jdoTemplate).getObjectById(Item.class, item.getId());
		assertNotNull(userId);
	}
	
	@Test
	public void testFind() {
		String filter = "value=='1'";
		String queryString = "select from " + Item.class.getName() + " where " + filter;
		when(jdoTemplate.find(queryString)).thenReturn(items);
		Collection<Item> result = itemDao.find(filter);
		verify(jdoTemplate).find(queryString);
		assertEquals(1, result.size());
	}

}
