/**
 * 
 */
package com.tenline.pinecone.persistence;

import java.util.Collection;

import com.tenline.pinecone.model.Variable;

/**
 * @author Bill
 *
 */
public interface VariableDao extends AbstractDao {
	
	/**
	 * 
	 * @param newInstance
	 * @return
	 */
	String save(Variable newInstance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	String update(Variable instance);
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<Variable> find(String filter);

}
