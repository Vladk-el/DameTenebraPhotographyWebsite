package com.vladkel.dametenebra.persistence.dao;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vladkel.dametenebra.model.Category;
import com.vladkel.dametenebra.model.Link;
import com.vladkel.dametenebra.model.Owner;
import com.vladkel.dametenebra.model.Photo;

/**
 * @author eliott
 *
 */
public class DAOIT {
	
	private DAO<Photo> photo;
	private DAO<Category> category;
	private DAO<Owner> owner;
	private DAO<Link> link;
	
	@BeforeTest
	public void init() {
		photo = new DAO<>(Photo.class, new TypeToken<List<Photo>>() {}.getType());
		category = new DAO<>(Category.class, new TypeToken<List<Category>>() {}.getType());
		owner = new DAO<>(Owner.class, new TypeToken<List<Owner>>() {}.getType());
		link = new DAO<>(Link.class, new TypeToken<List<Link>>() {}.getType());
	}

	@Test(enabled=false)
	public void photo() {
		System.out.println("################ START Photo TEST ################\n");
		
		int id = 12;
		
		// SELECT
		Photo object = photo.select(id);
		System.out.println("SELECT : " + new Gson().toJson(object));
		
		
		// INSERT
		boolean insert = photo.insert(object);
		System.out.println("INSERT : " + new Gson().toJson(insert));
		
		// SELECT ALL
		List<Photo> objects = photo.select();
		System.out.println("SELECT ALL : " + new Gson().toJson(object));
		object = objects.get(objects.size() - 1);
		
		// UPDATE
		object.setPhoto_name("HAHAHAHAHA");
		boolean update = photo.update(object);
		System.out.println("UPDATE : " + new Gson().toJson(update));
		
		// REMOVE
		boolean delete = photo.delete(object);
		System.out.println("DELETE : " + new Gson().toJson(delete));
		
		System.out.println("################ END Photo TEST ################\n\n");
	}
	
	@Test(enabled=false)
	public void category() {
		System.out.println("################ START Category TEST ################\n");
		
		int id = 14;
		
		// SELECT
		Category object = category.select(id);
		System.out.println("SELECT : " + new Gson().toJson(object));
		
		
		// INSERT
		boolean insert = category.insert(object);
		System.out.println("INSERT : " + new Gson().toJson(insert));
		
		// SELECT ALL
		List<Category> objects = category.select();
		System.out.println("SELECT ALL : " + new Gson().toJson(objects));
		object = objects.get(objects.size() - 1);
		
		// UPDATE
		object.setCategory_name("HAHAHAHAHA");
		boolean update = category.update(object);
		System.out.println("UPDATE : " + new Gson().toJson(update));
		
		// REMOVE
		boolean delete = category.delete(object);
		System.out.println("DELETE : " + new Gson().toJson(delete));
		
		System.out.println("################ END Category TEST ################\n\n");
	}
	
	@Test(enabled=false)
	public void owner() {
		System.out.println("################ START Owner TEST ################\n");
		
		int id = 12;
		
		// SELECT
		Owner object = owner.select(id);
		System.out.println("SELECT : " + new Gson().toJson(object));
		
		
		// INSERT
		boolean insert = owner.insert(object);
		System.out.println("INSERT : " + new Gson().toJson(insert));
		
		// SELECT ALL
		List<Owner> objects = owner.select();
		System.out.println("SELECT ALL : " + new Gson().toJson(objects));
		object = objects.get(objects.size() - 1);
		
		// UPDATE
		object.setOwner_name("HAHAHAHAHA");
		boolean update = owner.update(object);
		System.out.println("UPDATE : " + new Gson().toJson(update));
		
		// REMOVE
		boolean delete = owner.delete(object);
		System.out.println("DELETE : " + new Gson().toJson(delete));
		
		System.out.println("################ END Owner TEST ################\n\n");
	}
	
	@Test(enabled=false)
	public void link() {
		System.out.println("################ START Link TEST ################\n");
		
		int id = 12;
		
		// SELECT
		Link object = link.select(id);
		System.out.println("SELECT : " + new Gson().toJson(object));
		
		
		// INSERT
		boolean insert = link.insert(object);
		System.out.println("INSERT : " + new Gson().toJson(insert));
		
		// SELECT ALL
		List<Link> objects = link.select();
		System.out.println("SELECT ALL : " + new Gson().toJson(objects));
		object = objects.get(objects.size() - 1);
		
		// UPDATE
		object.setLink_name("HAHAHAHAHA");
		boolean update = link.update(object);
		System.out.println("UPDATE : " + new Gson().toJson(update));
		
		// REMOVE
		boolean delete = link.delete(object);
		System.out.println("DELETE : " + new Gson().toJson(delete));
		
		System.out.println("################ END Link TEST ################\n\n");
	}
	
	
	
	
	
	
	
	
	
	
}
