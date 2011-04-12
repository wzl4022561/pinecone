/**
 * 
 */
package com.tenline.pinecone.model;

import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * @author Bill
 *
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION, detachable="true")
public class Command {

	/**
	 * 
	 */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	/**
	 * 
	 */
    @Persistent
	private String name;
    
    /**
     * 
     */
    @Persistent
    private Device device;
    
    /**
     * 
     */
    @Persistent(mappedBy = "command", defaultFetchGroup = "true")
    @Element(dependent = "true")
    private List<Variable> variables;
    
	/**
	 * 
	 */
	public Command() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param device the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}

	/**
	 * @return the device
	 */
	public Device getDevice() {
		return device;
	}

	/**
	 * @param variables the variables to set
	 */
	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	/**
	 * @return the variables
	 */
	public List<Variable> getVariables() {
		return variables;
	}

}
