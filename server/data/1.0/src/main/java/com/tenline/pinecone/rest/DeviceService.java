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

import com.tenline.pinecone.model.Device;

/**
 * @author Bill
 *
 */
@Path("/api/device")
public interface DeviceService extends AbstractService {
	
	/**
	 * 
	 * @param device
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	Response create(Device device);
	
	/**
	 * 
	 * @param device
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	Response update(Device device);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Device show(@PathParam("id") String id);
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Collection<Device> showAll();
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/show/{filter}")
	@Produces(MediaType.APPLICATION_JSON)
	Collection<Device> showAllByFilter(@PathParam("filter") String filter);

}
