package com.vladkel.dametenebra.utils.listeners.category;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.vladkel.dametenebra.ihm.IhmCategory;
import com.vladkel.dametenebra.model.Category;

public class SaveListener extends MouseAdapter {
	
	private IhmCategory ihm;
	private Category category;
	
	public SaveListener(IhmCategory ihm, Category category) {
		this.category = category;
		this.ihm = ihm;
	}

	public void mouseClicked(MouseEvent e) {
		category.setCategory_name(ihm.getCategoryName());
		category.setCategory_description(ihm.getCategoryDescription());
		category.setActive(ihm.getActive());
		if (category.getCategory_id() == 0 ? ihm.getDao().insert(category) : ihm.getDao().update(category)) {
			JOptionPane.showMessageDialog(null, "La catégorie a bien été sauvegardée.");
			ihm.modify.dispose();
			ihm.jf_category.dispose();
			ihm.displayAll();
		} else {
			JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement.");
		}
	}
}
