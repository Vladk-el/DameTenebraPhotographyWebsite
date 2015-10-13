package com.vladkel.dametenebra.utils.listeners.photo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author eliott
 *
 */
public class OnOverListener extends MouseAdapter {
	
	private JTextField text_link_photo;

	private JFrame hover = null;
	
	public OnOverListener(JTextField text_link_photo) {
		this.text_link_photo = text_link_photo;
	}

	public void mouseEntered(MouseEvent e) {

		if (text_link_photo.getText().length() > 0) {
			File file = new File("data/img/full/" + text_link_photo.getText());
			if (!file.exists())
				file = new File(text_link_photo.getText());
			if (file.exists()) {
				hover = new JFrame();
				hover.setTitle(file.getName());
				JPanel p_hover = new JPanel();
				hover.setContentPane(p_hover);
				hover.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				JButton b = new JButton(new ImageIcon(file.getAbsolutePath()));
				b.setBorder(BorderFactory.createEmptyBorder());
				b.setContentAreaFilled(false);
				p_hover.add(b);
				hover.setVisible(true);
				hover.pack();
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		if (hover != null) {
			hover.dispose();
			hover = null;
		}
	}
}
