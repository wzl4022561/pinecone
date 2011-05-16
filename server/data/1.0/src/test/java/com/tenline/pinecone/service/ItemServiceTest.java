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

import com.tenline.pinecone.model.Item;
import com.tenline.pinecone.persistence.ItemDao;
import com.tenline.pinecone.service.restful.ItemRestfulService;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@RunWith(MockitoJUnitRunner.class) 
public class ItemServiceTest {
	
	private Item item;
	
	private List items;
	
	private ItemRestfulService itemService;
	
	@Mock
	private ItemDao itemDao;

	@Before
	public void testSetup() {
		itemService = new ItemRestfulService(itemDao);
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
		ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);  
		when(itemDao.save(item)).thenReturn(item);
		Item result = itemService.create(item);
		verify(itemDao).save(argument.capture()); 
		verify(itemDao).save(item);
		assertEquals("1", argument.getValue().getValue());
		assertEquals("1", result.getValue());
	}
	
	@Test
	public void testDelete() {
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);  
		itemService.delete(item.getId());
		verify(itemDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
	}
	
	@Test
	public void testUpdate() {
		ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);  
		when(itemDao.update(item)).thenReturn(item);
		Item result = itemService.update(item);
		verify(itemDao).update(argument.capture());
		verify(itemDao).update(item);
		assertEquals("1", argument.getValue().getValue());
		assertEquals("1", result.getValue());
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
