package Vues.BarresMenu.Menus;

import javax.swing.JFrame;
import javax.swing.JMenu;

import Controleurs.ControleurConsultation;
import Controleurs.ControleurSauvegarde;
import Vues.BarresMenu.Menus.Items.Item;
import Vues.Fenetres.FenetreTransactions;

/**
 * <p>
 * Classe générale qui permet la création d'un menu pour un JMenuBar.
 * </p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class MenuBanque extends JMenu {

	/* Fenêtre dans laquelle se trouve le JMenu. */
	FenetreTransactions fenetre;
	
	/**
	 * <p>
	 * Constructeur de la classe MenuBanque qui reçoit en paramètre le nom du
	 * menu et initialise les composants du menu.
	 * </p>
	 *
	 * @param titre 
	 */
	/**
	 * <p>
	 * Constructeur de la classe MenuBanque qui reçoit en paramètre le nom du
	 * menu et initialise les composants du menu.
	 * </p>
	 * 
	 * @param fenetre Fenêtre dans laquelle se trouve le JMenu.
	 * @param titre Le nom du menu.
	 */
	public MenuBanque(FenetreTransactions fenetre, String titre) {
		this.fenetre = fenetre;
		this.setText(titre);
		initComposants();
	}

	/**
	 * <p>
	 * Fonction qui initialise le menu en ajoutant les items ses items en
	 * provenance de la classe Item.
	 * </p>
	 */
	private void initComposants() {
		this.add(new Item("Sauvegarder l'utilisateur actif (.csv)", 'S',
				new ControleurSauvegarde()));
		this.add(new Item("Consulter un fichier utilisateur", 'O',
				new ControleurConsultation(fenetre)));
	}
}
