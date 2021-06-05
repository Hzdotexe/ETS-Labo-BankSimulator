package Vues.Panneaux;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;

/**
 * Le panneau principal servant à afficher notre contenu.
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class PanneauTransactions extends JPanel {
	/* Couleur d'arrière-plan du panneau d'accueil. */
	public static final Color COULEUR_FOND = Color.decode("#0F141F");

	/*
	 * Zone texte où on peut afficher des informations dans le panneau de
	 * Transaction
	 */
	JTextArea zoneMessage;

	/**
	 * Constructeur par défaut du panneau. Il configure certaines propriétés par
	 * défaut du panneau.
	 */
	public PanneauTransactions() {
		this.setBackground(COULEUR_FOND);
		this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

		initComposants();
	}

	/**
	 * Instancie le JTextArea et l'ajoute au panneau.
	 */
	private void initComposants() {
		zoneMessage = new JTextArea();

		this.add(zoneMessage);
	}

	/**
	 * Methode servant à ajouter un message au panneau principal.
	 * 
	 * @param message Information qu'on veut faire afficher dans le Panneau de
	 *            	  transactions
	 */
	public void setMessage(String message) {
		this.zoneMessage.setText(message);
	}
}
