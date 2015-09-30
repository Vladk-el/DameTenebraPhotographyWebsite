package com.vladkel.dametenebra.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.vladkel.dametenebra.utils.http.GetHelper;
import com.vladkel.dametenebra.utils.http.PostHelper;

/**
 * @author eliott
 *
 */
public class DAO<T> implements IDAO<T> {
	
	private static String DEFAULT_PATH = "http://localhost/dtpv2/php/services/"; //"http://www.dametenebra.com/php/services/";
	private static String CATEGORIES = "categories"; // ==> set in property class
	private static String INSERT = "_insert";
	private static String UPDATE = "_update";
	private static String DELETE = "_delete";
	private static String PHP = ".php";
	private static String SUCCESS = "success";

	private Class<T> clazz;
	private Type type;
	private Map<String, String> headers;
	
	public DAO(Class<T> clazz, Type type) {
		this.clazz = clazz;
		this.type = type;
		headers = new HashMap<>();
		headers.put("Content-Type", "application/json; charset=UTF-8");
	}
	
	/** (non-Javadoc)
	 * @see com.vladkel.dametenebra.dao.IDAO#select()
	 **/
	public List<T> select() {
		String url = new StringBuilder()
				.append(DEFAULT_PATH)
				.append(clazz.getSimpleName().toLowerCase())
				.append("/")
				.append(clazz.getSimpleName().toLowerCase().equalsIgnoreCase("category") ? CATEGORIES : clazz.getSimpleName().toLowerCase() + "s") // => properties.getPlurials(clazz.getSimpleName())
				.append(PHP)
				.toString();
		GetHelper helper = new GetHelper();
		return new Gson().fromJson(helper.getAsString(url, headers), type);
	}

	/** (non-Javadoc)
	 * @see com.vladkel.dametenebra.dao.IDAO#select(int)
	 **/
	public T select(int id) {
		String url = new StringBuilder()
				.append(DEFAULT_PATH)
				.append(clazz.getSimpleName().toLowerCase())
				.append("/")
				.append(clazz.getSimpleName().toLowerCase())
				.append(PHP)
				.append("?")
				.append(clazz.getSimpleName().toLowerCase())
				.append("=")
				.append(id)
				.toString();
		GetHelper helper = new GetHelper();
		return new Gson().fromJson(helper.getAsString(url, headers), clazz);
	}

	/** (non-Javadoc)
	 * @see com.vladkel.dametenebra.dao.IDAO#insert(java.lang.Object)
	 **/
	public boolean insert(T object) {
		String url = new StringBuilder()
				.append(DEFAULT_PATH)
				.append(clazz.getSimpleName().toLowerCase())
				.append("/")
				.append(clazz.getSimpleName().toLowerCase())
				.append(INSERT)
				.append(PHP)
				.toString();
		PostHelper helper = new PostHelper();
		return helper.postAsString(url, new Gson().toJson(object), headers).equalsIgnoreCase(SUCCESS) ? true : false;
	}

	/** (non-Javadoc)
	 * @see com.vladkel.dametenebra.dao.IDAO#update(java.lang.Object)
	 **/
	public boolean update(T object) {
		String url = new StringBuilder()
				.append(DEFAULT_PATH)
				.append(clazz.getSimpleName().toLowerCase())
				.append("/")
				.append(clazz.getSimpleName().toLowerCase())
				.append(UPDATE)
				.append(PHP)
				.toString();
		PostHelper helper = new PostHelper();
		return helper.postAsString(url, new Gson().toJson(object), headers).equalsIgnoreCase(SUCCESS) ? true : false;
	}

	/** (non-Javadoc)
	 * @see com.vladkel.dametenebra.dao.IDAO#delete(int)
	 **/
	public boolean delete(T object) {
		String url = new StringBuilder()
				.append(DEFAULT_PATH)
				.append(clazz.getSimpleName().toLowerCase())
				.append("/")
				.append(clazz.getSimpleName().toLowerCase())
				.append(DELETE)
				.append(PHP)
				.toString();
		PostHelper helper = new PostHelper();
		return helper.postAsString(url, new Gson().toJson(object), headers).equalsIgnoreCase(SUCCESS) ? true : false;
	}

}
