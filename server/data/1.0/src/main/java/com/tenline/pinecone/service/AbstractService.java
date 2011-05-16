/**
 * 
 */
package com.tenline.pinecone.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Bill
 *
 */
public interface AbstractService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/delete/{id}")
	void delete(@PathParam("id") String id);
	
}
