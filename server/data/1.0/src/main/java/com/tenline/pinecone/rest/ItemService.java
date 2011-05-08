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

import com.tenline.pinecone.model.Item;

/**
 * @author Bill
 *
 */
@Path("/api/item")
public interface ItemService extends AbstractService {

	/**
	 * 
	 * @param item
	 * @return
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	Response create(Item item);
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	Response update(Item item);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/show/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Item show(@PathParam("id") String id);
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/show/all")
	@Produces(MediaType.APPLICATION_JSON)
	Collection<Item> showAll();
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/show/all/{filter}")
	@Produces(MediaType.APPLICATION_JSON)
	Collection<Item> showAll(@PathParam("filter") String filter);

}
