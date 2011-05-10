/**
 * 
 */
package com.tenline.pinecone.rest;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tenline.pinecone.model.Protocol;

/**
 * @author Bill
 *
 */
@Path("/api/protocol")
public interface ProtocolService extends AbstractService {

	/**
	 * 
	 * @param protocol
	 * @return
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	Response create(Protocol protocol);
	
	/**
	 * 
	 * @param protocol
	 * @return
	 */
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	Response update(Protocol protocol);
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/show/{filter}")
	@Produces(MediaType.APPLICATION_JSON)
	Collection<Protocol> show(@PathParam("filter") String filter);
	
}
