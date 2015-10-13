package com.vladkel.dametenebra.ihm;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import com.vladkel.dametenebra.utils.ftp.FtpClient;
import com.vladkel.dametenebra.utils.ftp.threads.Retrieve;
import com.vladkel.dametenebra.utils.properties.Property;

/**
 * @author eliott
 *
 */
public class IhmPhoto implements IHM {

	private IDAO<Photo> dao;
	private IDAO<Category> categoryDao;
	private List<Category> categories;

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
	private JLabel title_mini_photo;
	private JTextField text_mini_photo;
	private JLabel title_category_photo;
	private JComboBox<Category> text_category_photo;
	private JButton add_photo;
	private String current_date;

	private JFrame hover;
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
		title_mini_photo = null;
		text_mini_photo = null;
		title_category_photo = null;
		text_category_photo = null;
		add_photo = null;
		img_mini = null;

		current_date = null;
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
		title_mini_photo = new JLabel("Nom de la miniature");
		text_mini_photo = new JTextField();
		title_category_photo = new JLabel("Categorie concernée");
		text_category_photo = new JComboBox<Category>(categories.toArray(new Category[categories.size()]));
		add_photo = new JButton("Ajouter");

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
		// TODO Auto-generated method stub

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#modify(java.lang.Object)
	 */
	@Override
	public void modify(Object object) {
		// TODO Auto-generated method stub

	}
	
	public void setIcon(ImageIcon icon) {
		img_mini.setIcon(icon);
		modify.repaint();
	}
	
	public void downloadFiles(String src_full, String src_mini){
		File file = new File("data/" + src_mini);
		FtpClient client = null;
		if(!file.exists()){
			if(DameTenebra.server.equalsIgnoreCase("local")){
				client = new FtpClient("localhost", Property.getInstance().get("ftp.user"), Property.getInstance().get("ftp.pwd"));
			}
			if(DameTenebra.server.equalsIgnoreCase("prod")){
				client = new FtpClient(Property.getInstance().get("ftp.url"), 
									   Property.getInstance().get("ftp.user"), 
									   Property.getInstance().get("ftp.pwd")
							 );
			}
			
			Thread mini = new Thread(new Retrieve(client, file, "www/" + src_mini.substring(0, src_full.lastIndexOf("/"))));
			file = new File("data/" + src_full);
			Thread full = new Thread(new Retrieve(client, file, "www/" + src_full.substring(0, src_full.lastIndexOf("/")), mini));
			full.start();
		}
		else{
			System.out.println("Files are already in cache, great !");
			setIcon(new ImageIcon("data/img/mini/" + file.getName()));
		}
	}

}
