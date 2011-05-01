/**
 * 
 */
package com.tenline.pinecone.persistence.integration;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * @author Bill
 *
 */
public abstract class AbstractDaoIntegrationTest {

	private LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	
	@Before
	public void testSetup() {
		helper.setUp();
	}
	
	@After
	public void testShutdown() {
		helper.tearDown();
	}

}
