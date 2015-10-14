package com.vladkel.dametenebra.ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.google.common.reflect.TypeToken;
import com.vladkel.dametenebra.DameTenebra;
import com.vladkel.dametenebra.model.Category;
import com.vladkel.dametenebra.model.Photo;
import com.vladkel.dametenebra.persistence.dao.DAO;
import com.vladkel.dametenebra.persistence.dao.IDAO;
import com.vladkel.dametenebra.utils.file.FileUtils;
import com.vladkel.dametenebra.utils.ftp.FtpClient;
import com.vladkel.dametenebra.utils.ftp.threads.Retrieve;
import com.vladkel.dametenebra.utils.ftp.threads.Store;
import com.vladkel.dametenebra.utils.img.ImgManager;
import com.vladkel.dametenebra.utils.listeners.photo.DeleteListener;
import com.vladkel.dametenebra.utils.listeners.photo.OnOverListener;
import com.vladkel.dametenebra.utils.listeners.photo.SaveListener;
import com.vladkel.dametenebra.utils.properties.Property;

/**
 * @author eliott
 *
 */
public class IhmPhoto implements IHM {

	private IDAO<Photo> dao;
	private IDAO<Category> categoryDao;
	private List<Category> categories;
	private FileUtils utils;

	/* ihm */
	private JFrame jf_photo;
	private JPanel p_photo;
	private JButton modif;
	private GridBagLayout bl;
	private JLabel title_name_photo;
	private JTextField text_name_photo;
	private JLabel title_desc_photo;
	private JTextField text_desc_photo;
	private JLabel title_link_photo;
	private JTextField text_link_photo;
	private JLabel title_category_photo;
	private JComboBox<Category> text_category_photo;
	private JButton add_photo;
	private JCheckBox is_active;

	private JFrame modify;

	private JButton save;
	private JButton delete;
	private JButton img_mini;

	@SuppressWarnings("serial")
	public IhmPhoto() {
		super();
		dao = new DAO<Photo>(Photo.class, new TypeToken<List<Photo>>() {
		}.getType());
		categoryDao = new DAO<Category>(Category.class, new TypeToken<List<Category>>() {
		}.getType());
		categories = categoryDao.select();
		utils = new FileUtils();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#init()
	 */
	@Override
	public void init() {
		empty();
		fill();
		p_photo.removeAll();
		p_photo.repaint();

		jf_photo.setSize(800, 600);
		jf_photo.setLocationRelativeTo(null);
		jf_photo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		p_photo.setLayout(bl);
		jf_photo.setContentPane(p_photo);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#empty()
	 */
	@Override
	public void empty() {
		jf_photo = null;
		p_photo = null;
		bl = null;

		title_name_photo = null;
		text_name_photo = null;
		title_desc_photo = null;
		text_desc_photo = null;
		title_link_photo = null;
		text_link_photo = null;
		title_category_photo = null;
		text_category_photo = null;
		add_photo = null;
		img_mini = null;
		is_active = null;

		modif = null;

		save = null;
		delete = null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#fill()
	 */
	@Override
	public void fill() {
		jf_photo = new JFrame();
		p_photo = new JPanel();
		bl = new GridBagLayout();

		title_name_photo = new JLabel("Nom");
		text_name_photo = new JTextField();
		title_desc_photo = new JLabel("Description");
		text_desc_photo = new JTextField();
		title_link_photo = new JLabel("Nom du fichier");
		text_link_photo = new JTextField();
		title_category_photo = new JLabel("Categorie concernée");
		text_category_photo = new JComboBox<Category>(categories.toArray(new Category[categories.size()]));
		add_photo = new JButton("Ajouter");
		is_active = new JCheckBox("Rendre visible sur le site ?");

		modif = new JButton("Détail");

		save = new JButton("Sauvegarder");
		delete = new JButton("Supprimer");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#displayAll()
	 */
	@Override
	public void displayAll() {
		init();
		jf_photo.setTitle("Consulter les photos");
		jf_photo.setResizable(false);

		final List<Photo> photos = dao.select();
		Object[][] data = new Object[photos.size()][6];

		for (int i = 0; i < photos.size(); i++) {
			data[i][0] = photos.get(i).getPhoto_id();
			data[i][1] = photos.get(i).getPhoto_name();
			data[i][2] = photos.get(i).getPhoto_description();
			data[i][3] = photos.get(i).getPhoto_date();

			for (Category category : categories) {
				if (category.getCategory_id() == photos.get(i).getCategory_photo()) {
					data[i][4] = category.getCategory_name();
					break;
				}
			}

			data[i][5] = photos.get(i).getActive() == 1 ? "oui" : "non";
		}

		String[] entete = { "Id", "Nom", "Description", "Date d'Ajout", "Categorie", "Visible" };
		final JTable tab = new JTable(data, entete);

		modif.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (tab.getSelectedRow() != -1) {
					modify(photos.get(tab.getSelectedRow()));
				}
			}
		});

		p_photo.add(new JScrollPane(tab));
		p_photo.add(modif);
		jf_photo.setVisible(true);
		p_photo.repaint();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#create()
	 */
	@Override
	public void create() {
		init();
		modify = new JFrame();
		modify.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modify.setTitle("Ajouter une photo");
		modify.setSize(new Dimension(380, 450));
		modify.setLocationRelativeTo(null);
		modify.setResizable(false);

		JPanel mp = new JPanel();
		modify.setContentPane(mp);
		mp.setLayout(new FlowLayout());

		text_name_photo.setPreferredSize(new Dimension(350, 20));
		text_desc_photo.setPreferredSize(new Dimension(350, 20));
		text_link_photo.setPreferredSize(new Dimension(350, 20));
		text_category_photo.setPreferredSize(new Dimension(350, 20));
		title_category_photo.setPreferredSize(new Dimension(350, 20));

		img_mini = new JButton(new ImageIcon());
		img_mini.setBorder(BorderFactory.createEmptyBorder());
		img_mini.setContentAreaFilled(false);

		mp.add(title_name_photo);
		mp.add(text_name_photo);
		mp.add(title_desc_photo);
		mp.add(text_desc_photo);
		mp.add(title_link_photo);
		mp.add(text_link_photo);
		mp.add(img_mini);
		mp.add(title_category_photo);
		mp.add(text_category_photo);

		mp.add(add_photo);

		final Date date = new Date();
		final DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");

		text_category_photo.setSelectedIndex(0);

		final JFileChooser fc = new JFileChooser();

		text_link_photo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					if (utils.isAnImage(file)) {
						text_link_photo.setText(file.getName());
						boolean imageIsResized = new ImgManager(file).resizeAndWrite();
						if (imageIsResized) {
							img_mini.setIcon(new ImageIcon("data/img/mini/" + file.getName()));
							modify.repaint();
						}
					} else {
						javax.swing.JOptionPane.showMessageDialog(null,
								"Veuillez sélectionner un fichier image valide.");
					}
				}
			}

		});
		
		img_mini.addMouseListener(new OnOverListener(text_link_photo));
		
		Photo photo = new Photo();
		photo.setPhoto_id(0);
		photo.setPhoto_date(formate.format(date));
		
		add_photo.addMouseListener(new SaveListener(this, photo));

		modify.setVisible(true);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#modify(java.lang.Object)
	 */
	@Override
	public void modify(Object object) {
		final Photo photo = (Photo) object;
		modify = new JFrame();

		modify.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modify.setTitle("Modifier la categorie : " + photo.getPhoto_name());
		modify.setSize(new Dimension(380, 450));
		modify.setLocationRelativeTo(null);
		modify.setResizable(false);

		JPanel mp = new JPanel();
		modify.setContentPane(mp);
		mp.setLayout(new FlowLayout());

		Dimension d = new Dimension(350, 20);

		text_name_photo.setPreferredSize(d);
		text_desc_photo.setPreferredSize(d);
		text_link_photo.setPreferredSize(d);
		text_category_photo.setPreferredSize(d);
		title_category_photo.setPreferredSize(d);
		is_active.setPreferredSize(d);

		File mini = new File("data/img/mini/" + photo.getPhoto_mini_link());
		img_mini = new JButton(new ImageIcon(mini.getAbsolutePath()));
		img_mini.setBorder(BorderFactory.createEmptyBorder());
		img_mini.setContentAreaFilled(false);

		mp.add(title_name_photo);
		mp.add(text_name_photo);
		mp.add(title_desc_photo);
		mp.add(text_desc_photo);
		mp.add(title_link_photo);
		mp.add(text_link_photo);
		mp.add(img_mini);
		mp.add(title_category_photo);
		mp.add(text_category_photo);
		mp.add(is_active);

		mp.add(save);
		mp.add(delete);

		text_name_photo.setText(photo.getPhoto_name());
		text_desc_photo.setText(photo.getPhoto_description());
		text_link_photo.setText(photo.getPhoto_link());
		is_active.setSelected(photo.getActive() == 1 ? true : false);

		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategory_id() == photo.getCategory_photo()) {
				text_category_photo.setSelectedIndex(i);
				break;
			}
		}

		final JFileChooser fc = new JFileChooser();

		/**
		 * Listeners
		 */

		text_link_photo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					if (utils.isAnImage(file)) {
						text_link_photo.setText(file.getName());
						boolean imageIsResized = new ImgManager(file).resizeAndWrite();
						if (imageIsResized) {
							img_mini.setIcon(new ImageIcon("data/img/mini/" + file.getName()));
							modify.repaint();
						}
					} else {
						javax.swing.JOptionPane.showMessageDialog(null,
								"Veuillez sélectionner un fichier image valide.");
					}
				}
			}
		});

		img_mini.addMouseListener(new OnOverListener(text_link_photo));

		save.addMouseListener(new SaveListener(this, photo));

		delete.addMouseListener(new DeleteListener(this, photo));

		downloadFiles("img/full/" + photo.getPhoto_link(), "img/mini/" + photo.getPhoto_mini_link());

		modify.setVisible(true);
	}

	public void setIcon(ImageIcon icon) {
		img_mini.setIcon(icon);
		modify.repaint();
	}

	public void store() {

		// storeOnCache();
		storeOnline();
	}

	public void storeOnline() {
		FtpClient client = null;

		if (DameTenebra.server.equalsIgnoreCase("local")) {
			client = new FtpClient("localhost", Property.getInstance().get("ftp.user"),
					Property.getInstance().get("ftp.pwd"));
		} else if (DameTenebra.server.equalsIgnoreCase("prod")) {
			client = new FtpClient(Property.getInstance().get("ftp.url"), Property.getInstance().get("ftp.user"),
					Property.getInstance().get("ftp.pwd"));
		}

		File img = new File(new File(text_link_photo.getText()).getName());
		File img_mini = new File(new File(text_link_photo.getText()).getName());

		Thread mini = new Thread(
				new Store(client, new File("data/img/mini/" + text_link_photo.getText()), img_mini, "/www/img/mini"));
		Thread full = new Thread(
				new Store(client, new File("data/img/full/" + text_link_photo.getText()), img, "/www/img/full", mini));
		full.start();

	}

	// public void storeOnCache() {
	//
	// System.out.println("Storing in cache . . .");
	//
	// File full = new File("data/img/full/" + new
	// File(text_link_photo.getText()).getName());
	// File mini = new File("data/img/mini/" + new
	// File(text_link_photo.getText()).getName());
	// try {
	// utils.copyFile(new File(text_link_photo.getText()), full);
	// utils.copyFile(new File(text_link_photo.getText()), mini);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// System.out.println("Storing in cache done.");
	//
	// }

	public void downloadFiles(String src_full, String src_mini) {
		File file = new File("data/" + src_mini);
		FtpClient client = null;
		if (!file.exists()) {
			if (DameTenebra.server.equalsIgnoreCase("local")) {
				client = new FtpClient("localhost", Property.getInstance().get("ftp.user"),
						Property.getInstance().get("ftp.pwd"));
			}
			if (DameTenebra.server.equalsIgnoreCase("prod")) {
				client = new FtpClient(Property.getInstance().get("ftp.url"), Property.getInstance().get("ftp.user"),
						Property.getInstance().get("ftp.pwd"));
			}

			Thread mini = new Thread(
					new Retrieve(client, file, "www/" + src_mini.substring(0, src_full.lastIndexOf("/")), this));
			file = new File("data/" + src_full);
			Thread full = new Thread(
					new Retrieve(client, file, "www/" + src_full.substring(0, src_full.lastIndexOf("/")), mini));
			full.start();
		} else {
			System.out.println("Files are already in cache, great !");
			setIcon(new ImageIcon("data/img/mini/" + file.getName()));
		}
	}

	public IDAO<Photo> getDao() {
		return dao;
	}

	public String getPhotoName() {
		return text_name_photo.getText();
	}

	public String getPhotoDescription() {
		return text_desc_photo.getText();
	}

	public String getPhotoLink() {
		return text_link_photo.getText().substring(text_link_photo.getText().lastIndexOf("\\") + 1);
	}

	public int getPhotoCategory() {
		return categories.get(text_category_photo.getSelectedIndex()).getCategory_id();
	}

	public int getActive() {
		return is_active.isSelected() ? 1 : 0;
	}

	public JFrame getMainFrame() {
		return jf_photo;
	}

	public JFrame getModifyFrame() {
		return modify;
	}

}
