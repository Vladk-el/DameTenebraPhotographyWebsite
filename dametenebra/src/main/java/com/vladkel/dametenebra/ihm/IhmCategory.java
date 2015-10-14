package com.vladkel.dametenebra.ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.google.common.reflect.TypeToken;
import com.vladkel.dametenebra.model.Category;
import com.vladkel.dametenebra.persistence.dao.DAO;
import com.vladkel.dametenebra.persistence.dao.IDAO;
import com.vladkel.dametenebra.utils.listeners.category.DeleteListener;
import com.vladkel.dametenebra.utils.listeners.category.SaveListener;

/**
 * @author eliott
 *
 */
public class IhmCategory implements IHM {

	private IDAO<Category> dao;

	/* ihm */
	public JFrame jf_category;
	private JPanel p_category;
	private JButton modif;
	private GridBagLayout bl;
	private JLabel title_name_category;
	private JTextField text_name_category;
	private JLabel title_desc_category;
	private JEditorPane text_desc_category;
	private JButton add_category;
	private JCheckBox is_active;

	private JButton save;
	private JButton delete;

	public JFrame modify;

	@SuppressWarnings("serial")
	public IhmCategory() {
		super();
		dao = new DAO<Category>(Category.class, new TypeToken<List<Category>>() {
		}.getType());
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
		p_category.removeAll();
		p_category.repaint();

		jf_category.setSize(800, 600);
		jf_category.setLocationRelativeTo(null);
		jf_category.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		p_category.setLayout(bl);
		jf_category.setContentPane(p_category);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#empty()
	 */
	@Override
	public void empty() {
		jf_category = null;
		p_category = null;
		bl = null;
		title_name_category = null;
		text_name_category = null;
		title_desc_category = null;
		text_desc_category = null;
		add_category = null;
		modif = null;
		is_active = null;

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
		jf_category = new JFrame();
		p_category = new JPanel();
		bl = new GridBagLayout();
		title_name_category = new JLabel("Nom");
		text_name_category = new JTextField();
		title_desc_category = new JLabel("Description");
		text_desc_category = new JEditorPane();
		add_category = new JButton("Ajouter");
		modif = new JButton("Modifier");
		is_active = new JCheckBox("Rendre visible sur le site ?");

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
		jf_category.setTitle("Consulter les categories");

		final List<Category> categories = dao.select();
		Object[][] data = new Object[categories.size()][5];

		for (int i = 0; i < categories.size(); i++) {
			data[i][0] = categories.get(i).getCategory_id();
			data[i][1] = categories.get(i).getCategory_name();
			data[i][2] = categories.get(i).getCategory_description();
			data[i][3] = categories.get(i).getCategory_date();
			data[i][4] = categories.get(i).getActive() == 1 ? "oui" : "non";
		}

		String[] entete = { "Id", "Nom", "Description", "Date d'Ajout", "Visible" };
		final JTable tab = new JTable(data, entete);

		modif.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (tab.getSelectedRow() != -1) {
					modify(categories.get(tab.getSelectedRow()));
				}
			}
		});

		p_category.add(new JScrollPane(tab));
		p_category.add(modif);
		jf_category.setVisible(true);
		p_category.repaint();
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
		modify.setTitle("Ajouter une categorie");
		modify.setSize(new Dimension(300, 400));
		modify.setLocationRelativeTo(null);

		JPanel mp = new JPanel();
		modify.setContentPane(mp);
		mp.setLayout(new FlowLayout());

		text_name_category.setPreferredSize(new Dimension(250, 20));
		text_desc_category.setPreferredSize(new Dimension(250, 80));

		mp.add(title_name_category);
		mp.add(text_name_category);
		mp.add(title_desc_category);
		mp.add(new JScrollPane(text_desc_category));
		mp.add(is_active);
		mp.add(add_category);

		final Date date = new Date();
		final DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");

		Category category = new Category();
		category.setCategory_id(0);
		category.setCategory_date(formate.format(date));

		add_category.addMouseListener(new SaveListener(this, category));

		modify.setVisible(true);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#modify()
	 */
	@Override
	public void modify(Object object) {
		final Category category = (Category) object;
		modify = new JFrame();
		modify.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modify.setTitle("Modifier la categorie : " + category.getCategory_name());
		modify.setSize(new Dimension(300, 400));
		modify.setLocationRelativeTo(null);

		JPanel mp = new JPanel();
		modify.setContentPane(mp);
		mp.setLayout(new FlowLayout());

		text_name_category.setPreferredSize(new Dimension(250, 20));
		text_desc_category.setPreferredSize(new Dimension(250, 80));

		mp.add(title_name_category);
		mp.add(text_name_category);
		mp.add(title_desc_category);
		mp.add(new JScrollPane(text_desc_category));
		mp.add(is_active);
		mp.add(save);
		mp.add(delete);

		text_name_category.setText(category.getCategory_name());
		text_desc_category.setText(category.getCategory_description());
		is_active.setSelected(category.getActive() == 1 ? true : false);

		save.addMouseListener(new SaveListener(this, category));

		delete.addMouseListener(new DeleteListener(this, category));

		modify.setVisible(true);
	}

	public IDAO<Category> getDao() {
		return dao;
	}

	public String getCategoryName() {
		return text_name_category.getText();
	}

	public String getCategoryDescription() {
		return text_desc_category.getText();
	}

	public int getActive() {
		return is_active.isSelected() ? 1 : 0;
	}

}
