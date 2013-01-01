/**
 * 
 */
package com.tenline.pinecone.platform.web.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;
import java.util.logging.Level;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tenline.pinecone.platform.model.Authority;
import com.tenline.pinecone.platform.model.Device;
import com.tenline.pinecone.platform.model.Entity;
import com.tenline.pinecone.platform.model.Link;
import com.tenline.pinecone.platform.model.User;
import com.tenline.pinecone.platform.sdk.RESTClient;

/**
 * @author Bill
 *
 */
public class RESTServiceTest extends AbstractServiceTest {
	
	private User user;
	private Authority authority;
	private Device device;
	private RESTClient client;
	
	@Before
	public void testSetup() throws Exception {
		user = new User();
		user.setName("bill");
		user.setEmail("billmse@gmail.com");
		user.setPassword("19821027");
		authority = new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setUserName("bill");
		device = new Device();
		device.setName("ATM");
		device.setCode(UUID.randomUUID().toString());
		client = new RESTClient(URL);
	}
	
	@After
	public void testShutdown() throws Exception {
		user = null;
		authority = null;
		device = null;
		client = null;
	}
	
	@Test
	public void test() throws Exception {
		logger.log(Level.INFO, client.post("/user", user));
		logger.log(Level.INFO, client.post("/authority", authority));
		logger.log(Level.INFO, client.post("/device", device));
		for (Entity entity : client.get("/user")) {
			User user = (User) entity;
			logger.log(Level.INFO, "--------------------");
			assertNotNull(user.getId());
			logger.log(Level.INFO, user.getId().toString());
			assertEquals("bill", user.getName());
			logger.log(Level.INFO, user.getName());
			assertEquals("billmse@gmail.com", user.getEmail());
			logger.log(Level.INFO, user.getEmail());
			assertNull(user.getPassword());
			for (Link link : user.get_links()) {
				logger.log(Level.INFO, "--------------------");
				logger.log(Level.INFO, link.getRel());
				logger.log(Level.INFO, link.getHref());
				logger.log(Level.INFO, "--------------------");
			}
			this.user.setName("Jack");
			this.user.setEmail("bill_mse@163.com");
			logger.log(Level.INFO, client.put("/user/" + user.getId(), this.user));
			user = (User) client.get("/user/" + user.getId()).toArray()[0];
			assertEquals("Jack", user.getName());
			logger.log(Level.INFO, user.getName());
			assertEquals("bill_mse@163.com", user.getEmail());
			logger.log(Level.INFO, user.getEmail());
			assertNull(user.getPassword());
			this.user.setPassword("123456");
			logger.log(Level.INFO, client.post("/user/" + user.getId(), this.user));// Password is updated (exported = false)
			user = (User) client.get("/user/" + user.getId()).toArray()[0];
			assertEquals("Jack", user.getName());
			logger.log(Level.INFO, user.getName());
			assertEquals("bill_mse@163.com", user.getEmail());
			logger.log(Level.INFO, user.getEmail());
			assertNull(user.getPassword());
			this.user.setId(user.getId());
			logger.log(Level.INFO, "--------------------");
		}
		for (Entity entity : client.get("/authority")) {
			Authority authority = (Authority) entity;
			logger.log(Level.INFO, "--------------------");
			assertNotNull(authority.getId());
			logger.log(Level.INFO, authority.getId().toString());
			assertEquals("ROLE_USER", authority.getAuthority());
			logger.log(Level.INFO, authority.getAuthority());
			assertNull(authority.getUserName());
			for (Link link : authority.get_links()) {
				logger.log(Level.INFO, "--------------------");
				logger.log(Level.INFO, link.getRel());
				logger.log(Level.INFO, link.getHref());
				logger.log(Level.INFO, "--------------------");
			}
			this.authority.setUserName("Jack");
			logger.log(Level.INFO, client.post("/authority/" + authority.getId(), this.authority));// UserName is updated (exported = false)
			authority = (Authority) client.get("/authority/" + authority.getId()).toArray()[0];
			logger.log(Level.INFO, authority.getId().toString());
			assertEquals("ROLE_USER", authority.getAuthority());
			logger.log(Level.INFO, authority.getAuthority());
			assertNull(authority.getUserName());
			logger.log(Level.INFO, client.post("/authority/" + authority.getId() + "/user", "/user/" + user.getId()));	
			logger.log(Level.INFO, "--------------------");
		}
		for (Entity entity : client.get("/device")) {
			Device device = (Device) entity;
			logger.log(Level.INFO, "--------------------");
			assertNotNull(device.getId());
			logger.log(Level.INFO, device.getId().toString());
			assertEquals("ATM", device.getName());
			logger.log(Level.INFO, device.getName());
			assertNotNull(device.getCode());
			logger.log(Level.INFO, device.getCode());
			for (Link link : device.get_links()) {
				logger.log(Level.INFO, "--------------------");
				logger.log(Level.INFO, link.getRel());
				logger.log(Level.INFO, link.getHref());
				logger.log(Level.INFO, "--------------------");
			}
			this.device.setName("FM");
			this.device.setCode("123456");
			logger.log(Level.INFO, client.put("/device/" + device.getId(), this.device));
			device = (Device) client.get("/device/" + device.getId()).toArray()[0];
			logger.log(Level.INFO, device.getId().toString());
			assertEquals("FM", device.getName());
			logger.log(Level.INFO, device.getName());
			assertEquals("123456", device.getCode());
			logger.log(Level.INFO, device.getCode());
			logger.log(Level.INFO, client.post("/device/" + device.getId() + "/user", "/user/" + user.getId()));	
			logger.log(Level.INFO, "--------------------");
		}
		assertEquals(1, client.get("/user/" + user.getId() + "/devices").size());
		assertEquals(1, client.get("/user/" + user.getId() + "/authorities").size());
		logger.log(Level.INFO, client.delete("/user/" + user.getId()));	
		assertEquals(0, client.get("/user").size());
		assertEquals(0, client.get("/authority").size());
		assertEquals(0, client.get("/device").size());
	}

}