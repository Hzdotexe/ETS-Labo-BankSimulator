package Vues.Panneaux;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Vues.Boutons.BoutonDeco;
import Vues.Boutons.BoutonNouvTransaction;
import Vues.Fenetres.FenetreTransactions;

/**
 * <p>Panneau contant les boutons Déconnexion et Nouvelle Transaction, qui
 * permettant d'éxécuter des actions sur la banque
 * ou sur le PanneauTransactions.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class PanneauMilieuGauche extends JPanel {

	//Contient la référence vers PanneauTransactions
	private FenetreTransactions fenetre;
	private BoutonDeco deconnexion;
	private BoutonNouvTransaction nouvelleTransaction;

	/**
	 * <p>Constructeur par défaut permettant de configurer tous
	 * les paramètres du panneau et d'instancier tous les boutons.</p>
	 *
	 * @param fenetre Référence à la fenêtre principale PanneauTransactions
	 */
	public PanneauMilieuGauche(FenetreTransactions fenetre) {

		//Enregistrement de la référence vers PanneauTransactions.
		this.fenetre = fenetre;
		this.setBackground(FenetreTransactions.COULEUR_FOND);

		//Création d'un nouveau bouton de déconnexion.
		deconnexion = new BoutonDeco(fenetre);

		//Applique un layout de type SpringLayout au panneau
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);

		//Organise la disposition du bouton déconnexion dans le panneau.
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, deconnexion, 0,
				SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, deconnexion, 5,
				SpringLayout.NORTH, this);

		//Ajout du bouton déconnexion au pannneau.
		this.add(deconnexion);

		//Création d'un nouveau bouton nouvelleTransaction.
		nouvelleTransaction = new BoutonNouvTransaction(fenetre);

		//Organise la disposition du bouton nouvelleTransaction dans le panneau.
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER,
				nouvelleTransaction, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, nouvelleTransaction, 5,
				SpringLayout.SOUTH, deconnexion);

		//Ajout du bouton nouvelleTransaction au pannneau.
		this.add(nouvelleTransaction);
	}
}
