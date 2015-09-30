package com.vladkel.dametenebra.persistence.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.vladkel.dametenebra.model.Category;
import com.vladkel.dametenebra.model.Link;
import com.vladkel.dametenebra.model.Owner;
import com.vladkel.dametenebra.model.Photo;

/**
 * @author eliott
 *
 */
public class DAOIT {
	
	private static Logger log = LoggerFactory.getLogger(DAOIT.class);
	
	private DAO<Photo> photo;
	private DAO<Category> category;
	private DAO<Owner> owner;
	private DAO<Link> link;
	
	@BeforeTest
	public void init() {
		System.out.println("Hello DAOIT");
		photo = new DAO<>(Photo.class);
		category = new DAO<>(Category.class);
		owner = new DAO<>(Owner.class);
		link = new DAO<>(Link.class);
	}

	@Test
	public void photo() {
		System.out.println("################ START PHOTO TEST ################\n");
		
		int photoId = 12;
		/*
		// SELECT
		Photo object = photo.select(photoId);
		//log.info("SELECT : {}", new Gson().toJson(object));
		System.out.println("SELECT : " + new Gson().toJson(object));
		
		
		// INSERT
		boolean insert = photo.insert(object);
		//log.info("INSERT : {}", new Gson().toJson(insert));
		System.out.println("INSERT : " + new Gson().toJson(insert));
		*/
		// SELECT ALL
		List<Photo> objects = photo.select();
		//log.info("SELECT ALL : {}", new Gson().toJson(object));
		System.out.println("SELECT ALL : " + new Gson().toJson(objects));
		//object = objects.get(objects.size() - 1);
		System.out.println("objects.get(1) : " + new Gson().toJson(objects.get(1)));
		Object p = (Object)objects.get(1);
		Photo ph = (Photo)p;
		/*
		// UPDATE
		object.setPhoto_name("HAHAHAHAHA");
		boolean update = photo.update(object);
		//log.info("UPDATE : {}", new Gson().toJson(update));
		System.out.println("UPDATE : " + new Gson().toJson(update));
		
		// REMOVE
		boolean delete = photo.delete(object);
		//log.info("DELETE : {}", new Gson().toJson(delete));
		System.out.println("DELETE : " + new Gson().toJson(delete));
		*/
		System.out.println("################ END PHOTO TEST ################\n\n");
	}
	
	
	
	
	
	
	
	
	
	
}
