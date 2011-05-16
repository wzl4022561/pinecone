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

import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.persistence.ProtocolDao;
import com.tenline.pinecone.service.restful.ProtocolRestfulService;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@RunWith(MockitoJUnitRunner.class)
public class ProtocolServiceTest {

	private Protocol protocol;
	
	private List protocols;
	
	private ProtocolRestfulService protocolService;
	
	@Mock
	private ProtocolDao protocolDao;
	
	@Before
	public void testSetup() {
		protocolService = new ProtocolRestfulService(protocolDao);
		protocol = new Protocol();
		protocol.setId("asa");
		protocol.setName("modbus");
		protocols = new ArrayList();
		protocols.add(protocol);
	}
	
	@After
	public void testShutdown() {	
		protocolService = null;
		protocolDao = null;
		protocols.remove(protocol);
		protocol = null;
		protocols = null;
	}

	@Test
	public void testCreate() {
		ArgumentCaptor<Protocol> argument = ArgumentCaptor.forClass(Protocol.class);  
		when(protocolDao.save(protocol)).thenReturn(protocol);
		Protocol result = protocolService.create(protocol);
		verify(protocolDao).save(argument.capture()); 
		verify(protocolDao).save(protocol);
		assertEquals("modbus", argument.getValue().getName());
		assertEquals("modbus", result.getName());
	}
	
	@Test
	public void testDelete() {
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class); 
		protocolService.delete(protocol.getId());
		verify(protocolDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
	}
	
	@Test
	public void testUpdate() {
		ArgumentCaptor<Protocol> argument = ArgumentCaptor.forClass(Protocol.class);  
		when(protocolDao.update(protocol)).thenReturn(protocol);
		Protocol result = protocolService.update(protocol);
		verify(protocolDao).update(argument.capture());
		verify(protocolDao).update(protocol);
		assertEquals("modbus", argument.getValue().getName());
		assertEquals("modbus", result.getName());
	}
	
	@Test
	public void testShow() {
		String filter = "name=='modbus'";
		when(protocolDao.find(filter)).thenReturn(protocols);
		Collection<Protocol> result = protocolService.show(filter);
		verify(protocolDao).find(filter);
		assertEquals(1, result.size());
	}

}
