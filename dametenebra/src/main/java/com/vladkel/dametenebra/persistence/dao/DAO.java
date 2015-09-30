package com.vladkel.dametenebra.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.vladkel.dametenebra.utils.http.GetHelper;

/**
 * @author eliott
 *
 */
public class DAO<T> implements IDAO<T> {
	
	private static String DEFAULT_PATH = "http://www.dametenebra.com/php/services/";
	private static String CATEGORIES = "categories"; // ==> set in property class
	private static String INSERT = "_insert";
	private static String UPDATE = "_update";
	private static String DELETE = "_delete";
	private static String PHP = ".php";

	private Class<T> clazz;
	private Map<String, String> headers;
	
	public DAO(Class<T> clazz) {
		this.clazz = clazz;
		headers = new HashMap<>();
		headers.put("Content-Type", "application/json; charset=UTF-8");
	}
	
	/* (non-Javadoc)
	 * @see com.vladkel.dametenebra.dao.IDAO#select()
	 */
	public List<T> select() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vladkel.dametenebra.dao.IDAO#select(int)
	 */
	public T select(int id) {
		String url = new StringBuilder()
				.append(DEFAULT_PATH)
				.append(clazz.getName())
				.append("/")
				.append(clazz.getName())
				.append(PHP)
				.append("?")
				.append(clazz.getName())
				.append("=")
				.append(id)
				.toString();
		GetHelper helper = new GetHelper();
		return new Gson().fromJson(helper.getAsString(url, headers), clazz);
	}

	/* (non-Javadoc)
	 * @see com.vladkel.dametenebra.dao.IDAO#insert(java.lang.Object)
	 */
	public boolean insert(T object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vladkel.dametenebra.dao.IDAO#update(java.lang.Object)
	 */
	public boolean update(T object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vladkel.dametenebra.dao.IDAO#delete(int)
	 */
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
