package com.vladkel.dametenebra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.vladkel.dametenebra.ihm.IhmCategory;
import com.vladkel.dametenebra.utils.properties.Property;

/**
 * @author eliott
 *
 */
public class DameTenebra implements ActionListener{

	private IhmCategory category;
	
	/* ihm */
	private JFrame jf;
	private JPanel p;
	
	private JButton add_category;
	private JButton consult_category;
	private JButton add_photo;
	private JButton consult_photo;
	
	public static final String server = Property.getInstance().getProperties().getProperty("prog.target");
	
	public DameTenebra(){
		
		category = new IhmCategory();
		
		jf = new JFrame();
		jf.setTitle("DameTénébra - Version 3.0 -  \u00A9 Eliott Laversin");
		jf.setSize(450, 120);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel();
		jf.setContentPane(p);
		
		add_category = new JButton("Ajouter une catégorie");
		consult_category = new JButton("Consulter les catégories");
		add_photo = new JButton("Ajouter une photo");
		consult_photo = new JButton("Consulter les photos");
		
		p.add(add_category);
		p.add(consult_category);
		p.add(add_photo);
		p.add(consult_photo);
		
		add_category.addActionListener(this);
		consult_category.addActionListener(this);
		add_photo.addActionListener(this);
		consult_photo.addActionListener(this);
		
		jf.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == add_category){
			//Category.create();
		}
		if(e.getSource() == consult_category){
			category.displayAll();
		}
		if(e.getSource() == add_photo){
			//Photo.create();
		}
		if(e.getSource() == consult_photo){
			//Photo.displayAll();
		}
		
	}
	
	public static void main(String[] args) {

		new DameTenebra();
	}	

}
