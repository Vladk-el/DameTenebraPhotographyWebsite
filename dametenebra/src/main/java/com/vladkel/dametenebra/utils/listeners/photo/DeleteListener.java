package com.vladkel.dametenebra.utils.listeners.photo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.vladkel.dametenebra.ihm.IhmPhoto;
import com.vladkel.dametenebra.model.Photo;

public class DeleteListener extends MouseAdapter {

	private IhmPhoto ihm;
	private Photo photo;
	
	public DeleteListener(IhmPhoto ihm, Photo photo) {
		this.photo = photo;
		this.ihm = ihm;
	}
	
	public void mouseClicked(MouseEvent e) {
		if (ihm.getDao().delete(photo)) {
			javax.swing.JOptionPane.showMessageDialog(null, "La photo n'est maintenant plus visible sur le site.");
			ihm.modify.dispose();
			
			while(ihm.jf_photo.isVisible()) {
				ihm.jf_photo.dispose();
			}
			
			//ihm.displayAll();
		} else {
			JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement.");
		}
	}
}
