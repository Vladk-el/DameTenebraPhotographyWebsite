package com.vladkel.dametenebra.utils.listeners.photo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import com.vladkel.dametenebra.ihm.IhmPhoto;
import com.vladkel.dametenebra.utils.file.FileUtils;
import com.vladkel.dametenebra.utils.img.ImgManager;

/**
 * @author eliott
 *
 */
public class ChooseFileListener extends MouseAdapter {

	private IhmPhoto ihm;
	
	public ChooseFileListener(IhmPhoto ihm) {
		this.ihm = ihm;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		
		final JFileChooser fc = new JFileChooser();
		
		int returnVal = fc.showOpenDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (new FileUtils().isAnImage(file)) {
				ihm.getTextLinkPhoto().setText(file.getName());
				boolean imageIsResized = new ImgManager(file).resizeAndWrite();
				if (imageIsResized) {
					ihm.setIcon(new ImageIcon("data/img/mini/" + file.getName()));
					ihm.getModifyFrame().repaint();
				}
			} else {
				javax.swing.JOptionPane.showMessageDialog(null,
						"Veuillez sélectionner un fichier image valide.");
			}
		}
	}
	
}
