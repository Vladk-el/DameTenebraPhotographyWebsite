package com.vladkel.dametenebra.persistence.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(DAOIT.class);
	
	private DAO<Photo> photo;
	private DAO<Category> category;
	private DAO<Owner> owner;
	private DAO<Link> link;
	
	@BeforeTest
	public void init() {
		System.out.println("Hello DAOIT");
		photo = new DAO<>(Photo.class, new TypeToken<List<Photo>>() {}.getType());
		category = new DAO<>(Category.class, new TypeToken<List<Category>>() {}.getType());
		owner = new DAO<>(Owner.class, new TypeToken<List<Owner>>() {}.getType());
		link = new DAO<>(Link.class, new TypeToken<List<Link>>() {}.getType());
	}

	@Test
	public void photo() {
		System.out.println("################ START Photo TEST ################\n");
		
		int id = 12;
		
		// SELECT
		Photo object = photo.select(id);
		//log.info("SELECT : {}", new Gson().toJson(object));
		System.out.println("SELECT : " + new Gson().toJson(object));
		
		
		// INSERT
		boolean insert = photo.insert(object);
		//log.info("INSERT : {}", new Gson().toJson(insert));
		System.out.println("INSERT : " + new Gson().toJson(insert));
		
		// SELECT ALL
		List<Photo> objects = photo.select();
		//log.info("SELECT ALL : {}", new Gson().toJson(object));
		System.out.println("SELECT ALL : " + new Gson().toJson(objects));
		object = objects.get(objects.size() - 1);
		
		// UPDATE
		object.setPhoto_name("HAHAHAHAHA");
		boolean update = photo.update(object);
		//log.info("UPDATE : {}", new Gson().toJson(update));
		System.out.println("UPDATE : " + new Gson().toJson(update));
		
		// REMOVE
		boolean delete = photo.delete(object);
		//log.info("DELETE : {}", new Gson().toJson(delete));
		System.out.println("DELETE : " + new Gson().toJson(delete));
		
		System.out.println("################ END Photo TEST ################\n\n");
	}
	
	@Test
	public void category() {
		System.out.println("################ START Category TEST ################\n");
		
		int id = 14;
		
		// SELECT
		Category object = category.select(id);
		//log.info("SELECT : {}", new Gson().toJson(object));
		System.out.println("SELECT : " + new Gson().toJson(object));
		
		
		// INSERT
		boolean insert = category.insert(object);
		//log.info("INSERT : {}", new Gson().toJson(insert));
		System.out.println("INSERT : " + new Gson().toJson(insert));
		
		// SELECT ALL
		List<Category> objects = category.select();
		//log.info("SELECT ALL : {}", new Gson().toJson(object));
		System.out.println("SELECT ALL : " + new Gson().toJson(objects));
		object = objects.get(objects.size() - 1);
		
		// UPDATE
		object.setCategory_name("HAHAHAHAHA");
		boolean update = category.update(object);
		//log.info("UPDATE : {}", new Gson().toJson(update));
		System.out.println("UPDATE : " + new Gson().toJson(update));
		
		// REMOVE
		boolean delete = category.delete(object);
		//log.info("DELETE : {}", new Gson().toJson(delete));
		System.out.println("DELETE : " + new Gson().toJson(delete));
		
		System.out.println("################ END Category TEST ################\n\n");
	}
	
	@Test
	public void owner() {
		System.out.println("################ START Owner TEST ################\n");
		
		int id = 12;
		
		// SELECT
		Owner object = owner.select(id);
		//log.info("SELECT : {}", new Gson().toJson(object));
		System.out.println("SELECT : " + new Gson().toJson(object));
		
		
		// INSERT
		boolean insert = owner.insert(object);
		//log.info("INSERT : {}", new Gson().toJson(insert));
		System.out.println("INSERT : " + new Gson().toJson(insert));
		
		// SELECT ALL
		List<Owner> objects = owner.select();
		//log.info("SELECT ALL : {}", new Gson().toJson(object));
		System.out.println("SELECT ALL : " + new Gson().toJson(objects));
		object = objects.get(objects.size() - 1);
		
		// UPDATE
		object.setOwner_name("HAHAHAHAHA");
		boolean update = owner.update(object);
		//log.info("UPDATE : {}", new Gson().toJson(update));
		System.out.println("UPDATE : " + new Gson().toJson(update));
		
		// REMOVE
		boolean delete = owner.delete(object);
		//log.info("DELETE : {}", new Gson().toJson(delete));
		System.out.println("DELETE : " + new Gson().toJson(delete));
		
		System.out.println("################ END Owner TEST ################\n\n");
	}
	
	@Test
	public void link() {
		System.out.println("################ START Link TEST ################\n");
		
		int id = 12;
		
		// SELECT
		Link object = link.select(id);
		//log.info("SELECT : {}", new Gson().toJson(object));
		System.out.println("SELECT : " + new Gson().toJson(object));
		
		
		// INSERT
		boolean insert = link.insert(object);
		//log.info("INSERT : {}", new Gson().toJson(insert));
		System.out.println("INSERT : " + new Gson().toJson(insert));
		
		// SELECT ALL
		List<Link> objects = link.select();
		//log.info("SELECT ALL : {}", new Gson().toJson(object));
		System.out.println("SELECT ALL : " + new Gson().toJson(objects));
		object = objects.get(objects.size() - 1);
		
		// UPDATE
		object.setLink_name("HAHAHAHAHA");
		boolean update = link.update(object);
		//log.info("UPDATE : {}", new Gson().toJson(update));
		System.out.println("UPDATE : " + new Gson().toJson(update));
		
		// REMOVE
		boolean delete = link.delete(object);
		//log.info("DELETE : {}", new Gson().toJson(delete));
		System.out.println("DELETE : " + new Gson().toJson(delete));
		
		System.out.println("################ END Link TEST ################\n\n");
	}
	
	
	
	
	
	
	
	
	
	
}
