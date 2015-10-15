package com.vladkel.dametenebra.utils.listeners.photo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.vladkel.dametenebra.ihm.IhmPhoto;
import com.vladkel.dametenebra.model.Photo;

/**
 * @author eliott
 *
 */
public class SaveListener extends MouseAdapter {

	private IhmPhoto ihm;
	private Photo photo;

	public SaveListener(IhmPhoto ihm, Photo photo) {
		this.photo = photo;
		this.ihm = ihm;
	}

	public void mouseClicked(MouseEvent e) {
		photo.setPhoto_name(ihm.getPhotoName());
		photo.setPhoto_description(ihm.getPhotoDescription());
		photo.setPhoto_link(ihm.getPhotoLink());
		photo.setPhoto_mini_link(photo.getPhoto_link());
		photo.setCategory_photo(ihm.getPhotoCategory());
		photo.setActive(ihm.getActive());

		if (photo.getPhoto_name().length() > 0 && photo.getPhoto_link().length() > 0) {
			if (photo.getPhoto_id() == 0 ? ihm.getDao().insert(photo) : ihm.getDao().update(photo)) {
				ihm.store();
				JOptionPane.showMessageDialog(null, "Votre photo à bien été sauvegardée.");
				ihm.getModifyFrame().dispose();
				ihm.getMainFrame().dispose();
				ihm.displayAll();
			} else {
				JOptionPane.showMessageDialog(null,
						"Un problème est survenu, veuillez vérifier l'intégrité des données et votre connexion internet.");
			}
		} else {
			if (photo.getPhoto_name().length() == 0 && photo.getPhoto_link().length() == 0) {
				ihm.getModifyFrame().dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Veuillez donner un nom et un fichier à vote photo.");
			}
		}

	}

}
