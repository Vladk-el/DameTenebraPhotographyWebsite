package com.vladkel.dametenebra.utils.ftp.progressbar;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * @author eliott
 *
 */
public class ProgressBar extends JFrame{

	private static final long serialVersionUID = 8785129814882330622L;
	public JProgressBar progressbar;
	
	
	public ProgressBar(String name){
		super();
		this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel pane = new JPanel();
        pane.setLayout(new FlowLayout());
        progressbar = new JProgressBar(0, 100);
        progressbar.setValue(0);
        progressbar.setStringPainted(true);
        pane.add(progressbar);
        setContentPane(pane);
        setVisible(true);
        pack();
	}


}
