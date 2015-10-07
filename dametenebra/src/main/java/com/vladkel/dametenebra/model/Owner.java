package com.vladkel.dametenebra.model;

/**
 * @author eliott
 *
 */
public class Owner {

	private int owner_id;

	private String owner_name;

	private String owner_description;

	private int active;
	
	public Owner() {
		super();
	}

	/**
	 * @return the owner_id
	 */
	public int getOwner_id() {
		return owner_id;
	}

	/**
	 * @param owner_id the owner_id to set
	 */
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	/**
	 * @return the owner_name
	 */
	public String getOwner_name() {
		return owner_name;
	}

	/**
	 * @param owner_name the owner_name to set
	 */
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	/**
	 * @return the owner_description
	 */
	public String getOwner_description() {
		return owner_description;
	}

	/**
	 * @param owner_description the owner_description to set
	 */
	public void setOwner_description(String owner_description) {
		this.owner_description = owner_description;
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
	
	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getOwner_name();
	}
	
}
