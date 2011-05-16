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
import com.tenline.pinecone.model.Protocol;
import com.tenline.pinecone.persistence.jdo.ProtocolJdoDao;

/**
 * @author Bill
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProtocolDaoTest extends AbstractDaoTest {
	
	private Protocol protocol;
	
	private Device device;
	
	private List protocols;
	
	private ProtocolJdoDao protocolDao;

	@Before
	public void testSetup() {
		protocolDao = new ProtocolJdoDao(persistenceManagerFactory);
		protocolDao.setJdoTemplate(jdoTemplate);
		protocol = new Protocol();
		protocol.setId("asa");
		protocol.setName("modbus");
		protocol.setVersion("1.0");
		device = new Device();
		device.setId("ddd");
		protocol.setDevice(device);
		protocols = new ArrayList();
		protocols.add(protocol);
	}
	
	@After
	public void testShutdown() {	
		protocolDao = null;
		protocols.remove(protocol);
		protocol = null;
		protocols = null;
		device = null;
	}
	
	@Test
	public void testSave() {
		when(jdoTemplate.makePersistent(protocol)).thenReturn(protocol);
		Protocol result = protocolDao.save(protocol);
		verify(jdoTemplate).makePersistent(protocol);
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
	    }).when(jdoTemplate).deletePersistent(protocol);
		when(jdoTemplate.getObjectById(Protocol.class, protocol.getId())).thenReturn(protocol);
		protocolDao.delete(protocol.getId());
		verify(jdoTemplate).getObjectById(Protocol.class, protocol.getId());
		verify(jdoTemplate).deletePersistent(protocol);
	}
	
	@Test
	public void testUpdate() {
		when(jdoTemplate.getObjectById(Protocol.class, protocol.getId())).thenReturn(protocol);
		when(jdoTemplate.makePersistent(protocol)).thenReturn(protocol);
		Protocol result = protocolDao.update(protocol);
		verify(jdoTemplate).makePersistent(protocol);
		verify(jdoTemplate).getObjectById(Protocol.class, protocol.getId());
		assertEquals("asa", result.getId());
	}
	
	@Test
	public void testFind() {
		String filter = "name=='modbus'";
		String queryString = "select from " + Protocol.class.getName() + " where " + filter;
		when(jdoTemplate.find(queryString)).thenReturn(protocols);
		Collection<Protocol> result = protocolDao.find(filter);
		verify(jdoTemplate).find(queryString);
		assertEquals(1, result.size());
	}

}
