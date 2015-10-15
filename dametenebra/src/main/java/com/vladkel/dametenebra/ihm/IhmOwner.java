package com.vladkel.dametenebra.ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.google.common.reflect.TypeToken;
import com.vladkel.dametenebra.model.Owner;
import com.vladkel.dametenebra.persistence.dao.DAO;
import com.vladkel.dametenebra.persistence.dao.IDAO;

/**
 * @author eliott
 *
 */
public class IhmOwner implements IHM {

	IDAO<Owner> dao;

	/* ihm */
	private JFrame jf_owner;
	private JPanel p_owner;
	private JButton modif;
	private GridBagLayout bl;
	private JLabel title_name_owner;
	private JTextField text_name_owner;
	private JLabel title_desc_owner;
	private JEditorPane text_desc_owner;
	private JButton add_owner;
	private JCheckBox is_active;

	private JButton save;
	private JButton delete;

	@SuppressWarnings("serial")
	public IhmOwner() {
		super();
		dao = new DAO<Owner>(Owner.class, new TypeToken<List<Owner>>() {
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
		p_owner.removeAll();
		p_owner.repaint();

		jf_owner.setSize(800, 600);
		jf_owner.setLocationRelativeTo(null);
		jf_owner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		p_owner.setLayout(bl);
		jf_owner.setContentPane(p_owner);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.vladkel.dametenebra.ihm.IHM#empty()
	 */
	@Override
	public void empty() {
		jf_owner = null;
		p_owner = null;
		bl = null;
		title_name_owner = null;
		text_name_owner = null;
		title_desc_owner = null;
		text_desc_owner = null;
		add_owner = null;
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
		jf_owner = new JFrame();
		p_owner = new JPanel();
		bl = new GridBagLayout();
		title_name_owner = new JLabel("Nom");
		text_name_owner = new JTextField();
		title_desc_owner = new JLabel("Description");
		text_desc_owner = new JEditorPane();
		add_owner = new JButton("Ajouter");
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
		jf_owner.setTitle("Consulter les owners");

		final List<Owner> owners = dao.select();
		Object[][] data = new Object[owners.size()][4];

		for (int i = 0; i < owners.size(); i++) {
			data[i][0] = owners.get(i).getOwner_id();
			data[i][1] = owners.get(i).getOwner_name();
			data[i][2] = owners.get(i).getOwner_description();
			data[i][3] = owners.get(i).getActive() == 1 ? "oui" : "non";
		}

		String[] entete = { "Id", "Nom", "Description", "Visible" };
		final JTable tab = new JTable(data, entete);

		modif.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (tab.getSelectedRow() != -1) {
					modify(owners.get(tab.getSelectedRow()));
				}
			}
		});

		p_owner.add(new JScrollPane(tab));
		p_owner.add(modif);
		jf_owner.setVisible(true);
		p_owner.repaint();
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
		modify.setTitle("Ajouter un photographe");
		modify.setSize(new Dimension(300, 400));
		modify.setLocationRelativeTo(null);

		JPanel mp = new JPanel();
		modify.setContentPane(mp);
		mp.setLayout(new FlowLayout());

		text_name_owner.setPreferredSize(new Dimension(250, 20));
		text_desc_owner.setPreferredSize(new Dimension(250, 80));
		is_active.setPreferredSize(new Dimension(250, 20));

		mp.add(title_name_owner);
		mp.add(text_name_owner);
		mp.add(title_desc_owner);
		mp.add(new JScrollPane(text_desc_owner));
		mp.add(is_active);
		mp.add(add_owner);

		add_owner.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				Owner owner = new Owner();
				owner.setOwner_id(0);
				owner.setOwner_name(text_name_owner.getText());
				owner.setOwner_description(text_desc_owner.getText());
				owner.setActive(is_active.isSelected() ? 1 : 0);
				if(owner.getOwner_name().length() > 0) {
					if (dao.insert(owner)) {
						JOptionPane.showMessageDialog(null,
								"Le photographe \"" + owner.getOwner_name() + "\" a bien été ajouté.");
						modify.dispose();
						jf_owner.dispose();
						displayAll();
					} else {
						JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement.");
					}
				} else {
					modify.dispose();
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
		final Owner owner = (Owner) object;
		final JFrame modify = new JFrame();
		modify.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modify.setTitle("Modifier le photographe : " + owner.getOwner_name());
		modify.setSize(new Dimension(300, 400));
		modify.setLocationRelativeTo(null);

		JPanel mp = new JPanel();
		modify.setContentPane(mp);
		mp.setLayout(new FlowLayout());

		text_name_owner.setPreferredSize(new Dimension(250, 20));
		text_desc_owner.setPreferredSize(new Dimension(250, 80));

		mp.add(title_name_owner);
		mp.add(text_name_owner);
		mp.add(title_desc_owner);
		mp.add(new JScrollPane(text_desc_owner));
		mp.add(is_active);
		mp.add(save);
		mp.add(delete);

		text_name_owner.setText(owner.getOwner_name());
		text_desc_owner.setText(owner.getOwner_description());
		is_active.setSelected(owner.getActive() == 1 ? true : false);

		save.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				owner.setOwner_name(text_name_owner.getText());
				owner.setOwner_description(text_desc_owner.getText());
				owner.setActive(is_active.isSelected() ? 1 : 0);
				
				if(owner.getOwner_name().length() > 0) {
					if (dao.update(owner)) {
						javax.swing.JOptionPane.showMessageDialog(null, "Le photographe a bien été modifié.");
						modify.dispose();
						jf_owner.dispose();
						displayAll();
					} else {
						JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement.");
					}
				} else {
					modify.dispose();
				}
			}
		});

		delete.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (dao.delete(owner)) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Le photographe n'est maintenant plus visible sur le site.");
					modify.dispose();
					jf_owner.dispose();
					displayAll();
				} else {
					JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement.");
				}
			}
		});

		modify.setVisible(true);
	}
}
