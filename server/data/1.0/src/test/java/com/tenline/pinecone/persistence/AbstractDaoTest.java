/**
 * 
 */
package com.tenline.pinecone.persistence;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tenline.pinecone.utils.JdoTemplate;

/**
 * @author Bill
 *
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractDaoTest {

	@Mock
	protected JdoTemplate jdoTemplate;

}
