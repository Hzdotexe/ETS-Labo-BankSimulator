package Vues.Boutons;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import Controleurs.ControleurConfirmer;
import Vues.Fenetres.FenetreTransactions;

/**
 * <p>Classe qui représente le bouton "Confirmer"
 * qui hérite de la classe JButton.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class BoutonConfirmer extends JButton {

	// Champs de destination de la fênetre PanneauTransaction
	private JTextField txtFieldCompteDestination, txtFieldMontant;

	// Référence de la boite de dialogue DialogTransaction
	private JDialog dialogueTransaction;

	// Référence de la fênetre PanneauTransaction
	private FenetreTransactions fenetre;

	/**
	 * <p>Contructeur par défaut qui permet d'afficher et ajouter un
	 * bouton à la fenêtre PanneauTransaction.</p>
	 *
	 * @param fenetre Référence de la fenêtre PanneauTransaction
	 * @param compteDestination Compte de destination
	 * @param montant Montant à transférer
	 * @param dialogue Référence de la boite de dialogue dialogueTransaction
	 */
	public BoutonConfirmer(FenetreTransactions fenetre,
			JTextField compteDestination, JTextField montant,
			JDialog dialogue) {
		this.fenetre = fenetre;
		this.setText("Confirmer");

		//Enregistre les données de la transaction dans des variables privée
		txtFieldCompteDestination = compteDestination;
		txtFieldMontant = montant;
		dialogueTransaction = dialogue;

		//Ajout d'un écouteur permettant d'effectuer la transaction.
		this.addActionListener(
				new ControleurConfirmer(this.fenetre, txtFieldCompteDestination,
						txtFieldMontant, dialogueTransaction));
	}
}