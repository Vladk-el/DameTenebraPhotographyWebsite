package com.vladkel.dametenebra.model;

/**
 * @author eliott
 *
 */
public class Link {

	private int link_id;

	private String link_name;

	private String link_link;

	private int link_owner_id;

	private int active;
	
	public Link() {
		super();
	}

	/**
	 * @return the link_id
	 */
	public int getLink_id() {
		return link_id;
	}

	/**
	 * @param link_id the link_id to set
	 */
	public void setLink_id(int link_id) {
		this.link_id = link_id;
	}

	/**
	 * @return the link_name
	 */
	public String getLink_name() {
		return link_name;
	}

	/**
	 * @param link_name the link_name to set
	 */
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}

	/**
	 * @return the link_link
	 */
	public String getLink_link() {
		return link_link;
	}

	/**
	 * @param link_link the link_link to set
	 */
	public void setLink_link(String link_link) {
		this.link_link = link_link;
	}

	/**
	 * @return the link_owner_id
	 */
	public int getLink_owner_id() {
		return link_owner_id;
	}

	/**
	 * @param link_owner_id the link_owner_id to set
	 */
	public void setLink_owner_id(int link_owner_id) {
		this.link_owner_id = link_owner_id;
	}

	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}
	
}
