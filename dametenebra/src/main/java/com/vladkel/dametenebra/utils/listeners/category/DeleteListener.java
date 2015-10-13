package com.vladkel.dametenebra.utils.listeners.category;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.vladkel.dametenebra.ihm.IhmCategory;
import com.vladkel.dametenebra.model.Category;

public class DeleteListener extends MouseAdapter {

	private IhmCategory ihm;
	private Category category;
	
	public DeleteListener(IhmCategory ihm, Category category) {
		this.category = category;
		this.ihm = ihm;
	}
	
	public void mouseClicked(MouseEvent e) {
		if (ihm.getDao().delete(category)) {
			JOptionPane.showMessageDialog(null,
					"La catégorie n'est maintenant plus visible sur le site.");
			ihm.modify.dispose();
			ihm.jf_category.dispose();
			ihm.displayAll();
		} else {
			JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement.");
		}
	}
}
