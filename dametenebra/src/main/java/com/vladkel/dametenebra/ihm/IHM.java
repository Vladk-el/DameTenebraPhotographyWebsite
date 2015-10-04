package com.vladkel.dametenebra.ihm;

/**
 * @author eliott
 *
 */
public interface IHM {
	
	/**
	 * Init IHM
	 */
	public void init();
	
	/**
	 * Empry all fields ~= reset
	 */
	public void empty();
	
	/**
	 * Set up all fields
	 */
	public void fill();
	
	/**
	 * Set up IHM to display all objects
	 */
	public void displayAll();

	/**
	 * Set up IHM to create an object
	 */
	public void create();
	
	/**
	 * Set up IHM to modify an object
	 */
	public void modify(Object object);
	
}
