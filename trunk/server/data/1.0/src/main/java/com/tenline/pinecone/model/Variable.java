/**
 * 
 */
package com.tenline.pinecone.model;

import java.util.Collection;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Bill
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Variable {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    private String id;
	
	@Persistent
	private String name;
	
	@Persistent
	private String type;
	
	@Persistent
	private Device device;
	
	@Persistent(mappedBy = "variable")
    @Element(dependent = "true")
    @Order(extensions = @Extension(vendorName="datanucleus", key="list-ordering", value="value asc, timestamp asc"))
	private Collection<Record> records;
	
	@Persistent(mappedBy = "variable")
    @Element(dependent = "true")
    @Order(extensions = @Extension(vendorName="datanucleus", key="list-ordering", value="value asc"))
	private Collection<Item> items;
	
	/**
	 * 
	 */
	public Variable() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
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
	 * @param records the records to set
	 */
	public void setRecords(Collection<Record> records) {
		this.records = records;
	}

	/**
	 * @return the records
	 */
	public Collection<Record> getRecords() {
		return records;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	/**
	 * @return the items
	 */
	public Collection<Item> getItems() {
		return items;
	}

}
