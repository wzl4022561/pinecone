/**
 * 
 */
package com.tenline.pinecone.rest;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.persistence.ItemDao;
import com.tenline.pinecone.rest.impl.ItemServiceImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@RunWith(MockitoJUnitRunner.class) 
public class ItemServiceTest {
	
	private Item item;
	
	private List items;
	
	private ItemServiceImpl itemService;
	
	@Mock
	private ItemDao itemDao;

	@Before
	public void testSetup() {
		itemService = new ItemServiceImpl(itemDao);
		item = new Item();
		item.setId("asa");
		item.setValue("1");
		items = new ArrayList();
		items.add(item);
	}
	
	@After
	public void testShutdown() {	
		itemService = null;
		itemDao = null;
		items.remove(item);
		item = null;
		items = null;
	}

	@Test
	public void testCreate() {
		Response result = itemService.create(item);
		ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);  
		verify(itemDao).save(argument.capture()); 
		assertEquals("1", argument.getValue().getValue());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testDelete() {
		Response result = itemService.delete(item.getId());
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);  
		verify(itemDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testUpdate() {
		Response result = itemService.update(item);
		ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);  
		verify(itemDao).update(argument.capture());
		assertEquals("1", argument.getValue().getValue());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testShow() {
		String filter = "value=='1'";
		when(itemDao.find(filter)).thenReturn(items);
		Collection<Item> result = itemService.show(filter);
		verify(itemDao).find(filter);
		assertEquals(1, result.size());
	}

}
