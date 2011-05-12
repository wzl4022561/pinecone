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

import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.persistence.ProtocolDao;
import com.tenline.pinecone.rest.impl.ProtocolServiceImpl;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@RunWith(MockitoJUnitRunner.class)
public class ProtocolServiceTest {

	private Protocol protocol;
	
	private List protocols;
	
	private ProtocolServiceImpl protocolService;
	
	@Mock
	private ProtocolDao protocolDao;
	
	@Before
	public void testSetup() {
		protocolService = new ProtocolServiceImpl(protocolDao);
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
		Response result = protocolService.create(protocol);
		ArgumentCaptor<Protocol> argument = ArgumentCaptor.forClass(Protocol.class);  
		verify(protocolDao).save(argument.capture()); 
		assertEquals("modbus", argument.getValue().getName());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testDelete() {
		Response result = protocolService.delete(protocol.getId());
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);  
		verify(protocolDao).delete(argument.capture());
		assertEquals("asa", argument.getValue());
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testUpdate() {
		Response result = protocolService.update(protocol);
		ArgumentCaptor<Protocol> argument = ArgumentCaptor.forClass(Protocol.class);  
		verify(protocolDao).update(argument.capture());
		assertEquals("modbus", argument.getValue().getName());
		assertEquals(200, result.getStatus());
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
