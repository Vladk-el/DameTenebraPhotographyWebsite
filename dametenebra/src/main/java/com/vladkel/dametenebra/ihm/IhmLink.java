package com.vladkel.dametenebra.ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.google.common.reflect.TypeToken;
import com.vladkel.dametenebra.model.Link;
import com.vladkel.dametenebra.model.Owner;
import com.vladkel.dametenebra.persistence.dao.DAO;
import com.vladkel.dametenebra.persistence.dao.IDAO;

/**
 * @author eliott
 *
 */
public class IhmLink implements IHM {

	private IDAO<Link> dao;
	private IDAO<Owner> ownerDao;
	private List<Owner> owners;

	/* ihm */
	private JFrame jf_link;
	private JPanel p_link;
	private JButton modif;
	private GridBagLayout bl;
	private JLabel title_name_link;
	private JTextField text_name_link;
	private JLabel title_desc_link;
	private JEditorPane text_desc_link;
	private JButton add_link;
	private JCheckBox is_active;
	private JLabel title_link_owner;
	private JComboBox<Owner> combo_link_owner;

	private JButton save;
	private JButton delete;

	@SuppressWarnings("serial")
	public IhmLink() {
		super();
		dao = new DAO<Link>(Link.class, new TypeToken<List<Link>>() {
		}.getType());
		ownerDao = new DAO<Owner>(Owner.class, new TypeToken<List<Owner>>() {
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
		p_link.removeAll();
		p_link.repaint();

		jf_link.setSize(800, 600);
		jf_link.setLocationRelativeTo(null);
		jf_link.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		p_link.setLayout(bl);
		jf_link.setContentPane(p_link);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#empty()
	 */
	@Override
	public void empty() {
		jf_link = null;
		p_link = null;
		bl = null;
		title_name_link = null;
		text_name_link = null;
		title_desc_link = null;
		text_desc_link = null;
		add_link = null;
		modif = null;
		title_link_owner = null;
		combo_link_owner = null;
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
		jf_link = new JFrame();
		p_link = new JPanel();
		bl = new GridBagLayout();
		title_name_link = new JLabel("Nom");
		text_name_link = new JTextField();
		title_desc_link = new JLabel("Description");
		text_desc_link = new JEditorPane();
		add_link = new JButton("Ajouter");
		modif = new JButton("Modifier");
		title_link_owner = new JLabel("Photographe correspondant");
		owners = ownerDao.select();
		combo_link_owner = new JComboBox<Owner>(owners.toArray(new Owner[owners.size()]));
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
		jf_link.setTitle("Consulter les links");

		final List<Link> links = dao.select();
		Object[][] data = new Object[links.size()][5];

		for (int i = 0; i < links.size(); i++) {
			data[i][0] = links.get(i).getLink_id();
			data[i][1] = links.get(i).getLink_name();
			data[i][2] = links.get(i).getLink_link();
			
			for(Owner owner : owners) {
				if(owner.getOwner_id() == links.get(i).getLink_owner_id()) {
					data[i][3] = owner.getOwner_name();
					break;
				}
			}
			
			data[i][4] = links.get(i).getActive() == 1 ? "oui" : "non";
		}

		String[] entete = { "Id", "Nom", "Lien", "Photographe", "Visible" };
		final JTable tab = new JTable(data, entete);

		modif.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (tab.getSelectedRow() != -1) {
					modify(links.get(tab.getSelectedRow()));
				}
			}
		});

		p_link.add(new JScrollPane(tab));
		p_link.add(modif);
		jf_link.setVisible(true);
		p_link.repaint();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#create()
	 */
	@Override
	public void create() {
		init();
		final JFrame modify = new JFrame();
		modify.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modify.setTitle("Ajouter un lien");
		modify.setSize(new Dimension(300, 400));
		modify.setLocationRelativeTo(null);

		JPanel mp = new JPanel();
		modify.setContentPane(mp);
		mp.setLayout(new FlowLayout());

		text_name_link.setPreferredSize(new Dimension(250, 20));
		text_desc_link.setPreferredSize(new Dimension(250, 80));
		combo_link_owner.setPreferredSize(new Dimension(250, 20));

		mp.add(title_name_link);
		mp.add(text_name_link);
		mp.add(title_desc_link);
		mp.add(new JScrollPane(text_desc_link));
		mp.add(title_link_owner);
		mp.add(combo_link_owner);
		mp.add(is_active);
		mp.add(add_link);

		add_link.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				Link link = new Link();
				link.setLink_id(0);
				link.setLink_name(text_name_link.getText());
				link.setLink_link(text_desc_link.getText());
				link.setLink_owner_id(((Owner)combo_link_owner.getSelectedItem()).getOwner_id());
				link.setActive(is_active.isSelected() ? 1 : 0);
				if (dao.insert(link)) {
					JOptionPane.showMessageDialog(null,
							"Le lien \"" + link.getLink_name() + "\" a bien été ajouté.");
					modify.dispose();
					jf_link.dispose();
					displayAll();
				} else {
					JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement.");
				}
			}

		});

		modify.setVisible(true);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#modify()
	 */
	@Override
	public void modify(Object object) {
		final Link link = (Link) object;
		final JFrame modify = new JFrame();
		modify.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modify.setTitle("Modifier le lien : " + link.getLink_name());
		modify.setSize(new Dimension(300, 400));
		modify.setLocationRelativeTo(null);

		JPanel mp = new JPanel();
		modify.setContentPane(mp);
		mp.setLayout(new FlowLayout());

		text_name_link.setPreferredSize(new Dimension(250, 20));
		text_desc_link.setPreferredSize(new Dimension(250, 80));
		combo_link_owner.setPreferredSize(new Dimension(250, 20));

		mp.add(title_name_link);
		mp.add(text_name_link);
		mp.add(title_desc_link);
		mp.add(new JScrollPane(text_desc_link));
		mp.add(title_link_owner);
		mp.add(combo_link_owner);
		mp.add(is_active);
		mp.add(save);
		mp.add(delete);

		text_name_link.setText(link.getLink_name());
		text_desc_link.setText(link.getLink_link());
		is_active.setSelected(link.getActive() == 1 ? true : false);
		
		for(int i = 0; i < owners.size(); i++){
			if(owners.get(i).getOwner_id() == link.getLink_owner_id()){
				combo_link_owner.setSelectedIndex(i);
				break;
			}
		}

		save.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				link.setLink_name(text_name_link.getText());
				link.setLink_link(text_desc_link.getText());
				link.setLink_owner_id(owners.get(combo_link_owner.getSelectedIndex()).getOwner_id());
				link.setActive(is_active.isSelected() ? 1 : 0);
				if (dao.update(link)) {
					javax.swing.JOptionPane.showMessageDialog(null, "Le lien a bien été modifié.");
					modify.dispose();
					jf_link.dispose();
					displayAll();
				} else {
					JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement.");
				}
			}
		});

		delete.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (dao.delete(link)) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Le lien n'est maintenant plus visible sur le site.");
					modify.dispose();
					jf_link.dispose();
					displayAll();
				} else {
					JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement.");
				}
			}
		});

		modify.setVisible(true);
	}

}
