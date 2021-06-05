package Vues.Boutons;

import javax.swing.JButton;

import Controleurs.ControleurTransaction;
import Vues.Fenetres.FenetreTransactions;

/**
 * <p>Classe qui représente le bouton "Nouvelle Transaction"
 * qui hérite de la classe JButton.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class BoutonNouvTransaction extends JButton {

	// Référence de la fênetre PanneauTransaction
	private FenetreTransactions fenetre;

	/**
	 * <p>Contructeur par défaut qui permet d'afficher et ajouter un
	 * bouton à la fenêtre PanneauTransaction.</p>
	 *
	 * @param fenetre Instance de la fenêtre PanneauTransaction
	 */
	public BoutonNouvTransaction(FenetreTransactions fenetre) {
		this.fenetre = fenetre;
		this.setText("Nouvelle Transaction");
		//Création d'un nouveau écouteur pour le contrôle du bouton.
		this.addActionListener(new ControleurTransaction(fenetre));
	}
}
