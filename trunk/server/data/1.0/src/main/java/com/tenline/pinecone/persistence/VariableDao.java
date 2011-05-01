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
public interface VariableDao {
	
	/**
	 * 
	 * @param newInstance
	 * @return
	 */
	String save(Variable newInstance);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Variable find(String id);
	
	/**
	 * 
	 * @return
	 */
	Collection<Variable> findAll();
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	Collection<Variable> findAllByFilter(String filter);

}
