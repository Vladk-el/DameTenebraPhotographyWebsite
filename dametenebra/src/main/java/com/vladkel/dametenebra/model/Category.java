package com.vladkel.dametenebra.model;

/**
 * @author eliott
 *
 */
public class Category {

	private int category_id;

	private String category_name;

	private String category_description;

	private String category_date;

	private int active;

	public Category() {
		super();
	}

	/**
	 * @return the category_id
	 */
	public int getCategory_id() {
		return category_id;
	}

	/**
	 * @param category_id
	 *            the category_id to set
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	/**
	 * @return the category_name
	 */
	public String getCategory_name() {
		return category_name;
	}

	/**
	 * @param category_name
	 *            the category_name to set
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	/**
	 * @return the category_description
	 */
	public String getCategory_description() {
		return category_description;
	}

	/**
	 * @param category_description
	 *            the category_description to set
	 */
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}

	/**
	 * @return the category_date
	 */
	public String getCategory_date() {
		return category_date;
	}

	/**
	 * @param category_date
	 *            the category_date to set
	 */
	public void setCategory_date(String category_date) {
		this.category_date = category_date;
	}

	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return category_name;
	}
}
