/**
 * 
 */
package com.tenline.pinecone.platform.model;

import java.io.Serializable;

/**
 * @author Bill
 *
 */
@SuppressWarnings("serial")
public class Link implements Serializable {

	private String rel;
	
	private String href;
	
	/**
	 * 
	 */
	public Link() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the rel
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * @param rel the rel to set
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

}
