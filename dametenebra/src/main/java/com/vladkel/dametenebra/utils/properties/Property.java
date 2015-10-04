package com.vladkel.dametenebra.utils.properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * @author eliott
 *
 */
public class Property {
	private static Property instance;

	private static final String FILE_LOCATION = "data/conf/conf.properties";

	private Properties properties;

	private Property() {
		init();
	}

	private void init() {
		try {
			properties = new Properties();
			InputStream input = new FileInputStream(FILE_LOCATION);
			properties.load(input);
			input.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Une erreur est survenue durant le chargement du fichier de configuration. Veuillez réessayer s'il vous plait.");
		}

//		for (Object str : getProperties().keySet()) {
//			System.out.println(str + " ==> " + get((String) str));
//		}

	}

	public static Property getInstance() {
		if (instance == null)
			instance = new Property();
		return instance;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String get(String str) {
		return properties.getProperty(str);
	}
}
