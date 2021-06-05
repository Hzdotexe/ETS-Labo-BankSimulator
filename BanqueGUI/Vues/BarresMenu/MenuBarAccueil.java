package Vues.BarresMenu;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import Vues.BarresMenu.Menus.MenuBanque;
import Vues.Fenetres.FenetreTransactions;

/**
 * <p>
 * Classe générale qui permet la création d'une barre de menu pour la fenêtre
 * d'accueil.
 * </p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class MenuBarAccueil extends JMenuBar {
	
	/* Fenêtre dans laquelle se trouve le JMenuBar. */
	FenetreTransactions fenetre;
	
	/**
	 * Constructeur par défaut de la classe MenuBarAccueil qui permet 
	 * d'initialiser les composants du JMenuBar.
	 * 
	 * @param fenetre Fenêtre dans laquelle se trouve le JMenuBar.
	 */
	public MenuBarAccueil(FenetreTransactions fenetre) {
		this.fenetre = fenetre;
		initComposants();
	}

	/**
	 * <p>
	 * Fonction qui permet l'ajout d'un menu à la barre de menu du programme.
	 * </p>
	 */
	private void initComposants() {
		this.add(new MenuBanque(this.fenetre, "Banque"));
	}
}