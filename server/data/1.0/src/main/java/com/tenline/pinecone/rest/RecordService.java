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

import com.tenline.pinecone.model.Record;

/**
 * @author Bill
 *
 */
@Path("/api/record")
public interface RecordService extends AbstractService {

	/**
	 * 
	 * @param record
	 * @return
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	Response create(Record record);
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	Response update(Record record);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/show/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Record show(@PathParam("id") String id);
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/show/all")
	@Produces(MediaType.APPLICATION_JSON)
	Collection<Record> showAll();
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/show/all/{filter}")
	@Produces(MediaType.APPLICATION_JSON)
	Collection<Record> showAll(@PathParam("filter") String filter);
	
}
