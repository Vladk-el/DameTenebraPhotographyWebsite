package com.vladkel.dametenebra;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.vladkel.dametenebra.ihm.IhmCategory;
import com.vladkel.dametenebra.ihm.IhmLink;
import com.vladkel.dametenebra.ihm.IhmOwner;
import com.vladkel.dametenebra.ihm.IhmPhoto;
import com.vladkel.dametenebra.utils.properties.Property;

/**
 * @author eliott
 *
 */
public class DameTenebra implements ActionListener{

	private IhmCategory category;
	private IhmPhoto photo;
	private IhmOwner owner;
	private IhmLink link;
	
	/* ihm */
	private JFrame jf;
	private JPanel p;
	
	private JButton add_category;
	private JButton consult_category;
	private JButton add_photo;
	private JButton consult_photo;
	private JButton add_owner;
	private JButton consult_owner;
	private JButton add_link;
	private JButton consult_link;
	
	public static final String server = Property.getInstance().getProperties().getProperty("prog.target");
	
	public DameTenebra(){
		
		category = new IhmCategory();
		photo = new IhmPhoto();
		owner = new IhmOwner();
		link = new IhmLink();
		
		jf = new JFrame();
		jf.setTitle("DameTénébra - Version 3.0 -  \u00A9 Eliott Laversin");
		jf.setSize(500, 185);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel();
		jf.setContentPane(p);
		
		Dimension d = new Dimension(240, 30); 
		
		add_category = new JButton("Ajouter une catégorie");
		add_category.setPreferredSize(d);
		consult_category = new JButton("Consulter les catégories");
		consult_category.setPreferredSize(d);
		add_photo = new JButton("Ajouter une photo");
		add_photo.setPreferredSize(d);
		consult_photo = new JButton("Consulter les photos");
		consult_photo.setPreferredSize(d);
		add_owner = new JButton("Ajouter un photographe");
		add_owner.setPreferredSize(d);
		consult_owner = new JButton("Consulter les photographes");
		consult_owner.setPreferredSize(d);
		add_link = new JButton("Ajouter un lien");
		add_link.setPreferredSize(d);
		consult_link = new JButton("Consulter les liens");
		consult_link.setPreferredSize(d);
		
		p.add(add_category);
		p.add(consult_category);
		p.add(add_photo);
		p.add(consult_photo);
		p.add(add_owner);
		p.add(consult_owner);
		p.add(add_link);
		p.add(consult_link);
		
		add_category.addActionListener(this);
		consult_category.addActionListener(this);
		add_photo.addActionListener(this);
		consult_photo.addActionListener(this);
		add_owner.addActionListener(this);
		consult_owner.addActionListener(this);
		add_link.addActionListener(this);
		consult_link.addActionListener(this);
		
		jf.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		/* Category */
		if(e.getSource() == add_category){
			category.create();
		}
		if(e.getSource() == consult_category){
			category.displayAll();
		}
		
		/* Photo */
		if(e.getSource() == add_photo){
			photo.create();
		}
		if(e.getSource() == consult_photo){
			photo.displayAll();
		}
		
		/* Owner */
		if(e.getSource() == add_owner){
			owner.create();
		}
		if(e.getSource() == consult_owner){
			owner.displayAll();
		}
		
		/* Link */
		if(e.getSource() == add_link){
			link.create();
		}
		if(e.getSource() == consult_link){
			link.displayAll();
		}
		
	}
	
	public static void main(String[] args) {

		new DameTenebra();
	}	

}
