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
public class Device {

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
    @Persistent(mappedBy = "device", defaultFetchGroup = "true")
    @Element(dependent = "true")
    private List<Command> commands;

	/**
	 * 
	 */
	public Device() {
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
	 * @param commands the commands to set
	 */
	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	/**
	 * @return the commands
	 */
	public List<Command> getCommands() {
		return commands;
	}

}
