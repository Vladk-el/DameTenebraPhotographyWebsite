package com.vladkel.dametenebra.persistence.dao;

import java.util.List;

/**
 * @author eliott
 *
 */
public interface IDAO <T> {

	List<T> select();
	
	T select(int id);
		
	boolean insert(T object);
	
	boolean update(T object);
	
	boolean delete(T object);
	
}
